/*
import java.io.*;
import java_cup.runtime.*;
import mulet_es5tsc.*;
import visitor.XMLVisitor;
import visitor.SemanticVisitor;
import ast.ASTNode;

public class SimpleTesterDebugg {

    public static void main(String[] args) {
        try {
            // Código de entrada
            String input = "/* Esempio base del linguaggio Toy3 \n" +
                    "\n" +
                    "program\n" +
                    "\n" +
                    " sommagrande | sommapiccola: double;\n" +
                    " i:0;\n" +
                    " x | y | risultato : double;\n" +
                    " grande | nonusata: bool;\n" +
                    "\n" +
                    " def moltiplicazione( x: double; y: double; ref res : double; ref  grande: bool)\n" +
                    " {\n" +
                    "\trisultato= x*y | nonusata : double;\n" +
                    "\n" +
                    "\tif ((x*y) >= 100) then {\n" +
                    "\t\tgrande := true;\n" +
                    "\t}\n" +
                    "\telse{\n" +
                    "\t\tgrande := false;\n" +
                    "\t}\n" +
                    "\tres := risultato;                   // commento di prova\n" +
                    " }\n" +
                    "\n" +
                    " def saluto(): string\n" +
                    " {\n" +
                    "\treturn \"ciao\";\n" +
                    " }\n" +
                    "\n" +
                    "\n" +
                    "begin\n" +
                    "\n" +
                    "sommagrande | sommapiccola := 0, 0;\n" +
                    "\n" +
                    "\"Questo programma permette di svolgere una serie di moltiplicazioni\" !>>;\n" +
                    "\"sommando i risultati < 100 in sommagrande e quelli < 100 in sommapiccola\" !>>;\n" +
                    "\n" +
                    "i := -1;\n" +
                    "while (i <= 0) do{\n" +
                    "\tsaluto : \"ciao\";\n" +
                    "\t\"Quante moltiplicazioni vuoi svolgere? (inserire intero > 0)\">>;\n" +
                    "\ti <<;\n" +
                    "\tsaluto !>>;\n" +
                    "}\n" +
                    "\n" +
                    "while (i > 0) do{\n" +
                    "\n" +
                    "\tx := -1;\n" +
                    "\ty := -1;\n" +
                    "\n" +
                    "\twhile (not(x > 0 and y >0)) do\n" +
                    "\t{\n" +
                    "\t\tsaluto :\"byebye\";\n" +
                    "\t\t\"Moltiplicazione \" , i , \": inserisci due numeri positivi\" !>>;\n" +
                    "\t\tx | y <<;\n" +
                    "\t\tsaluto !>>;\n" +
                    "\t}\n" +
                    "\n" +
                    "\tmoltiplicazione(x, y , risultato, grande);\n" +
                    "\trisultato !>>;\n" +
                    "\n" +
                    "\tif(grande) then{\n" +
                    "\t\t\"il risultato e grande\" !>>;\n" +
                    "\t\tsommagrande := sommagrande + risultato;\n" +
                    "\t}else{\n" +
                    "\t\t\"il risultato e piccolo\" !>>;\n" +
                    "\t\tsommapiccola := sommapiccola + risultato;\n" +
                    "\t}\n" +
                    "\ti := i-1;\n" +
                    "}\n" +
                    "\t\"\\n sommagrande e \", sommagrande !>>;\n" +
                    "\t\"sommapiccola e \", sommapiccola !>>;\n" +
                    "\n" +
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

            // Aplicar el análisis semántico
            System.out.println("\nSemantic Analysis:");
            SemanticVisitor semanticVisitor = new SemanticVisitor();
            ast.accept(semanticVisitor);

            if (semanticVisitor.hasErrors()) {
                System.err.println("Semantic analysis failed with errors:");
                return;
            } else {
                System.out.println("Semantic analysis completed successfully.");
            }

            // Crear un escritor para imprimir el XML en pantalla
            StringWriter stringWriter = new StringWriter();
            PrintWriter xmlWriter = new PrintWriter(stringWriter);

            // Aplicar el visitante para generar XML
            XMLVisitor visitor = new XMLVisitor(xmlWriter);
            ast.accept(visitor);

            // Imprimir el XML generado en consola
            xmlWriter.flush();
            System.out.println("\nGenerated XML:\n" + stringWriter.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
