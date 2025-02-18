package visitor;

import ast.*;

public interface ASTVisitor {

    // Métodos para visitar los diferentes nodos del AST
    void visit(AddOp addOp);
    void visit(AndOp andOp);
    void visit(AssignOp AssignOp);
    void visit(BeginEndOp BeginEndOp);
    void visit(BodyOp BodyOp);
    void visit(CallOp CallOp);
    void visit(Constant constant);
    void visit(DefDeclOp defdeclOp);
    void visit(DiffOp diffOp);
    void visit(DivOp divOp);
    void visit(EQOp eQOp);
    void visit(GEOp gEOp);
    void visit(GTOp gTOp);
    void visit(Identifier identifier);
    void visit(IfThenElseOp ifThenElseOp);
    void visit(IfThenOp ifThenOp);
    void visit(LEOp lEOp);
    void visit(LTOp lTOp);
    void visit(MulOp mulOp);
    void visit(NotOp notOp);
    void visit(OrOp orOp);
    void visit(ParDeclOp parDeclOp);
    void visit(ProgramOp programOp);
    void visit(PVarOp pVarOp);
    void visit(ReadOp readOp);
    void visit(UMinusOp uMinusOp);
    void visit(VarDeclOp varDeclOp);
    void visit(VarOptInitOp varOptInitOp);
    void visit(WhileOp whileOp);
    void visit(WriteOp writeOp);
    void visit(DeclsOp declsOp);

    void visit(VarDeclsOp varDeclsOp);

    void visit(BinaryOp binaryOp);

    void visit(TermTailOp termTailOp);

    void visit(FactorOp factorOp);

    void visit(TermOp termOp);

    void visit(ReturnOp returnOp);

    void visit(ExprTailOp exprTailOp);

    void visit(Type type);
}
