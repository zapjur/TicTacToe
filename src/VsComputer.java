import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VsComputer extends TicTacToe implements ActionListener {

    private boolean playerXTurn = true;

    public VsComputer() {
        textField.setText("X turn!");
        scoreFieldX.setText("X points: 0");
        scoreFieldO.setText("O points: 0");

        titlePanel.add(scoreFieldX);
        titlePanel.add(textField);
        titlePanel.add(scoreFieldO);

        for (int i = 0; i < 9; i++) {
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
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (playerXTurn && buttons[i].getText().isEmpty()) {
                    buttons[i].setText("X");
                    buttons[i].setForeground(XColor);
                    if (checkIfWon(buttons[i].getText())) {
                        return;
                    }
                    if (checkIfOver()) {
                        gameDraw();
                        return;
                    }
                    playerXTurn = false;
                    textField.setText("O turn!");

                    computerMove();
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

    private void computerMove() {
        char[] board = new char[9];
        int k = 0;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                board[k] = ' ';
            } else if (button.getText().equals("X")) {
                board[k] = 'X';
            } else {
                board[k] = 'O';
            }
            k++;
        }
        int bestMove = minMaxAlg(board, 'O');
        buttons[bestMove].setForeground(OColor);
        buttons[bestMove].setText("O");

        if(checkIfWon("O")){
            int[] bs = new int[3];
            int l = 0;
            for(int i = 0; i < 9; i++){
                if(buttons[i].getText() == "O") bs[l++] = i;
            }

            gameWon("O",bs[0],bs[1],bs[2]);
        }
        if(checkIfOver()){
            gameDraw();
        }

    }

    private int minMaxAlg(char[] board, char player) {
        int result = -1;
        int bestScore = (player == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = player;
                int score = minMaxAlgScore(board, 0, false);
                board[i] = ' ';
                if ((player == 'O' && score > bestScore) || (player == 'X' && score < bestScore)) {
                    bestScore = score;
                    result = i;
                }
            }
        }
        return result;
    }

    private int minMaxAlgScore(char[] board, int depth, boolean isMaximizing) {
        if (checkIfWinForComp(board, 'X')) return -1;
        if (checkIfWinForComp(board, 'O')) return 1;
        if (checkIfOver(board)) return 0;
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    board[i] = 'O';
                    bestScore = Math.max(bestScore, minMaxAlgScore(board, depth + 1, false));
                    board[i] = ' ';
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    board[i] = 'X';
                    bestScore = Math.min(bestScore, minMaxAlgScore(board, depth + 1, true));
                    board[i] = ' ';
                }
            }
            return bestScore;
        }
    }

    private boolean checkIfOver(char[] board) {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }

    private boolean checkIfWinForComp(char[] board, char sign) {
        for (int[] condition : winConditions) {
            int b1 = condition[0];
            int b2 = condition[1];
            int b3 = condition[2];

            if (board[b1] == sign && board[b2] == sign && board[b3] == sign) {
                return true;
            }
        }
        return false;
    }
}
