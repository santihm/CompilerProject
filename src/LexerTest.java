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
            String input = "program\n" +
                    "def incrementa(n: int): int{\n" +
                    "    n := n + 1;\n" +
                    "    return n;\n" +
                    "}\n" +
                    "\n" +
                    "begin\n" +
                    "    y : 4;\n" +
                    "    x : int;\n" +
                    "    x := map(ADD, incrementa, 1, y, 4);\n" +
                    "end";

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