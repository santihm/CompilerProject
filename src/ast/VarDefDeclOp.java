package ast;


import visitor.ASTVisitor;

public abstract class VarDefDeclOp extends ASTNode {
    @Override
    public abstract void accept(ASTVisitor visitor);
}
