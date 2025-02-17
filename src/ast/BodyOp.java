package ast;

import visitor.ASTVisitor;
import java.util.List;

public class BodyOp extends ASTNode {
    public List<VarDeclOp> varDeclarations;  // Lista de declaraciones de variables
    public List<StatOp> statements;  // Lista de declaraciones de sentencias

    public BodyOp(List<VarDeclOp> varDeclarations, List<StatOp> statements) {
        this.varDeclarations = varDeclarations;
        this.statements = statements;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
