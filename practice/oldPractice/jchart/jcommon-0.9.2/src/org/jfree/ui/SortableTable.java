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
 * SortableTable.java
 * ------------------
 * (C) Copyright 2000-2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: SortableTable.java,v 1.5 2004/03/05 09:50:39 mungady Exp $
 *
 * Changes (from 26-Oct-2001)
 * --------------------------
 * 26-Oct-2001 : Changed package to com.jrefinery.ui.*;
 * 14-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 *
 */

package org.jfree.ui;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 * A simple extension of JTable that supports the use of a SortableTableModel.
 *
 * @author David Gilbert
 */
public class SortableTable extends JTable {

    /** A listener for sorting. */
    private SortableTableHeaderListener headerListener;

    /**
     * Standard constructor - builds a table for the specified model.
     *
     * @param model  the data.
     */
    public SortableTable(SortableTableModel model) {

        super(model);

        SortButtonRenderer renderer = new SortButtonRenderer();
        TableColumnModel columnModel = getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setHeaderRenderer(renderer);
        }

        JTableHeader header = getTableHeader();
        this.headerListener = new SortableTableHeaderListener(model, renderer);
        header.addMouseListener(this.headerListener);
        header.addMouseMotionListener(this.headerListener);

        model.sortByColumn(0, true);

    }

    /**
     * Changes the model for the table.  Takes care of updating the header listener at the
     * same time.
     *
     * @param model  the table model.
     *
     */
    public void setSortableModel(SortableTableModel model) {

        super.setModel(model);
        this.headerListener.setTableModel(model);
        SortButtonRenderer renderer = new SortButtonRenderer();
        TableColumnModel columnModel = getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setHeaderRenderer(renderer);
        }
        model.sortByColumn(0, true);

    }

}
