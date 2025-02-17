package ast;

import visitor.ASTVisitor;

// Nodo para un factor en una expresión
public class FactorOp extends ExprOp {
    public String operator; // Puede ser "-", "not"
    public ExprOp expression; // Expresión si es un paréntesis o un operador unario
    public Identifier identifier; // Para identificadores
    public CallOp functionCall; // Para llamadas a funciones
    public Constant constant; // Para constantes

    // Constructor para operadores unarios (-, !)
    public FactorOp(String operator, ExprOp expression) {
        this.operator = operator;
        this.expression = expression;
    }

    // Constructor para identificadores
    public FactorOp(Identifier identifier) {
        this.identifier = identifier;
    }

    // Constructor para llamadas a funciones
    public FactorOp(CallOp functionCall) {
        this.functionCall = functionCall;
    }

    // Constructor para constantes
    public FactorOp(Constant constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        if (operator != null) return operator + " " + expression.toString();
        if (identifier != null) return identifier.toString();
        if (functionCall != null) return functionCall.toString();
        if (constant != null) return constant.toString();
        return "(" + expression.toString() + ")";
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}

