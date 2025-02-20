package ast;

import visitor.ASTVisitor;

public abstract class ExprOp extends ASTNode {
    public String type;
    @Override
    public abstract void accept(ASTVisitor visitor);
}
