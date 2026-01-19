package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FicheroUtil {

    public static List<String> leerFichero(String ruta) {

        List<String> lineas = new ArrayList<>();

        try {
            File f = new File(ruta);
            if (!f.exists()) {
                return lineas;
            }

            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;

            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineas;
    }

    public static void escribirFichero(String ruta, List<String> lineas) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));

            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
