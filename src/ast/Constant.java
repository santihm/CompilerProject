package ast;

import visitor.ASTVisitor;

public class Constant extends TypeConstant {
    public String value;

    public Constant(String type, String value) {
        super(type);
        this.value = value;
    }

    public Constant (Constant constant){
        super(constant.type);
        this.value = constant.value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
