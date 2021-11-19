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

//            String sensorLoc = JOptionPane.showInputDialog(null,
//                    "Sensor location?",
//                    "Enter subscription information",
//                    JOptionPane.QUESTION_MESSAGE);
//            try {
//                if (sensorLoc != null) {
//                    Sensor s = new Sensor(sensorLoc, ac);
//                    desktop.add(new SensorUI(s, AlarmControllerUI.this));
//                }
        }
    }

    // EFFECTS: Outputs a table of all subscriptions in the list
    private class ViewSubAction extends AbstractAction {
        ViewSubAction() {
            super("View all subscriptions");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            System.out.println("Viewed!");
        }
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
