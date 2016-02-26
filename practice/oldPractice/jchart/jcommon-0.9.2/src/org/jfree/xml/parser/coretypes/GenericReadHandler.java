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
 * GenericReadHandler.java
 * -----------------------
 * (C)opyright 2003, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: GenericReadHandler.java,v 1.10 2004/03/24 23:20:23 mungady Exp $
 *
 * Changes
 * -------
 * 23-Sep-2003 : Initial version
 *
 */

package org.jfree.xml.parser.coretypes;

import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.xml.parser.AbstractXmlReadHandler;
import org.jfree.xml.parser.RootXmlReadHandler;
import org.jfree.xml.parser.XmlReadHandler;
import org.jfree.xml.parser.XmlReaderException;
import org.jfree.xml.util.AttributeDefinition;
import org.jfree.xml.util.ConstructorDefinition;
import org.jfree.xml.util.GenericObjectFactory;
import org.jfree.xml.util.LookupDefinition;
import org.jfree.xml.util.ObjectDescriptionException;
import org.jfree.xml.util.PropertyDefinition;
import org.jfree.util.Log;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * A SAX handler for reading a generic object from an XML element.
 */
public class GenericReadHandler extends AbstractXmlReadHandler {

    /** The object under construction. */
    private Object object;
    
    /** The generic object factory. */
    private GenericObjectFactory objectFactory;
    
    /** The object reference handlers. */
    private ArrayList objectRefHandlers;
    
    /** The created handler. */
    private HashMap createdHandler;

    /**
     * Creates a new handler.
     *
     * @param factory  the generic object factory.
     */
    public GenericReadHandler(GenericObjectFactory factory) {
        this.createdHandler = new HashMap();
        this.objectRefHandlers = new ArrayList();
        this.objectFactory = factory;
    }

    /**
     * Called at the start of parsing.
     * 
     * @param attrs  the attributes.
     * 
     * @throws SAXException if there is a parsing error.
     */
    protected void startParsing(Attributes attrs) throws SAXException {

        try {
            AttributeDefinition[] attribs = this.objectFactory.getAttributeDefinitions();
            for (int i = 0; i < attribs.length; i++) {
                AttributeDefinition attrDef = attribs[i];
                String value = attrs.getValue(attrDef.getAttributeName());
                if (value == null) {
                    // System.out.println("No value for " + attrDef.getAttributeName());
                    continue;
                }
                Object o = attrDef.getHandler().toPropertyValue(value);
                this.objectFactory.setProperty(attrDef.getPropertyName(), o);
            }
        }
        catch (ObjectDescriptionException ode) {
            throw new SAXException(ode);
        }
    }

    protected XmlReadHandler getHandlerForChild(String tagName, Attributes atts)
        throws SAXException {
        try {
            if (tagName.equals("objectRef")) {
                // store them all and copy the properties later when the object is created
                XmlReadHandler handler = new ObjectRefHandler();
                this.objectRefHandlers.add(handler);
                return handler;
            }
            XmlReadHandler handler = this.rootHandler.createHandler
                (this.objectFactory.getTypeForTagName(tagName), tagName, atts);
            if (handler != null) {
                this.createdHandler.put(tagName, handler);
            }
            // will throw exception if handler is null...
            return handler;
        }
        catch (ObjectDescriptionException ode) {
            Log.debug ("Failed to get handler for child: ", ode);
            throw new SAXException(ode);
        }
    }

    public Object getObject() throws XmlReaderException {

        if (this.object != null) {
            return this.object;
        }
        RootXmlReadHandler rootHandler = getRootHandler();
        try {
            for (int i = 0; i < this.objectRefHandlers.size(); i++) {
                ObjectRefHandler handler = (ObjectRefHandler) this.objectRefHandlers.get(i);
                this.objectFactory.setProperty(handler.getPropertyName(), handler.getObject());
            }

            ArrayList lookups = new ArrayList();
            LookupDefinition[] lookupDefs = this.objectFactory.getLookupDefinitions();
            for (int i = 0; i < lookupDefs.length; i++) {
                LookupDefinition ldef = lookupDefs[i];
                lookups.add(ldef.getPropertyName());
                Log.debug ("lookup object: " + ldef.getPropertyName());

                Object value = rootHandler.getObject(ldef.getRegistryKey());
                if (value == null) {
                    // todo may or may not be fatal -> define it in the xml?
                    Log.warn ("Failed to lookup object: " + value);
                }
                else {
                    this.objectFactory.setProperty(ldef.getPropertyName(), value);
                }
            }

            ConstructorDefinition[] conDefs = this.objectFactory.getConstructorDefinitions();
            for (int i = 0; i < conDefs.length; i++) {
                ConstructorDefinition cDef = conDefs[i];
                // if this is a lookup, then ignore
                if (lookups.contains(cDef.getPropertyName())) {
                    continue;
                }
                if (this.objectFactory.isPropertyDefinition(cDef.getPropertyName())) {
                    PropertyDefinition pd = this.objectFactory.getPropertyDefinitionByPropertyName(
                        cDef.getPropertyName()
                    );
                    XmlReadHandler handler = (XmlReadHandler) this.createdHandler.get(
                        pd.getElementName()
                    );
                    if (handler != null) {
                        this.objectFactory.setProperty(pd.getPropertyName(), handler.getObject());
                    }
                }
                // hoping that the attribute is set ..
            }

            this.object = this.objectFactory.createObject();
            Object oldValue = null;
            if (this.objectFactory.getRegisterName() != null) {
                oldValue = rootHandler.getObject(this.objectFactory.getRegisterName());
                rootHandler.putObject(this.objectFactory.getRegisterName(), this.object);
            }

            PropertyDefinition[] propertyDefs = this.objectFactory.getPropertyDefinitions();
            for (int i = 0; i < propertyDefs.length; i++) {
                PropertyDefinition pdef = propertyDefs[i];
                XmlReadHandler handler = (XmlReadHandler) this.createdHandler.get(
                    pdef.getElementName()
                );
                if (handler == null) {
                    continue;
                }
                this.objectFactory.setProperty(pdef.getPropertyName(), handler.getObject());
            }

            this.objectFactory.writeObjectProperties(this.object);

            if (this.objectFactory.getRegisterName() != null) {
                rootHandler.putObject(this.objectFactory.getRegisterName(), oldValue);
            }
        }
        catch (ObjectDescriptionException ode) {
            Log.error ("Unable to create object.", ode);
            throw new XmlReaderException("Unable to create object.", ode);
        }
        return this.object;
    }

}
