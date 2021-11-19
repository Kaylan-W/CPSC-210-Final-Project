package ui;

import model.ListOfSubscriptions;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

// This class references code from the repo "AlarmSystem"
// Link: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
class SubHubUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private JDesktopPane desktop;
    private JInternalFrame mainPanel;
    private static final String JSON_STORE = "./data/workroom.json";
    private Scanner readInput = new Scanner(System.in);
    ListOfSubscriptions newList = new ListOfSubscriptions();
    private JsonWriter output;
    private JsonReader input;

    //EFFECTS: Constructor creates a new window with all required buttons and menus.
    public SubHubUI() {
        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        mainPanel = new JInternalFrame("Subscription manager", true, false, true, false);
        mainPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("SubHub: Your Safe and Secure Subscription Manager!");
        setSize(WIDTH, HEIGHT);

        makeMenuButtons();
        addMenuBar();

        mainPanel.pack();
        mainPanel.setVisible(true);
        desktop.add(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void makeMenuButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.add(new JButton(new AddSubAction()));
        buttonPanel.add(new JButton(new ViewSubAction()));
        //buttonPanel.add(new JButton(new CancelAction())); //SECONDARY
        //buttonPanel.add(new JButton(new RenewAction())); //SECONDARY
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    // EFFECTS: creates and adds a menu bar to the panel.
    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        addMenuItem(fileMenu, new SaveFileAction(), null);
        addMenuItem(fileMenu, new LoadFileAction(), null);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    /**
     * Adds an item with given handler to the given menu
     * @param theMenu  menu to which new item is added
     * @param action   handler for new menu item
     * @param accelerator    keystroke accelerator for this menu item
     */
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new subscription and adds it to the list
    private class AddSubAction extends AbstractAction {
        AddSubAction() {
            super("Add a new subscription");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            System.out.println("Added!");
            JTextField service = new JTextField();
            JTextField cost = new JTextField();
            JTextField date = new JTextField();
            JTextField period = new JTextField();
            addSubPanel(service, cost, date, period);
        }
    }

    public void addSubPanel(JTextField service, JTextField cost, JTextField date, JTextField period) {
        Object[] message = {
                "Service Name:", service,
                "Cost: $", cost,
                "Purchase Date (yyyy-mm-dd) :", date,
                "Renewal Period:", period
        };

        int addStatus = JOptionPane.showConfirmDialog(null,
                message,"Enter subscription information", JOptionPane.OK_CANCEL_OPTION);

        String name = service.getText();
        Double amount = Double.parseDouble(cost.getText());
        String pdate = date.getText();
        int rtype = Integer.parseInt(period.getText());

        if (addStatus == JOptionPane.OK_OPTION) {
            if (emptyField(name, amount, pdate, rtype)) {
                JOptionPane.showMessageDialog(null,
                        "Missing Field!", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                newList.addSub(name, amount, rtype, pdate);
                JOptionPane.showMessageDialog(null,
                        "Subscription Added", "Success!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public boolean emptyField(String name, Double amount, String pdate, int rtype) {
        return (name.isEmpty() || amount.equals(null) || pdate.isEmpty() || rtype > 3 || rtype < 1);
    }

    // EFFECTS: Outputs a table of all subscriptions in the list
    private class ViewSubAction extends AbstractAction {
        ViewSubAction() {
            super("View all subscriptions");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
//            int size = newList.size();
//            if (size == 0) {
//                System.out.println("There are no subscriptions!");
//            } else {
//                System.out.println("NAME\t\t COST ($)\t RENEWAL PERIOD\t\t\t PURCHASE DATE");
//                for (int ind = 0; ind < size; ind++) {
//                    System.out.println(newList.getSubString(ind));
//                }
//            }
            createViewTable();

        }
    }

    // EFFECTS: Creates a non-modifiable table displaying all the current subscriptions in a new panel
    public void createViewTable() {
        //Create and set up the window.
        JFrame frame = new JFrame("View Subscriptions");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        ViewTable newContentPane = new ViewTable();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: allows user to save current subscriptions to the file.
    private class SaveFileAction extends AbstractAction {
        SaveFileAction() {
            super("Save File");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                output.open();
                output.write(newList);
                output.close();
                System.out.println("Saved subscriptions!");
            } catch (FileNotFoundException e) {
                System.out.println("Unable to read from file!!");
            }
        }
    }

    // EFFECTS: allows user to load previously saved subscriptions from a file.
    private class LoadFileAction extends AbstractAction {
        LoadFileAction() {
            super("Load File");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                newList = input.readList();
                System.out.println("List loaded from file");
            } catch (IOException i) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            SubHubUI.this.requestFocusInWindow();
        }
    }
}
