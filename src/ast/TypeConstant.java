package ast;

import visitor.ASTVisitor;

public abstract class TypeConstant extends ASTNode {
    public String type;

    public TypeConstant(String type) {
        this.type = type;
    }

    @Override
    public abstract void accept(ASTVisitor visitor);
}