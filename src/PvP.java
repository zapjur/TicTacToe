import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PvP extends TicTacToe implements ActionListener {
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

}
