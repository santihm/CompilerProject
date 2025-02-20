package ast;

import visitor.ASTVisitor;

public class Identifier extends ASTNode {
    public String name;      // Nombre del identificador
    public String type;

    public Identifier(String name) {
        this.name = name;
    }

    public Identifier (Identifier identifier) {
        this.name = identifier.name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
