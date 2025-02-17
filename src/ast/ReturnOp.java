package ast;

import visitor.ASTVisitor;

public class ReturnOp extends StatOp {
    public ExprOp returnValue;

    public ReturnOp(ExprOp returnValue ) {
        this.returnValue = returnValue;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}