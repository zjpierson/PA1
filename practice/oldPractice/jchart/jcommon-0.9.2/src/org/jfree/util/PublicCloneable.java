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
 * --------------------
 * PublicCloneable.java
 * --------------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: PublicCloneable.java,v 1.3 2004/01/01 23:59:29 mungady Exp $
 *
 * Changes
 * -------
 * 18-Aug-2003 : Version 1 (DG);
 * 
 */

package org.jfree.util;

/**
 * An interface that exposes the clone() method.
 * 
 * @author David Gilbert
 */
public interface PublicCloneable {
    
    /**
     * Returns a clone of the object.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if cloning is not supported for some reason.
     */
    public Object clone() throws CloneNotSupportedException;

}
