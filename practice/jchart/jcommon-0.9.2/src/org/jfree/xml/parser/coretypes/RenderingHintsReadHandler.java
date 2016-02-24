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
 * ------------------------------
 * RenderingHintsReadHandler.java
 * ------------------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: RenderingHintsReadHandler.java,v 1.5 2004/03/05 13:46:07 mungady Exp $
 *
 * Changes
 * -------
 * 03-Dec-2003 : Initial version
 * 11-Feb-2004 : Added missing Javadocs (DG);
 * 
 */

package org.jfree.xml.parser.coretypes;

import java.util.ArrayList;
import java.awt.RenderingHints;

import org.jfree.xml.parser.AbstractXmlReadHandler;
import org.jfree.xml.parser.XmlReaderException;
import org.jfree.xml.parser.XmlReadHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

/**
 * A read handler that can parse the XML element for a {@link RenderingHints} collection.
 */
public class RenderingHintsReadHandler extends AbstractXmlReadHandler {

    /** The subhandlers. */
    private ArrayList handlers;
    
    /** The rendering hints under construction. */
    private RenderingHints renderingHints;

    /**
     * Creates a new read handler.
     */
    public RenderingHintsReadHandler() {
        super();
    }

    /**
     * Starts parsing.
     *
     * @param attrs  the attributes.
     *
     * @throws SAXException never.
     */
    protected void startParsing(Attributes attrs) throws SAXException {
        this.handlers = new ArrayList();
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

        if (!tagName.equals("entry")) {
            throw new SAXException("Expected 'entry' tag.");
        }

        XmlReadHandler handler = new RenderingHintValueReadHandler();
        this.handlers.add(handler);
        return handler;
    }

    /**
     * Done parsing.
     *
     * @throws SAXException if there is a parsing error.
     * @throws XmlReaderException if there is a reader error.
     */
    protected void doneParsing() throws SAXException, XmlReaderException {
        this.renderingHints = new RenderingHints(null);

        for (int i = 0; i < this.handlers.size(); i++) {
            RenderingHintValueReadHandler rh =
                (RenderingHintValueReadHandler) this.handlers.get(i);
            this.renderingHints.put(rh.getKey(), rh.getValue());
        }
    }

    /**
     * Returns the object for this element.
     *
     * @return the object.
     *
     * @throws XmlReaderException if there is a parsing error.
     */
    public Object getObject() throws XmlReaderException {
        return this.renderingHints;
    }
}