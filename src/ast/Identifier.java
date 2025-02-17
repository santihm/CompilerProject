package ast;

import visitor.ASTVisitor;

public class Identifier extends TypeConstant {
    public String name;      // Nombre del identificador

    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
