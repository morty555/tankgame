package TankGame03;

import javax.swing.*;

public class TankGame03 extends JFrame {

    MyPanel mp = null;
    public static void main(String[] args) {

        TankGame03 tankGame02 = new TankGame03();
    }
    public TankGame03(){
      mp= new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
      this.addKeyListener(mp);
      this.setSize(1000,750);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
}
