package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FicheroUtil {

    public static <T> List<T> leerFichero(String ruta) {
        List<T> datos = new ArrayList<>();

        try {
            File f = new File(ruta);
            if (!f.exists()) {
                return datos;
            }

            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(f));
            datos = (List<T>) ois.readObject();
            ois.close();

        } catch (Exception e) {
            datos = new ArrayList<>();
        }

        return datos;
    }

    public static <T> void escribirFichero(String ruta, List<T> datos) {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(ruta));
            oos.writeObject(datos);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
