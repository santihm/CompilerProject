package ast;

import visitor.ASTVisitor;

public class BinaryOp extends ExprOp {
    public ExprOp left;
    public String operator;
    public ExprOp right;

    public BinaryOp(ExprOp left, String operator, ExprOp right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public ExprOp getLeft() { return left; }
    public String getOperator() { return operator; }
    public ExprOp getRight() { return right; }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
