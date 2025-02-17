package ast;

import visitor.ASTVisitor;
import java.util.List;

public class ProgramOp extends ASTNode {
    public DeclsOp decls;  // Lista de VarDeclOp o DefDeclOp
    public BeginEndOp beginEnd;  // Contiene VarDeclOp y StatOp

    public ProgramOp(DeclsOp decls, BeginEndOp beginEnd) {
        this.decls = decls;
        this.beginEnd = beginEnd;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
