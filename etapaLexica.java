import java.util.List;

public class etapaLexica {
    public static List<lexema> analisisLexico(List<linea> lLineas){
        return null;
    }
}

class lexema{
    String valorCadena;
    int token;
    int posicionTabla;
    int numLinea; 

    lexema(String valorCadena, int numLinea) {
        this.valorCadena = valorCadena;
        this.numLinea = numLinea;
    }

    public lexema(String valorCadena, int token, int posicionTabla, int numLinea) {
        this.valorCadena = valorCadena;
        this.token = token;
        this.posicionTabla = posicionTabla;
        this.numLinea = numLinea;
    }
    
    @Override
    public String toString() {
        return (this.valorCadena+", "+this.token+", "+this.posicionTabla+", "+this.numLinea);
    }
}