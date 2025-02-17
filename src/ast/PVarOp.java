package ast;

import visitor.ASTVisitor;

public class PVarOp extends ASTNode {
    public Identifier identifier;  // Ahora es de tipo Identifier
    public ASTNode reference;      // Puede ser una referencia a otro nodo o null

    public PVarOp(Identifier identifier, ASTNode reference) {
        this.identifier = identifier;
        this.reference = reference;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
