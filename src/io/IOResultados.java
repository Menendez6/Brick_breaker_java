package io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IOResultados {
    File fichero;
    public IOResultados(){
        fichero = new File("./resources/players.obj");
    }

    public HashMap leerResultado() {
        HashMap<String,Integer> players = null;
        try {
            ObjectInputStream ios = new ObjectInputStream(new FileInputStream(fichero));
            try {
                while (true) {
                    players = (HashMap<String, Integer>) ios.readObject();
                    System.out.println(players);
                }
            }catch (EOFException eofException)
            {
                ios.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return players;
    }

    public void escribirResultado(HashMap<String,Integer> players){
        try {
            FileOutputStream fos = new FileOutputStream(fichero);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(players);

            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

