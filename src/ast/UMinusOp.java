package ast;

import visitor.ASTVisitor;

public class UMinusOp extends ExprOp {
    public ExprOp expr;  // Operando único

    public UMinusOp(ExprOp expr) {
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
