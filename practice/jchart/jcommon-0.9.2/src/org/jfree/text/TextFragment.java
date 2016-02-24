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
 * TextFragment.java
 * -----------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: TextFragment.java,v 1.13 2004/03/26 13:41:10 mungady Exp $
 *
 * Changes
 * -------
 * 07-Nov-2003 : Version 1 (DG);
 * 25-Nov-2003 : Fixed bug in the dimension calculation (DG);
 * 22-Dec-2003 : Added workaround for Java bug 4245442 (DG);
 * 29-Jan-2004 : Added paint attribute (DG);
 * 22-Mar-2004 : Added equals() method and implemented Serializable (DG);
 *
 */
 
package org.jfree.text;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.font.LineMetrics;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A text item, with an associated font, that fits on a single line (see {@link TextLine}).  
 * Instances of the class are immutable.
 * 
 * @author David Gilbert
 */
public class TextFragment implements Serializable {

    /** The default font. */
    public static final Font DEFAULT_FONT = new Font("Serif", Font.PLAIN, 12);
    
    /** The default text color. */
    public static final Paint DEFAULT_PAINT = Color.black;
    
    /** The text. */
    private String text;
    
    /** The font. */
    private Font font;
    
    /** The text color. */
    private Paint paint;

    /** Log4j logging. */
    static Logger logger = Logger.getLogger(TextFragment.class);
    
    /**
     * Creates a new text fragment.
     * 
     * @param text  the text (<code>null</code> not permitted).
     */
    public TextFragment(String text) {
        this(text, DEFAULT_FONT, DEFAULT_PAINT);
    }
    
    /**
     * Creates a new text fragment.
     * 
     * @param text  the text (<code>null</code> not permitted).
     * @param font  the font (<code>null</code> not permitted).
     */
    public TextFragment(String text, Font font) {
        this(text, font, DEFAULT_PAINT);
    }

    /**
     * Creates a new text fragment.
     * 
     * @param text  the text (<code>null</code> not permitted).
     * @param font  the font (<code>null</code> not permitted).
     * @param paint  the text color (<code>null</code> not permitted).
     */
    public TextFragment(String text, Font font, Paint paint) {
        if (text == null) {
            throw new IllegalArgumentException("Null 'text' argument.");  
        }
        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.text = text;
        this.font = font;
        this.paint = paint;
    }

    /**
     * Returns the text.
     * 
     * @return The text (possibly <code>null</code>).
     */
    public String getText() {
        return this.text;
    }
    
    /**
     * Returns the font.
     * 
     * @return The font (never <code>null</code>).
     */
    public Font getFont() {
        return this.font;
    }
    
    /**
     * Returns the text paint.
     * 
     * @return The text paint (never <code>null</code>).
     */
    public Paint getPaint() {
        return this.paint;
    }
    
    /**
     * Draws the text fragment.
     * 
     * @param g2  the graphics device.
     * @param anchorX  the x-coordinate of the anchor point.
     * @param anchorY  the y-coordinate of the anchor point.
     * @param anchor  the location of the text that is aligned to the anchor point.
     * @param rotateX  the x-coordinate of the rotation point.
     * @param rotateY  the y-coordinate of the rotation point.
     * @param angle  the angle.
     */
    public void draw(Graphics2D g2, 
                     float anchorX, float anchorY, TextAnchor anchor,
                     float rotateX, float rotateY, double angle) {
    
        g2.setFont(this.font);
        g2.setPaint(this.paint);
        RefineryUtilities.drawRotatedString(
            this.text, g2, anchorX, anchorY, anchor, rotateX, rotateY, angle
        );
    
    }
    
    /**
     * Calculates the dimensions of the text fragment.
     * 
     * @param g2  the graphics device.
     * 
     * @return The width and height of the text.
     */
    public Dimension2D calculateDimensions(Graphics2D g2) {
        Dimension2D result = new Dimension();
        FontMetrics fm = g2.getFontMetrics(this.font);
        
        // WORKAROUND: the getStringBounds() method is not giving reliable results on all
        // platforms...
        // Rectangle2D bounds = fm.getStringBounds(this.text, g2);
        Rectangle2D bounds = new Rectangle2D.Double(
            0.0, 0.0, fm.stringWidth(this.text), fm.getHeight()
        );
        // END WORKAROUND
        if (logger.isDebugEnabled()) {
            logger.debug(
                "Bounds height = " + bounds.getHeight() 
                + " (font size = " + this.font.getSize() + ")"
            );  
        }
        result.setSize((int) Math.ceil(bounds.getWidth()), (int) Math.ceil(bounds.getHeight()));
        return result;
    }
    
    /**
     * Calculates the vertical offset between the baseline and the specified text anchor.
     * 
     * @param g2  the graphics device.
     * @param anchor  the anchor.
     * 
     * @return the offset.
     */
    public float calculateBaselineOffset(Graphics2D g2, TextAnchor anchor) {
        float result = 0.0f;
        FontMetrics fm = g2.getFontMetrics(this.font);
        LineMetrics lm = fm.getLineMetrics("ABCxyz", g2);
        if (anchor == TextAnchor.TOP_LEFT || anchor == TextAnchor.TOP_CENTER
                                          || anchor == TextAnchor.TOP_RIGHT) {
            result = lm.getAscent();
        }
        else if (anchor == TextAnchor.BOTTOM_LEFT || anchor == TextAnchor.BOTTOM_CENTER
                                                  || anchor == TextAnchor.BOTTOM_RIGHT) {
            result = -lm.getDescent() - lm.getLeading();
        }
        return result;                                             
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;   
        }
        if (obj == this) {
            return true;   
        }
        if (obj instanceof TextFragment) {
            TextFragment tf = (TextFragment) obj;
            if (!this.text.equals(tf.text)) {
                return false;   
            }
            if (!this.font.equals(tf.font)) {
                return false;   
            }
            if (!this.paint.equals(tf.paint)) {
                return false;   
            }
            return true;
        }
        return false;
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.paint, stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.paint = SerialUtilities.readPaint(stream);
    }
   
}
