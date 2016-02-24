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
 * CenterLayout.java
 * -----------------
 * (C) Copyright 2000-2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: CenterLayout.java,v 1.8 2004/03/05 07:16:12 mungady Exp $
 *
 * Changes (from 5-Nov-2001)
 * -------------------------
 * 05-Nov-2001 : Changed package to com.jrefinery.layout.* (DG);
 * 10-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 *
 */

package org.jfree.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.Serializable;

/**
 * A layout manager that displays a single component in the center of its container.
 *
 * @author David Gilbert
 */
public class CenterLayout implements LayoutManager, Serializable {

    /**
     * Creates a new layout manager.
     */
    public CenterLayout() {
        super();
    }

    /**
     * Returns the preferred size.
     *
     * @param parent  the parent.
     *
     * @return the preferred size.
     */
    public Dimension preferredLayoutSize(Container parent) {

        Dimension d = null;
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            if (parent.getComponentCount() > 0) {
                Component component = parent.getComponent(0);
                d = component.getPreferredSize();
            }
            return new Dimension((int) d.getWidth() + insets.left + insets.right,
                                 (int) d.getHeight() + insets.top + insets.bottom);
        }

    }

    /**
     * Returns the minimum size.
     *
     * @param parent  the parent.
     *
     * @return the minimum size.
     */
    public Dimension minimumLayoutSize(Container parent) {

        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            if (parent.getComponentCount() > 0) {
                Component component = parent.getComponent(0);
                Dimension d = component.getMinimumSize();
                return new Dimension(d.width + insets.left + insets.right,
                                 d.height + insets.top + insets.bottom);
            }
            else {
              return new Dimension(insets.left + insets.right,
                                   insets.top + insets.bottom);
            }
        }

    }

    /**
     * Lays out the components.
     *
     * @param parent  the parent.
     */
    public void layoutContainer(Container parent) {

        synchronized (parent.getTreeLock()) {
            if (parent.getComponentCount() > 0) {
                Insets insets = parent.getInsets();
                Dimension parentSize = parent.getSize();
                Component component = parent.getComponent(0);
                Dimension componentSize = component.getPreferredSize();
                int xx = insets.left
                         + (Math.max((parentSize.width - insets.left - insets.right
                                      - componentSize.width) / 2, 0));
                int yy = insets.top
                         + (Math.max((parentSize.height - insets.top - insets.bottom
                                      - componentSize.height) / 2, 0));
                component.setBounds(xx, yy, componentSize.width, componentSize.height);
            }
        }

    }

    /**
     * Not used.
     *
     * @param comp  the component.
     */
    public void addLayoutComponent(Component comp) {
        // not used.
    }

    /**
     * Not used.
     *
     * @param comp  the component.
     */
    public void removeLayoutComponent(Component comp) {
        // not used
    }

    /**
     * Not used.
     *
     * @param name  the component name.
     * @param comp  the component.
     */
    public void addLayoutComponent(String name, Component comp) {
        // not used
    }

    /**
     * Not used.
     *
     * @param name  the component name.
     * @param comp  the component.
     */
    public void removeLayoutComponent(String name, Component comp) {
        // not used
    }

}