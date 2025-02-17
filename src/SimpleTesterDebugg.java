/*

import java.io.*;
import java_cup.runtime.*;
import mulet_es5tsc.*;
import visitor.XMLVisitor;
import ast.ASTNode;

public class SimpleTesterDebugg {

    public static void main(String[] args) {
        try {
            // Código de entrada
            String input = "program\n" +
                    "\n" +
                    "x | y = 10 : int;\n" +
                    "\n" +
                    "begin\n" +
                    "x | y << ;\n" +
                    "if (x > y) then {\n" +
                    "\"I valori di x e y sono:\", x, \" \", y !>> ;\n" +
                    "}\n" +
                    "end";

            // Crear el lexer
            Lexer lexer = new Lexer(new StringReader(input));

            // Crear el parser con el lexer como entrada
            parser parser = new parser(lexer);

            // Debug del Lexer
            System.out.println("Tokens:");
            Symbol symbol;
            while ((symbol = lexer.next_token()) != null && symbol.sym != sym.EOF) {
                System.out.println("Token: " + symbol.toString() + ", Value: " + symbol.value);
            }

            // Resetear el lexer para el análisis sintáctico
            lexer.yyreset(new StringReader(input));

            // Debug del Parser
            System.out.println("\nParsing:");
            Symbol sym = parser.debug_parse();
            ASTNode ast = (ASTNode) sym.value;



            // Comprobar si el AST es válido
            if (ast == null) {
                System.err.println("Error: AST is null. Check your parser.");
                return;
            }


            // Crear un escritor para imprimir el XML en pantalla
            StringWriter stringWriter = new StringWriter();
            PrintWriter xmlWriter = new PrintWriter(stringWriter);

            // Aplicar el visitante para generar XML
            XMLVisitor visitor = new XMLVisitor(xmlWriter);
            ((ASTNode) ast).accept(visitor);

            // Imprimir el XML generado en consola
            xmlWriter.flush();
            System.out.println("\nGenerated XML:\n" + stringWriter.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/