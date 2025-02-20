package ast;

import visitor.ASTVisitor;

public class ReturnOp extends StatOp {
    public ExprOp returnValue;
    public String type;

    public ReturnOp(ExprOp returnValue ) {
        this.returnValue = returnValue;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}