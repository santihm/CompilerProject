package ast;

import visitor.ASTVisitor;

public class GEOp extends ExprOp {
    public ExprOp left;  // Operando izquierdo
    public ExprOp right; // Operando derecho

    public GEOp(ExprOp left, ExprOp right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
