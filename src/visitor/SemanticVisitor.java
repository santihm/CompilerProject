package visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import ast.*;
import java.io.*;

public class SemanticVisitor implements ASTVisitor {

    private Stack<SymbolTable> symbolTableStack;
    private boolean error = false;

    // Constructor
    public SemanticVisitor() {
        this.symbolTableStack = new Stack<>();
    }

    // Método auxiliar para obtener la tabla de símbolos actual
    private SymbolTable getCurrentSymbolTable() {
        return symbolTableStack.peek();
    }

    // Método auxiliar para gestionar el cambio de ámbito (push a la pila)
    private void enterNewScope() {
        SymbolTable newTable = new SymbolTable();
        symbolTableStack.push(newTable);
    }

    // Método auxiliar para gestionar el final del ámbito (pop de la pila)
    private void exitCurrentScope() {
        symbolTableStack.pop();
    }

    public SymbolTableEntry findSymbol(String name) {
        for (int i = symbolTableStack.size() - 1; i >= 0; i--) {
            SymbolTable table = symbolTableStack.get(i);
            if (table.contains(name)) {
                return table.get(name);
            }
        }
        return null; // Si no se encuentra
    }

    private String InferType(String left, String right) {
        // Normalizar constantes a sus tipos base
        left = NormalizeConstantType(left);
        right = NormalizeConstantType(right);

        if (left.equals(right)) {
            return left; // Si ambos operandos son del mismo tipo, el resultado es de ese tipo
        }

        if ((left.equals("int") && right.equals("double")) || (left.equals("double") && right.equals("int"))) {
            return "double"; // Promoción a double si hay una combinación de int y double
        }

        if ((left.equals("char") && right.equals("string")) || (left.equals("string") && right.equals("char"))) {
            return "string"; // Concatenación de string y char da como resultado string
        }

        if (left.equals("bool") || right.equals("bool")) {
            return "error"; // No se permiten operaciones aritméticas con booleanos
        }

        if (left.equals("string") || right.equals("string")) {
            return "string"; // Concatenación con string siempre devuelve un string
        }

        return "error"; // Si los tipos no son compatibles, se devuelve un error
    }

    /**
     * Normaliza constantes a sus tipos base.
     */
    private String NormalizeConstantType(String type) {
        switch (type) {
            case "int_constant":
                return "int";
            case "double_constant":
                return "double";
            case "true":
            case "false":
                return "bool";
            case "char_constant":
                return "char";
            case "string_constant":
                return "string";
            default:
                return type;
        }
    }


    private String InferConstantType(TypeConstant c){
       if (c.type.equals("int_const")){
           return "int";
       }
       if (c.type.equals("double_const")){
           return "double";
       }
       if (c.type.equals("char_const")){
           return "char";
       }
       if (c.type.equals("string_const")){
           return "string";
       }
       if (c.type.equals("true") || c.type.equals("false")){
           return "bool";
       }
       return c.type;
    }

    private String FromParamsToString(DefDeclOp defDeclOp) {
        StringBuilder s = new StringBuilder("(");
        boolean first = true;  // Para evitar la coma inicial

        if (defDeclOp.parameters != null) {
            for (ParDeclOp parDeclOp : defDeclOp.parameters) {
                for (PVarOp pVarOp : parDeclOp.parameters) {
                    if (!first) {
                        s.append(", ");
                    }
                    s.append(pVarOp.identifier.type);
                    first = false;
                }
            }
        }

        s.append(")");

        if (defDeclOp.returnType != null) {
            s.append(": ").append(defDeclOp.returnType.type);
        }

        return s.toString();
    }


    private List<String> extractParamTypes(String functionSignature) {
        List<String> paramTypes = new ArrayList<>();
        int start = functionSignature.indexOf("(");
        int end = functionSignature.indexOf(")");

        if (start != -1 && end != -1) {
            String params = functionSignature.substring(start + 1, end).trim();
            if (!params.isEmpty()) {
                paramTypes = Arrays.asList(params.split(", "));
            }
        }
        return paramTypes;
    }

    private String extractReturnType(String functionSignature) {
        int colonIndex = functionSignature.indexOf(":");
        if (colonIndex != -1) {
            return functionSignature.substring(colonIndex + 2).trim();
        }
        return "void"; // Si no se especifica un tipo de retorno, asumimos "void"
    }



    @Override
    public void visit(AddOp addOp) {

    }

    @Override
    public void visit(AndOp andOp) {

    }

    @Override
    public void visit(AssignOp assignOp) {
        if (assignOp.identifiers.size() != assignOp.expressions.size()) {
            System.err.println("Error: Mismatched number of identifiers and expressions in assignment.");
            error = true;
        }

        // Procesar identificadores y expresiones
        for (Identifier id : assignOp.identifiers) {
            id.accept(this); // Esto busca el identificador en la tabla de símbolos y asigna su tipo
        }
        for (ExprOp expr : assignOp.expressions) {
            expr.accept(this); // Se visita la expresión para inferir su tipo
        }

        // Verificación de tipos
        for (int i = 0; i < assignOp.identifiers.size(); i++) {
            Identifier id = assignOp.identifiers.get(i);
            ExprOp expr = assignOp.expressions.get(i);

            if (id.type == null || expr.type == null) {
                System.err.println("Error: Unable to determine type for assignment involving " + id.name);
                error = true;
            }

            if (!isTypeCompatible(id.type, expr.type)) {
                System.err.println("Type Error: Cannot assign expression of type " + expr.type + " to variable " + id.name + " of type " + id.type);
                error = true;
            }
        }
    }

    /**
     * Método auxiliar para verificar la compatibilidad de tipos en asignaciones.
     */
    private boolean isTypeCompatible(String left, String right) {
        if (left.equals(right)) {
            return true; // Mismo tipo siempre compatible
        }

        // Compatibilidad numérica: int y double pueden operar entre sí
        if ((left.equals("int") && right.equals("double")) || (left.equals("double") && right.equals("int"))) {
            return true;
        }

        // Compatibilidad de concatenación: char puede unirse con string
        if ((left.equals("char") && right.equals("string")) || (left.equals("string") && right.equals("char"))) {
            return true;
        }

        // Los booleanos no son compatibles con otros tipos
        if (left.equals("bool") && !right.equals("bool") || right.equals("bool") && !left.equals("bool")) {
            return false;
        }


        // Cualquier otro caso no es compatible
        return false;
    }



    @Override
    public void visit(BeginEndOp beginEndOp) {
        if (beginEndOp.varDecls != null) {
            for (VarDeclOp var : beginEndOp.varDecls) {
                var.accept(this);
            }
        }

        if (beginEndOp.statements != null) {
            for (StatOp stat : beginEndOp.statements) {
                stat.accept(this);
            }
        }
    }

    @Override
    public void visit(BodyOp bodyOp) {
        // Procesar las declaraciones de variables
        if (bodyOp.varDeclarations != null) {
            for (VarDeclOp varDecl : bodyOp.varDeclarations) {
                varDecl.accept(this);
            }
        }

        // Procesar las declaraciones de instrucciones
        if (bodyOp.statements != null) {
            for (StatOp stat : bodyOp.statements) {
                stat.accept(this);
            }
        }

    }

    @Override
    public void visit(CallOp callOp) {
        callOp.identifier.accept(this);
        if (callOp.expressions != null) {
            for (ExprOp expr : callOp.expressions) {
                expr.accept(this);
            }
        }
        // Verificar si la función está definida en la tabla de símbolos
        String functionName = callOp.identifier.name;
        SymbolTable currentTable = getCurrentSymbolTable();

        // Obtener la información de la función almacenada en la tabla de símbolos
        SymbolTableEntry functionEntry = findSymbol(functionName);
        if (functionEntry == null) {
            System.err.println("Error: Function '" + functionName + "' is not defined.");
            error = true;
        }


        if (!functionEntry.getCategory().equals("func")) {
            System.err.println("Error: '" + functionName + "' is not a function.");
            error = true;
        }

        // Extraer la firma de la función desde la tabla de símbolos
        String functionSignature = functionEntry.getType(); // Ejemplo: "(int, bool): int"
        List<String> expectedParams = extractParamTypes(functionSignature);
        String expectedReturnType = extractReturnType(functionSignature);

        // Procesar los argumentos de la llamada a la función
        List<ExprOp> providedArgs = callOp.expressions;
        if (providedArgs == null) {
            providedArgs = new ArrayList<>();
        }

        // Verificar el número de argumentos
        if (providedArgs.size() != expectedParams.size()) {
            System.err.println("Error: Function '" + functionName + "' expects "
                    + expectedParams.size() + " arguments, but got " + providedArgs.size() + ".");
            error = true;
        }

        // Verificar la compatibilidad de tipos de cada argumento
        for (int i = 0; i < providedArgs.size(); i++) {
            ExprOp arg = providedArgs.get(i);
            String argType = arg.type;
            String expectedType = expectedParams.get(i);

            if (!isTypeCompatible(expectedType, argType)) {
                System.err.println("Error: Argument " + (i + 1) + " of function '"
                        + functionName + "' expects type '" + expectedType
                        + "', but got '" + argType + "'.");
                error = true;
            }
        }

        // Asignar el tipo de retorno de la función a la operación de llamada
        callOp.type = expectedReturnType;
    }


    @Override
    public void visit(Constant constant) {

    }

    @Override
    public void visit(DefDeclOp defDeclOp) {
        // Crear un nuevo scope para la función
        defDeclOp.returnType.accept(this);
        enterNewScope();
        // Procesamos los parámetros de la función
        if (defDeclOp.parameters != null) {
            for (ParDeclOp param : defDeclOp.parameters) {
                param.accept(this);
            }
        }
        // Procesamos el cuerpo de la función
        defDeclOp.body.accept(this);

        // Salir del scope de la función
        exitCurrentScope();

        // Comprobamos si el identificador ya está declarado
        if (getCurrentSymbolTable().contains(defDeclOp.identifier.name)) {
            System.err.println("Error: Multiple declarations of " + defDeclOp.identifier.name);
            error = true;
        } else {
            // Añadir la función a la tabla de símbolos
            getCurrentSymbolTable().add(defDeclOp.identifier.name, FromParamsToString(defDeclOp), "func");
        }
        //if (getCurrentSymbolTable().contains(defDeclOp.identifier.name)) {
        //    System.err.println("Funcion " + defDeclOp.identifier.name + " puesta en SymbolTable con parametros: " + FromParamsToString(defDeclOp));
        //}
        defDeclOp.identifier.accept(this);

    }

    @Override
    public void visit(DiffOp diffOp) {

    }

    @Override
    public void visit(DivOp divOp) {

    }

    @Override
    public void visit(EQOp eQOp) {

    }

    @Override
    public void visit(GEOp gEOp) {

    }

    @Override
    public void visit(GTOp gTOp) {

    }

    @Override
    public void visit(Identifier identifier) {

        // Buscar el identificador en la pila de tablas de símbolos
        SymbolTableEntry entry = findSymbol(identifier.name);

        if (entry == null) {
            System.err.println("Error: Identifier " + identifier.name + " not declared.");
            error = true;
        } else {
            // Asignar el tipo al nodo
            identifier.type = entry.type;
        }
    }


    @Override
    public void visit(IfThenElseOp ifThenElseOp) {
        ifThenElseOp.condition.accept(this);
        enterNewScope();
        ifThenElseOp.thenBody.accept(this);
        exitCurrentScope();
        enterNewScope();
        ifThenElseOp.elseBody.accept(this);
        exitCurrentScope();
        if (!ifThenElseOp.condition.type.equals("bool")){
            System.err.println("Error: IfThenElseOp condition is not of type bool");
            error = true;
        }
    }

    @Override
    public void visit(IfThenOp ifThenOp) {
        ifThenOp.condition.accept(this);
        if (!ifThenOp.condition.type.equals("bool")){
            System.err.println("Error: IfThenOp condition is not of type bool");
            error = true;
        }
        enterNewScope();
        ifThenOp.thenBody.accept(this);
        exitCurrentScope();
    }

    @Override
    public void visit(LEOp lEOp) {

    }

    @Override
    public void visit(LTOp lTOp) {

    }

    @Override
    public void visit(MulOp mulOp) {

    }

    @Override
    public void visit(NotOp notOp) {

    }

    @Override
    public void visit(OrOp orOp) {

    }

    @Override
    public void visit(ParDeclOp parDeclOp) {
        // Aceptar el tipo del parámetro
        parDeclOp.type.accept(this);
        // Verificar si ya existe una declaración con el mismo nombre
        for (PVarOp pVarOp: parDeclOp.parameters){
            if (getCurrentSymbolTable().contains(pVarOp.identifier.name)) {
                System.err.println("Error: Multiple declarations of " + pVarOp.identifier.name);
                error = true;
            } else {
                // Añadir el parámetro a la tabla de símbolos
                getCurrentSymbolTable().add(pVarOp.identifier.name, parDeclOp.type.type, "var");
            }
        }
        if (parDeclOp.parameters != null) {
            for (PVarOp param : parDeclOp.parameters) {
                param.accept(this);
            }
        }

    }

    @Override
    public void visit(ProgramOp programOp) {
        // Crear nuevo scope para el programa
        enterNewScope();

        // Visitar las declaraciones y el cuerpo del programa
        programOp.decls.accept(this);
        programOp.beginEnd.accept(this);

        // Salir del scope
        exitCurrentScope();
    }

    @Override
    public void visit(PVarOp pVarOp) {
        if (pVarOp.identifier != null) {
            pVarOp.identifier.accept(this);
        }
    }

    @Override
    public void visit(ReadOp readOp) {

    }

    @Override
    public void visit(UMinusOp uMinusOp) {

    }

    @Override
    public void visit(VarDeclOp varDeclOp) {
        // Aceptar el tipo de la variable (por si hay alias o validaciones de tipos)
        varDeclOp.type.accept(this);

        // Primera pasada: agregar los identificadores a la tabla de símbolos
        for (VarOptInitOp varOptInitOp : varDeclOp.vars) {
            String varName = varOptInitOp.identifier.name;

            // Comprobar si el tipo de la variable es válido
            if (!isValidType(varDeclOp.type)) {
                System.err.println("Error: Invalid type '" + varDeclOp.type + "' for variable '" + varName + "'.");
                error = true;
                continue;
            }

            // Verificar si ya existe una declaración con el mismo nombre
            if (getCurrentSymbolTable().contains(varName)) {
                System.err.println("Error: Multiple declarations of variable '" + varName + "'.");
                error = true;
            } else {
                // Agregar la variable a la tabla de símbolos con su tipo declarado
                getCurrentSymbolTable().add(varName, InferConstantType(varDeclOp.type), "var");
            }
        }

        // Segunda pasada: procesar las inicializaciones
        for (VarOptInitOp varOptInitOp : varDeclOp.vars) {
            varOptInitOp.accept(this);
        }
    }


    private boolean isValidType(TypeConstant typeConstant) {
        // Validar que el objeto no sea null
        if (typeConstant == null || typeConstant.type == null) {
            return false; // No es válido si el objeto o el tipo no están definidos
        }

        switch (typeConstant.type) {
            case "int":
            case "double":
            case "bool":
            case "char":
            case "string":
            case "int_const":
            case "double_const":
            case "true":
            case "false":
            case "char_const":
            case "string_const":
                return true; // Tipo válido
            default:
                return false; // Tipo no reconocido
        }
    }




    @Override
    public void visit(VarOptInitOp varOptInitOp) {
        varOptInitOp.identifier.accept(this); // Verifica si el identificador está en la tabla de símbolos

        if (varOptInitOp.expr != null) {
            varOptInitOp.expr.accept(this); // Evaluar la expresión de inicialización

            // Verificar compatibilidad de tipos
            String declaredType = varOptInitOp.identifier.type;
            String assignedType = varOptInitOp.expr.type;
            //System.err.println("EnVarOptInitOp. expr.typ = " + assignedType);
            if (!isTypeCompatible(declaredType, assignedType)) {
                System.err.println("Error: Type mismatch in assignment. Variable '"
                        + varOptInitOp.identifier.name + "' declared as '" + declaredType
                        + "', but assigned '" + assignedType + "'.");
                error = true;
            }
        }
    }

    @Override
    public void visit(WhileOp whileOp) {
        whileOp.condition.accept(this);
        enterNewScope();
        whileOp.body.accept(this);
        exitCurrentScope();
        if (!whileOp.condition.type.equals("bool")){
            System.err.println("Error: WhileOp condition is not of type bool");
            error = true;
        }
    }

    @Override
    public void visit(WriteOp writeOp) {
        for(ExprOp exprOp : writeOp.expressions) {
            exprOp.accept(this);
        }
    }

    @Override
    public void visit(DeclsOp declsOp) {
        for (ASTNode decl : declsOp.decls) {
            if (decl instanceof VarDeclOp) {
                ((VarDeclOp) decl).accept(this);
            }
            else if (decl instanceof DefDeclOp) {
                ((DefDeclOp) decl).accept(this);
            }
        }
    }

    @Override
    public void visit(VarDeclsOp varDeclsOp) {
        for (VarDeclOp varDecl : varDeclsOp.varDecls) {
            varDecl.accept(this);
        }
    }

    @Override
    public void visit(BinaryOp binaryOp) {
        switch (binaryOp.operator){
            case "+":
                binaryOp.left.accept(this);
                binaryOp.right.accept(this);
                if (binaryOp.left.type.equals("bool") || binaryOp.right.type.equals("bool")) {
                    if (!isTypeCompatible(binaryOp.left.type, binaryOp.right.type)) {
                        System.err.println("Error: Invalid Types");
                        error = true;
                    }
                }
                binaryOp.type = InferType(binaryOp.left.type, binaryOp.right.type);
                break;
            case "-":
                binaryOp.left.accept(this);
                binaryOp.right.accept(this);
                if (binaryOp.left.type.equals("bool") || binaryOp.right.type.equals("bool")) {
                    if (!isTypeCompatible(binaryOp.left.type, binaryOp.right.type)) {
                        System.err.println("Error: Invalid Types");
                        error = true;
                    }
                }
                binaryOp.type = InferType(binaryOp.left.type, binaryOp.right.type);
                break;
            case "&&":
                binaryOp.left.accept(this);
                binaryOp.right.accept(this);
                if (!binaryOp.left.type.equals("bool") || !binaryOp.right.type.equals("bool")) {
                    System.err.println("Error: Binary operation requires both operands to be bool. Left: " + binaryOp.left.type + ", Right: " + binaryOp.right.type);
                    error = true;
                }
                binaryOp.type = "bool";
                break;
            case "||":
                binaryOp.left.accept(this);
                binaryOp.right.accept(this);
                if (!binaryOp.left.type.equals("bool") || !binaryOp.right.type.equals("bool")) {
                    System.err.println("Error: Binary operation requires both operands to be bool. Left: " + binaryOp.left.type + ", Right: " + binaryOp.right.type);
                    error = true;
                }

                binaryOp.type = "bool";
                break;
            case ">":
                binaryOp.left.accept(this);
                binaryOp.right.accept(this);

                // Verificar que ambos operandos sean numéricos (int o double)
                if (!(binaryOp.left.type.equals("int") || binaryOp.left.type.equals("double")) ||
                        !(binaryOp.right.type.equals("int") || binaryOp.right.type.equals("double"))) {
                    System.err.println("Error: Binary operation '>' requires both operands to be numeric (int or double). Left: "
                            + binaryOp.left.type + ", Right: " + binaryOp.right.type);
                    error = true;
                }

                binaryOp.type = "bool"; // El resultado de una comparación es siempre un booleano
                break;
            case ">=":
                binaryOp.left.accept(this);
                binaryOp.right.accept(this);

                // Verificar que ambos operandos sean numéricos (int o double)
                if (!(binaryOp.left.type.equals("int") || binaryOp.left.type.equals("double")) ||
                        !(binaryOp.right.type.equals("int") || binaryOp.right.type.equals("double"))) {
                    System.err.println("Error: Binary operation '>' requires both operands to be numeric (int or double). Left: "
                            + binaryOp.left.type + ", Right: " + binaryOp.right.type);
                    error = true;
                }

                binaryOp.type = "bool"; // El resultado de una comparación es siempre un booleano
                break;
            case "<":
                binaryOp.left.accept(this);
                binaryOp.right.accept(this);

                // Verificar que ambos operandos sean numéricos (int o double)
                if (!(binaryOp.left.type.equals("int") || binaryOp.left.type.equals("double")) ||
                        !(binaryOp.right.type.equals("int") || binaryOp.right.type.equals("double"))) {
                    System.err.println("Error: Binary operation '>' requires both operands to be numeric (int or double). Left: "
                            + binaryOp.left.type + ", Right: " + binaryOp.right.type);
                    error = true;
                }

                binaryOp.type = "bool"; // El resultado de una comparación es siempre un booleano
                break;
            case "<=":
                binaryOp.left.accept(this);
                binaryOp.right.accept(this);

                // Verificar que ambos operandos sean numéricos (int o double)
                if (!(binaryOp.left.type.equals("int") || binaryOp.left.type.equals("double")) ||
                        !(binaryOp.right.type.equals("int") || binaryOp.right.type.equals("double"))) {
                    System.err.println("Error: Binary operation '>' requires both operands to be numeric (int or double). Left: "
                            + binaryOp.left.type + ", Right: " + binaryOp.right.type);
                    error = true;
                }

                binaryOp.type = "bool"; // El resultado de una comparación es siempre un booleano
                break;
            case "==":
                binaryOp.left.accept(this);
                binaryOp.right.accept(this);
                if (!(!binaryOp.left.type.equals("bool") || !binaryOp.right.type.equals("bool"))) {
                    System.err.println("Error: Binary operation requires both bool and bool. Identifer left " + binaryOp.left.type + ". Identifier right " + binaryOp.right.type);
                    error = true;
                }
                binaryOp.type = "bool";
                break;
            case "<>":
                binaryOp.left.accept(this);
                binaryOp.right.accept(this);
                if (!(!binaryOp.left.type.equals("bool") || !binaryOp.right.type.equals("bool"))) {
                    System.err.println("Error: Binary operation requires both bool and bool. Identifer left " + binaryOp.left.type + ". Identifier right " + binaryOp.right.type);
                    error = true;
                }
                binaryOp.type = "bool";
                break;
        }
        //System.err.println("En Binary: " + binaryOp.left.type + " " + binaryOp.right.type);
    }

    @Override
    public void visit(TermTailOp termTailOp) {
        // Visitar el factor para obtener su tipo
        if (termTailOp.factor != null) {
            termTailOp.factor.accept(this);
        }

        // Si hay un siguiente TermTailOp, visitarlo
        if (termTailOp.next != null) {
            termTailOp.next.accept(this);
        }

        // Determinar el tipo de la expresión usando el operador y los tipos de operandos
        if (termTailOp.factor != null) {
            if (termTailOp.next == null) {
                // Caso base: no hay más operaciones en la cola, el tipo es el del único operando
                termTailOp.type = termTailOp.factor.type;
            } else {
                // Caso recursivo: hay una operación encadenada
                if (!isTypeCompatible(termTailOp.factor.type, termTailOp.next.type)) {
                    System.err.println("Error: Type mismatch in TermTailOp between '"
                            + termTailOp.factor.type + "' and '" + termTailOp.next.type + "'.");
                    error = true;
                }

                // Inferir el tipo resultante de la operación
                termTailOp.type = InferType(termTailOp.factor.type, termTailOp.next.type);
            }
        } else {
            // Si no hay factor, es un error
            System.err.println("Error: TermTailOp must have a factor.");
            error = true;
        }
    }



    @Override
    public void visit(FactorOp factorOp) {
        if (factorOp.expression != null) {
            factorOp.expression.accept(this);
            if(factorOp.operator != null){
                if (factorOp.operator.equals("MINUS")){
                    if (!factorOp.expression.type.equals("int") && !factorOp.expression.type.equals("double")) {
                        System.err.println("Error: Minus operator required numeric type");
                        error = true;
                    }
                }
                if (factorOp.operator.equals("not")){
                    if (!factorOp.expression.type.equals("bool")){
                        System.err.println("Error: Not operator required bool type");
                    }
                }
            }
            factorOp.type = factorOp.expression.type;
        }
        else if (factorOp.identifier != null) {
            factorOp.identifier.accept(this);
            factorOp.type = factorOp.identifier.type;
        }
        else if (factorOp.constant != null) {
            factorOp.constant.accept(this);
            factorOp.type = InferConstantType(factorOp.constant);
        }
        else if (factorOp.functionCall != null) {
            factorOp.functionCall.accept(this);
            factorOp.type = factorOp.functionCall.type;
        }
        else{
            System.err.println("Error en Factor");
        }
    }

    @Override
    public void visit(TermOp termOp) {
        termOp.factor.accept(this);
        if (termOp.termTail != null){
            termOp.termTail.accept(this);
            //System.err.println("En TermOp. factor.type = " + termOp.factor.type + " termTail.type = " + termOp.termTail.type);
            if (!isTypeCompatible(termOp.factor.type, termOp.termTail.type)){
                System.err.println("Error: Type mismatch");
                error = true;
            }
            termOp.type = InferType(termOp.factor.type, termOp.termTail.type);
        }
        else{
            termOp.type = termOp.factor.type;
        }
    }

    @Override
    public void visit(ReturnOp returnOp) {
        if (returnOp.returnValue != null) {
            returnOp.returnValue.accept(this);
            returnOp.type = returnOp.returnValue.type;
        }
    }

    @Override
    public void visit(ExprTailOp exprTailOp) {
        exprTailOp.getLeft().accept(this);
        exprTailOp.getRight().accept(this);
        if (!isTypeCompatible(exprTailOp.getLeft().type, exprTailOp.getRight().type)) {
            System.err.println("Error: Type mismatch");
            error = true;
        }
        exprTailOp.type = InferType(exprTailOp.getLeft().type, exprTailOp.getRight().type);
    }

    @Override
    public void visit(Type type) {

    }

    public boolean hasErrors() {
        return error;
    }
}