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
 * -------------
 * Rotation.java
 * -------------
 * (C)opyright 2003, 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: Rotation.java,v 1.7 2004/03/24 23:20:22 mungady Exp $
 *
 * Changes
 * -------
 * 19-Aug-2003 : Version 1 (DG);
 * 20-Feb-2004 : Updated Javadocs (DG);
 * 
 */

package org.jfree.util;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Represents a direction of rotation (<code>CLOCKWISE</code> or <code>ANTICLOCKWISE</code>).
 */
public final class Rotation implements Serializable {

    /** Clockwise. */
    public static final Rotation CLOCKWISE = new Rotation("Rotation.CLOCKWISE", -1.0);

    /** The reverse order renders the primary dataset first. */
    public static final Rotation ANTICLOCKWISE = new Rotation("Rotation.ANTICLOCKWISE", 1.0);

    /** The name. */
    private String name;
    
    /** The factor (-1.0 for <code>CLOCKWISE</code> and 1.0 for <code>ANTICLOCKWISE</code>). */
    private double factor;

    /**
     * Private constructor.
     *
     * @param name  the name.
     * @param factor  the rotation factor.
     */
    private Rotation(String name, double factor) {
        this.name = name;
        this.factor = factor;
    }

    /**
     * Returns a string representing the object.
     *
     * @return the string (never <code>null</code>).
     */
    public String toString() {
        return this.name;
    }

    /**
     * Returns the rotation factor, which is -1.0 for <code>CLOCKWISE</code> and 1.0 for 
     * <code>ANTICLOCKWISE</code>.
     * 
     * @return the rotation factor.
     */
    public double getFactor() {
        return this.factor;
    }

    /**
     * Compares this object for equality with an other object.
     * Implementation note: This simply compares the factor instead
     * of the name.
     *
     * @param o the other object
     * @return true or false
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rotation)) {
            return false;
        }

        final Rotation rotation = (Rotation) o;

        if (this.factor != rotation.factor) {
            return false;
        }

        return true;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return the hashcode
     */
    public int hashCode() {
        final long temp = Double.doubleToLongBits(this.factor);
        return (int) (temp ^ (temp >>> 32));
    }

    /**
     * Ensures that serialization returns the unique instances.
     * 
     * @return the object.
     * 
     * @throws ObjectStreamException if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(Rotation.CLOCKWISE)) {
            return Rotation.CLOCKWISE;
        }
        else if (this.equals(Rotation.ANTICLOCKWISE)) {
            return Rotation.ANTICLOCKWISE;
        }      
        return null;
    }

}
