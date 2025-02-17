package ast;

import visitor.ASTVisitor;
import java.util.List;

public class WriteOp extends StatOp {
    public List<ExprOp> expressions;
    public boolean newLine;

    public WriteOp(List<ExprOp> expressions, boolean newLine) {
        this.expressions = expressions;
        this.newLine = newLine;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
