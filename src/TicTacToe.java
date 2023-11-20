import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicTacToe {
    protected JFrame frame = new JFrame();
    protected JPanel titlePanel = new JPanel();
    protected JPanel buttonPanel = new JPanel();
    protected JPanel resultPanel = new JPanel();
    protected JLabel textField = new JLabel();
    protected JLabel scoreFieldX = new JLabel();
    protected JLabel scoreFieldO = new JLabel();
    protected int scoreX = 0;
    protected int scoreO = 0;
    protected JButton[] buttons = new JButton[9];
    protected static JButton playAgainButton = new JButton("Play Again");
    protected static JButton newGameButton = new JButton("New Game");
    protected static JButton exitButton = new JButton("Exit");

    protected int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    protected final Color OColor = new Color(20, 145, 217);
    protected final Color XColor = new Color(214, 46, 70);
    protected final Color bgColor = new Color(33, 37, 41);
    protected final Color fgColor = new Color(255,255,255);

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
        textField.setOpaque(true);

        scoreFieldX.setBackground(bgColor);
        scoreFieldX.setForeground(XColor);
        scoreFieldX.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreFieldX.setHorizontalAlignment(JLabel.CENTER);
        scoreFieldX.setOpaque(true);

        scoreFieldO.setBackground(bgColor);
        scoreFieldO.setForeground(OColor);
        scoreFieldO.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreFieldO.setHorizontalAlignment(JLabel.CENTER);
        scoreFieldO.setOpaque(true);

        titlePanel.setLayout(new GridLayout(1, 3, 10, 10));
        titlePanel.setBounds(0, 0, 500, 100);
        titlePanel.setBackground(bgColor);

        buttonPanel.setLayout(new GridLayout(3,3, 10, 10));
        buttonPanel.setBackground(bgColor);

        playAgainButton.setUI(new ResultButtonUI());
        newGameButton.setUI(new ResultButtonUI());
        exitButton.setUI(new ResultButtonUI());

        resultPanel.setLayout(new GridBagLayout());
        resultPanel.setBackground(bgColor);
        resultPanel.setForeground(fgColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        resultPanel.add(playAgainButton, gbc);

        gbc.gridx = 1;
        resultPanel.add(newGameButton, gbc);

        gbc.gridx = 2;
        resultPanel.add(exitButton, gbc);

    }

    protected boolean checkIfWon(String player){

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

    protected boolean checkIfOver(){
        for(JButton button : buttons){
            if(button.getText().isEmpty())
                return false;
        }
        return true;
    }

    protected void gameDraw(){
        textField.setText("Draw!");
        for(JButton button : buttons){
            button.setEnabled(false);
        }
        showResult();
    }

    protected void gameWon(String player, int b1, int b2, int b3){
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
            scoreFieldO.setText("O points: " + ++scoreO);
        }

        int delay = 1000;
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResult();
            }
        });
        timer.setRepeats(false);
        timer.start();

    }

    protected void playAgain(){
        for(JButton button : buttons){
            button.setEnabled(true);
            button.setText("");
            button.setUI(new GameButtonUI());
        }
        buttonPanel.setVisible(true);
        frame.remove(resultPanel);
        textField.setText("X turn!");

    }

    protected void newGame(){
        frame.remove(buttonPanel);
        frame.remove(resultPanel);
        StartWindow st = new StartWindow();
    }

    protected void showResult() {

        buttonPanel.setVisible(false);
        frame.add(resultPanel);
    }


    public static class GameButtonUI extends javax.swing.plaf.basic.BasicButtonUI{
        public void paint(Graphics g, JComponent c){
            c.setBackground(new Color(222, 226, 230));
            c.setFont(new Font("Arial", Font.PLAIN, 48));
            super.paint(g, c);
        }
    }

    public static class WonButtonUI extends javax.swing.plaf.basic.BasicButtonUI{
        public void paint(Graphics g, JComponent c){
            c.setBackground(new Color(82, 183, 136));
            c.setFont(new Font("Arial", Font.PLAIN, 48));
            super.paint(g, c);
        }
    }

    public static class ResultButtonUI extends javax.swing.plaf.basic.BasicButtonUI{
        public void paint(Graphics g, JComponent c){
            c.setBackground(new Color(222, 226, 230));
            c.setForeground(Color.BLACK);
            c.setFont(new Font("Arial", Font.PLAIN, 18));
            c.setPreferredSize(new Dimension(150, 200));
            super.paint(g, c);
        }
    }

    public class ChooseModeButtonUI extends javax.swing.plaf.basic.BasicButtonUI{
        public void paint(Graphics g, JComponent c){
            c.setBackground(new Color(222, 226, 230));
            c.setForeground(Color.BLACK);
            c.setFont(new Font("Arial", Font.PLAIN, 18));
            c.setPreferredSize(new Dimension(200, 50));
            super.paint(g, c);
        }
    }

    public static void main(String[] args){
        StartWindow st = new StartWindow();
    }
}
