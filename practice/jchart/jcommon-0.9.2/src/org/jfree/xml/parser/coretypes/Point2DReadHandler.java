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
 * ------------------
 * Point2DReadHandler
 * ------------------
 * (C) Copyright 2003, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: Point2DReadHandler.java,v 1.6 2004/03/05 13:46:07 mungady Exp $
 *
 * Changes (from 25-Nov-2003)
 * --------------------------
 * 25-Nov-2003 : Added standard header and Javadocs (DG);
 *
 */

package org.jfree.xml.parser.coretypes;


import java.awt.geom.Point2D;

import org.jfree.xml.parser.AbstractXmlReadHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * A SAX handler for reading {@link Point2D} objects from an XML element.
 */
public class Point2DReadHandler extends AbstractXmlReadHandler  {

    /** The point under construction. */
    private Point2D point;

    /**
     * Creates a new handler.
     */
    public Point2DReadHandler() {
        super();
    }

    /** 
     * At the start of parsing, the attributes are read and used to construct the point.
     * 
     * @param attrs  the attributes.
     * 
     * @throws SAXException if there is a parsing error.
     */
    protected void startParsing(Attributes attrs) throws SAXException {
        String x = attrs.getValue("x");
        String y = attrs.getValue("y");
        this.point = new Point2D.Double(Double.parseDouble(x),
                                        Double.parseDouble(y));
    }

    /**
     * Returns the point under construction.
     * 
     * @return the point.
     */
    public Point2D getPoint2D() {
        return this.point;
    }

    /**
     * Returns the point under construction.
     * 
     * @return the point.
     */
    public Object getObject() {
        return this.point;
    }
    
}
