import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class CalibrationGUI {
    private JFrame frame;
    private CalibrationListener calibrationListener;
    private JPanel panel;
    private JLabel instructions;
    private JLabel instructions1;
    private static JTextField textField;
    private String[] positionNames = {"Parasternal (Long Axis)", "Parasternal (Short Axis)", "Apical (4 Chamber)", "Apical (2 Chamber)", "Subxiphoid (4 Chamber/Outlets)", "Suprasternal (Arch)"};
    private JPanel panel_1;
    private JProgressBar progressBar;
    private JButton btnFinishCalibration;
    private JPanel panel_2;

    public CalibrationGUI() {
        
    }

    /**
     * @wbp.parser.entryPoint
     */
    public void create() {
    	frame = new JFrame("Calibration");
        panel = new JPanel();
        calibrationListener = new CalibrationListener();
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{55, 474, 60, 0};
        gbl_panel.rowHeights = new int[]{44, 36, 16, 0, 116, 46, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
                
                panel_2 = new JPanel();
                GridBagConstraints gbc_panel_2 = new GridBagConstraints();
                gbc_panel_2.insets = new Insets(0, 0, 5, 0);
                gbc_panel_2.fill = GridBagConstraints.BOTH;
                gbc_panel_2.gridx = 2;
                gbc_panel_2.gridy = 0;
                panel.add(panel_2, gbc_panel_2);
                instructions = new JLabel("Hold probe in correct location and angle for calibration of viewpoint.");
                
                        GridBagConstraints gbc_instructions = new GridBagConstraints();
                        gbc_instructions.anchor = GridBagConstraints.SOUTHWEST;
                        gbc_instructions.insets = new Insets(0, 0, 5, 0);
                        gbc_instructions.gridwidth = 2;
                        gbc_instructions.gridx = 1;
                        gbc_instructions.gridy = 1;
                        panel.add(instructions, gbc_instructions);
                instructions1 = new JLabel("Press \"Set Viewpoint\" to record this position.");
                GridBagConstraints gbc_instructions1 = new GridBagConstraints();
                gbc_instructions1.anchor = GridBagConstraints.WEST;
                gbc_instructions1.insets = new Insets(0, 0, 5, 5);
                gbc_instructions1.gridx = 1;
                gbc_instructions1.gridy = 2;
                panel.add(instructions1, gbc_instructions1);
                
                progressBar = new JProgressBar();
                GridBagConstraints gbc_progressBar = new GridBagConstraints();
                gbc_progressBar.insets = new Insets(0, 0, 5, 5);
                gbc_progressBar.gridx = 1;
                gbc_progressBar.gridy = 3;
                panel.add(progressBar, gbc_progressBar);
                
                panel_1 = new JPanel();
                GridBagConstraints gbc_panel_1 = new GridBagConstraints();
                gbc_panel_1.insets = new Insets(0, 0, 5, 5);
                gbc_panel_1.fill = GridBagConstraints.BOTH;
                gbc_panel_1.gridx = 1;
                gbc_panel_1.gridy = 4;
                panel_1.setBorder(BorderFactory.createTitledBorder(positionNames[0]));
                GridBagLayout gbl_panel_1 = new GridBagLayout();
                gbl_panel_1.columnWidths = new int[]{42, 150, 129, 0};
                gbl_panel_1.rowHeights = new int[]{0, 0, 29, 0};
                gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
                gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
                panel_1.setLayout(gbl_panel_1);
                panel.add(panel_1, gbc_panel_1);
                                JLabel location = new JLabel("Current probe location: ");
                                GridBagConstraints gbc_location = new GridBagConstraints();
                                gbc_location.anchor = GridBagConstraints.WEST;
                                gbc_location.insets = new Insets(0, 0, 5, 5);
                                gbc_location.gridx = 1;
                                gbc_location.gridy = 0;
                                panel_1.add(location, gbc_location);
                                
                                        JButton okButton = new JButton("Set Viewpoint");
                                        GridBagConstraints gbc_okButton = new GridBagConstraints();
                                        gbc_okButton.insets = new Insets(0, 0, 0, 5);
                                        gbc_okButton.anchor = GridBagConstraints.NORTHWEST;
                                        gbc_okButton.gridx = 1;
                                        gbc_okButton.gridy = 2;
                                        panel_1.add(okButton, gbc_okButton);
                                        //okButton.setEnabled(false);
                                        okButton.setForeground(Color.BLACK);
                                        okButton.setActionCommand("Calibrate Position");
                        okButton.addActionListener(calibrationListener);
        frame.getContentPane().add(panel);
        
        btnFinishCalibration = new JButton("Finish Calibration");
        GridBagConstraints gbc_btnFinishCalibration = new GridBagConstraints();
        gbc_btnFinishCalibration.anchor = GridBagConstraints.EAST;
        gbc_btnFinishCalibration.insets = new Insets(0, 0, 0, 5);
        gbc_btnFinishCalibration.gridx = 1;
        gbc_btnFinishCalibration.gridy = 5;
        panel.add(btnFinishCalibration, gbc_btnFinishCalibration);

        frame.setSize(600,400);
        frame.setVisible(true);
    }

    public void nextImage(int imageNumber) {
        frame.getContentPane().removeAll();
        panel = new JPanel();
        JLabel imageLabel = new JLabel(positionNames[imageNumber] + " Calibration:");

        JButton okButton = new JButton("OK");
        okButton.setActionCommand("Calibrate Position");
        okButton.addActionListener(calibrationListener);

        panel.add(instructions);
        panel.add(imageLabel);
        panel.add(okButton);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void finish() {
        frame.getContentPane().removeAll();
        panel = new JPanel();

        JLabel complete = new JLabel("Calibration Complete. Would you like to save this calibration?");
        JButton saveButton = new JButton("Yes");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(calibrationListener);

        JButton noSaveButton = new JButton("No");
        noSaveButton.setActionCommand("Don't Save");
        noSaveButton.addActionListener(calibrationListener);

        panel.add(complete);
        panel.add(saveButton);
        panel.add(noSaveButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void save() {
        frame.getContentPane().removeAll();
        panel = new JPanel();

        JLabel saveAs = new JLabel("Enter a name for your calibration:");
        textField = new JTextField(20);
        JButton saveAsButton = new JButton("Save");

        saveAsButton.setActionCommand("Save as");
        saveAsButton.addActionListener(calibrationListener);

        panel.add(saveAs);
        panel.add(textField);
        panel.add(saveAsButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }

    static JTextField getTextField() {
        return textField;
    }

    public void noSaveNameError() {
        JOptionPane.showMessageDialog(null, "Please enter a name before saving.");
    }
}
