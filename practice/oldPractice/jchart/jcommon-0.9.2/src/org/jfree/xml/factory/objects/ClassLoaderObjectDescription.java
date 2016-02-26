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
 * ---------------------------------
 * ClassLoaderObjectDescription.java
 * ---------------------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: ClassLoaderObjectDescription.java,v 1.5 2004/02/11 08:25:08 mungady Exp $
 *
 * Changes (from 19-Feb-2003)
 * -------------------------
 * 19-Feb-2003 : Added standard header and Javadocs (DG);
 * 29-Apr-2003 : Destilled from the JFreeReport project and moved into JCommon
 *
 */

package org.jfree.xml.factory.objects;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * An object-description for a class loader.
 *
 * @author Thomas Morgner
 */
public class ClassLoaderObjectDescription extends AbstractObjectDescription {

    /**
     * Creates a new object description.
     */
    public ClassLoaderObjectDescription() {
        super(Object.class);
        setParameterDefinition("class", String.class);
    }

    /**
     * Creates an object based on this object description.
     *
     * @return The object.
     */
    public Object createObject() {
        try {
            String o = (String) getParameter("class");
            return getClass().getClassLoader().loadClass(o).newInstance();
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Sets the parameters of the object description to match the supplied object.
     *
     * @param o  the object.
     *
     * @throws ObjectFactoryException if there is a problem while reading the
     * properties of the given object.
     */
    public void setParameterFromObject(Object o) throws ObjectFactoryException {
        if (o == null) {
            throw new ObjectFactoryException("The Object is null.");
        }
        try {
            Constructor c = o.getClass().getConstructor(new Class[0]);
            if (!Modifier.isPublic(c.getModifiers())) {
                throw new ObjectFactoryException
                    ("The given object has no public default constructor.");
            }
            setParameter("class", o.getClass().getName());
        }
        catch (Exception e) {
            throw new ObjectFactoryException
                ("The given object has no default constructor.", e);
        }
    }
}
