package ui;

import model.ListOfSubscriptions;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.WindowEvent;

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
        output = new JsonWriter(JSON_STORE);
        input = new JsonReader(JSON_STORE);
        displaySplash();
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
        setVisible(true);
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.out.println("Event Log:");
            printLog(EventLog.getInstance());
            System.exit(0);
        }
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }
    }

    // EFFECTS: Displays a splash screen of SubHub logo for 5 seconds upon opening app
    private void displaySplash() {
        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("", new ImageIcon("./data/LOGO.png"), SwingConstants.CENTER));
        window.setBounds(230, 100, 800, 600);
        window.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.dispose();
    }

    // MODIFIES: this
    // EFFECTS: Creates buttons in main interface
    private void makeMenuButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.add(new JButton(new AddSubAction()));
        buttonPanel.add(new JButton(new ViewSubAction()));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
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

    // MODIFIES: this
    /**
     * EFFECTS: Adds an item with given handler to the given menu
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
            JTextField service = new JTextField();
            JTextField cost = new JTextField();
            JTextField date = new JTextField();
            JTextField period = new JTextField();
            addSubPanel(service, cost, date, period);
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates a popup panel prompting the user to enter new subscription information
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

    // EFFECTS: Checks to see if any fields to create a subscription are left empty
    public boolean emptyField(String name, Double amount, String pdate, int rtype) {
        return (name.isEmpty() || amount.equals(null) || pdate.isEmpty() || rtype > 3 || rtype < 1);
    }

    // MODIFIES: this
    // EFFECTS: Outputs a table of all subscriptions in the list
    private class ViewSubAction extends AbstractAction {
        ViewSubAction() {
            super("View all subscriptions");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            createViewTable();
        }
    }


    // EFFECTS: Creates a non-modifiable table displaying all the current subscriptions in a new panel
    public void createViewTable() {
        //Create and set up the window.
        JFrame frame = new JFrame("View Subscriptions");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        ViewTable newContentPane = new ViewTable(newList);
        //newContentPane.populateTable(newList);

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
            } catch (IOException i) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    /**
     * EFFECTS: Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            SubHubUI.this.requestFocusInWindow();
        }
    }
}
