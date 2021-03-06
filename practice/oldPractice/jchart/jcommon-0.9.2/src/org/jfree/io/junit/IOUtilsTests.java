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
 * -----------------
 * IOUtilsTests.java
 * -----------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: IOUtilsTests.java,v 1.4 2004/03/05 06:54:14 mungady Exp $
 *
 * Changes 
 * -------
 * 05-Jan-2004 : Initial version
 *  
 */

package org.jfree.io.junit;

import java.net.URL;
import java.io.IOException;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.jfree.io.IOUtils;

/**
 * Creates a new test case.
 */
public class IOUtilsTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return the test suite.
     */
    public static Test suite() {
        return new TestSuite(IOUtilsTests.class);
    }

    /**
     * Constructs a new set of tests.
     */
    public IOUtilsTests() {
        super();
    }

    /**
     * Constructs a new set of tests.
     *
     * @param  name the name of the tests.
     */
    public IOUtilsTests(String name) {
        super(name);
    }

    /**
     * A test for the createRelativeURL() method.
     * 
     * @throws IOException if there is an I/O problem.
     */
    public void testCreateRelativeURL() throws IOException {
        final URL baseurl = new URL
            ("http://test.com:80/test/a/funny/directory/basefile.xml");

        URL testInput1 = new URL ("http://test.com:80/test/a/funny/directory/datafile.jpg");
        String result = IOUtils.getInstance().createRelativeURL(testInput1, baseurl);
        assertEquals("datafile.jpg", result);
        assertEquals(testInput1, new URL (baseurl, result));

        URL testInput2 = new URL ("http://test.com:80/test/adatafile.jpg");
        result = IOUtils.getInstance().createRelativeURL(testInput2, baseurl);
        assertEquals("../../../adatafile.jpg", result);
        assertEquals(testInput2, new URL (baseurl, result));

        URL testInput3 = new URL ("http://test.com:80/test/adatafile.jpg?query=test");
        result = IOUtils.getInstance().createRelativeURL(testInput3, baseurl);
        assertEquals("../../../adatafile.jpg?query=test", result);
        assertEquals(testInput3, new URL (baseurl, result));
    }
    
}
