import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class App {
    private static String ukaz;
    private static Boolean paused;
    
    private static final Color COLOR_EMPTY = new Color(204, 192, 179);
    private static final Color COLOR_2 = new Color(238, 228, 218);
    private static final Color COLOR_4 = new Color(237, 224, 200);
    private static final Color COLOR_8 = new Color(242, 177, 121);
    private static final Color COLOR_16 = new Color(245, 149, 99);
    private static final Color COLOR_32 = new Color(246, 124, 95);
    private static final Color COLOR_64 = new Color(246, 94, 59);
    private static final Color COLOR_128 = new Color(237, 207, 114);
    private static final Color COLOR_256 = new Color(237, 204, 97);
    private static final Color COLOR_512 = new Color(237, 200, 80);
    private static final Color COLOR_1024 = new Color(237, 197, 63);
    private static final Color COLOR_2048 = new Color(237, 194, 46);
    private static final Color COLOR_2048PLUS = new Color(237, 191, 29);
 
    private static final Color COLOR_VALUE_LIGHT = new Color(249, 246, 242);
 
    private static final Color COLOR_VALUE_DARK = new Color(119, 110, 101); 
    public static void main(String[] args) throws Exception {
        int tempSeznam[][] = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
        int seznam[][] = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
        boolean poteka, loop, razlicen, veljavenVnos, random;
        random = false;
        poteka = true;
        paused = false;
        File data = new File("data.txt");
        Scanner vnos = new Scanner(System.in);
        Random rand = new Random();
        int x, y, z, vred;
        x = 0;
        y = 0;
        vred = 2;
        String save;
        ukaz = "a";
        
        JFrame nalozi = new JFrame();
        nalozi.setTitle("2048");
        nalozi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nalozi.setResizable(false);
        nalozi.setSize(400,105);
        
        JLabel text = new JLabel("\u017Deli\u0161 nalo\u017Eiti zadnje shranjeno polje?");
        text.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel top = new JPanel();
        top.setBounds(0, 0, 400, 20);
        top.add(text);
        
        JButton da = new JButton("Da");
        da.addActionListener(e -> ukaz = "y");
        da.setBounds(270, 35, 50, 20);
        
        JButton ne = new JButton("Ne");
        ne.addActionListener(e -> ukaz = "n");
        ne.setBounds(330, 35, 50, 20);
        
        nalozi.add(top);
        nalozi.add(da);
        nalozi.add(ne);
        nalozi.setLayout(null);
        nalozi.setVisible(true);

        JFrame shrani = new JFrame();
        shrani.setTitle("2048");
        shrani.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        shrani.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.out.println("Waaah");
                paused = false;
                shrani.setVisible(false);
            }
        });
        shrani.setResizable(false);
        shrani.setSize(400,105);
        
        JLabel shrText = new JLabel("\u017Deli\u0161 shraniti zadnje shranjeno polje?");
        shrText.setHorizontalAlignment(JLabel.LEFT);
        
        JPanel shrTop = new JPanel();
        shrTop.setBounds(0, 0, 400, 20);
        shrTop.add(shrText);

        JButton shrDa = new JButton("Shrani");
        shrDa.addActionListener(e -> ukaz = "ends");
        shrDa.setBounds(115, 35, 75, 20);

        JButton shrNe = new JButton("Ne shrani");
        shrNe.addActionListener(e -> ukaz = "end");
        shrNe.setBounds(200, 35, 90, 20);

        JButton shrPreklic = new JButton("Prekli\u010Di");
        shrPreklic.addActionListener(e -> shrani.dispose());
        shrPreklic.addActionListener(e -> paused = false);
        shrPreklic.setBounds(300, 35, 80, 20);

        shrani.add(shrDa);
        shrani.add(shrNe);
        shrani.add(shrPreklic);
        shrani.add(shrTop);
        shrani.setLayout(null);
        shrani.setVisible(false);

        JFrame okno = new JFrame();
        okno.setTitle("2048");
        okno.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        okno.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                paused = true;
                shrani.setVisible(true);
            }
        });
        okno.setResizable(false);
        okno.setSize(316, 539);
        
        JPanel igralnoPolje = new JPanel();
        igralnoPolje.setBounds(0, 0, 300, 300);
        igralnoPolje.setLayout(new GridLayout(4, 4, 2, 2));
        okno.add(igralnoPolje);
        okno.addKeyListener(new MyKeyListener());

        JButton gor = new JButton("\u2191");
        gor.setFont(new Font("Arial", Font.BOLD, 20));
        gor.addActionListener(e -> ukaz = "w");
        gor.setBounds(120, 305, 60, 60);
        

        JButton levo = new JButton("\u2190");
        levo.setFont(new Font("Arial", Font.BOLD, 20));
        levo.addActionListener(e -> ukaz = "a");
        levo.setBounds(55, 370, 60, 60);
        

        JButton desno = new JButton("\u2192");
        desno.setFont(new Font("Arial", Font.BOLD, 20));
        desno.addActionListener(e -> ukaz = "d");
        desno.setBounds(185, 370, 60, 60);
        

        JButton dol = new JButton("\u2193");
        dol.setFont(new Font("Arial", Font.BOLD, 20));
        dol.addActionListener(e -> ukaz = "s");
        dol.setBounds(120, 435, 60, 60);
        
        JPanel poljeZGumbi = new JPanel(new GridLayout(3, 3, 5, 5));
        poljeZGumbi.setBounds(50, 300, 200, 200);
        poljeZGumbi.add(new JLabel());
        poljeZGumbi.add(gor);
        poljeZGumbi.add(new JLabel());
        poljeZGumbi.add(levo);
        poljeZGumbi.add(new JLabel());
        poljeZGumbi.add(desno);
        poljeZGumbi.add(new JLabel());
        poljeZGumbi.add(dol);
        poljeZGumbi.add(new JLabel());
        okno.add(poljeZGumbi);

        okno.setLayout(null);
        
        veljavenVnos = true;
        while (veljavenVnos) {
            if (ukaz.equals("y")) {
                Scanner rf = new Scanner(data);
                save = rf.nextLine();
                rf.close();
                if (!save.equals("-1")) {
                    String[] saveArray = save.split(";");
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            seznam[i][j] = Integer.parseInt(saveArray[4*i+j]);
                        }
                    }
                }
                else {
                    random = true;
                }
                veljavenVnos = false;
            }
            else if (ukaz.equals("n")) {
                random = true;
                veljavenVnos = false;
            }
            else {
                Thread.sleep(50);
            }
        }
        nalozi.dispose();
        ukaz = "";
        
        if (random) {
            x = rand.nextInt(4);
            y = rand.nextInt(4);
            vred = 2;
            seznam[y][x] = vred;
            loop = true;
            while (loop) {
                x = rand.nextInt(4);
                y = rand.nextInt(4);
                if (seznam[y][x] == 0) {
                    loop = false;
                }
            }
            seznam[y][x] = vred;
        }

        for (int i = 0; i < 4; i++) {
            System.out.println();
            for (int j = 0; j < 4; j++) {
                System.out.print(seznam[i][j] + " \t");
            }
        }
        System.out.println();
        okno.setVisible(true);
        
        while (poteka) {
            System.out.println("bro");
            if (!paused) {
                igralnoPolje.removeAll();
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        JLabel celica = new JLabel(String.valueOf(seznam[i][j]));
                        celica.setVerticalAlignment(JLabel.CENTER);
                        celica.setHorizontalAlignment(JLabel.CENTER);
                        
                        if (seznam[i][j] == 0) {
                            celica.setForeground(COLOR_VALUE_DARK);
                            celica.setBackground(COLOR_EMPTY);
                            celica.setOpaque(true);
                        } else if (seznam[i][j] == 2) {
                            celica.setForeground(COLOR_VALUE_DARK);
                            celica.setBackground(COLOR_2);
                            celica.setOpaque(true);
                        } else if (seznam[i][j] == 4) {
                            celica.setForeground(COLOR_VALUE_DARK);
                            celica.setBackground(COLOR_4);
                            celica.setOpaque(true);
                        } else if (seznam[i][j] == 8) {
                            celica.setForeground(COLOR_VALUE_LIGHT);
                            celica.setBackground(COLOR_8);
                            celica.setOpaque(true);
                        } else if (seznam[i][j] == 16) {
                            celica.setForeground(COLOR_VALUE_LIGHT);
                            celica.setBackground(COLOR_16);
                            celica.setOpaque(true);
                        } else if (seznam[i][j] == 32) {
                            celica.setForeground(COLOR_VALUE_LIGHT);
                            celica.setBackground(COLOR_32);
                            celica.setOpaque(true);
                        } else if (seznam[i][j] == 64) {
                            celica.setForeground(COLOR_VALUE_LIGHT);
                            celica.setBackground(COLOR_64);
                            celica.setOpaque(true);
                        } else if (seznam[i][j] == 128) {
                            celica.setForeground(COLOR_VALUE_LIGHT);
                            celica.setBackground(COLOR_128);
                            celica.setOpaque(true);
                        } else if (seznam[i][j] == 256) {
                            celica.setForeground(COLOR_VALUE_LIGHT);
                            celica.setBackground(COLOR_256);
                            celica.setOpaque(true);
                        } else if (seznam[i][j] == 512) {
                            celica.setForeground(COLOR_VALUE_LIGHT);
                            celica.setBackground(COLOR_512);
                            celica.setOpaque(true);
                        } else if (seznam[i][j] == 1024) {
                            celica.setForeground(COLOR_VALUE_LIGHT);
                            celica.setBackground(COLOR_1024);
                            celica.setOpaque(true);
                        } else if (seznam[i][j] == 2048) {
                            celica.setForeground(COLOR_VALUE_LIGHT);
                            celica.setBackground(COLOR_2048);
                            celica.setOpaque(true);
                        } else{
                            celica.setForeground(COLOR_VALUE_LIGHT);
                            celica.setBackground(COLOR_2048PLUS);
                            celica.setOpaque(true);
                        }

                        igralnoPolje.add(celica);
                    }
                }
                SwingUtilities.updateComponentTreeUI(okno);
                loop = true;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        tempSeznam[i][j] = seznam[i][j];
                    }    
                }
                if (ukaz.equals("w")) {
                    z = 0;
                    while (z < 3) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (seznam[2 - i][j] == 0) {
                                    seznam[2 - i][j] = seznam[3 - i][j];
                                    seznam[3 - i][j] = 0;
                                }
                            }
                        }
                        z++;
                    }
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 4; j++) {
                                if (seznam[i][j] == seznam[i + 1][j]) {
                                    seznam[i][j] = 2 * seznam[i + 1][j];
                                    seznam[i + 1][j] = 0;
                            }
                        }
                    }
                    z = 0;
                    while (z < 3) {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (seznam[2 - i][j] == 0) {
                                    seznam[2 - i][j] = seznam[3 - i][j];
                                    seznam[3 - i][j] = 0;
                                }
                            }
                        }
                        z++;
                    }
                    ukaz = "";
                } else if (ukaz.equals("s")) {
                    z = 0;
                    while (z < 3) {
                        for (int i = 1; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (seznam[i][j] == 0) {
                                    seznam[i][j] = seznam[i - 1][j];
                                    seznam[i - 1][j] = 0;
                                }
                            }
                        }
                        z++;
                    }
                    for (int i = 3; i > 0; i--) {
                        for (int j = 0; j < 4; j++) {
                                if (seznam[i][j] == seznam[i - 1][j]) {
                                    seznam[i][j] = 2 * seznam[i - 1][j];
                                    seznam[i - 1][j] = 0;
                            }
                        }
                    }
                    z = 0;
                    while (z < 3) {
                        for (int i = 1; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (seznam[i][j] == 0) {
                                    seznam[i][j] = seznam[i - 1][j];
                                    seznam[i - 1][j] = 0;
                                }
                            }
                        }
                        z++;
                    }
                    ukaz = "";
                } else if (ukaz.equals("a")) {
                    z = 0;
                    while (z < 3) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (seznam[i][2 - j] == 0) {
                                    seznam[i][2 - j] = seznam[i][3 - j];
                                    seznam[i][3 - j] = 0;
                                }
                            }
                        }
                        z++;
                    }
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 3; j++) {
                                if (seznam[i][j] == seznam[i][j + 1]) {
                                    seznam[i][j] = 2 * seznam[i][j + 1];
                                    seznam[i][j + 1] = 0;
                            }
                        }
                    }
                    z = 0;
                    while (z < 3) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (seznam[i][2 - j] == 0) {
                                    seznam[i][2 - j] = seznam[i][3 - j];
                                    seznam[i][3 - j] = 0;
                                }
                            }
                        }
                        z++;
                    }
                    ukaz = "";
                } else if (ukaz.equals("d")) {
                    z = 0;
                    while (z < 3) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 1; j < 4; j++) {
                                if (seznam[i][j] == 0) {
                                    seznam[i][j] = seznam[i][j - 1];
                                    seznam[i][j - 1] = 0;
                                }
                            }
                        }
                        z++;
                    }
                    for (int i = 0; i < 4; i++) {
                        for (int j = 3; j > 0; j--) {
                                if (seznam[i][j] == seznam[i][j - 1]) {
                                    seznam[i][j] = 2 * seznam[i][j - 1];
                                    seznam[i][j - 1] = 0;
                            }
                        }
                    }
                    z = 0;
                    while (z < 3) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 1; j < 4; j++) {
                                if (seznam[i][j] == 0) {
                                    seznam[i][j] = seznam[i][j - 1];
                                    seznam[i][j - 1] = 0;
                                }
                            }
                        }
                        z++;
                    }
                    ukaz = "";
                } else {
                    while (ukaz.equals("") && !paused) {
                        Thread.sleep(50);
                        System.out.println("test");
                        okno.requestFocus();
                        
                    }
                }
                razlicen = false;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (seznam[i][j] != tempSeznam[i][j]) {
                            razlicen = true;
                        }
                    }
                }
                poteka = false;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (seznam[i][j] == 0) {
                            poteka = true;
                        }
                    }
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (seznam[i][j] == seznam[i + 1][j]) {
                            poteka = true;
                        } else if (seznam[i][j] == seznam[i][j + 1]) {
                            poteka = true;
                        }
                    }
                }

                loop = true;
                while (loop && razlicen) {
                    x = rand.nextInt(4);
                    y = rand.nextInt(4);
                    if (seznam[y][x] == 0) {
                        loop = false;
                    }
                }
                if (razlicen) {
                    seznam[y][x] = vred;
                }
                for (int i = 0; i < 4; i++) {
                    System.out.println();
                    for (int j = 0; j < 4; j++) {
                        System.out.print(seznam[i][j] + "\t");
                    }
                }
                System.out.println();
                System.out.println((razlicen ? "" : "Ni spremembe"));
                System.out.println("Ukaz = " + ukaz);
            }
            else {
                if (ukaz.equals("ends") || ukaz.equals("end")) {
                    
                }
            }
        }
    

        if (data.createNewFile()) {
            System.out.println("File created: " + data.getName());
        } else {
            System.out.println("File already exists.");
        }
        if (ukaz.equals("ends")) {
            FileWriter fw = new FileWriter(data);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    fw.write(seznam[i][j]+";");
                }
            }
            fw.close();
            veljavenVnos = false;
        }
        else if (ukaz.equals("end")) {
            FileWriter fw = new FileWriter(data);
            fw.write("-1");
            fw.close();
            veljavenVnos = false;
        }
        shrani.dispose();
        okno.dispose();
        vnos.close();
    }
      
}

class MyKeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent evt) {
        if (evt.getKeyChar() == 'a') {
            System.out.println("Check for key characters: " + evt.getKeyChar());
        }
        if (evt.getKeyCode() == KeyEvent.VK_HOME) {
            System.out.println("Check for key codes: " + evt.getKeyCode());
        }
    }
}
