package org.albfan;

import javax.swing.*;
import javax.swing.table.*;

/**
 * A ScrollPane which "freezes" the specified number of
 * columns of a JTable.
 *
 * @author fahdshariff
 */

public class FrozenColumnTablePane extends JScrollPane {

    public FrozenColumnTablePane(JTable table, int colsToFreeze) {
        super(table);

        TableModel model = table.getModel();

        //create a frozen model
        TableModel frozenModel = new DefaultTableModel(
                model.getRowCount(),
                colsToFreeze);

        //populate the frozen model
        DefaultTableColumnModel cm = new DefaultTableColumnModel();
        for (int j = 0; j < colsToFreeze; j++) {
            TableColumn tc = new TableColumn();
            tc.setHeaderValue(model.getColumnName(j));
            cm.addColumn(tc);
            for (int i = 0; i < model.getRowCount(); i++) {
                String value = (String) model.getValueAt(i, j);
                frozenModel.setValueAt(value, i, j);
            }
        }

        //create frozen table
        JTable frozenTable = new JTable(frozenModel, cm);

        //remove the frozen columns from the original table
        for (int j = 0; j < colsToFreeze; j++) {
            table.removeColumn(table.getColumnModel().getColumn(0));
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //format the frozen table
        JTableHeader header = table.getTableHeader();
        frozenTable.setBackground(header.getBackground());
        frozenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        frozenTable.setEnabled(false);

        //set frozen table as row header view
        JViewport viewport = new JViewport();
        viewport.setView(frozenTable);
        viewport.setPreferredSize(frozenTable.getPreferredSize());
        setRowHeaderView(viewport);
        setCorner(JScrollPane.UPPER_LEFT_CORNER, frozenTable.getTableHeader());
    }
}
