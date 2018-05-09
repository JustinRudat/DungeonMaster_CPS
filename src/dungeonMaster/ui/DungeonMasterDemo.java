package dungeonMaster.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import dungeonMaster.services.GobelinService;
import dungeonMaster.services.LootService;
import dungeonMaster.services.LootType;
import dungeonMaster.services.MinotaurService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.MonsterService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class DungeonMasterDemo {
    private static final Color GREEN = new Color(200, 255, 200);
    private static final Color BLUE = new Color(200, 200, 255);
    private static EngineService engine = new EngineImplem();
    private static MyPanel panel_info=new MyPanel(1200, 100);
    private static PlayerService player_info ;



    private static MyPanel createAndShowGui() {
        engine = new EngineImplem();
        engine = engine.generateRandomSquareGame();
        JFrame frame = new JFrame("SimpleLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PlayerService player = null;
        for(EntityService entity : engine.getEntities()) {
            if(entity instanceof PlayerService) {
                player = (PlayerService) entity;
            }
        }
        player_info = player;
        final PlayerService player_final = player;
        // note that a JFrame's contentPane uses BorderLayout by default

        panel_info.setBackground(Color.WHITE);
        JLabel lab = new JLabel(handlePlayerInfo(player));
        panel_info.add(lab);
        frame.getContentPane().add(panel_info, BorderLayout.SOUTH);
        MyPanel display = new MyPanel(700, 520);
        display.setLayout(new GridBagLayout());

        display.setBackground(Color.BLACK);
        frame.getContentPane().add(display, BorderLayout.WEST);
        drawAsciiPanelFinal(display);


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

        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(10,0,0,0);
        button = new JButton("DF");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                player_final.setLastCommand(Command.DF);

            }
        });
        panel_button.add(button,c);

        c.gridx = 3;
        c.gridy = 3;
        c.insets = new Insets(10,0,0,0);
        button = new JButton("PF");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                player_final.setLastCommand(Command.PF);

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
        label.setFont(new Font("Droid Sans Mono", Font.PLAIN, 12));
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


    private static String gereLineSec(PlayerService player,int index) {
        String str = "";
        Cell[][] plateau = engine.getEnv().getPlateau();
        int x,y;
        for(int j = 0 ; j<engine.getEnv().getWidth();j++) {
            switch(player.getFace()) {
                case N:
                    x = index - player.getRow();
                    y = j - player.getCol();
                    if(y>=-1 && y<=1) {
                        if(x <= 4 && x>=-1) {
                            str+=handleCellNatSec(player, x, y);
                        }
                        else {
                            str+="*";
                        }
                    }else {
                        str+="*";
                    }
                    break;

                case S:
                    x = index - player.getRow();
                    y = j - player.getCol();
                    if(y>=-1 && y<=1) {
                        if(x <= 1 && x>=-4) {
                            str+=handleCellNatSec(player, x, y);
                        }else {
                            str+="*";
                        }
                    }else {
                        str+="*";
                    }
                    break;

                case E:
                    x = index - player.getRow();
                    y = j - player.getCol();
                    if(y>=-1 && y<=4) {
                        if(x <= 1 && x>=-1) {
                            str+=handleCellNatSec(player, x, y);
                        }else {
                            str+="*";
                        }
                    }else {
                        str+="*";
                    }
                    break;

                case W:
                    x = index - player.getRow();
                    y = j - player.getCol();
                    if(y>=-4 && y<=1) {
                        if(x <= 1 && x>=-1) {
                            str+=handleCellNatSec(player, x, y);
                        }else {
                            str+="*";
                        }
                    }else {
                        str+="*";
                    }
                    break;

                default:
                    break;
            }
            if(player.isViewable(j, index)!=null) {
                str+=handleCellNatSec(player, index, j);
            }else {
                str+="*";
            }
        }
        return str;
    }


    private static String handleCellNatSec(PlayerService player ,int x,int y ) {
        Cell nat = player.isViewable(x, y);
        //Cell nat = engine.getEnv().cellNature(x, y);

        if (nat != null) {
            OptionService<MobService> opt = engine.getEnv().cellContent(x, y);
            switch (nat) {
                case WLL:
                    return "#";
                case EMP:
                    switch (opt.getOption()) {
                        case No:
                            return ".";
                        case So:
                            if (opt.getElem() instanceof LootService) {
                                if (((LootService) opt.getElem()).getLootType() == LootType.Treasure) {
                                    return "t";
                                }
                                if (((LootService) opt.getElem()).getLootType() == LootType.Potion) {
                                    return "p";
                                }
                                if (((LootService) opt.getElem()).getLootType() == LootType.Armor) {
                                    return "a";
                                }
                                if (((LootService) opt.getElem()).getLootType() == LootType.Key) {
                                    return "k";
                                }
                                return "L";
                            }
                            if (opt.getElem() instanceof CowService) {
                                return "C";
                            }
                            if (opt.getElem() instanceof MonsterService) {
                                return "G";
                            }
                            if (opt.getElem() instanceof PlayerService) {
                                return "@";
                            }
                    }
                case IN:
                    if (opt.getElem() instanceof PlayerService) {
                        return "@";
                    }
                    return "+";
                case OUT:
                    return "O";
                case DNC:
                    return "f";
                case DWC:
                    return "f";
                case DWL:
                    return "l";
                case DNL:
                    return "l";
                case DNO:
                    switch (opt.getOption()) {
                        case No:
                            return "o";
                        case So:
                            if (opt.getElem() instanceof LootService) {
                                return "L";
                            }
                            if (opt.getElem() instanceof CowService) {
                                return "C";
                            }
                            if (opt.getElem() instanceof MonsterService) {
                                return "G";
                            }
                    }

                case DWO:
                    switch (opt.getOption()) {
                        case No:
                            return "o";
                        case So:
                            if (opt.getElem() instanceof LootService) {
                                return "L";
                            }
                            if (opt.getElem() instanceof CowService) {
                                return "C";
                            }
                            if (opt.getElem() instanceof MonsterService) {
                                return "G";
                            }
                    }
                    return "?";
            }
        } else {
            return "?";
        }
        return "?";
    }
    private static String handlePlayerInfo(PlayerService player ) {

        if(engine.isGameOver()) {
            if(engine.isWin()) {
                return "YOU WON";
            }else {
                return "Game is Over";
            }
        }
        return "HP : "+player.getHealthPoints()+"|| dmg = "+player.getDegats()+"|| armor = "+player.getArmor()+"|| dir = "+player.getFace();
    }

    private static void handlePannelInfo(MyPanel panel) {
        panel.removeAll();
        JLabel lab = new JLabel(handlePlayerInfo(player_info));
        panel_info.add(lab);
        panel_info.updateUI();
    }


    private static void initEngine(EngineService engine) {
        engine.generateRandomSquareGame();
    }

    private static void drawAsciiPanelFinal(JPanel panel) {
        panel.removeAll();
        GridBagConstraints c_panel = new GridBagConstraints();
        PlayerService player = null;
        for(EntityService entity : engine.getEntities()) {
            if(entity instanceof PlayerService) {
                player = (PlayerService) entity;
            }
        }
        c_panel.fill=GridBagConstraints.HORIZONTAL;
        c_panel.gridx = 0;
        c_panel.gridy = 0;
        for (int i = 0; i < engine.getEnv().getWidth(); i++) {
            String str = "";
            for (int j = 0; j < engine.getEnv().getHeight(); j++) {
                str += handleCellNatSec(player, i, j);
            }
            str += "\n";
            JLabel label = new JLabel(str);
            label.setFont(new Font("Dejavu Sans Mono", Font.PLAIN, 12));
            label.setForeground(Color.white);
            panel.add(label, c_panel);
            c_panel.gridy++;
        }
            //String str = gereLineSec(player, i);
    }




    public static void main(String[] args) {

        Thread t = new Thread() {
            public void run() {
                try {
                    MyPanel display = createAndShowGui();
                    while(!engine.isGameOver()) {
                        if(player_info.getLastCommand().getOption()!=Option.No) {
                            runStep();
                            drawAsciiPanelFinal(display);
                            display.updateUI();
                            handlePannelInfo(panel_info);
                        }
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


