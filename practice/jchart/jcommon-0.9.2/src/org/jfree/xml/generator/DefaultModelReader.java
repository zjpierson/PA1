/* ========================================================================
 * JCommon : a free general purpose class library for the Java(tm) platform
 * ========================================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 * 
 * Project Info:  http://www.jfree.org/jcommon/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 * 
 * -----------------------
 * DefaultModelReader.java
 * -----------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: DefaultModelReader.java,v 1.11 2004/03/24 23:20:23 mungady Exp $
 *
 * Changes
 * -------
 * 12-Nov-2003 : Initial version (TM);
 * 26-Nov-2003 : Updated header and Javadocs (DG);
 *
 */

package org.jfree.xml.generator;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.jfree.io.IOUtils;
import org.jfree.xml.generator.model.ClassDescription;
import org.jfree.xml.generator.model.Comments;
import org.jfree.xml.generator.model.DescriptionModel;
import org.jfree.xml.generator.model.IgnoredPropertyInfo;
import org.jfree.xml.generator.model.ManualMappingInfo;
import org.jfree.xml.generator.model.MultiplexMappingInfo;
import org.jfree.xml.generator.model.PropertyInfo;
import org.jfree.xml.generator.model.PropertyType;
import org.jfree.xml.generator.model.TypeInfo;
import org.jfree.xml.util.AbstractModelReader;
import org.jfree.xml.util.ObjectDescriptionException;

/**
 * A reader for the class model.
 */
public class DefaultModelReader extends AbstractModelReader {

    /** A model containing classes and the corresponding class descriptions. */
    private DescriptionModel model;

    /** The class description under construction. */
    private ClassDescription currentClassDescription;

    /** Information about the class being processed. */
    private BeanInfo currentBeanInfo;

    /** The base URL. */
    private URL baseURL;
    
    /** The source. */
    private String source;
    
    /** The multiplex mapping info. */
    private MultiplexMappingInfo multiplexInfo;
    
    /** The multiplex type info.*/
    private ArrayList multiplexTypeInfos;

    /** Storage for the properties of the current class. */
    private ArrayList propertyList;

    /** Storage for the constructors of the current class. */
    private ArrayList constructorList;

    /**
     * Creates a new model reader.
     */
    public DefaultModelReader() {
        super();
    }

    /**
     * Loads a description model.
     * 
     * @param file  the file name.
     * 
     * @return A description model.
     * 
     * @throws IOException  if there is an I/O problem.
     * @throws ObjectDescriptionException  if there is a problem reading the object descriptions.
     */
    public synchronized DescriptionModel load(String file) throws IOException, 
                                                                  ObjectDescriptionException {
        
        this.model = new DescriptionModel();
        this.baseURL = new File (file).toURL();
        parseXml(this.baseURL);
        fillSuperClasses();
        return this.model;
        
    }

    /**
     * Iterates through all the class descriptions in the model, setting the superclass
     * attribute in all cases where the superclass definitions are contained in the model.
     */
    protected void fillSuperClasses() {
        for (int i = 0; i < this.model.size(); i++) {
            ClassDescription cd = this.model.get(i);
            Class parent = cd.getObjectClass().getSuperclass();
            if (parent == null) {
                continue;
            }
            ClassDescription superCD = this.model.get(parent);
            if (superCD != null) {
                cd.setSuperClass(superCD.getObjectClass());
            }
        }
    }

    /**
     * Begin processing an object definition element.
     *
     * @param className  the class name.
     * @param register  the register name (<code>null</code> permitted).
     * @param ignore  ??
     *
     * @return <code>true</code> if the class is available, and <code>false</code> otherwise.
     */
    protected boolean startObjectDefinition(String className, String register, boolean ignore) {
        Class c = loadClass(className);
        if (c == null) {
            return false;
        }
        this.currentClassDescription = new ClassDescription(c);
        this.currentClassDescription.setPreserve(ignore);
        this.currentClassDescription.setRegisterKey(register);
        try {
            this.currentBeanInfo = Introspector.getBeanInfo(c, Object.class);
        }
        catch (IntrospectionException ie) {
            return false;
        }
        this.propertyList = new java.util.ArrayList();
        this.constructorList = new java.util.ArrayList();
        return true;
    }

    /**
     * Finishes processing an object definition (sets the constructor and property info for the
     * class description, and adds the class description to the model).
     * 
     * @throws ObjectDescriptionException if there is a problem with the object description.
     */
    protected void endObjectDefinition() throws ObjectDescriptionException {
        PropertyInfo[] pis = (PropertyInfo[])
            this.propertyList.toArray(new PropertyInfo[this.propertyList.size()]);
        this.currentClassDescription.setProperties(pis);

        TypeInfo[] tis = (TypeInfo[])
        this.constructorList.toArray(new TypeInfo[this.constructorList.size()]);

        this.currentClassDescription.setConstructorDescription(tis);
        this.currentClassDescription.setComments
            (new Comments(getOpenComment(), getCloseComment()));
        this.currentClassDescription.setSource(this.source);

        this.model.addClassDescription(this.currentClassDescription);

        this.propertyList = null;
        this.currentBeanInfo = null;
        this.currentClassDescription = null;
    }

    /**
     * Handles the description of an attribute within an object definition.
     *
     * @param name  the name.
     * @param attribName  the attribute name.
     * @param handlerClass  the fully qualified class name for the attribute handler.
     * 
     * @throws ObjectDescriptionException if there is a problem with the object description.
     */
    protected void handleAttributeDefinition(String name, String attribName, String handlerClass)
        throws ObjectDescriptionException {

        PropertyInfo propertyInfo = ModelBuilder.getInstance().createSimplePropertyInfo
            (getPropertyDescriptor(name));

        if (propertyInfo == null) {
            throw new ObjectDescriptionException("Unable to load property " + name);
        }

        propertyInfo.setComments(new Comments(getOpenComment(), getCloseComment()));
        propertyInfo.setPropertyType(PropertyType.ATTRIBUTE);
        propertyInfo.setXmlName(attribName);
        propertyInfo.setXmlHandler(handlerClass);
        this.propertyList.add(propertyInfo);
    }

    /**
     * Handles the constructor definition.
     * 
     * @param tagName  the tag name.
     * @param parameterClass  the parameter class.
     * 
     * @throws ObjectDescriptionException if there is a problem with the object description.
     */
    protected void handleConstructorDefinition(String tagName, String parameterClass)
        throws ObjectDescriptionException {

        Class c = loadClass(parameterClass);
        if (c == null) {
            throw new ObjectDescriptionException("Failed to load class " + parameterClass);
        }
        TypeInfo ti = new TypeInfo(tagName, c);
        ti.setComments(new Comments(getOpenComment(), getCloseComment()));
        this.constructorList.add (ti);
    }

    /**
     * Handles the description of an element within an object definition.
     *
     * @param name  the property name.
     * @param element  the element name.
     * 
     * @throws ObjectDescriptionException if there is a problem with the object description.
     */
    protected void handleElementDefinition(String name, String element) 
        throws ObjectDescriptionException {

        PropertyInfo propertyInfo = ModelBuilder.getInstance().createSimplePropertyInfo
            (getPropertyDescriptor(name));

        if (propertyInfo == null) {
            throw new ObjectDescriptionException("Unable to load property " + name);
        }

        propertyInfo.setComments(new Comments(getOpenComment(), getCloseComment()));
        propertyInfo.setPropertyType(PropertyType.ELEMENT);
        propertyInfo.setXmlName(element);
        propertyInfo.setXmlHandler(null);
        this.propertyList.add(propertyInfo);

    }

    /**
     * Handles a lookup definition.
     * 
     * @param name  the name.
     * @param lookupKey  the lookup key.
     * 
     * @throws ObjectDescriptionException if there is a problem with the object description.
     */
    protected void handleLookupDefinition(String name, String lookupKey) 
        throws ObjectDescriptionException {
        PropertyInfo propertyInfo = ModelBuilder.getInstance().createSimplePropertyInfo
            (getPropertyDescriptor(name));

        if (propertyInfo == null) {
            throw new ObjectDescriptionException("Unable to load property " + name);
        }

        propertyInfo.setComments(new Comments(getOpenComment(), getCloseComment()));
        propertyInfo.setPropertyType(PropertyType.LOOKUP);
        propertyInfo.setXmlName(lookupKey);
        propertyInfo.setXmlHandler(null);
        this.propertyList.add(propertyInfo);
    }

    /**
     * Returns a property descriptor for the named property, or <code>null</code> if there is
     * no descriptor with the given name.
     *
     * @param propertyName  the property name.
     *
     * @return a property descriptor.
     */
    protected PropertyDescriptor getPropertyDescriptor(String propertyName) {
        PropertyDescriptor[] pds = this.currentBeanInfo.getPropertyDescriptors();
        for (int i = 0; i < pds.length; i++) {
            if (pds[i].getName().equals(propertyName)) {
                return pds[i];
            }
        }
        return null;
    }

    /**
     * Handles an ignored property.
     * 
     * @param name  the name.
     */
    protected void handleIgnoredProperty(String name) {
        IgnoredPropertyInfo propertyInfo = new IgnoredPropertyInfo(name);
        propertyInfo.setComments(new Comments(getOpenComment(), getCloseComment()));
        this.propertyList.add(propertyInfo);
    }

    /**
     * Handles a manual mapping.
     *
     * @param className  the class name.
     * @param readHandler  the read handler.
     * @param writeHandler  the write handler.
     * 
     * @return A boolean.
     * 
     * @throws ObjectDescriptionException if there is a problem with the object description.
     */
    protected boolean handleManualMapping(String className, String readHandler, String writeHandler)
        throws ObjectDescriptionException {

        ManualMappingInfo manualMappingInfo =
            new ManualMappingInfo(loadClass(className),
                loadClass(readHandler), loadClass(writeHandler));
        manualMappingInfo.setComments(new Comments(getOpenComment(), getCloseComment()));
        manualMappingInfo.setSource(this.source);
        this.model.getMappingModel().addManualMapping(manualMappingInfo);
        return true;
    }

    /**
     * Start a multiplex mapping.
     * 
     * @param className  the class name.
     * @param typeAttr  the type.
     */
    protected void startMultiplexMapping(String className, String typeAttr) {
        this.multiplexInfo = new MultiplexMappingInfo(loadClass(className), typeAttr);
        this.multiplexInfo.setSource(this.source);
        this.multiplexTypeInfos = new ArrayList();
    }

    /**
     * Handles a multiplex mapping.
     * 
     * @param typeName  the type name.
     * @param className  the class name.
     * 
     * @throws ObjectDescriptionException if there is a problem with the object description.
     */
    protected void handleMultiplexMapping(String typeName, String className)
        throws ObjectDescriptionException {
        TypeInfo info = new TypeInfo(typeName, loadClass(className));
        info.setComments(new Comments(getOpenComment(), getCloseComment()));
        this.multiplexTypeInfos.add (info);
    }

    /**
     * Ends a multiplex mapping.
     * 
     * @throws ObjectDescriptionException if there is a problem with the object description. 
     */
    protected void endMultiplexMapping() throws ObjectDescriptionException {
        TypeInfo[] typeInfos = (TypeInfo[]) this.multiplexTypeInfos.toArray(
            new TypeInfo[this.multiplexTypeInfos.size()]
        );
        this.multiplexInfo.setComments(new Comments(getOpenComment(), getCloseComment()));
        this.multiplexInfo.setChildClasses(typeInfos);
        this.model.getMappingModel().addMultiplexMapping(this.multiplexInfo);
        this.multiplexInfo = null;
    }

    /**
     * Starts include handling.
     * 
     * @param resource  the URL.
     */
    protected void startIncludeHandling(URL resource) {
        this.source = IOUtils.getInstance().createRelativeURL(resource, this.baseURL);
        this.model.addSource(this.source);
        this.model.addIncludeComment(
            this.source, new Comments(getOpenComment(), getCloseComment())
        );
    }

    /**
     * Ends include handling.
     */
    protected void endIncludeHandling() {
        this.source = "";
    }

    /**
     * Starts the root document.
     */
    protected void startRootDocument() {
        this.source = "";
    }

    /**
     * Ends the root document.
     */
    protected void endRootDocument() {
        this.model.setModelComments(new Comments(getOpenComment(), getCloseComment()));
    }
}
