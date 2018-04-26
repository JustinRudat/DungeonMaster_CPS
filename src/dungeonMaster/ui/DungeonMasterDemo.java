package dungeonMaster.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class DungeonMasterDemo {
   private static final Color GREEN = new Color(200, 255, 200);
   private static final Color BLUE = new Color(200, 200, 255);

   private static void createAndShowGui() {
      JFrame frame = new JFrame("SimpleLayout");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // note that a JFrame's contentPane uses BorderLayout by default
      frame.getContentPane().add(new MyPanel(1200, 80), BorderLayout.SOUTH);
      MyPanel panel = new MyPanel(900, 620);
      panel.setBackground(Color.BLACK);
      frame.getContentPane().add(panel, BorderLayout.WEST);
      MyPanel panel_button = new MyPanel(300,620);
      panel_button.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      c.fill = GridBagConstraints.BOTH;
      c.gridx = 1;
      c.gridy = 0;
      panel_button.add(new JButton("TL"),c);
      c.gridx = 2;
      c.gridy = 0;
      panel_button.add(new JButton("FF"),c);
      c.gridx = 3;
      c.gridy = 0;
      panel_button.add(new JButton("TR"),c);
      c.gridx = 1;
      c.gridy = 1;
      panel_button.add(new JButton("LL"),c);
      c.gridx = 2;
      c.gridy = 1;
      panel_button.add(new JButton("BB"),c);
      c.gridx = 3;
      c.gridy = 1;
      panel_button.add(new JButton("RR"),c);
      c.gridx = 0;
      c.gridy = 3;
      c.insets = new Insets(10,0,0,0);
      panel_button.add(new JButton("AA"),c);
      c.gridx = 4;
      c.gridy = 3;
      c.insets = new Insets(10,0,0,0);
      panel_button.add(new JButton("DD"),c);
      
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
