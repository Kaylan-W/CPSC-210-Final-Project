package ui;

import javax.swing.*;
import java.awt.*;

public class ViewTable extends JPanel {
    // EFFECTS: table constructor
    public ViewTable() {
        String[] columns = {"Service", "Cost", "Renewal Period", "Purchase Date"};
        String[][] data = {{"Netflix", "$100.00", "Monthly", "2021-01-30"},
                {"Hulu", "$50.00", "Weekly", "2021-11-25"}};
        JTable table = new JTable(data, columns);
        table.setPreferredScrollableViewportSize(new Dimension(500,200));
        table.setFillsViewportHeight(true);
        JScrollPane jsp = new JScrollPane(table);
        add(jsp);
    }
}
