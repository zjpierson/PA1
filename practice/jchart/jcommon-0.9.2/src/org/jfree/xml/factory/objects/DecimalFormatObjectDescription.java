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
 * -----------------------------------
 * DecimalFormatObjectDescription.java
 * -----------------------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: DecimalFormatObjectDescription.java,v 1.4 2004/01/01 23:59:30 mungady Exp $
 *
 * Changes (from 19-Feb-2003)
 * -------------------------
 * 19-Feb-2003 : Added standard header and Javadocs (DG);
 * 29-Apr-2003 : Destilled from the JFreeReport project and moved into JCommon
 *
 */

package org.jfree.xml.factory.objects;

import java.text.DecimalFormat;

/**
 * An object-description for a <code>DecimalFormat</code> object.
 *
 * @author Thomas Morgner
 */
public class DecimalFormatObjectDescription extends BeanObjectDescription {

    /**
     * Creates a new object description.
     */
    public DecimalFormatObjectDescription() {
        this(DecimalFormat.class);
    }

    /**
     * Creates a new object description.
     *
     * @param className  the class.
     */
    public DecimalFormatObjectDescription(Class className) {
        this(className, true);
    }

    /**
     * Creates a new object description.
     *
     * @param className  the class.
     * @param init  initialise?
     */
    public DecimalFormatObjectDescription(Class className, boolean init) {
        super(className, init);
        setParameterDefinition("localizedPattern", String.class);
        setParameterDefinition("pattern", String.class);
    }

    /**
     * Sets the parameters of this description object to match the supplied object.
     *
     * @param o  the object (should be an instance of <code>DecimalFormat</code>).
     *
     * @throws ObjectFactoryException if there is a problem while reading the
     * properties of the given object.
     */
    public void setParameterFromObject(Object o)
        throws ObjectFactoryException {
        super.setParameterFromObject(o);
        DecimalFormat format = (DecimalFormat) o;
        setParameter("localizedPattern", format.toLocalizedPattern());
        setParameter("pattern", format.toPattern());
    }

    /**
     * Creates an object (<code>DecimalFormat</code>) based on this description.
     *
     * @return The object.
     */
    public Object createObject() {
        DecimalFormat format = (DecimalFormat) super.createObject();
        if (getParameter("pattern") != null) {
            format.applyPattern((String) getParameter("pattern"));
        }
        if (getParameter("localizedPattern") != null) {
            format.applyLocalizedPattern((String) getParameter("localizedPattern"));
        }
        return format;
    }
}
