package ast;

import visitor.ASTVisitor;

public class WhileOp extends StatOp {
    public ExprOp condition;
    public BodyOp body;

    public WhileOp(ExprOp condition, BodyOp body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
