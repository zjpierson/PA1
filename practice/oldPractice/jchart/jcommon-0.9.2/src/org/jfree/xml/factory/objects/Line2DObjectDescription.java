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
 * ----------------------------
 * Line2DObjectDescription.java
 * ----------------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: Line2DObjectDescription.java,v 1.5 2004/02/11 08:25:08 mungady Exp $
 *
 * Changes (from 19-Feb-2003)
 * -------------------------
 * 19-Feb-2003 : Added standard header and Javadocs (DG);
 * 29-Apr-2003 : Destilled from the JFreeReport project and moved into JCommon
 *
 */

package org.jfree.xml.factory.objects;

import java.awt.geom.Line2D;

/**
 * An object-description for a <code>Line2D</code> object.
 *
 * @author Thomas Morgner
 */
public class Line2DObjectDescription extends AbstractObjectDescription {

    /**
     * Creates a new object description.
     */
    public Line2DObjectDescription() {
        super(Line2D.class);
        setParameterDefinition("x1", Float.class);
        setParameterDefinition("x2", Float.class);
        setParameterDefinition("y1", Float.class);
        setParameterDefinition("y2", Float.class);
    }

    /**
     * Creates an object based on this description.
     *
     * @return The object.
     */
    public Object createObject() {
        Line2D line = new Line2D.Float();

        float x1 = getFloatParameter("x1");
        float x2 = getFloatParameter("x2");
        float y1 = getFloatParameter("y1");
        float y2 = getFloatParameter("y2");
        line.setLine(x1, y1, x2, y2);
        return line;
    }

    /**
     * Returns a parameter value as a float.
     *
     * @param param  the parameter name.
     *
     * @return The float value.
     */
    private float getFloatParameter(String param) {
        Float p = (Float) getParameter(param);
        if (p == null) {
            return 0;
        }
        return p.floatValue();
    }

    /**
     * Sets the parameters of this description object to match the supplied object.
     *
     * @param o  the object (should be an instance of <code>Line2D</code>).
     *
     * @throws ObjectFactoryException if the object is not an instance of <code>Line2D</code>.
     */
    public void setParameterFromObject(Object o) throws ObjectFactoryException {
        if (!(o instanceof Line2D)) {
            throw new ObjectFactoryException("The given object is no java.awt.geom.Line2D.");
        }
        Line2D line = (Line2D) o;
        float x1 = (float) line.getX1();
        float x2 = (float) line.getX2();
        float y1 = (float) line.getY1();
        float y2 = (float) line.getY2();

        setParameter("x1", new Float(x1));
        setParameter("x2", new Float(x2));
        setParameter("y1", new Float(y1));
        setParameter("y2", new Float(y2));
    }
}
