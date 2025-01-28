/*import java.io.*;
import mulet_es5tsc.parser;
import mulet_es5tsc.sym;
import java.io.*;
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Toy3 Tester - Choose mode:");
        System.out.println("1 - Interactive Mode");
        System.out.println("2 - Run Test Files");
        System.out.print("Option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        if (choice == 1) {
            runInteractiveMode(scanner);
        } else if (choice == 2) {
            System.out.print("Enter test number (1-4): ");
            int testNumber = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (testNumber >= 1 && testNumber <= 4) {
                runTest(testNumber);
            } else {
                System.out.println("Invalid test number.");
            }
        } else {
            System.out.println("Invalid option.");
        }

        scanner.close();
    }

    private static void runInteractiveMode(Scanner scanner) {
        System.out.println("Interactive Mode - Type your code (type 'exit' to quit):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                runLexerAndParser(new StringReader(input));
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void runTest(int testNumber) {
        String testPath = "../mulet_es5tsc/test/valid" + testNumber + "/valid" + testNumber;
        File inputFile = new File(testPath + "_in.txt");
        File expectedOutputFile = new File(testPath + "_out.txt");

        if (!inputFile.exists() || !expectedOutputFile.exists()) {
            System.err.println("Missing test files for test " + testNumber);
            return;
        }

        try {
            // Leer archivo de entrada
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            StringBuilder input = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                input.append(line).append("\n");
            }
            bufferedReader.close();

            // Leer archivo de salida esperada
            BufferedReader expectedReader = new BufferedReader(new FileReader(expectedOutputFile));
            StringBuilder expectedOutput = new StringBuilder();
            while ((line = expectedReader.readLine()) != null) {
                expectedOutput.append(line).append("\n");
            }
            expectedReader.close();

            // Ejecutar el lexer y parser
            StringReader stringReader = new StringReader(input.toString());
            StringWriter outputWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(outputWriter);

            try {
                runLexerAndParser(stringReader);
                printWriter.println("Parsing successful!");
            } catch (Exception e) {
                printWriter.println("Syntax error: " + e.getMessage());
            }

            // Comparar salida con el archivo esperado
            String result = outputWriter.toString();
            if (result.equals(expectedOutput.toString())) {
                System.out.println("[✔] Test " + testNumber + " passed!");
            } else {
                System.out.println("[✘] Test " + testNumber + " failed!");
                System.out.println("Expected:\n" + expectedOutput);
                System.out.println("Got:\n" + result);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
        }
    }

    private static void runLexerAndParser(Reader input) throws Exception {
        Lexer lexer = new Lexer(input);
        parser p = new parser(lexer); // Cambio aquí: 'parser' en minúscula

        try {
            p.parse();
            System.out.println("Parsing successful!");
        } catch (Exception e) {
            System.err.println("Syntax error: " + e.getMessage());
        }
    }
}*/
