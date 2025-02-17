package ast;

import visitor.ASTVisitor;

public class IfThenOp extends StatOp {
    public ExprOp condition;
    public BodyOp thenBody;

    public IfThenOp(ExprOp condition, BodyOp thenBody) {
        this.condition = condition;
        this.thenBody = thenBody;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
