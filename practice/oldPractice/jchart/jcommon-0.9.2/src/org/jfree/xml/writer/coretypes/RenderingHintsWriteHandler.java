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
 * --------------------------
 * RenderingHintsHandler.java
 * --------------------------
 * (C)opyright 2003, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: RenderingHintsWriteHandler.java,v 1.7 2004/03/05 10:41:52 mungady Exp $
 *
 * Changes 
 * -------------------------
 * 22.11.2003 : Initial version
 *  
 */

package org.jfree.xml.writer.coretypes;

import java.io.IOException;
import java.awt.RenderingHints;
import java.util.Iterator;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.jfree.xml.writer.AbstractXmlWriteHandler;
import org.jfree.xml.writer.XMLWriterException;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.AttributeList;
import org.jfree.util.Log;

/**
 * A handler for writing a {@link RenderingHints} object.
 */
public class RenderingHintsWriteHandler extends AbstractXmlWriteHandler {

    /**
     * Default constructor.
     */
    public RenderingHintsWriteHandler() {
        super();
    }

    /**
     * Performs the writing of a single object.
     *
     * @param tagName  the tag name.
     * @param object  the object.
     * @param writer  the writer.
     * @param mPlexAttribute  ??.
     * @param mPlexValue  ??.
     * 
     * @throws IOException if there is an I/O error.
     * @throws XMLWriterException if there is a writer problem.
     */
    public void write(String tagName, Object object, XMLWriter writer,
                      String mPlexAttribute, String mPlexValue)
        throws IOException, XMLWriterException {

        writer.writeTag(tagName, mPlexAttribute, mPlexValue, XMLWriter.OPEN);
        writer.allowLineBreak();
        RenderingHints hints = (RenderingHints) object;
        Iterator it = hints.keySet().iterator();
        while (it.hasNext()) {
            RenderingHints.Key key = (RenderingHints.Key) it.next();
            String keyname = hintFieldToString(key);
            String value = hintFieldToString(hints.get(key));
            AttributeList attribs = new AttributeList();
            attribs.setAttribute("key", keyname);
            attribs.setAttribute("value", value);
            writer.writeTag("entry", attribs, XMLWriter.CLOSE);
            writer.allowLineBreak();
        }
        writer.writeCloseTag(tagName);
        writer.allowLineBreak();
    }

    private String hintFieldToString(Object o) {
        Field[] fields = RenderingHints.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            if (Modifier.isFinal(f.getModifiers()) 
                && Modifier.isPublic(f.getModifiers()) 
                && Modifier.isStatic(f.getModifiers())) {
                try {
                    Object value = f.get(null);
                    if (o.equals(value)) {
                        return f.getName();
                    }
                }
                catch (Exception e) {
                    Log.info ("Unable to write RenderingHint", e);
                }
            }
        }
        throw new IllegalArgumentException("Invalid value given");
    }

}
