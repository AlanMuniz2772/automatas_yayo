import java.util.HashMap;
import java.util.List;

public class etapaLexica {
    public static HashMap<String, Integer> tablaSimbolos = new HashMap<>();

    static {
        tablaSimbolos.put("program", -1);
        tablaSimbolos.put("begin", -2);
        tablaSimbolos.put("end", -3);
        tablaSimbolos.put("read", -4);
        tablaSimbolos.put("write", -5);
        tablaSimbolos.put("if", -6);
        tablaSimbolos.put("else", -7);
        tablaSimbolos.put("while", -8);
        tablaSimbolos.put("repeat", -9);
        tablaSimbolos.put("until", -10);
        tablaSimbolos.put("int", -11);
        tablaSimbolos.put("real", -12);
        tablaSimbolos.put("string", -13);
        tablaSimbolos.put("bool", -14);
        tablaSimbolos.put("var", -15);
        tablaSimbolos.put("then", -16);
        tablaSimbolos.put("do", -17);
        tablaSimbolos.put("*", -21);
        tablaSimbolos.put("/", -22);
        tablaSimbolos.put("+", -24);
        tablaSimbolos.put("-", -25);
        tablaSimbolos.put(":=", -26);
        tablaSimbolos.put("<", -31);
        tablaSimbolos.put("<=", -32);
        tablaSimbolos.put(">", -33);
        tablaSimbolos.put(">=", -34);
        tablaSimbolos.put("==", -35);
        tablaSimbolos.put("!=", -36);
        tablaSimbolos.put("&&", -41);
        tablaSimbolos.put("||", -42);
        tablaSimbolos.put("!", -43);
        tablaSimbolos.put("iEntero", -51);
        tablaSimbolos.put("iReal", -52);
        tablaSimbolos.put("iCadena", -53);
        tablaSimbolos.put("iLogico", -54);
        tablaSimbolos.put("iGral", -55);
        tablaSimbolos.put("cEntero", -61);
        tablaSimbolos.put("cReal", -62);
        tablaSimbolos.put("cCadena", -63);
        tablaSimbolos.put("cTrue", -64);
        tablaSimbolos.put("cFalse", -65);
        tablaSimbolos.put("(", -73);
        tablaSimbolos.put(")", -74);
        tablaSimbolos.put(";", -75);
        tablaSimbolos.put(",", -76);
        tablaSimbolos.put(":", -77);
    }
    
    public static List<lexema> analisisLexico(List<linea> lLineas){
        
        return null;
    }
}

class lexema{
    String cadena;
    int token;
    int posicionTabla;
    int numLinea; 

    lexema(String cadena, int numLinea) {
        this.cadena = cadena;
        this.numLinea = numLinea;
    }

    public lexema(String cadena, int token, int posicionTabla, int numLinea) {
        this.cadena = cadena;
        this.token = token;
        this.posicionTabla = posicionTabla;
        this.numLinea = numLinea;
    }
    
    @Override
    public String toString() {
        return (this.cadena+", "+this.token+", "+this.posicionTabla+", "+this.numLinea);
    }
}