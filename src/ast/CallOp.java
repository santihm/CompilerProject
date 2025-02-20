package ast;

import visitor.ASTVisitor;
import java.util.List;

public class CallOp extends StatOp {
    public Identifier identifier;
    public List<ExprOp> expressions;
    public String type;

    public CallOp(Identifier identifier, List<ExprOp> expressions) {
        this.identifier = identifier;
        this.expressions = expressions;
    }

    public CallOp(CallOp callOp) {
        this.identifier = callOp.identifier;
        this.expressions = callOp.expressions;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
