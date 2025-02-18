package ast;

import visitor.ASTVisitor;

public class Constant extends TypeConstant {
    public String type;  // Tipo de constante (int, double, etc.)
    public String value;

    public Constant(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public Constant (Constant constant){
        this.type = constant.type;
        this.value = constant.value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
