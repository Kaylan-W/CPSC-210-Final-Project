package ui;

import model.ListOfSubscriptions;
import model.Subscription;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class ViewTable extends JPanel {
    // EFFECTS: table constructor
    public ViewTable(ListOfSubscriptions los) {
        String[] col = {"Service", "Cost", "Renewal Period", "Purchase Date"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        JTable table = new JTable(tableModel);


        for (int i = 0; i < los.size(); i++) {
            Subscription s = los.getSub(i);
            String name = s.getName();
            Double cost = s.getCost();
            String rtype = s.getPeriodType();
            LocalDate pdate = s.getPurchaseDate();
            Object[] data = {name, cost, rtype, pdate};
            tableModel.addRow(data);
        }

        table.setPreferredScrollableViewportSize(new Dimension(500,200));
        table.setFillsViewportHeight(true);
        JScrollPane jsp = new JScrollPane(table);
        add(jsp);
    }
}
