package ast;

import visitor.ASTVisitor;

public class Type extends TypeConstant {

    public Type(String type) {
        super(type);
    }

    public Type(Type t) {
        super(t.type);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
