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
 * ---------------
 * ShapeUtils.java
 * ---------------
 * (C)opyright 2003, 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $$
 *
 * Changes
 * -------
 * 13-Aug-2003 : Version 1 (DG);
 * 16-Mar-2004 : Moved rotateShape() from RefineryUtilities.java to here (DG);
 * 
 */

package org.jfree.util;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.RectangularShape;
import java.util.Arrays;

/**
 * Utility methods for {@link Shape} objects.
 * 
 * @author David Gilbert.
 */
public class ShapeUtils {

    /**
     * Rotates a shape about the specified coordinates.
     * 
     * @param base  the shape (<code>null</code> permitted, returns <code>null</code>).
     * @param angle  the angle (in radians).
     * @param x  the x coordinate for the rotation point (in Java2D space).
     * @param y  the y coordinate for the rotation point (in Java2D space).
     * 
     * @return the rotated shape.
     */
    public static Shape rotateShape(Shape base, double angle, float x, float y) {
        if (base == null) {
            return null;
        }
        AffineTransform rotate = AffineTransform.getRotateInstance(angle, x, y);
        Shape result = rotate.createTransformedShape(base);
        return result;
    }

    /**
     * Returns a clone of the specified shape, or <code>null</code>.
     * 
     * @param shape  the shape to clone.
     * 
     * @return A clone.
     */
    public static Shape clone(Shape shape) {
    
        Shape result = null;
        
        if (shape instanceof Line2D) {
            Line2D line = (Line2D) shape;
            result = (Shape) line.clone();    
        }
        // RectangularShape includes:  Arc2D, Ellipse2D, Rectangle2D, RoundRectangle2D.
        else if (shape instanceof RectangularShape) {
            RectangularShape rectangle = (RectangularShape) shape;
            result = (Shape) rectangle.clone();
        }
        else if (shape instanceof Area) {
            Area area = (Area) shape;
            result = (Shape) area.clone();
        }
        else if (shape instanceof GeneralPath) {
            GeneralPath path = (GeneralPath) shape;
            result = (Shape) path.clone();
        }
        
        return result;
    }

    /**
     * Tests two polygons for equality.
     * 
     * @param p1  polygon 1.
     * @param p2  polygon 2.
     * 
     * @return A boolean.
     */    
    public boolean equal(Polygon p1, Polygon p2) {
        
        if (p1 == null) {
            return (p2 == null);
        }

        if (p2 == null) {
            return false;
        }

        if (p1.npoints != p2.npoints) {
            return false;
        }

        if (Arrays.equals(p1.xpoints, p2.xpoints) == false) {
            return false;
        }

        if (Arrays.equals(p1.ypoints, p2.ypoints) == false) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a translated shape.
     * 
     * @param shape  the shape (<code>null</code> not permitted).
     * @param transX  the x translation.
     * @param transY  the y translation.
     * 
     * @return the translated shape.
     */
    public static Shape translateShape(Shape shape, double transX, double transY) {
        if (shape == null) {
            throw new IllegalArgumentException("Null 'shape' argument.");
        }
        AffineTransform transform = AffineTransform.getTranslateInstance(transX, transY);
        return transform.createTransformedShape(shape);
    }


}
