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
 * ListWriteHandler.java
 * ---------------------
 * (C)opyright 2003, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: ListWriteHandler.java,v 1.5 2004/02/11 10:38:59 mungady Exp $
 *
 * Changes
 * -------
 * 22-Nov-2003 : Initial version
 *
 */

package org.jfree.xml.writer.coretypes;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jfree.xml.writer.AbstractXmlWriteHandler;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.XMLWriterException;

/**
 * A handler for writing a {@link List} object.
 */
public class ListWriteHandler extends AbstractXmlWriteHandler {

    /**
     * Performs the writing of a {@link List} object.
     *
     * @param tagName  the tag name.
     * @param object  the {@link List} object.
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

        writer.writeTag(tagName, mPlexAttribute, mPlexValue, XMLWriter.OPEN);

        List list = (List) object;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object value = it.next();
            if (value == null) {
                writer.writeTag("null", XMLWriter.CLOSE);
            }
            else {
                getRootHandler().write("object", value, Object.class, writer);
            }
        }

        writer.writeCloseTag(tagName);
    }
    
}
