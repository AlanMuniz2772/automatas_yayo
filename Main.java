import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        try {
            String sPath = JOptionPane.showInputDialog("Ingrese direccion de archivo de entrada: ");
            compilar(sPath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en main: " + e.getMessage());
        }
        
    }

    public static void compilar(String sPath){
        try{
            //Tabla de tokens
            List<lexema> lLexemas = new ArrayList<lexema>();

            //Proceso
            List<linea> lLineas = leerArchivo(sPath);

            lLexemas = etapaLexica.analisisLexico(lLineas);
            
            etapaSintactica.analisisSintactico(lLexemas);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en compilar(): " + e.getMessage());
        }
    }

    public static List<linea> leerArchivo(String sPath){
        List<linea> lLineas = new ArrayList<linea>();

        try{
            File archivoEntrada = new File(sPath);
            
            if (!archivoEntrada.exists()) {
                JOptionPane.showMessageDialog(null, "Archivo no existe");
                return null;
            }

            BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
            String sLinea;
            int iNumeroLinea = 1;

            while((sLinea = lector.readLine()) != null){
                lLineas.add(new linea(sLinea, iNumeroLinea));
                iNumeroLinea++;
            }

            lector.close();
            return lLineas;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en leer archivo(): " + e.getMessage());
            return null;
        }
    }
}

class linea{
    String sLinea;
    int iNumeroLinea;

    public linea(String sLinea, int iNumeroLinea){
        this.sLinea = sLinea;
        this.iNumeroLinea = iNumeroLinea;
    }
}
