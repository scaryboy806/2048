import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        int tempSeznam[][] = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
        int seznam[][] = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
        boolean konec, loop, razlicen, veljavenVnos, random;
        random = false;
        konec = false;
        File data = new File("data.txt");
        Scanner vnos = new Scanner(System.in);
        Random rand = new Random();
        int x, y, z, vred;
        x = 0;
        y = 0;
        vred = 2;
        String save;
        String ukaz = "";
        System.out.println("Zelis naloziti zadnje shranjeno polje? y/n");
        veljavenVnos = true;
        while (veljavenVnos) {
            ukaz = vnos.nextLine();
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
                System.out.println("moznosti sta le y ali n");
            }
        }
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
        while (konec != true) {
            
            ukaz = vnos.nextLine();
            
            if (ukaz.equals("end")) {
                konec = true;
            }
            else {
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
                }
                if (ukaz.equals("s")) {
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
                }
                if (ukaz.equals("a")) {
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
                }
                if (ukaz.equals("d")) {
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
                }
                razlicen = false;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (seznam[i][j] != tempSeznam[i][j]) {
                            razlicen = true;
                        }
                    }
                }
                konec = true;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (seznam[i][j] == 0) {
                            konec = false;
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
            }
        }
        if (data.createNewFile()) {
            System.out.println("File created: " + data.getName());
        } else {
            System.out.println("File already exists.");
        }
        System.out.println("Zelis shraniti trenutno polje? (drugace bo shranjeno prazno polje) y/n");
        ukaz = vnos.nextLine();
        veljavenVnos = true;
        while (veljavenVnos)
            if (ukaz.equals("y")) {
                FileWriter fw = new FileWriter(data);
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        fw.write(seznam[i][j]+";");
                    }
                }
                fw.close();
                veljavenVnos = false;
            }
            else if (ukaz.equals("n")) {
                FileWriter fw = new FileWriter(data);
                fw.write("-1");
                fw.close();
                veljavenVnos = false;
            }
            else {
                System.out.println("Mozna vnosa sta le y in n");
            }
        vnos.close();
    }
}
