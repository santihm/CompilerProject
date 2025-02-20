package ast;

import visitor.ASTVisitor;

public class TermOp extends ExprOp {
    public FactorOp factor;
    public TermTailOp termTail;

    public TermOp(FactorOp factor, TermTailOp termTail) {
        this.factor = factor;
        this.termTail = termTail;
    }

    public FactorOp getFactor() { return factor; }
    public TermTailOp getTermTail() { return termTail; }

    @Override
    public String toString() {
        return factor.toString() + (termTail != null ? " " + termTail.toString() : "");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
