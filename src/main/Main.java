

import main.GamePanel;

import javax.swing.JFrame;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setTitle("Serach of the UCL : Barcelona Edition");

    GamePanel gamePanel = new GamePanel();
    frame.add(gamePanel);

    frame.pack();

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    gamePanel.startGameThread();

  }

