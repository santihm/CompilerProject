package ast;

import visitor.ASTVisitor;

public class IfThenElseOp extends StatOp {
    public ExprOp condition;
    public BodyOp thenBody;
    public BodyOp elseBody;

    public IfThenElseOp(ExprOp condition, BodyOp thenBody, BodyOp elseBody) {
        this.condition = condition;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
