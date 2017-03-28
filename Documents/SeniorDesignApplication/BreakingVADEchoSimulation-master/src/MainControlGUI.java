import java.util.HashMap;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;

public class MainControlGUI {
    static JFrame frame;
    private Calibration calibration;

    public MainControlGUI() {
        frame = new JFrame("Echo Control Panel");
        calibration = new Calibration();
    }
    
    public void create() {
        JLabel connecting = new JLabel("Connecting...");
        JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("End Session");
        cancel.addActionListener(new ButtonListener());
        JPanel panel = new JPanel();

        panel.add(connecting);
        panel.add(cancel);
        frame.getContentPane().add(panel);

        frame.setSize(800,500);
        frame.setVisible(true);
    }

    /**
     * @wbp.parser.entryPoint
     */
    public void connected() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();

        ButtonListener listener = new ButtonListener();

        frame.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{69, 519, 83};
        gbl_panel.rowHeights = new int[]{0, 19, 29, 0, 63, 0, 67, 16, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0};
        gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 2;
        gbc_panel_1.gridy = 0;
        panel.add(panel_1, gbc_panel_1);
        JLabel connected = new JLabel("Connected to Arduino.");
        GridBagConstraints gbc_connected = new GridBagConstraints();
        gbc_connected.insets = new Insets(0, 0, 5, 5);
        gbc_connected.anchor = GridBagConstraints.WEST;
        gbc_connected.gridx = 1;
        gbc_connected.gridy = 2;
        panel.add(connected, gbc_connected);
                         
                         JPanel panel1 = new JPanel();
                         panel1.setBorder(BorderFactory.createTitledBorder("Create New Calibration"));
                         //frame.getContentPane().add(panel2);
                         GridBagConstraints gbc_panel1 = new GridBagConstraints();
                         gbc_panel1.fill = GridBagConstraints.BOTH;
                         gbc_panel1.insets = new Insets(0, 0, 5, 5);
                         gbc_panel1.gridx = 1;
                         gbc_panel1.gridy = 4;
                         GridBagLayout gbl_panel1 = new GridBagLayout();
                         gbl_panel1.columnWidths = new int[]{389, 100, 0};
                         gbl_panel1.rowHeights = new int[]{29, 0};
                         gbl_panel1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
                         gbl_panel1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
                         panel1.setLayout(gbl_panel1);
                         JButton calibrate = new JButton("Calibrate");
                         calibrate.setActionCommand("Calibrate");
                         calibrate.addActionListener(listener);
                         JLabel calinstruction = new JLabel("Set correct probe positions for each of 6 available viewpoints.");
                         GridBagConstraints gbc_calinstruction = new GridBagConstraints();
                         gbc_calinstruction.anchor = GridBagConstraints.WEST;
                         gbc_calinstruction.insets = new Insets(0, 0, 0, 5);
                         gbc_calinstruction.gridx = 0;
                         gbc_calinstruction.gridy = 0;
                         panel1.add(calinstruction, gbc_calinstruction);
                         GridBagConstraints gbc_calibrate = new GridBagConstraints();
                         gbc_calibrate.anchor = GridBagConstraints.NORTHWEST;
                         gbc_calibrate.insets = new Insets(0, 0, 0, 5);
                         gbc_calibrate.gridx = 1;
                         gbc_calibrate.gridy = 0;
                         panel1.add(calibrate);
                         panel.add(panel1, gbc_panel1);
                        
                         JPanel panel2 = new JPanel();
                         GridBagLayout gbl_panel2 = new GridBagLayout();
                         gbl_panel2.columnWidths = new int[]{107, 151, 0};
                         gbl_panel2.rowHeights = new int[]{33, 0};
                         gbl_panel2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
                         gbl_panel2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
                         panel2.setLayout(gbl_panel2);
                         
                          panel2.setBorder(BorderFactory.createTitledBorder("Use Previous Calibration"));
                          GridBagConstraints gbc_panel2 = new GridBagConstraints();
                          gbc_panel2.fill = GridBagConstraints.BOTH;
                          gbc_panel2.insets = new Insets(0, 0, 5, 5);
                          gbc_panel2.gridx = 1;
                          gbc_panel2.gridy = 6;
                          panel.add(panel2, gbc_panel2);
                          JLabel instructions = new JLabel("Please select from the saved calibrations below:");
                          GridBagConstraints gbc_instructions = new GridBagConstraints();
                          gbc_instructions.gridwidth = 2;
                          gbc_instructions.anchor = GridBagConstraints.NORTHWEST;
                          gbc_instructions.gridx = 0;
                          gbc_instructions.gridy = 0;
                          panel2.add(instructions);
                          JButton closeButton = new JButton("Close");
                          closeButton.setActionCommand("End Session");
                          closeButton.addActionListener(listener);
                          GridBagConstraints gbc_closeButton = new GridBagConstraints();
                          gbc_closeButton.anchor = GridBagConstraints.NORTHEAST;
                          gbc_closeButton.insets = new Insets(0, 0, 5, 5);
                          gbc_closeButton.gridx = 1;
                          gbc_closeButton.gridy = 7;
                          panel.add(closeButton, gbc_closeButton);
                        
                        HashMap<String, HashMap<String, ImagingRegion>> savedCals = calibration.getCalibrationsFromFirebase();
                        for (String key : savedCals.keySet()) {
                            JButton button = new JButton(key);
                            button.setActionCommand(key);
                            button.addActionListener(new PreviousCalibrationsListener());
                            panel2.add(button);
                        }
        frame.setSize(800,500);
        frame.setVisible(true);
    }

    public void disconnected() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();
        JLabel connected = new JLabel("Arduino has been disconnected.");
        JButton close = new JButton("Close");
        close.setActionCommand("End Session");
        close.addActionListener(new ButtonListener());

        panel.add(connected);
        panel.add(close);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    static void close() {
        frame.dispose();
        ProbeDetection.setEndSession(true);
    }
}
