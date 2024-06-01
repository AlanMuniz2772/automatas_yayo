import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        tablaSimbolos.put("comentario", -78);
    }
    
    public static List<lexema> analisisLexico(List<linea> lLineas){
        List<lexema> lLexemas = new ArrayList<lexema>();
        
        for (linea line : lLineas){
            lLexemas.addAll(analizarTokens(line));
        }
            
        return lLexemas;
    }

    public static List<lexema> analizarTokens (linea line) {
        List<lexema> lexemasFinales = new ArrayList<lexema>();

        List<lexema> lexemas = quitarComentarioString(line);

        for(lexema lex : lexemas){
            //comentario o string
            if(lex.token == -78 || lex.token == -63){
                //si es String se agrega, si es comentario se ignora
                if(lex.token == -63){
                    lexemasFinales.add(lex);
                }
                continue;
            }

            String cadena = lex.cadena;
            Pattern pattern = Pattern.compile(":=|<=|>=|==|!=|\\|\\||\\||-?\\d+\\.\\d*|[-+*;,<>():!]|\\d+!|\\b[a-zA-Z\\d_]+\\b[#%&$?]*|\\.[^ \\t\\n\\r\\f\\v]+");
            Matcher matcher = pattern.matcher(cadena);

            while (matcher.find()) {
                lexemasFinales.add(new lexema(matcher.group().trim(), line.iNumeroLinea));            
            }
        }

        for (lexema lex : lexemasFinales) {
            //si no es string
            if(lex.token != -63){
                int valorToken = getValorToken(lex.cadena);
                int posicionTabla = -1;
                
                //Los identificadores tiene valor de -2
                if (valorToken == -51 || valorToken == -52 || valorToken == -53 || valorToken == -54 || valorToken == -55) {
                    posicionTabla = -2;
                }
                
                lex.token = valorToken;
                
                lex.posicionTabla = posicionTabla;
            }
            
        }
        
        return lexemasFinales;
    }

    

    public static boolean esNumEntero(String cadena) {
        return cadena.matches("\\d+");
    }


    public static boolean esNumReal(String cadena) {
        return cadena.matches("\\d+\\.\\d+");
    }

    public static int getValorToken(String token) {
        // if (tablaSimbolos.containsKey(token)) {
        //     return tablaSimbolos.get(token);
        // } else if (esNumEntero(token)) {
        //     return -61;
        // } else if (esNumReal(token)) {
        //     return -62;
        // } else if (token.matches("\".*\"")) {
        //     return -63;
        // } else if (token.matches("[a-zA-Z]+[a-zA-Z0-9_]*")) {
        //     return -55;
        // } else {
        //     return -99;
        // }
        return 0;
    }
    
    public static List<lexema> quitarComentarioString(linea line){
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