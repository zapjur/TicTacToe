import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends TicTacToe implements ActionListener {
    private JPanel gameModeButtonPanel = new JPanel();
    private JPanel difficultyModeButtonPanel = new JPanel();
    private JButton pvpButton = new JButton("PvP");
    private JButton vsComputerButton = new JButton("vs Computer");
    private JButton easyModeButton = new JButton("Easy");
    private JButton mediumModeButton = new JButton("Medium");
    private JButton hardModeButton = new JButton("Hard");

    public StartWindow() {

        textField.setText("Choose game mode");
        titlePanel.add(textField);

        pvpButton.setUI(new ChooseModeButtonUI());
        vsComputerButton.setUI(new ChooseModeButtonUI());
        pvpButton.addActionListener(this);
        vsComputerButton.addActionListener(this);
        pvpButton.setForeground(fgColor);
        vsComputerButton.setForeground(fgColor);

        gameModeButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        gameModeButtonPanel.setBackground(bgColor);
        gameModeButtonPanel.add(pvpButton);
        gameModeButtonPanel.add(vsComputerButton);

        easyModeButton.setUI(new EasyModeButtonUI());
        mediumModeButton.setUI(new MediumModeButtonUI());
        hardModeButton.setUI(new HardModeButtonUI());
        easyModeButton.addActionListener(this);
        hardModeButton.addActionListener(this);
        mediumModeButton.addActionListener(this);
        easyModeButton.setForeground(fgColor);
        mediumModeButton.setForeground(fgColor);
        hardModeButton.setForeground(fgColor);

        difficultyModeButtonPanel.setLayout(new GridBagLayout());
        difficultyModeButtonPanel.setBackground(bgColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 50, 0);

        difficultyModeButtonPanel.add(easyModeButton, gbc);
        gbc.gridy = 1;
        difficultyModeButtonPanel.add(mediumModeButton, gbc);
        gbc.gridy = 2;
        difficultyModeButtonPanel.add(hardModeButton, gbc);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(gameModeButtonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pvpButton) {
            frame.remove(gameModeButtonPanel);
            PvP pvpGame = new PvP();
        }
        if(e.getSource() == vsComputerButton){
            frame.add(difficultyModeButtonPanel, BorderLayout.SOUTH);
            textField.setText("Choose difficulty!");
        }
    }

    public class EasyModeButtonUI extends javax.swing.plaf.basic.BasicButtonUI{
        public void paint(Graphics g, JComponent c){
            c.setBackground(new Color(82, 183, 136));
            c.setForeground(Color.BLACK);
            c.setFont(new Font("Arial", Font.PLAIN, 18));
            c.setPreferredSize(new Dimension(300, 50));
            super.paint(g, c);
        }
    }
    public class MediumModeButtonUI extends javax.swing.plaf.basic.BasicButtonUI{
        public void paint(Graphics g, JComponent c){
            c.setBackground(new Color(254, 226, 49));
            c.setForeground(Color.BLACK);
            c.setFont(new Font("Arial", Font.PLAIN, 18));
            c.setPreferredSize(new Dimension(300, 50));
            super.paint(g, c);
        }
    }
    public class HardModeButtonUI extends javax.swing.plaf.basic.BasicButtonUI{
        public void paint(Graphics g, JComponent c){
            c.setBackground(XColor);
            c.setForeground(Color.BLACK);
            c.setFont(new Font("Arial", Font.PLAIN, 18));
            c.setPreferredSize(new Dimension(300, 50));
            super.paint(g, c);
        }
    }
}
