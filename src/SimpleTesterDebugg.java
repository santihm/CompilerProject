import java.io.*;
import java_cup.runtime.*;
import mulet_es5tsc.*;

public class SimpleTesterDebugg {

    public static void main(String[] args) {
        try {
            // Suponemos que el código de entrada está en un String
            String input = "program myProgram begin int x := 10 ; end";  // Tu código de entrada aquí

            // Crear el lexer
            Lexer lexer = new Lexer(new StringReader(input));

            // Crear el parser con el lexer como entrada
            parser parser = new parser(lexer);

            // Debug del Lexer
            System.out.println("Tokens:");
            Symbol symbol;
            while ((symbol = lexer.next_token()) != null && symbol.sym != sym.EOF) {
                // Aquí imprimimos el token. `sym` es el número del token, y `value` es su valor.
                System.out.println("Token: " + symbol.toString() + ", Value: " + symbol.value);
            }

            // Resetear el lexer para el análisis sintáctico
            lexer.yyreset(new StringReader(input));

            // Debug del Parser
            System.out.println("\nParsing:");
            parser.debug_parse();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
