import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static GameFrame instance;
    private GameFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setTitle("Tic Tac Toe");
        getContentPane().setBackground(TicTacToe.bgColor);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
    }
    public static synchronized GameFrame getInstance(){
        if(instance == null){
            instance = new GameFrame();
        }
        return instance;
    }
}
