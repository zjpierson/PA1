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
 * RootXmlReadHandler.java
 * -----------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: RootXmlReadHandler.java,v 1.12 2004/03/05 13:46:26 mungady Exp $
 *
 * Changes (from 25-Nov-2003)
 * --------------------------
 * 25-Nov-2003 : Added Javadocs (DG);
 *
 */
package org.jfree.xml.parser;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.jfree.xml.parser.coretypes.GenericReadHandler;
import org.jfree.xml.parser.coretypes.ListReadHandler;
import org.jfree.xml.parser.coretypes.Rectangle2DReadHandler;
import org.jfree.xml.parser.coretypes.FontReadHandler;
import org.jfree.xml.parser.coretypes.ColorReadHandler;
import org.jfree.xml.parser.coretypes.GradientPaintReadHandler;
import org.jfree.xml.parser.coretypes.Point2DReadHandler;
import org.jfree.xml.parser.coretypes.BasicStrokeReadHandler;
import org.jfree.xml.parser.coretypes.InsetsReadHandler;
import org.jfree.xml.parser.coretypes.RenderingHintsReadHandler;
import org.jfree.xml.parser.coretypes.StringReadHandler;
import org.jfree.xml.util.ManualMappingDefinition;
import org.jfree.xml.util.MultiplexMappingDefinition;
import org.jfree.xml.util.MultiplexMappingEntry;
import org.jfree.xml.util.ObjectFactory;
import org.jfree.xml.util.SimpleObjectFactory;
import org.jfree.xml.ParseException;
import org.jfree.util.Log;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.Locator;
import org.xml.sax.helpers.DefaultHandler;


/**
 * A base root SAX handler.
 */
public abstract class RootXmlReadHandler extends DefaultHandler {

    /** The current handlers. */
    private Stack currentHandlers;

    /** ??. */
    private Stack outerScopes;

    /** The root handler. */
    private XmlReadHandler rootHandler;

    /** The object registry. */
    private HashMap objectRegistry;

    /** Maps classes to handlers. */
    private SimpleObjectFactory classToHandlerMapping;
    
    /** ??. */
    private Locator locator;

    /**
     * Creates a new root SAX handler.
     */
    public RootXmlReadHandler() {
        this.objectRegistry = new HashMap();
        this.classToHandlerMapping = new SimpleObjectFactory();

        MultiplexMappingEntry[] paintEntries = new MultiplexMappingEntry[2];
        paintEntries[0] = new MultiplexMappingEntry("color", Color.class.getName());
        paintEntries[1] = new MultiplexMappingEntry("gradientPaint", GradientPaint.class.getName());
        addMultiplexMapping(Paint.class, "type", paintEntries);
        addManualMapping(Color.class, ColorReadHandler.class);
        addManualMapping(GradientPaint.class, GradientPaintReadHandler.class);

        MultiplexMappingEntry[] point2DEntries = new MultiplexMappingEntry[2];
        point2DEntries[0] = new MultiplexMappingEntry("float", Point2D.Float.class.getName());
        point2DEntries[1] = new MultiplexMappingEntry("double", Point2D.Double.class.getName());
        addMultiplexMapping(Point2D.class, "type", point2DEntries);
        addManualMapping(Point2D.Float.class, Point2DReadHandler.class);
        addManualMapping(Point2D.Double.class, Point2DReadHandler.class);

        MultiplexMappingEntry[] rectangle2DEntries = new MultiplexMappingEntry[2];
        rectangle2DEntries[0] = new MultiplexMappingEntry(
            "float", Rectangle2D.Float.class.getName()
        );
        rectangle2DEntries[1] = new MultiplexMappingEntry(
            "double", Rectangle2D.Double.class.getName()
        );
        addMultiplexMapping(Rectangle2D.class, "type", rectangle2DEntries);
        addManualMapping(Rectangle2D.Float.class, Rectangle2DReadHandler.class);
        addManualMapping(Rectangle2D.Double.class, Rectangle2DReadHandler.class);

        // Handle list types
        MultiplexMappingEntry[] listEntries = new MultiplexMappingEntry[4];
        listEntries[0] = new MultiplexMappingEntry("array-list", ArrayList.class.getName());
        listEntries[1] = new MultiplexMappingEntry("linked-list", LinkedList.class.getName());
        listEntries[2] = new MultiplexMappingEntry("vector", Vector.class.getName());
        listEntries[3] = new MultiplexMappingEntry("stack", Stack.class.getName());
        addMultiplexMapping(List.class, "type", listEntries);
        addManualMapping(LinkedList.class, ListReadHandler.class);
        addManualMapping(Vector.class, ListReadHandler.class);
        addManualMapping(ArrayList.class, ListReadHandler.class);
        addManualMapping(Stack.class, ListReadHandler.class);

        MultiplexMappingEntry[] strokeEntries = new MultiplexMappingEntry[1];
        strokeEntries[0] = new MultiplexMappingEntry("basic", BasicStroke.class.getName());
        addMultiplexMapping(Stroke.class, "type", strokeEntries);
        addManualMapping(BasicStroke.class, BasicStrokeReadHandler.class);

        addManualMapping(Font.class, FontReadHandler.class);
        addManualMapping(Insets.class, InsetsReadHandler.class);
        addManualMapping(RenderingHints.class, RenderingHintsReadHandler.class);
        addManualMapping(String.class, StringReadHandler.class);
    }

    /** 
     * Returns the object factory. 
     * 
     * @return The object factory.
     */
    public abstract ObjectFactory getFactoryLoader();

    /**
     * Adds a mapping between a class and the handler for the class.
     *
     * @param classToRead  the class.
     * @param handler  the handler class.
     */
    protected void addManualMapping(Class classToRead, Class handler) {
        if (handler == null) {
            throw new NullPointerException("handler must not be null.");
        }
        if (classToRead == null) {
            throw new NullPointerException("classToRead must not be null.");
        }
        if (!XmlReadHandler.class.isAssignableFrom(handler)) {
            throw new IllegalArgumentException("The given handler is no XmlReadHandler.");
        }
        this.classToHandlerMapping.addManualMapping
            (new ManualMappingDefinition(classToRead, handler.getName(), null));
    }

    /**
     * Adds a multiplex mapping.
     * 
     * @param baseClass  the base class.
     * @param typeAttr  the type attribute.
     * @param mdef  the mapping entry.
     */
    protected void addMultiplexMapping(Class baseClass, 
                                       String typeAttr, 
                                       MultiplexMappingEntry[] mdef) {
        
        this.classToHandlerMapping.addMultiplexMapping(
            new MultiplexMappingDefinition(baseClass, typeAttr, mdef)
        );
    }

    /**
     * Adds an object to the registry.
     * 
     * @param key  the key.
     * @param value  the object.
     */
    public void putObject(String key, Object value) {
        if (value == null) {
            this.objectRegistry.remove(key);
        }
        else {
            this.objectRegistry.put(key, value);
        }
    }

    /**
     * Returns an object from the registry.
     * 
     * @param key  the key.
     * 
     * @return The object.
     */
    public Object getObject(String key) {
        return this.objectRegistry.get(key);
    }

    /**
     * Creates a SAX handler for the specified class.
     *
     * @param classToRead  the class.
     * @param tagName  the tag name.
     * @param atts  the attributes.
     *
     * @return a SAX handler.
     *
     * @throws XmlReaderException if there is a problem with the reader.
     */
    public XmlReadHandler createHandler(Class classToRead, String tagName, Attributes atts)
        throws XmlReaderException {

        XmlReadHandler retval = findHandlerForClass(classToRead, atts, new ArrayList());
        if (retval == null) {
            throw new NullPointerException("Unable to find handler for class: " + classToRead);
        }
        retval.init(this, tagName);
        return retval;
    }

    /**
     * Finds a handler for the specified class.
     * 
     * @param classToRead  the class to be read.
     * @param atts  the attributes.
     * @param history  the history.
     * 
     * @return A handler for the specified class.
     * 
     * @throws XmlReaderException if there is a problem with the reader.
     */
    private XmlReadHandler findHandlerForClass(Class classToRead, Attributes atts, 
                                               ArrayList history)
        throws XmlReaderException {
        ObjectFactory genericFactory = getFactoryLoader();

        if (history.contains(classToRead)) {
            throw new IllegalStateException("Circular reference detected: " + history);
        }
        history.add(classToRead);
        // check the manual mappings ...
        ManualMappingDefinition manualDefinition =
            this.classToHandlerMapping.getManualMappingDefinition(classToRead);
        if (manualDefinition == null) {
            manualDefinition = genericFactory.getManualMappingDefinition(classToRead);
        }
        if (manualDefinition != null) {
            Log.debug ("Locating handler for " + manualDefinition.getBaseClass());
            return loadHandlerClass(manualDefinition.getReadHandler());
        }

        // check whether a multiplexer is defined ...
        // find multiplexer for this class...
        MultiplexMappingDefinition mplex =
            getFactoryLoader().getMultiplexDefinition(classToRead);
        if (mplex == null) {
            mplex = this.classToHandlerMapping.getMultiplexDefinition(classToRead);
        }
        if (mplex != null) {
            String attributeValue = atts.getValue(mplex.getAttributeName());
            if (attributeValue == null) {
                throw new XmlReaderException(
                    "Multiplexer type attribute is not defined: " + mplex.getAttributeName() 
                    + " for " + classToRead
                );
            }
            MultiplexMappingEntry entry =
                mplex.getEntryForType(attributeValue);
            if (entry == null) {
                throw new XmlReaderException(
                    "Invalid type attribute value: " + mplex.getAttributeName() + " = " 
                    + attributeValue
                );
            }
            Class c = loadClass(entry.getTargetClass());
            if (!c.equals(mplex.getBaseClass())) {
                Log.debug(
                    "Continue search on next level : " + c + " after " + mplex.getBaseClass()
                );
                return findHandlerForClass(c, atts, history);
            }
            else {
                Log.debug ("Muliplexer is also generic definition ...");
            }
        }

        // check for generic classes ...
        // and finally try the generic handler matches ...
        if (this.classToHandlerMapping.isGenericHandler(classToRead)) {
            return new GenericReadHandler
                (this.classToHandlerMapping.getFactoryForClass(classToRead));
        }
        if (getFactoryLoader().isGenericHandler(classToRead)) {
            return new GenericReadHandler
                (getFactoryLoader().getFactoryForClass(classToRead));
        }
        return null;
    }

    /**
     * Sets the root SAX handler.
     *
     * @param handler  the SAX handler.
     */
    protected void setRootHandler(XmlReadHandler handler) {
        this.rootHandler = handler;
    }

    /**
     * Returns the root SAX handler.
     *
     * @return the root SAX handler.
     */
    protected XmlReadHandler getRootHandler() {
        return this.rootHandler;
    }

    /**
     * ??
     * 
     * @param handler  the handler.
     * @param tagName  the tag name.
     * @param attrs  the attributes.
     * 
     * @throws XmlReaderException if there is a problem with the reader.
     * @throws SAXException if there is a problem with the parser.
     */
    public void recurse(XmlReadHandler handler, String tagName, Attributes attrs)
        throws XmlReaderException, SAXException {
        
        this.outerScopes.push(this.currentHandlers);
        this.currentHandlers = new Stack();
        this.currentHandlers.push(handler);
        handler.startElement(tagName, attrs);
    
    }

    /**
     * Delegate to another handler.
     * 
     * @param handler  the new handler.
     * @param tagName  the tag name.
     * @param attrs  the attributes.
     * 
     * @throws XmlReaderException if there is a problem with the reader.
     * @throws SAXException if there is a problem with the parser.
     */
    public void delegate(XmlReadHandler handler, String tagName, Attributes attrs)
        throws XmlReaderException, SAXException {
        this.currentHandlers.push(handler);
        handler.startElement(tagName, attrs);
    }

    /**
     * Hand control back to the previous handler.
     * 
     * @param tagName  the tagname.
     * 
     * @throws SAXException if there is a problem with the parser.
     * @throws XmlReaderException if there is a problem with the reader.
     */
    public void unwind(String tagName) throws SAXException, XmlReaderException {
        this.currentHandlers.pop();
        if (this.currentHandlers.isEmpty() && !this.outerScopes.isEmpty()) {
            this.currentHandlers = (Stack) this.outerScopes.pop();
        }
        if (!this.currentHandlers.isEmpty()) {
            getCurrentHandler().endElement(tagName);
        }
    }

    /**
     * Returns the current handler.
     * 
     * @return The current handler.
     */
    protected XmlReadHandler getCurrentHandler() {
        return (XmlReadHandler) this.currentHandlers.peek();
    }

    /**
     * Starts processing a document.
     * 
     * @throws SAXException not in this implementation.
     */
    public void startDocument() throws SAXException {
        this.outerScopes = new Stack();
        this.currentHandlers = new Stack();
        this.currentHandlers.push(this.rootHandler);
    }

    /**
     * Starts processing an element.
     * 
     * @param uri  the URI.
     * @param localName  the local name.
     * @param qName  the qName.
     * @param attributes  the attributes.
     * 
     * @throws SAXException if there is a parsing problem.
     */
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
        throws SAXException {
        try {
            getCurrentHandler().startElement(qName, attributes);
        }
        catch (XmlReaderException xre) {
            throw new ParseException(xre, getLocator());
        }
    }

    /**
     * Process character data.
     * 
     * @param ch  the character buffer.
     * @param start  the start index.
     * @param length  the length of the character data.
     * 
     * @throws SAXException if there is a parsing error.
     */
    public void characters(char[] ch, int start, int length) throws SAXException {
        try {
            getCurrentHandler().characters(ch, start, length);
        }
        catch (SAXException se) {
            throw se;
        }
        catch (Exception e) {
            throw new ParseException(e, getLocator());
        }
    }

    /**
     * Finish processing an element.
     * 
     * @param uri  the URI.
     * @param localName  the local name.
     * @param qName  the qName.
     * 
     * @throws SAXException if there is a parsing error.
     */
    public void endElement(String uri, String localName, String qName)
        throws SAXException {
        try {
            getCurrentHandler().endElement(qName);
        }
        catch (XmlReaderException xre) {
            throw new ParseException(xre, getLocator());
        }
    }

    /**
     * Loads the given class, and ignores all exceptions which may occur
     * during the loading. If the class was invalid, null is returned instead.
     *
     * @param className the name of the class to be loaded.
     * @return the class or null.
     * @throws XmlReaderException if there is a reader error.
     */
    protected XmlReadHandler loadHandlerClass(String className)
        throws XmlReaderException {
        try {
            Class c = loadClass(className);
            return (XmlReadHandler) c.newInstance();
        }
        catch (Exception e) {
            // ignore buggy classes for now ..
            throw new XmlReaderException("LoadHanderClass: Unable to instantiate " + className, e);
        }
    }

    /**
     * Loads the given class, and ignores all exceptions which may occur
     * during the loading. If the class was invalid, null is returned instead.
     *
     * @param className the name of the class to be loaded.
     * @return the class or null.
     * @throws XmlReaderException if there is a reader error.
     */
    protected Class loadClass(String className)
        throws XmlReaderException {
        if (className == null) {
            throw new XmlReaderException("LoadHanderClass: Class name not defined");
        }
        try {
            Class c = this.getClass().getClassLoader().loadClass(className);
            return c;
        }
        catch (Exception e) {
            // ignore buggy classes for now ..
            throw new XmlReaderException("LoadHanderClass: Unable to load " + className, e);
        }
    }

    /**
     * Receive an object for locating the origin of SAX document events.
     *
     * The locator allows the application to determine the end position of
     * any document-related event, even if the parser is not reporting an
     * error. Typically, the application will use this information for
     * reporting its own errors (such as character content that does not
     * match an application's business rules). The information returned by
     * the locator is probably not sufficient for use with a search engine.
     *
     * @param locator  the locator.
     */
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    /**
     * Returns the current locator.
     *
     * @return the locator.
     */
    public Locator getLocator() {
        return this.locator;
    }


}
