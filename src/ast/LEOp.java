package ast;

import visitor.ASTVisitor;

public class LEOp extends ExprOp {
    public ExprOp left;  // Operando izquierdo
    public ExprOp right; // Operando derecho

    public LEOp(ExprOp left, ExprOp right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
