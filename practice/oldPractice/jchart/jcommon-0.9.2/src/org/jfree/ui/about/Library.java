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
 * ------------
 * Library.java
 * ------------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: Library.java,v 1.3 2004/01/01 23:59:29 mungady Exp $
 *
 * Changes
 * -------
 * 21-Feb-2002 : Version 1 (DG);
 * 25-Mar-2002 : Added a new constructor (DG);
 *
 */

package org.jfree.ui.about;

/**
 * A simple class representing a library in a software project.
 * <P>
 * Used in the AboutFrame class.
 *
 * @author David Gilbert
 */
public class Library {

    /** The name. */
    private String name;

    /** The version. */
    private String version;

    /** The licence. */
    private String licence;

    /** The version. */
    private String info;

    /**
     * Creates a new library reference.
     *
     * @param name  the name.
     * @param version  the version.
     * @param licence  the licence.
     * @param info  the web address or other info.
     */
    public Library(String name, String version, String licence, String info) {

        this.name = name;
        this.version = version;
        this.licence = licence;
        this.info = info;

    }

    /**
     * Constructs a library reference from a ProjectInfo object.
     *
     * @param project  information about a project.
     */
    public Library(ProjectInfo project) {

        this.name = project.getName();
        this.version = project.getVersion();
        this.licence = project.getLicenceName();
        this.info = project.getInfo();

    }

    /**
     * Returns the library name.
     *
     * @return the library name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the library version.
     *
     * @return the library version.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Returns the licence text.
     *
     * @return the licence text.
     */
    public String getLicence() {
        return this.licence;
    }

    /**
     * Returns the project info for the library.
     *
     * @return the project info.
     */
    public String getInfo() {
        return this.info;
    }

}
