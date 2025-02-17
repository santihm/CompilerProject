package ast;

import visitor.ASTVisitor;

public class ExprTailOp extends ASTNode {
    private ExprOp left;  // Nueva variable para la izquierda
    private String operator;
    private ExprOp right;

    public ExprTailOp(ExprOp left, String operator, ExprOp right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public ExprOp getLeft() {
        return left;
    }

    public void setLeft(ExprOp left) {
        this.left = left;
    }

    public String getOperator() {
        return operator;
    }

    public ExprOp getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "ExprTailOp{" +
                "left=" + left +
                ", operator='" + operator + '\'' +
                ", right=" + right +
                '}';
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}


