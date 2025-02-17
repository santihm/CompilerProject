package ast;

import visitor.ASTVisitor;
import java.util.List;

public class ReadOp extends StatOp {
    public List<Identifier> identifiers;

    public ReadOp(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
