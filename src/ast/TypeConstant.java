package ast;

import visitor.ASTVisitor;

public abstract class TypeConstant extends ASTNode {
    @Override
    public abstract void accept(ASTVisitor visitor);
}