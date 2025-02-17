package ast;

import visitor.ASTVisitor;

import java.util.List;

public class DeclsOp extends ASTNode {
    public List<VarDefDeclOp> decls;
    public DeclsOp(List<VarDefDeclOp> decls) {
        this.decls = decls;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
