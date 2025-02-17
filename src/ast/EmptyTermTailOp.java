package ast;

import visitor.ASTVisitor;

public class EmptyTermTailOp extends TermTailOp {
    public EmptyTermTailOp() {
        super("", null, null);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}