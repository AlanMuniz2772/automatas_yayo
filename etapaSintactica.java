import java.util.List;
import javax.swing.JOptionPane;

public class etapaSintactica {
    private static List<lexema> lLexemas;
    private static int posicionActual;

    public static void analisisSintactico(List<lexema> lexemas) {
        lLexemas = lexemas;
        posicionActual = 0;

        try {
            if (programa()) {
                System.out.println("Análisis Sintáctico: La sintaxis del programa es correcta.");
            } else {
                System.out.println("Análisis Sintáctico: Error de sintaxis en el programa.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en analisisSintactico(): " + e.getMessage());
        }
    }

    private static boolean programa() {
        return comprobarToken(-1) &&  // 'program'
               comprobarToken(-55) &&  // ID
               comprobarToken(-75) &&  // ';'
               comprobarToken(-15) &&  // 'var'
               declaraciones() &&
               comprobarToken(-2) &&  // 'begin'
               sentencias() &&
               comprobarToken(-3);  // 'end'
    }

    private static boolean declaraciones() {
        while (tipo()) {
            if (!lista_ids() || !comprobarToken(-75)) {  // ';'
                return false;
            }
        }
        return true;  // ε
    }

    private static boolean tipo() {
        return comprobarToken(-11) ||  // 'int'
               comprobarToken(-12) ||  // 'real'
               comprobarToken(-13) ||  // 'string'
               comprobarToken(-14);    // 'bool'
    }

    private static boolean lista_ids() {
        if (!comprobarToken(-55)) {  // ID
            return false;
        }
        while (comprobarToken(-76)) {  // ','
            if (!comprobarToken(-55)) {  // ID
                return false;
            }
        }
        return true;
    }

    private static boolean sentencias() {
        while (sentencia()) {
            if (!comprobarToken(-75)) {  // ';'
                return false;
            }
        }
        return true;  // ε
    }

    private static boolean sentencia() {
        return asignacion() || estructura_control() || IO();
    }

    private static boolean asignacion() {
        int pos = posicionActual;
        if (comprobarToken(-55) && comprobarToken(-26) && expresion()) {
            return true;
        }
        posicionActual = pos;
        return false;
    }

    private static boolean estructura_control() {
        return if_condicional() || while_loop() || repeat_until();
    }

    private static boolean if_condicional() {
        int pos = posicionActual;
        if (comprobarToken(-6) && comprobarToken(-73) && expresion() && comprobarToken(-74) &&
            comprobarToken(-16) && comprobarToken(-2) && sentencias() && comprobarToken(-3) &&
            else_part()) {
            return true;
        }
        posicionActual = pos;
        return false;
    }

    private static boolean else_part() {
        int pos = posicionActual;
        if (comprobarToken(-7) && comprobarToken(-2) && sentencias() && comprobarToken(-3)) {
            return true;
        }
        posicionActual = pos;
        return true;  // ε
    }

    private static boolean while_loop() {
        int pos = posicionActual;
        if (comprobarToken(-8) && comprobarToken(-73) && expresion() && comprobarToken(-74) &&
            comprobarToken(-17) && comprobarToken(-2) && sentencias() && comprobarToken(-3)) {
            return true;
        }
        posicionActual = pos;
        return false;
    }

    private static boolean repeat_until() {
        int pos = posicionActual;
        if (comprobarToken(-9) && comprobarToken(-2) && sentencias() && comprobarToken(-3) &&
            comprobarToken(-10) && comprobarToken(-73) && expresion() && comprobarToken(-74)) {
            return true;
        }
        posicionActual = pos;
        return false;
    }

    private static boolean IO() {
        int pos = posicionActual;
        if (comprobarToken(-5) && comprobarToken(-73) && comprobarToken(-55) && comprobarToken(-74)) {
            return true;
        }
        posicionActual = pos;
        if (comprobarToken(-4) && comprobarToken(-73) && comprobarToken(-55) && comprobarToken(-74)) {
            return true;
        }
        posicionActual = pos;
        return false;
    }

    private static boolean expresion() {
        if (!termino()) {
            return false;
        }
        while (operador_arit()) {
            if (!termino()) {
                return false;
            }
        }
        return true;
    }

    private static boolean termino() {
        if (!factor()) {
            return false;
        }
        while (operador_arit()) {
            if (!factor()) {
                return false;
            }
        }
        return true;
    }

    private static boolean factor() {
        int pos = posicionActual;
        if (comprobarToken(-55)) {
            return true;
        }
        posicionActual = pos;
        if (comprobarToken(-61) || comprobarToken(-62)) {
            return true;
        }
        posicionActual = pos;
        if (comprobarToken(-73) && expresion() && comprobarToken(-74)) {
            return true;
        }
        posicionActual = pos;
        return false;
    }

    private static boolean operador_arit() {
        return comprobarToken(-24) || comprobarToken(-25) || comprobarToken(-21) || comprobarToken(-22);
    }

    private static boolean comprobarToken(int tokenEsperado) {
        if (posicionActual < lLexemas.size() && lLexemas.get(posicionActual).token == tokenEsperado) {
            posicionActual++;
            return true;
        }
        return false;
    }
}

