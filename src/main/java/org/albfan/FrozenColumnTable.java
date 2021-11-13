package org.albfan;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class FrozenColumnTable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                int columnCount = 20;
                Vector<String> columnNames = new Vector<>();
                for (int i = 0; i < columnCount; i++) {
                    columnNames.add("col"+i);
                }

                Vector<Vector<String>> data = new Vector<>();
                for (int i = 0; i < 100; i++) {
                    Vector<String> row = new Vector<>();
                    for (int j = 0; j < columnNames.size(); j++) {
                        row.add(""+i+j);
                    }
                    data.add(row);
                }

                JTable table = new JTable(data, columnNames);
                JScrollPane scrollPane = new FrozenColumnTablePane(table, 3);
                JFrame frame = new JFrame();
                frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                frame.setSize(300, 150);
                frame.setVisible(true);
            }
        });
    }
}
