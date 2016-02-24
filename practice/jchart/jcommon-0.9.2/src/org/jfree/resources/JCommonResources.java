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
 * ---------------------
 * JCommonResources.java
 * ---------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: JCommonResources.java,v 1.15 2004/03/18 16:58:57 mungady Exp $
 *
 * Changes
 * -------
 * 28-Feb-2002 : Version 1 (DG);
 * 19-Apr-2002 : Changed version number to 0.6.1-dev (DG);
 * 06-Jun-2002 : Changed version number to 0.6.2 (DG);
 * 13-Jun-2002 : Changed version number to 0.6.3 (DG);
 * 26-Jun-2002 : Changed version number to 0.6.4 (DG);
 * 27-Aug-2002 : Changed version number to 0.7.0 (DG);
 * 16-Oct-2002 : Changed version number to 0.7.1 (DG);
 * 29-Jan-2003 : Changed version number to 0.7.2 (DG);
 * 03-Apr-2003 : Changed version number to 0.7.3 (DG);
 * 24-Apr-2003 : Changed version number to 0.8.0 (DG);
 * 08-Aug-2003 : Updated version number (DG);
 * 02-Sep-2003 : Updated version number (DG);
 * 24-Sep-2003 : Updated version number to 0.8.8 (DG);
 * 11-Nov-2003 : Updated version number to 0.8.9 (DG);
 * 27-Nov-2003 : Updated version number to 0.9.0 (DG);
 * 23-Dec-2003 : Updated version number to 0.9.1 (DG);
 * 18-Mar-2004 : Updated version number to 0.9.2 (DG);
 *
 */

package org.jfree.resources;

import java.util.ListResourceBundle;

/**
 * Localised resources for the JCommon Class Library.
 *
 * @author David Gilbert
 */
public class JCommonResources extends ListResourceBundle {

    /**
     * Returns the array of strings in the resource bundle.
     * 
     * @return the array of strings in the resource bundle.
     */
    public Object[][] getContents() {
        return CONTENTS;
    }

    /** The resources to be localised. */
    private static final Object[][] CONTENTS = {

        {"project.name",      "JCommon"},
        {"project.version",   "0.9.2"},
        {"project.info",      "http://www.jfree.org/jcommon/index.html"},
        {"project.copyright", "(C)opyright 2000-2004, by Object Refinery Limited and"
                            + " Contributors"}

    };

}
