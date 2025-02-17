package ast;

import visitor.ASTVisitor;

public class NotOp extends ExprOp {
    public ExprOp expr;  // Operando único

    public NotOp(ExprOp expr) {
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
