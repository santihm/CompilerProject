
import java.io.*;
import mulet_es5tsc.parser;
import java_cup.runtime.*;
import visitor.*;
import ast.*;

public class Tester {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Tester <input_file.txt>");
            System.exit(1);
        }

        String inputFilePath = args[0];

        // Validar que el archivo tiene extensión .txt
        if (!inputFilePath.endsWith(".txt")) {
            System.err.println("Error: The input file must have a .txt extension.");
            System.exit(1);
        }

        File inputFile = new File(inputFilePath);
        if (!inputFile.exists()) {
            System.err.println("Error: Input file does not exist.");
            System.exit(1);
        }

        // Obtener solo el nombre del archivo sin la ruta y definir salida de AST
        String fileName = inputFile.getName().replace(".txt", ".xml");

        // Definir la ruta de salida en test_files/xml_out/
        File outputDir = new File("test_files" + File.separator + "xml_out");
        if (!outputDir.exists()) {
            outputDir.mkdirs(); // Crear el directorio si no existe
        }

        File outputFile = new File(outputDir, fileName);

        try {
            // Preparar los flujos de entrada y salida
            FileReader fileReader = new FileReader(inputFile);
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));
            StringWriter stringWriter = new StringWriter();
            PrintWriter consoleWriter = new PrintWriter(stringWriter);

            Lexer lexer = new Lexer(fileReader);
            parser p = new parser(lexer); // Parser generado por CUP

            // Ejecutar el análisis sintáctico y generar el AST
            Symbol sym = p.debug_parse();
            ASTNode ast = (ASTNode) sym.value;
            if (ast == null) {
                System.err.println("Error: AST is null. Check your parser.");
                System.exit(1);
            }

            // Fase de análisis semántico
            SemanticVisitor semanticVisitor = new SemanticVisitor();
            ast.accept(semanticVisitor);

            // Si el análisis semántico detecta errores, abortar la ejecución
            if (semanticVisitor.hasErrors()) {
                System.err.println("Semantic analysis failed due to errors.");
                System.exit(1);
            }

            // Crear el visitante XMLVisitor
            XMLVisitor visitor = new XMLVisitor(writer); // Escribe en el archivo
            XMLVisitor consoleVisitor = new XMLVisitor(consoleWriter); // Para imprimir en consola

            // Procesar el AST y generar el XML
            ast.accept(visitor);  // Escribir en el archivo XML
            ast.accept(consoleVisitor); // Imprimir en consola

            // Asegúrate de que el contenido XML está completo y correcto
            writer.close();
            consoleWriter.close();
            fileReader.close();

            // Imprimir XML en consola
            System.out.println("Generated XML:\n" + stringWriter.toString());

            // Imprimir ruta de salida
            System.out.println("Parsing and semantic analysis completed successfully. XML AST output saved to: " + outputFile.getPath());
        } catch (Exception e) {
            System.err.println("Parsing failed: " + e.getMessage());
        }
    }
}
