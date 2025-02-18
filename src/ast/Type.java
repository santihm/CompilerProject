package ast;

import visitor.ASTVisitor;

public class Type extends TypeConstant {
    public String type;  // Tipo de constante (int, double, etc.)

    public Type(String type) {
        this.type = type;
    }

    public Type(Type t) {
        this.type = t.type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
