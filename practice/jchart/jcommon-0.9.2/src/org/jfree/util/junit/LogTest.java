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
 * LogRemoveTest.java
 * ------------------------------
 * (C)opyright 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Simba Management Limited);
 *
 * $Id: LogTest.java,v 1.2 2004/03/05 13:47:03 mungady Exp $
 *
 * Changes
 * -------------------------
 * 21.02.2004 : Initial version
 *
 */

package org.jfree.util.junit;

import junit.framework.TestCase;
import org.jfree.util.LogTarget;
import org.jfree.util.Log;

public class LogTest extends TestCase {

    private class LogTargetImpl implements LogTarget {

        public LogTargetImpl() {
            super();
        }

        /**
         * Logs a message at a specified log level.
         *
         * @param level  the log level.
         * @param message  the log message.
         */
        public void log(int level, Object message) {
        }

        /**
         * Logs a message at a specified log level.
         *
         * @param level  the log level.
         * @param message  the log message.
         * @param e  the exception
         */
        public void log(int level, Object message, Exception e) {
        }
    }

    public LogTest(String s) {
        super(s);
    }

    public void testAddRemove() {
        LogTarget a = new LogTargetImpl();
        LogTarget b = new LogTargetImpl();

        Log.getInstance().removeTarget(a);
        Log.getInstance().removeTarget(b);

        Log.getInstance().addTarget(a);
        Log.getInstance().addTarget(b);

        Log.getInstance().removeTarget(a);
        Log.getInstance().removeTarget(b);

        Log.getInstance().addTarget(a);
        Log.getInstance().addTarget(b);

        Log.getInstance().removeTarget(b);
        Log.getInstance().removeTarget(a);

        Log.getInstance().getTargets();
    }
}
