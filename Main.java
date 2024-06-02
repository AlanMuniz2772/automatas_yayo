import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        try {
            String sPath = JOptionPane.showInputDialog("Ingrese direccion de archivo de entrada: ");
            if (sPath != null && !sPath.trim().isEmpty()) {
                compilar(sPath);
            } else {
                JOptionPane.showMessageDialog(null, "No se proporcionó una dirección de archivo válida");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en main: " + e.getMessage());
        }
        
    }

    public static void compilar(String sPath){
        try{
            //Tabla de tokens
            List<lexema> lLexemas = new ArrayList<lexema>();
            List<linea> lLineas = new ArrayList<linea>();

            //Proceso
            lLineas = leerArchivo(sPath);

            lLexemas = etapaLexica.analisisLexico(lLineas);
            
            etapaSintactica.analisisSintactico(lLexemas);

            for (lexema lex : lLexemas) {
                System.out.println(lex);
            }

            //Generar archivo de salida
            generarArchivoSalida(lLexemas);


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

    public static void generarArchivoSalida(List<lexema> lLexemas) {
        try (FileWriter writer = new FileWriter("salida.txt")) {
            writer.write("Tokens válidos:\n");
            for(lexema lex : lLexemas) {
                if(lex.token != -99) {
                    writer.write(lex + "\n");
                }
            }

            writer.write("\nTokens no válidos:\n");
            for(lexema lex : lLexemas) {
                if(lex.token == -99) {
                    writer.write(lex + "\n");
                }
            }

            writer.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar archivo de salida: " + e.getMessage());
        }
    }

}

//class linea{
    //String sLinea;
    //int iNumeroLinea;

    //public linea(String sLinea, int iNumeroLinea){
    //    this.sLinea = sLinea;
    //    this.iNumeroLinea = iNumeroLinea;
   // }
//}
