package ast;

import visitor.ASTVisitor;

public class VarOptInitOp extends ASTNode {
    public Identifier identifier;  // Ahora es de tipo Identifier
    public ExprOp expr;           // Expresión de inicialización o null

    public VarOptInitOp(Identifier identifier, ExprOp expr) {
        this.identifier = identifier;
        this.expr = expr;  // Puede ser null si no hay inicialización
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
