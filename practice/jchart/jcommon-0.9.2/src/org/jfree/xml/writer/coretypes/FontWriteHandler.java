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
 * ---------------------
 * FontWriteHandler.java
 * ---------------------
 * (C) Copyright 2003, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: FontWriteHandler.java,v 1.10 2004/03/05 10:39:28 mungady Exp $
 *
 * Changes (from 25-Nov-2003)
 * --------------------------
 * 25-Nov-2003 : Added standard header (DG);
 *
 */

package org.jfree.xml.writer.coretypes;

import java.awt.Font;
import java.io.IOException;

import org.jfree.xml.writer.AbstractXmlWriteHandler;
import org.jfree.xml.writer.AttributeList;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.XMLWriterException;

/**
 * A handler for writing {@link Font} objects.
 */
public class FontWriteHandler extends AbstractXmlWriteHandler  {
    
    /**
     * Default constructor.
     */
    public FontWriteHandler() {
        super();
    }

    /**
     * Performs the writing of a {@link Font} object.
     *
     * @param tagName  the tag name.
     * @param object  the {@link Font} object.
     * @param writer  the writer.
     * @param mPlexAttribute  ??.
     * @param mPlexValue  ??.
     * 
     * @throws IOException if there is an I/O error.
     * @throws XMLWriterException if there is a writer error.
     */
    public void write(String tagName, Object object, XMLWriter writer,
                      String mPlexAttribute, String mPlexValue)
        throws IOException, XMLWriterException {
        Font font = (Font) object;
        AttributeList attribs = new AttributeList();
        if (mPlexAttribute != null) {
            attribs.setAttribute(mPlexAttribute, mPlexValue);
        }
        attribs.setAttribute("family", font.getFamily());
        attribs.setAttribute("size", String.valueOf(font.getSize()));
        attribs.setAttribute("style", String.valueOf(getFontStyle(font)));
        writer.writeTag(tagName, attribs, true);
    }

    private String getFontStyle(Font font) {
        if (font.isBold() && font.isItalic()) {
            return "bold-italic";
        }
        if (font.isBold()) {
            return "bold";
        }
        if (font.isItalic()) {
            return "italic";
        }
        return "plain";
    }
    
}