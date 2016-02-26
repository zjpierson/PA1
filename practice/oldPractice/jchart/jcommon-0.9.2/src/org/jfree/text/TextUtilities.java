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
 * TextUtilities.java
 * ------------------
 * (C) Copyright 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: TextUtilities.java,v 1.3 2004/03/24 13:28:53 mungady Exp $
 *
 * Changes
 * -------
 * 07-Jan-2004 : Version 1 (DG);
 * 24-Mar-2004 : Added 'paint' argument to createTextBlock() method (DG);
 *
 */

package org.jfree.text;

import java.awt.Font;
import java.awt.Paint;
import java.text.BreakIterator;

/**
 * Some utility methods for working with text.
 */
public class TextUtilities {

    /**
     * Creates a new text block from the given string.
     * 
     * @param text  the text.
     * @param font  the font.
     * @param paint  the paint.
     * @param maxWidth  the maximum width for each line.
     * @param measurer  the text measurer.
     * 
     * @return A text block.
     */
    public static TextBlock createTextBlock(String text, 
                                            Font font, 
                                            Paint paint,
                                            float maxWidth, 
                                            TextMeasurer measurer) {
        return createTextBlock(text, font, paint, maxWidth, Integer.MAX_VALUE, measurer);
    }
    
    /**
     * Creates a new text block from the given string.
     * 
     * @param text  the text.
     * @param font  the font.
     * @param paint  the paint.
     * @param maxWidth  the maximum width for each line.
     * @param maxLines  the maximum number of lines.
     * @param measurer  the text measurer.
     * 
     * @return A text block.
     */
    public static TextBlock createTextBlock(String text, 
                                            Font font,
                                            Paint paint,
                                            float maxWidth, 
                                            int maxLines, 
                                            TextMeasurer measurer) {
        TextBlock result = new TextBlock();
        BreakIterator iterator = BreakIterator.getWordInstance();
        iterator.setText(text);
        int current = 0;
        int length = text.length();
        while (current < length) {
            int next = nextLineBreak(text, current, maxWidth, iterator, measurer);
            if (next == BreakIterator.DONE) {
                result.addLine(text.substring(current), font, paint); 
                return result;
            }
            result.addLine(text.substring(current, next), font, paint);
            current = next;
        }
        return result;
    }
    
    /**
     * Returns the character index of the next line break.
     * 
     * @param text  the text.
     * @param start  the start index.
     * @param width  the end index.
     * @param iterator  the word break iterator.
     * @param measurer  the text measurer.
     * 
     * @return The index of the next line break.
     */
    private static int nextLineBreak(String text, int start, float width, 
                                     BreakIterator iterator, TextMeasurer measurer) {
        // this method is (loosely) based on code in JFreeReport's TextParagraph class
        int current = start;
        int end;
        float x = 0.0f;
        boolean firstWord = true;
        while (((end = iterator.next()) != BreakIterator.DONE)) {
            x += measurer.getStringWidth(text, current, end);  
            if (x > width) {
                if (firstWord) {
                    while (measurer.getStringWidth(text, start, end) > width) {
                        end--;
                    }
                    //iterator.setPosition(end);
                    return end;
                }
                else {
                    end = iterator.previous();
                    return end;
                }
            }
            // we found at least one word that fits ...
            firstWord = false;
            current = end;
        }
        return BreakIterator.DONE;
    }
    
}
