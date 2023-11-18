import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TicTacToe implements ActionListener {

    private JFrame frame = new JFrame();
    private Random random = new Random();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel textField = new JLabel();
    private JLabel scoreFieldX = new JLabel();
    private JLabel scoreFieldO = new JLabel();
    private int scoreX = 0;
    private int scoreO = 0;
    private JButton[] buttons = new JButton[9];
    private final Color bgColor = new Color(33, 37, 41);
    private final Color fgColor = new Color(255,255,255);
    private final Color OColor = new Color(20, 145, 217);
    private final Color XColor = new Color(214, 46, 70);
    private int[] winningCombination;
    private boolean playerXTurn = true;
    public TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setTitle("Tic Tac Toe");
        frame.getContentPane().setBackground(bgColor);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        textField.setBackground(bgColor);
        textField.setForeground(fgColor);
        textField.setFont(new Font("Arial", Font.PLAIN, 40));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic Tac Toe");
        textField.setOpaque(true);

        scoreFieldX.setBackground(bgColor);
        scoreFieldX.setForeground(XColor);
        scoreFieldX.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreFieldX.setHorizontalAlignment(JLabel.CENTER);
        scoreFieldX.setText("X points: 0");
        scoreFieldX.setOpaque(true);

        scoreFieldO.setBackground(bgColor);
        scoreFieldO.setForeground(OColor);
        scoreFieldO.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreFieldO.setHorizontalAlignment(JLabel.CENTER);
        scoreFieldO.setText("O points: 0");
        scoreFieldO.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 500, 100);
        titlePanel.add(scoreFieldX, BorderLayout.WEST);
        titlePanel.add(textField, BorderLayout.CENTER);
        titlePanel.add(scoreFieldO, BorderLayout.EAST);

        buttonPanel.setLayout(new GridLayout(3,3, 10, 10));
        buttonPanel.setBackground(bgColor);

        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 48));
            buttons[i].setUI(new BasicButtonUI());
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < 9; i++){
            if(e.getSource() == buttons[i]){
                if(playerXTurn){
                    if(!buttons[i].getText().isEmpty()) return;

                    buttons[i].setText("X");
                    buttons[i].setForeground(XColor);
                    if(checkIfWon(buttons[i].getText())){
                        return;
                    }
                    if(checkIfOver()){
                        textField.setText("Draw!");
                        return;
                    }
                    playerXTurn = false;
                    textField.setText("O turn!");
                }
                else{
                    if(!buttons[i].getText().isEmpty()) return;

                    buttons[i].setText("O");
                    buttons[i].setForeground(OColor);
                    checkIfWon(buttons[i].getText());
                    if(checkIfOver()){
                        textField.setText("Draw!");
                        return;
                    }
                    playerXTurn = true;
                    textField.setText("X turn!");
                }
            }
        }
    }

    private boolean checkIfWon(String player){

        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}};

        for (int[] condition : winConditions) {
            int b1 = condition[0];
            int b2 = condition[1];
            int b3 = condition[2];

            if (buttons[b1].getText().equals(player) &&
                buttons[b2].getText().equals(player) &&
                buttons[b3].getText().equals(player)) {

                gameWon(player, b1, b2, b3);
                return true;
            }
        }
        return false;
    }

    private boolean checkIfOver(){
        for(int i = 0; i < 9; i++){
            if(buttons[i].getText().isEmpty())
                return false;
        }
        return true;
    }

    private void gameWon(String player, int b1, int b2, int b3){
        buttons[b1].setUI(new WonButtonUI());
        buttons[b2].setUI(new WonButtonUI());
        buttons[b3].setUI(new WonButtonUI());

        for(JButton button : buttons){
            button.setEnabled(false);
        }

        textField.setText(player + " Won!");
        if(player.equals("X")){
            scoreFieldX.setText("X points: " + ++scoreX);
        }
        else{
            scoreFieldO.setText("X points: " + ++scoreO);
        }
    }

    private class BasicButtonUI extends javax.swing.plaf.basic.BasicButtonUI{
        public void paint(Graphics g, JComponent c){
            c.setBackground(new Color(222, 226, 230));
            super.paint(g, c);
        }
    }

    private class WonButtonUI extends javax.swing.plaf.basic.BasicButtonUI{
        public void paint(Graphics g, JComponent c){
            c.setBackground(Color.GREEN);
            super.paint(g, c);
        }
    }
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
    }

}
