/**
 * ========================================
 * JFreeReport : a free Java report library
 * ========================================
 *
 * Project Info:  http://www.jfree.org/jfreereport/index.html
 * Project Lead:  Thomas Morgner (taquera@sherito.org);
 *
 * (C) Copyright 2000-2003, by Simba Management Limited and Contributors.
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
 * ------------------------------
 * FontChooserDialogTest.java
 * ------------------------------
 * (C)opyright 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Simba Management Limited);
 *
 * $Id: FontChooserDialogTest.java,v 1.2 2004/03/24 23:20:23 mungady Exp $
 *
 * Changes 
 * -------------------------
 * 21.02.2004 : Initial version
 *  
 */

package org.jfree.ui.junit;

import java.awt.Frame;
import java.awt.Font;

import junit.framework.TestCase;
import org.jfree.ui.FontChooserDialog;

public class FontChooserDialogTest extends TestCase {

    public FontChooserDialogTest(String s) {
        super(s);
    }

    public void testCreateDialog () {
        try {
            new FontChooserDialog
                (new Frame(), "Title", false, new Font("Serif", Font.PLAIN, 10));
        }
        catch (UnsupportedOperationException use) {
            // Headless mode exception is instance of this ex.
        }
    }
}
