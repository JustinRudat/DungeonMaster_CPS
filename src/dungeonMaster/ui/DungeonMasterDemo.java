package dungeonMaster.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.*;

public class DungeonMasterDemo {
   private static final Color GREEN = new Color(200, 255, 200);
   private static final Color BLUE = new Color(200, 200, 255);

   private static void createAndShowGui() {
      JFrame frame = new JFrame("SimpleLayout");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // note that a JFrame's contentPane uses BorderLayout by default
      frame.getContentPane().add(new MyPanel(1200, 80), BorderLayout.SOUTH);
      frame.getContentPane().add(new MyPanel(1000, 620), BorderLayout.WEST);
      MyPanel panel_button = new MyPanel(200,620);
      panel_button.add(new JButton("1"));
      panel_button.add(new JButton("2"));
      panel_button.add(new JButton("3"));
      panel_button.add(new JButton("4"));
      panel_button.add(new JButton("5"));
      panel_button.add(new JButton("6"));
      frame.getContentPane().add(panel_button, BorderLayout.CENTER);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}

class MyPanel extends JPanel {
   private int prefW;
   private int prefH;

   public MyPanel(int prefW, int prefH) {
      this.prefW = prefW;
      this.prefH = prefH;
   }



   @Override
   public Dimension getPreferredSize() {
      return new Dimension(prefW, prefH);
   }
}
