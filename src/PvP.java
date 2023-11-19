import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PvP extends TicTacToe implements ActionListener {

    private int[] winningCombination;
    private boolean playerXTurn = true;
    public PvP(){

        textField.setText("X turn!");
        scoreFieldX.setText("X points: 0");
        scoreFieldO.setText("O points: 0");

        titlePanel.add(scoreFieldX);
        titlePanel.add(textField);
        titlePanel.add(scoreFieldO);

        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setUI(new GameButtonUI());
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        playAgainButton.addActionListener(this);
        newGameButton.addActionListener(this);
        exitButton.addActionListener(this);

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
                        gameDraw();
                        return;
                    }
                    playerXTurn = false;
                    textField.setText("O turn!");
                }
                else{
                    if(!buttons[i].getText().isEmpty()) return;

                    buttons[i].setText("O");
                    buttons[i].setForeground(OColor);
                    if(checkIfWon(buttons[i].getText())){
                        return;
                    }
                    if(checkIfOver()){
                        gameDraw();
                        return;
                    }
                    playerXTurn = true;
                    textField.setText("X turn!");
                }
            }
        }
        if(e.getSource() == exitButton){
            System.exit(0);
        }
        if(e.getSource() == playAgainButton){
            playAgain();
        } else if (e.getSource() == newGameButton) {
            newGame();
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
        for(JButton button : buttons){
            if(button.getText().isEmpty())
                return false;
        }
        return true;
    }

    private void gameDraw(){
        textField.setText("Draw!");
        for(JButton button : buttons){
            button.setEnabled(false);
        }
        showResult();
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

    private void playAgain(){
        for(JButton button : buttons){
            button.setEnabled(true);
            button.setText("");
            button.setUI(new GameButtonUI());
        }
        buttonPanel.setVisible(true);
        frame.remove(resultPanel);

        textField.setText("X turn!");

    }

    private void newGame(){
        frame.remove(buttonPanel);
        frame.remove(resultPanel);
        StartWindow st = new StartWindow();
    }

    private void showResult() {

        buttonPanel.setVisible(false);
        frame.add(resultPanel);
    }

}
