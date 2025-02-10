import java.io.*;

import mulet_es5tsc.parser;

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

        // Obtener solo el nombre del archivo sin la ruta
        String fileName = inputFile.getName().replace(".txt", ".c");

        // Definir la ruta de salida en test_files/c_out/
        File outputDir = new File("test_files" + File.separator + "c_out");
        if (!outputDir.exists()) {
            outputDir.mkdirs(); // Crear el directorio si no existe
        }

        File outputFile = new File(outputDir, fileName);

        try {
            FileReader fileReader = new FileReader(inputFile);
            PrintStream originalOut = System.out; // Guardar la salida estándar
            PrintStream fileOut = new PrintStream(new FileOutputStream(outputFile));

            System.setOut(fileOut); // Redirigir salida estándar al archivo

            Lexer lexer = new Lexer(fileReader);
            parser p = new parser(lexer); // Parser de CUP generado

            p.parse(); // Ejecutar el análisis sintáctico

            System.setOut(originalOut); // Restaurar la salida estándar
            fileReader.close();
            fileOut.close();

            System.out.println("Parsing completed successfully. Output saved to: " + outputFile.getPath());
        } catch (Exception e) {
            System.err.println("Parsing failed: " + e.getMessage());
        }
    }
}