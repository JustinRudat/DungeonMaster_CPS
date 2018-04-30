package dungeonMaster.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import dungeonMaster.components.EngineImplem;
import dungeonMaster.components.EnvironmentImplem;
import dungeonMaster.services.Cell;
import dungeonMaster.services.Command;
import dungeonMaster.services.CowService;
import dungeonMaster.services.EngineService;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.LootService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class DungeonMasterDemo {
   private static final Color GREEN = new Color(200, 255, 200);
   private static final Color BLUE = new Color(200, 200, 255);
   private static EngineService engine = new EngineImplem();
 
   
   
   private static MyPanel createAndShowGui() {
	  engine = new EngineImplem();
	  engine = engine.generateRandomGame();
      JFrame frame = new JFrame("SimpleLayout");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      PlayerService player = null;
      for(EntityService entity : engine.getEntities()) {
    	  if(entity instanceof PlayerService) {
    		  player = (PlayerService) entity;
    	  }
      }
      final PlayerService player_final = player;
      // note that a JFrame's contentPane uses BorderLayout by default
      frame.getContentPane().add(new MyPanel(1200, 80), BorderLayout.SOUTH);
      MyPanel display = new MyPanel(900, 620);
      display.setLayout(new GridBagLayout());
      
      display.setBackground(Color.BLACK);
      frame.getContentPane().add(display, BorderLayout.WEST);
      drawAsciiPanelSquare(display);
      MyPanel panel_button = new MyPanel(300,620);
      panel_button.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      c.fill = GridBagConstraints.BOTH;
      c.gridx = 1;
      c.gridy = 0;
      JButton button = new JButton("TL");
      button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			player_final.setLastCommand(Command.TL);
			
		}
	});
      panel_button.add(button,c);
      c.gridx = 2;
      c.gridy = 0;
      button = new JButton("FF");
      button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			player_final.setLastCommand(Command.FF);
			
		}
	});
      panel_button.add(button,c);
      c.gridx = 3;
      c.gridy = 0;
      button = new JButton("TR");
      button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			player_final.setLastCommand(Command.TR);
			
		}
	});
      panel_button.add(button,c);
      c.gridx = 1;
      c.gridy = 1;
      button = new JButton("LL");
      button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			player_final.setLastCommand(Command.LL);
			
		}
	});
      panel_button.add(button,c);
      c.gridx = 2;
      c.gridy = 1;
      button = new JButton("BB");
      button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			player_final.setLastCommand(Command.BB);
			
		}
	});
      panel_button.add(button,c);
      c.gridx = 3;
      c.gridy = 1;
      button = new JButton("RR");
      button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			player_final.setLastCommand(Command.RR);
			
		}
	});
      panel_button.add(button,c);
      c.gridx = 0;
      c.gridy = 3;
      c.insets = new Insets(10,0,0,0);
      button = new JButton("AA");
      button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			player_final.setLastCommand(Command.AA);
			
		}
	});
      panel_button.add(button,c);
      
      c.gridx = 4;
      c.gridy = 3;
      c.insets = new Insets(10,0,0,0);
      button = new JButton("TK");
      button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			player_final.setLastCommand(Command.TK);
			
		}
	});
      panel_button.add(button,c);
      
      frame.getContentPane().add(panel_button, BorderLayout.CENTER);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
      return display;
   }
   private static void drawAsciiPanel(JPanel panel) {
	   GridBagConstraints c_panel = new GridBagConstraints();
	   PlayerService player = null;
	      for(EntityService entity : engine.getEntities()) {
	    	  if(entity instanceof PlayerService) {
	    		  player = (PlayerService) entity;
	    	  }
	      }
	      c_panel.fill=GridBagConstraints.HORIZONTAL;
	      String str = "                      _________________\n";
	      str = str.replace(" ","  ");
	      JLabel label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 0;
	      panel.add(label,c_panel);
	      str = "                     /      |       |      \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 1;
	      panel.add(label,c_panel);
	      
	      if(player.getRow()+2<=engine.getEnv().getHeight()) {
	    	  if(player.getCol()-1>=0) {
	    			  switch(engine.getEnv().cellNature(player.getRow()+2,player.getCol()-1)) {
	    			  case WLL:
	    				  break;
	    			  case EMP:
	    				  break;
	    			  case IN:
	    				  break;
	    			  case OUT:
	    				  break;
	    			  default:// door
	    				  break;
	    			  }
	    	  }
	      }
	      
	      str = "                    /       |       |       \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 2;
	      panel.add(label,c_panel);
	      str = "                   /_______|______|_______\\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 3;
	      panel.add(label,c_panel);
	      str = "                  /        /         \\        \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 4;
	      panel.add(label,c_panel);
	      str = "                 /         |         |         \\\n";
	      str = str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 5;
	      panel.add(label,c_panel);
	      str = "                /          |         |          \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 6;
	      panel.add(label,c_panel);
	      str = "               /_________|________|_________\\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 7;
	      panel.add(label,c_panel);
	      str = "              /           /           \\           \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 8;
	      panel.add(label,c_panel);
	      str = "             /            |           |            \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 9;
	      panel.add(label,c_panel);
	      str = "            /             |           |             \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 10;
	      panel.add(label,c_panel);
	      str = "           /              |           |              \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 11;
	      panel.add(label,c_panel);
	      str = "          /_____________|_________|_____________\\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 12;
	      panel.add(label,c_panel);
	      str = "         /               /             \\               \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 13;
	      panel.add(label,c_panel);
	      str = "        /                |             |                \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 14;
	      panel.add(label,c_panel);
	      str = "       /                 |      P      |                 \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 15;
	      panel.add(label,c_panel);
	      str = "      /                  |             |                  \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 16;
	      panel.add(label,c_panel);
	      str = "     /________________|____________|________________\\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 17;
	      panel.add(label,c_panel);
	      str = "    /                   /               \\                   \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 18;
	      panel.add(label,c_panel);
	      str = "   /                    |                |                   \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 19;
	      panel.add(label,c_panel);
	      str = "  /                     |                |                    \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 20;
	      panel.add(label,c_panel);
	      str = " /                      |                |                     \\\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 21;
	      panel.add(label,c_panel);
	      str = "/____________________|______________|__________________\\\n";
	      str =  str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 22;
	      panel.add(label,c_panel);
	      
   }
   
   private static String gereLine(PlayerService player,int index) {
	   String str = "|          |          |          |\n";
	   switch(player.getFace()) {
	   case N:
		   if(player.getRow()+index<engine.getEnv().getHeight()) {
			   if (player.getCol()-1>=0) {
	
	
	
				   str="|     "+handleCellNat(engine.getEnv().cellNature(player.getCol()-1, player.getRow()+index),engine.getEnv().cellContent(player.getCol()-1, player.getRow()+index))+"    ";
			   }else {
				   str="|          ";
			   }
			   if(index==0) {
				   str+="|     P    |";
			   }else {
				   str+="|     "+handleCellNat(engine.getEnv().cellNature(player.getCol(), player.getRow()+index),engine.getEnv().cellContent(player.getCol(), player.getRow()+index))+"    |";
			   }
			   if(player.getCol()+1<engine.getEnv().getWidth()) {
				   str+="     "+handleCellNat(engine.getEnv().cellNature(player.getCol()+1, player.getRow()+index),engine.getEnv().cellContent(player.getCol()+1, player.getRow()+index))+"    |\n";
			   }else {
				   str+="          |\n";
			   }
		   }
		   break;
	   case S:
	   if(player.getRow()-index>=0) {
		   if (player.getCol()+1<engine.getEnv().getWidth()) {



			   str="|     "+handleCellNat(engine.getEnv().cellNature(player.getCol()+1, player.getRow()-index),engine.getEnv().cellContent(player.getCol()+1, player.getRow()-index))+"    ";
		   }else {
			   str="|          ";
		   }
		   if(index==0) {
			   str+="|     P    |";
		   }else {
			   str+="|     "+handleCellNat(engine.getEnv().cellNature(player.getCol(), player.getRow()-index),engine.getEnv().cellContent(player.getCol(), player.getRow()-index))+"    |";
		   }
		   if(player.getCol()-1>=0) {
			   str+="     "+handleCellNat(engine.getEnv().cellNature(player.getCol()-1, player.getRow()-index),engine.getEnv().cellContent(player.getCol()-1, player.getRow()-index))+"    |\n";
		   }else {
			   str+="          |\n";
		   }
	   }
	   break;
	   case W:
	   if(player.getCol()-index>=0) {
		   if (player.getRow()-1>=0) {



			   str="|     "+handleCellNat(engine.getEnv().cellNature(player.getCol()-index, player.getRow()-1),engine.getEnv().cellContent(player.getCol()-index, player.getRow()-1))+"    ";
		   }else {
			   str="|          ";
		   }
		   if(index==0) {
			   str+="|     P    |";
		   }else {
			   str+="|     "+handleCellNat(engine.getEnv().cellNature(player.getCol()-index, player.getRow()),engine.getEnv().cellContent(player.getCol()-index, player.getRow()))+"    |";
		   }
		   if(player.getRow()+1<engine.getEnv().getHeight()) {
			   str+="     "+handleCellNat(engine.getEnv().cellNature(player.getCol()-index, player.getRow()+1),engine.getEnv().cellContent(player.getCol()-index, player.getRow()+1))+"    |\n";
		   }else {
			   str+="          |\n";
		   }
	   }
	   break;
	   case E:
	   if(player.getCol()+index<engine.getEnv().getWidth()) {
		   if (player.getRow()+1<engine.getEnv().getHeight()) {



			   str="|     "+handleCellNat(engine.getEnv().cellNature(player.getCol()+index, player.getRow()+1),engine.getEnv().cellContent(player.getCol()+index, player.getRow()+1))+"    ";
		   }else {
			   str="|          ";
		   }
		   if(index==0) {
			   str+="|     P    |";
		   }else {
			   str+="|     "+handleCellNat(engine.getEnv().cellNature(player.getCol()+index, player.getRow()+0),engine.getEnv().cellContent(player.getCol()+index, player.getRow()+0))+"    |";
		   }
		   if(player.getRow()-1>=0) {
			   str+="     "+handleCellNat(engine.getEnv().cellNature(player.getCol()+index, player.getRow()-1),engine.getEnv().cellContent(player.getCol()+index, player.getRow()-1))+"    |\n";
		   }else {
			   str+="          |\n";
		   }
	   }
	   break;
	   }
	   return str;
   }
   private static String handleCellNat(Cell nat,OptionService<MobService> opt) {
	   switch(nat) {
	   	case WLL:
	   		return "*";
		  case EMP:
			  switch(opt.getOption()) {
			  case No:
				  return " ";
			  case So:
				  if(opt.getElem() instanceof LootService) {
					  return "L";
				  }
				  if(opt.getElem() instanceof CowService) {
					  return "C";
				  }
				  default:
					  return " ";
			  }
		  case IN:
			  return "+";
		  case OUT:
			  return "O";
		  case DNC:
		   		return "_c";
			  case DWC:
				  return "|c";
			  case DNO:
				  return "|o";
			  case DWO:
				  return "_o";
				  default:
					  return " ";
		  }
   }
   private static void drawAsciiPanelSquare(JPanel panel) {
	   panel.removeAll();
	   GridBagConstraints c_panel = new GridBagConstraints();
	   PlayerService player = null;
	      for(EntityService entity : engine.getEntities()) {
	    	  if(entity instanceof PlayerService) {
	    		  player = (PlayerService) entity;
	    	  }
	      }
	      c_panel.fill=GridBagConstraints.HORIZONTAL;
	      String str = "___________________________\n";
	      JLabel label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = 0;
	      panel.add(label,c_panel);
	      str = "|          |          |          |\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      
	      str = gereLine(player, 2);
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      
	      str = "|          |          |          |\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      str = "___________________________\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      str = "|          |          |          |\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      
	      str = gereLine(player, 1);
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      
	      str = "|          |          |          |\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      str = "___________________________\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      str = "|          |          |          |\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      str = "|          |          |          |\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);

	      str = gereLine(player, 0);
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      
	     
	      str = "|          |          |          |\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      str = "___________________________\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str ); 
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      str = "|          |          |          |\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      str = "|          |          |          |\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      

	      str = gereLine(player, -1);
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      
	      str = "|          |          |          |\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      str = "___________________________\n";
	      str = str.replace(" ","  ");
	      label = new JLabel(str);
	      label.setForeground(Color.white);
	      c_panel.gridx = 0;
	      c_panel.gridy = c_panel.gridy+1;
	      panel.add(label,c_panel);
	      
   }
   
   
   private static void initEngine(EngineService engine) {
	   engine.generateRandomGame();
   }
   
   
   
   
   public static void main(String[] args) {
      
      Thread t = new Thread() {
          public void run() {
        	  try {
        		  MyPanel display = createAndShowGui();
    		      while(!engine.isGameOver()) {
    		    	  runStep();
    		    	  drawAsciiPanelSquare(display);
    		    	  display.updateUI();
    		    	  this.sleep(500);
    				
    		      }
    	      } catch (InterruptedException e1) {
    				e1.printStackTrace();
    			}
          }
       };
       t.start();
   }
   
   public static void runStep() {
	   engine.step();
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


