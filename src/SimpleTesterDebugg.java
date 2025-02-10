/*
import java.io.*;
import java_cup.runtime.*;
import mulet_es5tsc.*;

public class SimpleTesterDebugg {

    public static void main(String[] args) {
        try {
            // Suponemos que el código de entrada está en un String
            String input = "// Programma esemplificativo in sintassi Toy3\n" +
                    "\n" +
                    "program\n" +
                    "\n" +
                    "// Dichiarazione variabili globali\n" +
                    "c: 1;\n" +
                    "a | b | x : double;\n" +
                    "taglia | ans1 | ans : string;\n" +
                    "risultato: double;\n" +
                    "\n" +
                    "// Definizione delle funzioni\n" +
                    "def sommac(a: double; d: double; b: double; ref size: string; ref result: double)\n" +
                    "{\n" +
                    "    result := a + b + c + d;\n" +
                    "\n" +
                    "    if (result > 100) then {\n" +
                    "        size := \"grande\";\n" +
                    "    }\n" +
                    "    else {\n" +
                    "        if (result > 50) then {\n" +
                    "            size := \"media\";\n" +
                    "        }\n" +
                    "        else {\n" +
                    "            size := \"piccola\";\n" +
                    "        }\n" +
                    "    }\n" +
                    "}\n" +
                    "\n" +
                    "def stampa(messaggio: string): string\n" +
                    "{\n" +
                    "    i: 0;\n" +
                    "    while (i < 4) do {\n" +
                    "        \"\" !>>; // Ritorno a capo\n" +
                    "        i := i + 1;\n" +
                    "    }\n" +
                    "    messaggio !>>;\n" +
                    "    return \"ok\";\n" +
                    "}\n" +
                    "\n" +
                    "begin\n" +
                    "    a := 1;\n" +
                    "    b := 2.2;\n" +
                    "    x := 3;\n" +
                    "    risultato := 0.0;\n" +
                    "    ans := \"no\";\n" +
                    "\n" +
                    "    sommac(a, x, b, taglia, risultato);\n" +
                    "\n" +
                    "    stampa(\"La somma di \" + a + \" e \" + b + \" incrementata di \" + c + \" è \" + taglia);\n" +
                    "    stampa(\"Ed è pari a \" + risultato);\n" +
                    "\n" +
                    "    \"Vuoi continuare? (si/no) - inserisci due volte la risposta\\n\">>;\n" +
                    "    ans <<;\n" +
                    "    ans1 <<;\n" +
                    "\n" +
                    "    while (ans == \"si\") do {\n" +
                    "        \"Inserisci un intero: \">>;\n" +
                    "        a <<;\n" +
                    "        \"Inserisci un reale: \">>;\n" +
                    "        b <<;\n" +
                    "\n" +
                    "        sommac(a, x, b, taglia, risultato);\n" +
                    "\n" +
                    "        stampa(\"La somma di \" + a + \" e \" + b + \" incrementata di \" + c + \" è \" + taglia);\n" +
                    "        stampa(\"Ed è pari a \" + risultato);\n" +
                    "\n" +
                    "        \"Vuoi continuare? (si/no): \">>;\n" +
                    "        ans <<;\n" +
                    "    }\n" +
                    "\n" +
                    "    \"\" !>>; // Ritorno a capo finale\n" +
                    "    \"Ciao\" !>>;\n" +
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
            parser.debug_parse();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/