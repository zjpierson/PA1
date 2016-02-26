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
 * TextBoxTests.java
 * -----------------
 * (C) Copyright 2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: TextBoxTests.java,v 1.2 2004/03/25 13:45:07 mungady Exp $
 *
 * Changes:
 * --------
 * 22-Mar-2004 : Version 1 (DG);
 *
 */

package org.jfree.text.junit;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.text.TextBlock;
import org.jfree.text.TextBox;
import org.jfree.text.TextLine;
import org.jfree.ui.Spacer;

/**
 * Tests for the {@link TextBox} class.
 */
public class TextBoxTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return the test suite.
     */
    public static Test suite() {
        return new TestSuite(TextBoxTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param  name the name of the tests.
     */
    public TextBoxTests(String name) {
        super(name);
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    public void testEquals() {
        
        TextBox b1 = new TextBox("Hello");
        TextBox b2 = new TextBox("Hello");
        assertTrue(b1.equals(b2));
        assertTrue(b2.equals(b1));
        
        // outlinePaint
        b1.setOutlinePaint(Color.blue);
        assertFalse(b1.equals(b2));
        b2.setOutlinePaint(Color.blue);
        assertTrue(b1.equals(b2));

        // outlineStroke
        b1.setOutlineStroke(new BasicStroke(1.1f));
        assertFalse(b1.equals(b2));
        b2.setOutlineStroke(new BasicStroke(1.1f));
        assertTrue(b1.equals(b2));

        // interiorGap
        b1.setInteriorGap(new Spacer(Spacer.ABSOLUTE, 10, 10, 10, 10));
        assertFalse(b1.equals(b2));
        b2.setInteriorGap(new Spacer(Spacer.ABSOLUTE, 10, 10, 10, 10));
        assertTrue(b1.equals(b2));
        
        // backgroundPaint
        b1.setBackgroundPaint(Color.blue);
        assertFalse(b1.equals(b2));
        b2.setBackgroundPaint(Color.blue);
        assertTrue(b1.equals(b2));

        // shadowPaint
        b1.setShadowPaint(Color.blue);
        assertFalse(b1.equals(b2));
        b2.setShadowPaint(Color.blue);
        assertTrue(b1.equals(b2));

        // shadowXOffset
        b1.setShadowXOffset(1.0);
        assertFalse(b1.equals(b2));
        b2.setShadowXOffset(1.0);
        assertTrue(b1.equals(b2));
        
        // shadowYOffset
        b1.setShadowYOffset(1.0);
        assertFalse(b1.equals(b2));
        b2.setShadowYOffset(1.0);
        assertTrue(b1.equals(b2));
        
        // textBlock
        TextBlock tb1 = new TextBlock();
        tb1.addLine(new TextLine("Testing"));
        b1.setTextBlock(tb1);
        assertFalse(b1.equals(b2));
        TextBlock tb2 = new TextBlock();
        tb2.addLine(new TextLine("Testing"));
        b2.setTextBlock(tb2);
        assertTrue(b1.equals(b2));
        
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        TextBox b1 = new TextBox("Hello");
        TextBox b2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(b1);
            out.close();

            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            b2 = (TextBox) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        assertEquals(b1, b2);

    }

}
