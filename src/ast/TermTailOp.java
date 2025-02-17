package ast;

import visitor.ASTVisitor;

public class TermTailOp extends ASTNode {
    public String operator;
    public FactorOp factor;
    public TermTailOp next;

    public TermTailOp(String operator, FactorOp factor, TermTailOp next) {
        this.operator = operator;
        this.factor = factor;
        this.next = next;
    }

    public String getOperator() { return operator; }
    public FactorOp getFactor() { return factor; }
    public TermTailOp getNext() { return next; }

    @Override
    public String toString() {
        return operator + " " + factor.toString() + (next != null ? " " + next.toString() : "");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
