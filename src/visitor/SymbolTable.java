package visitor;

import java.util.HashMap;
import java.util.Map;

// Clase que representa una entrada en la tabla de símbolos
class SymbolTableEntry {
    String name;    // Nombre del identificador
    String type;    // Tipo del identificador
    String category; // Categoría (puede ser "variable", "función", "parámetro", etc.)

    // Constructor
    public SymbolTableEntry(String name, String type, String category) {
        this.name = name;
        this.type = type;
        this.category = category;
    }

    // Getters y setters (si es necesario)
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "SymbolTableEntry{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

// Clase que representa una tabla de símbolos
class SymbolTable {
    // Mapa que almacena los identificadores (como claves) y sus entradas (como valores)
    private Map<String, SymbolTableEntry> table;

    // Constructor
    public SymbolTable() {
        this.table = new HashMap<>();
    }

    // Constructor que copia otra tabla de símbolos (para el scope)
    public SymbolTable(SymbolTable parent) {
        this.table = new HashMap<>();
        if (parent != null) {
            // Copiar las entradas de la tabla de símbolos anterior (si es un nuevo scope)
            for (Map.Entry<String, SymbolTableEntry> entry : parent.table.entrySet()) {
                this.table.put(entry.getKey(), entry.getValue());
            }
        }
    }

    // Añadir una entrada a la tabla de símbolos
    public void add(String name, String type, String category) {
        SymbolTableEntry entry = new SymbolTableEntry(name, type, category);
        table.put(name, entry);
    }

    // Verificar si un identificador existe en la tabla
    public boolean contains(String name) {
        return table.containsKey(name);
    }

    // Obtener la entrada de un identificador
    public SymbolTableEntry get(String name) {
        return table.get(name);
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                "table=" + table +
                '}';
    }
}
