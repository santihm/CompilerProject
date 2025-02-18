package ast;

import visitor.ASTVisitor;

import java.util.List;

public class VarDeclsOp extends ASTNode {
    public List<VarDeclOp> varDecls;
    public VarDeclsOp(List<VarDeclOp> decls) {
        this.varDecls = decls;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
