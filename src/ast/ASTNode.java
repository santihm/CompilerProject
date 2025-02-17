package ast;

import visitor.ASTVisitor;

// Base class for all AST nodes
public abstract class ASTNode {
    public abstract void accept(ASTVisitor visitor);
}