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
 * ----------------------
 * BasicStrokeReadHandler
 * ----------------------
 * (C) Copyright 2003, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: BasicStrokeReadHandler.java,v 1.9 2004/03/05 11:07:13 mungady Exp $
 *
 * Changes (from 25-Nov-2003)
 * --------------------------
 * 25-Nov-2003 : Added standard header and Javadocs (DG);
 *
 */

package org.jfree.xml.parser.coretypes;

import java.awt.BasicStroke;
import java.util.StringTokenizer;

import org.jfree.xml.parser.AbstractXmlReadHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * A SAX handler for reading a {@link BasicStroke} object from XML.
 * <p>
 * To do: this should have a multiplexer in front like the PaintReadHandler
 */
public class BasicStrokeReadHandler extends AbstractXmlReadHandler  {
    
    /** The stroke under construction. */
    private BasicStroke stroke;

    /**
     * Creates a new handler.
     */
    public BasicStrokeReadHandler() {
        super();
    }

    /**
     * Called at the start of parsing a {@link BasicStroke} element, this method reads the 
     * attributes and constructs the stroke. 
     * 
     * @param attrs  the attributes.
     * 
     * @throws SAXException to indicate a parsing error.
     */
    protected void startParsing(Attributes attrs) throws SAXException {
        int endCap = Integer.parseInt(attrs.getValue("endCap"));
        int lineJoin = Integer.parseInt(attrs.getValue("lineJoin"));
        float lineWidth = Float.parseFloat(attrs.getValue("lineWidth"));
        float miterLimit = Float.parseFloat(attrs.getValue("miterLimit"));
        String dashArrayAttrib = attrs.getValue("dashArray");
        if (dashArrayAttrib != null) {
            float[] dashArray = parseDashArray(dashArrayAttrib);
            float dashPhase = Float.parseFloat(attrs.getValue("dashPhase"));
            this.stroke = new BasicStroke(
                lineWidth, endCap, lineJoin, miterLimit, dashArray, dashPhase
            );
        } 
        else {
            this.stroke = new BasicStroke(lineWidth, endCap, lineJoin, miterLimit);
        }
    }
    
    /**
     * Parses the dash array.
     * 
     * @param string  the dash array string representation.
     * 
     * @return a dash array.
     */
    private float[] parseDashArray(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string, ",");
        float[] retVal = new float[tokenizer.countTokens()];
        for (int i = 0; i < retVal.length; i++) {
            retVal[i] = Float.parseFloat(tokenizer.nextToken());
        }
        return retVal;
    }

    /**
     * Returns the stroke under construction.
     * 
     * @return the stroke.
     */
    public Object getObject() {
        return this.stroke;
    }
    
}
