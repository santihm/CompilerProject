package ast;

import visitor.ASTVisitor;
import java.util.List;

public class BeginEndOp extends ASTNode {
    public List<VarDeclOp> varDecls;  // Lista de VarDeclOp
    public List<StatOp> statements;  // Lista de StatOp

    public BeginEndOp(List<VarDeclOp> varDecls, List<StatOp> statements) {
        this.varDecls = varDecls;
        this.statements = statements;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
