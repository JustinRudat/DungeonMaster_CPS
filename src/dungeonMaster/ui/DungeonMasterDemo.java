package dungeonMaster.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import dungeonMaster.components.EngineImplem;
import dungeonMaster.components.EnvironmentImplem;
import dungeonMaster.services.EngineService;
import dungeonMaster.services.EnvironmentService;

public class DungeonMasterDemo {
   private static final Color GREEN = new Color(200, 255, 200);
   private static final Color BLUE = new Color(200, 200, 255);
   private static final EngineService engine = new EngineImplem();
   
   
   private static void createAndShowGui() {
	  initEngine(engine);
      JFrame frame = new JFrame("SimpleLayout");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // note that a JFrame's contentPane uses BorderLayout by default
      frame.getContentPane().add(new MyPanel(1200, 80), BorderLayout.SOUTH);
      MyPanel panel = new MyPanel(900, 620);
      panel.setLayout(new GridBagLayout());
      
      panel.setBackground(Color.BLACK);
      frame.getContentPane().add(panel, BorderLayout.WEST);
      drawAsciiPanel(panel);
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
      panel_button.add(new JButton("TK"),c);
      
      frame.getContentPane().add(panel_button, BorderLayout.CENTER);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }
   private static void drawAsciiPanel(JPanel panel) {
	   GridBagConstraints c_panel = new GridBagConstraints();
	      c_panel.fill=GridBagConstraints.HORIZONTAL;
	      JLabel label = new JLabel("                        _________________\n"); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 0;
	      panel.add(label,c_panel);
	      
	      label = new JLabel("                      /            |              |            \\\n");
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 1;
	      panel.add(label,c_panel);
	      label = new JLabel("                    /              |              |              \\\n" );
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 2;
	      panel.add(label,c_panel);
	      label = new JLabel("                  /_______|______|_______\\\n" ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 3;
	      panel.add(label,c_panel);
	      label = new JLabel("                /                /                  \\                \\\n" ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 4;
	      panel.add(label,c_panel);
	      label = new JLabel("              /                  |                  |                  \\\n" ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 5;
	      panel.add(label,c_panel);
	      label = new JLabel("            /                    |                  |                    \\\n" );
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 6;
	      panel.add(label,c_panel);
	      label = new JLabel("          /_________|________|_________\\\n" ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 7;
	      panel.add(label,c_panel);
	      label = new JLabel("        /                      /                      \\                      \\\n" ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 8;
	      panel.add(label,c_panel);
	      label = new JLabel("      /                        |                      |                        \\\n" ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 9;
	      panel.add(label,c_panel);
	      label = new JLabel("    /                          |                      |                          \\\n" ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 10;
	      panel.add(label,c_panel);
	      label = new JLabel("  /                            |                      |                            \\\n" ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 11;
	      panel.add(label,c_panel);
	      label = new JLabel("/_____________|_________|_____________\\");
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 12;
	      panel.add(label,c_panel);
   }
   
   
   private static void initEngine(EngineService engine) {
	   EnvironmentService env = new EnvironmentImplem();
	   env.init(70, 50);
	   engine.init(env);
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
