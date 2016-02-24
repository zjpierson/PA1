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
 * ShapeTableTests.java
 * --------------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: ShapeTableTests.java,v 1.5 2004/03/05 13:47:03 mungady Exp $
 *
 * Changes
 * -------
 * 25-Mar-2003 : Version 1 (DG);
 *
 */

package org.jfree.util.junit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Tests for the {@link org.jfree.util.ShapeTable} class.
 *
 * @author David Gilbert
 */
public class ShapeTableTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return the test suite.
     */
    public static Test suite() {
        return new TestSuite(ShapeTableTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param  name the name of the tests.
     */
    public ShapeTableTests(String name) {
        super(name);
    }

    /**
     * Does nothing but to surpress a failure message from JUnit when
     * being confronted with an empty suite.
     */
    public void testDummy() {
        // do nothing
    }
    
// THE FOLLOWING TEST HAS BEEN COMMENTED OUT UNTIL I CAN FIND A WAY TO MAKE IT WORK.  ALTHOUGH
// THE SERIALISATION APPEARS TO BE WORKING OK, THE TEST FAILS ON THE EQUALITY CHECK.  THIS IS 
// BECAUSE THE equals(...) METHOD FOR SOME SHAPES (E.G. Line2D) IS THE DEFAULT METHOD INHERITED
// FROM Object --> THIS ONLY TESTS THAT THE OBJECTS ARE ONE AND THE SAME, RATHER THAN TWO OBJECTS
// THAT ARE IDENTICAL TO EACH OTHER.

//    /**
//     * Serialize an instance, restore it, and check for equality.
//     */
//    public void testSerialization() {
//
//        ShapeTable t1 = new ShapeTable();
//        t1.setShape(0, 0, new Line2D.Double(50, 50, 100, 200));
//        t1.setShape(0, 1, new Rectangle2D.Double(50, 50, 100, 200));
//        t1.setShape(1, 0, new Ellipse2D.Double(50, 50, 100, 200));
//        t1.setShape(1, 1, new Line2D.Double(50, 50, 100, 200));
//
//        ShapeTable t2 = null;
//
//        try {
//            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//            ObjectOutput out = new ObjectOutputStream(buffer);
//            out.writeObject(t1);
//            out.close();
//
//            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(
//                                                   buffer.toByteArray()));
//            t2 = (ShapeTable) in.readObject();
//            in.close();
//        }
//        catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        boolean equal = t1.equals(t2);
//        assertEquals(t1, t2);
//
//    }

}
