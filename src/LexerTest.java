/*
import java_cup.runtime.Symbol;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import mulet_es5tsc.sym;

public class LexerTest {

    public static void main(String[] args) {
        try {
            // Crear el lexer con un ejemplo de entrada
            String input = "program myProgram begin int x := 10; end";

            // Usar StringReader para la entrada de texto
            Lexer lexer = new Lexer(new StringReader(input));

            Symbol token;

            // Leer y mostrar los tokens
            while ((token = lexer.next_token()).sym != sym.EOF) {
                System.out.println("Token: " + sym.terminalNames[token.sym] + " | Value: " + token.value);
            }

        } catch (IOException e) {
            System.err.println("Error al leer la entrada: " + e.getMessage());
        }
    }
}
*/