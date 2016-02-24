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
 * ---------------------------
 * AbstractXmlReadHandler.java
 * ---------------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: AbstractXmlReadHandler.java,v 1.10 2004/03/05 13:46:26 mungady Exp $
 *
 * Changes (from 25-Nov-2003)
 * --------------------------
 * 25-Nov-2003 : Added Javadocs (DG);
 *
 */
package org.jfree.xml.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * A base class for implementing an {@link XmlReadHandler}.
 */
public abstract class AbstractXmlReadHandler implements XmlReadHandler {
    /** The root handler. */
    protected RootXmlReadHandler rootHandler;

    /** The tag name. */
    protected String tagName;

    /** A flag indicating the first call. */
    private boolean firstCall = true;

    /**
     * Creates a new handler.
     */
    public AbstractXmlReadHandler() {
        super();
    }

    public void init(RootXmlReadHandler rootHandler, String tagName) {
        if (rootHandler == null) {
            throw new NullPointerException("Root handler must not be null");
        }
        if (tagName == null) {
            throw new NullPointerException("Tag name must not be null");
        }
        this.rootHandler = rootHandler;
        this.tagName = tagName;
    }

    /**
     * This method is called at the start of an element.
     *
     * @param tagName  the tag name.
     * @param attrs  the attributes.
     *
     * @throws SAXException if there is a parsing error.
     * @throws XmlReaderException if there is a reader error.
     */
    public final void startElement(String tagName, Attributes attrs)
        throws XmlReaderException, SAXException {
        if (this.firstCall) {
            if (!this.tagName.equals(tagName)) {
                throw new SAXException("Expected <" + this.tagName + ">, found <" + tagName + ">");
            }
            this.firstCall = false;
            startParsing(attrs);
        }
        else {
            XmlReadHandler childHandler = getHandlerForChild(tagName, attrs);
            if (childHandler == null) {
                System.err.println("Unknown tag <" + tagName + ">");
                return;
            }
            childHandler.init(getRootHandler(), tagName);
            this.rootHandler.recurse(childHandler, tagName, attrs);
        }
    }

    /**
     * This method is called to process the character data between element tags.
     *
     * @param ch  the character buffer.
     * @param start  the start index.
     * @param length  the length.
     *
     * @throws SAXException if there is a parsing error.
     */
    public void characters(char[] ch, int start, int length) throws SAXException {
    }

    /**
     * This method is called at the end of an element.
     *
     * @param tagName  the tag name.
     *
     * @throws SAXException if there is a parsing error.
     */
    public final void endElement(String tagName) throws SAXException {
        if (this.tagName.equals(tagName)) {
            try {
                doneParsing();
                this.rootHandler.unwind(tagName);
            }
            catch (XmlReaderException xre) {
                throw new SAXException(xre);
            }
        }
    }

    /**
     * Starts parsing.
     *
     * @param attrs  the attributes.
     *
     * @throws SAXException if there is a parsing error.
     */
    protected void startParsing(Attributes attrs) throws SAXException {
    }

    /**
     * Done parsing.
     *
     * @throws SAXException if there is a parsing error.
     * @throws XmlReaderException if there is a reader error.
     */
    protected void doneParsing() throws SAXException, XmlReaderException {
    }

    /**
     * Returns the handler for a child element.
     *
     * @param tagName  the tag name.
     * @param atts  the attributes.
     *
     * @return the handler.
     *
     * @throws SAXException  if there is a parsing error.
     * @throws XmlReaderException if there is a reader error.
     */
    protected XmlReadHandler getHandlerForChild(String tagName, Attributes atts)
        throws XmlReaderException, SAXException {
        return null;
    }

    /**
     * Returns the tag name.
     *
     * @return the tag name.
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Returns the root handler for the parsing.
     *
     * @return the root handler.
     */
    public RootXmlReadHandler getRootHandler() {
        return rootHandler;
    }
}

