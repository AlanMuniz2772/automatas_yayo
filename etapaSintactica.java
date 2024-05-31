import java.util.List;

import javax.swing.JOptionPane;

public class etapaSintactica {
    public static void analisisSintactico(List<lexema> lLexemas){
        try{
            System.out.println("Analisis Sintactico");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en analisisSintactico(): " + e.getMessage());
        }
    }
}
