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
                    "// Variable Declarations\n" +
                    "\n" +
                    "\n" +
                    "def scoping(n: int; m: int; ref message: string)\n" +
                    "{\n" +
                    "    message := \"level 1\";\n" +
                    "\n" +
                    "    if (n <= 1) then {\n" +
                    "        message := \"level 2.1\";\n" +
                    "\n" +
                    "        if (m <= 1) then {\n" +
                    "            message := \"level 3.1\";\n" +
                    "            message !>>;\n" +
                    "        }\n" +
                    "        else {\n" +
                    "            if ((m > 1) and (m < 5)) then {\n" +
                    "                message := \"level 3.2\";\n" +
                    "                message !>>;\n" +
                    "            }\n" +
                    "            else {\n" +
                    "                message := \"level 3.3\";\n" +
                    "                message !>>;\n" +
                    "            }\n" +
                    "        }\n" +
                    "        message !>>;\n" +
                    "    }\n" +
                    "    else {\n" +
                    "        message := \"level 2.2\";\n" +
                    "\n" +
                    "        if (m <= 1) then {\n" +
                    "            message := \"level 3.4\";\n" +
                    "            message !>>;\n" +
                    "        }\n" +
                    "        else {\n" +
                    "            if ((m > 1) and (m < 5)) then {\n" +
                    "                message := \"level 3.5\";\n" +
                    "                message !>>;\n" +
                    "            }\n" +
                    "            else {\n" +
                    "                message := \"level 3.6\";\n" +
                    "                message !>>;\n" +
                    "            }\n" +
                    "        }\n" +
                    "        message !>>;\n" +
                    "    }\n" +
                    "    // message !>>;\n" +
                    "}\n" +
                    "\n" +
                    "def glob(): int\n" +
                    "{\n" +
                    "    return 100;\n" +
                    "}\n" +
                    "\n" +
                    "message : \"level 0\";\n" +
                    "n | m | k : int;\n" +
                    "\n" +
                    "begin\n" +
                    "k := 6;\n" +
                    "\n" +
                    "while (k >= 1) do {\n" +
                    "    \"Inserisci n: \">>;\n" +
                    "    n <<;\n" +
                    "    \"Inserisci m: \">>;\n" +
                    "    m <<;\n" +
                    "\n" +
                    "    \"I valori inseriti sono \", n, \" e \", m !>>;\n" +
                    "\n" +
                    "    scoping(n, m, message);\n" +
                    "\n" +
                    "    k := k - 1;\n" +
                    "}\n" +
                    "\n" +
                    "message !>>;\n" +
                    "glob() !>>;\n" +
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