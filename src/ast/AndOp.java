package ast;

import visitor.ASTVisitor;

public class AndOp extends ExprOp {
    public ExprOp left;  // Operando izquierdo
    public ExprOp right; // Operando derecho

    public AndOp(ExprOp left, ExprOp right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
