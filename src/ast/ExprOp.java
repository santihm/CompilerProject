package ast;

import visitor.ASTVisitor;

public abstract class ExprOp extends ASTNode {
    @Override
    public abstract void accept(ASTVisitor visitor);
}
