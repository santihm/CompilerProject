package ast;

import visitor.ASTVisitor;

public class Constant extends TypeConstant {
    public String type;  // Tipo de constante (int, double, etc.)

    public Constant(String type) {
        this.type = type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
