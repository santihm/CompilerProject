package ast;

import visitor.ASTVisitor;
import java.util.List;

public class AssignOp extends StatOp {
    public List<Identifier> identifiers;
    public List<ExprOp> expressions;

    public AssignOp(List<Identifier> identifiers, List<ExprOp> expressions) {
        this.identifiers = identifiers;
        this.expressions = expressions;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
