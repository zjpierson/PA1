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
 * Configuration.java
 * ------------------
 * (C)opyright 2002-2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner (taquera@sherito.org);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: Configuration.java,v 1.8 2004/01/01 23:59:29 mungady Exp $
 *
 * Changes
 * -------
 * 02-May-2003 : Initial version (TM);
 * 
 */

package org.jfree.util;

import java.io.Serializable;

/**
 * A simple query interface for a configuration.
 */
public interface Configuration extends Serializable {

    /**
     * Returns the configuration property with the specified key.
     *
     * @param key  the property key.
     *
     * @return the property value.
     */
    public String getConfigProperty(String key);

    /**
     * Returns the configuration property with the specified key (or the specified default value
     * if there is no such property).
     * <p>
     * If the property is not defined in this configuration, the code will lookup the property in
     * the parent configuration.
     *
     * @param key  the property key.
     * @param defaultValue  the default value.
     *
     * @return the property value.
     */
    public String getConfigProperty(String key, String defaultValue);

}
