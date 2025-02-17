package visitor;

import ast.*;
import java.io.*;

public class XMLVisitor implements ASTVisitor {

    private PrintWriter writer;
    private int indentationLevel;

    public XMLVisitor(PrintWriter writer) {
        this.writer = writer;
        this.indentationLevel = 0;  // Inicia con nivel de indentación 0
    }

    private void openTag(String tagName) {
        writeIndentation();
        writer.println("<" + tagName + ">");
        indentationLevel++; // Aumenta el nivel de indentación
    }

    private void closeTag(String tagName) {
        indentationLevel--; // Disminuye el nivel de indentación
        writeIndentation();
        writer.println("</" + tagName + ">");
    }

    private void writeContent(String content) {
        writeIndentation();
        writer.println(content);
    }

    private void writeIndentation() {
        for (int i = 0; i < indentationLevel; i++) {
            writer.print("    "); // 4 espacios por cada nivel
        }
    }

    @Override
    public void visit(AddOp addOp) {
        // Implementar el código para AddOp
    }

    @Override
    public void visit(AndOp andOp) {
        // Implementar el código para AndOp
    }

    @Override
    public void visit(AssignOp assignOp) {
        openTag("AssignOp");
        for (Identifier id : assignOp.identifiers) {
            id.accept(this);
        }
        for (ExprOp expr : assignOp.expressions) {
            expr.accept(this);
        }
        closeTag("AssignOp");
    }

    @Override
    public void visit(BeginEndOp beginEndOp) {
        openTag("BeginEndOp");

        if (beginEndOp.varDecls != null) {
            for (VarDeclOp var : beginEndOp.varDecls) {
                var.accept(this);
            }
        }

        if (beginEndOp.statements != null) {
            for (StatOp stat : beginEndOp.statements) {
                stat.accept(this);
            }
        }

        closeTag("BeginEndOp");
    }

    @Override
    public void visit(BodyOp bodyOp) {
        openTag("BodyOp");

        if (bodyOp.varDeclarations != null) {
            for (VarDeclOp decl : bodyOp.varDeclarations) {
                decl.accept(this);
            }
        }

        if (bodyOp.statements != null) {
            for (StatOp stat : bodyOp.statements) {
                stat.accept(this);
            }
        }

        closeTag("BodyOp");
    }

    @Override
    public void visit(CallOp callOp) {
        openTag("CallOp");
        callOp.identifier.accept(this);
        for (ExprOp arg : callOp.expressions) {
            arg.accept(this);
        }
        closeTag("CallOp");
    }

    @Override
    public void visit(Constant constant) {
        writeContent(constant.type);
    }

    @Override
    public void visit(DefDeclOp defdeclOp) {
        openTag("DefDeclOp");
        defdeclOp.identifier.accept(this);
        writeContent(defdeclOp.returnType);
        for(ParDeclOp parDeclOp : defdeclOp.parameters){
            parDeclOp.accept(this);
        }
        defdeclOp.body.accept(this);
        closeTag("DefDeclOp");
    }

    @Override
    public void visit(DiffOp diffOp) {
        // Implementar el código para DiffOp
    }

    @Override
    public void visit(DivOp divOp) {
        // Implementar el código para DivOp
    }

    @Override
    public void visit(EQOp eQOp) {
        // Implementar el código para EQOp
    }

    @Override
    public void visit(GEOp gEOp) {
        // Implementar el código para GEOp
    }

    @Override
    public void visit(GTOp gTOp) {
        // Implementar el código para GTOp
    }

    @Override
    public void visit(Identifier identifier) {
        writeContent(identifier.name);
    }

    @Override
    public void visit(ParDeclOp parDeclOp) {
        openTag("ParDeclOp");
        for (PVarOp pVarOp : parDeclOp.parameters) {
            pVarOp.accept(this);
        }
        parDeclOp.type.accept(this);
        closeTag("ParDeclOp");
    }

    @Override
    public void visit(ProgramOp programOp) {
        openTag("ProgramOp");
        programOp.decls.accept(this);
        programOp.beginEnd.accept(this);
        closeTag("ProgramOp");
    }

    @Override
    public void visit(PVarOp pVarOp) {
        openTag("PVarOp");
        pVarOp.identifier.accept(this);
        closeTag("PVarOp");
    }

    @Override
    public void visit(VarDeclOp varDeclOp) {
        openTag("VarDeclOp");
        for (VarOptInitOp varOptInitOp : varDeclOp.vars){
            varOptInitOp.accept(this);
        }
        varDeclOp.type.accept(this);
        closeTag("VarDeclOp");
    }

    @Override
    public void visit(VarOptInitOp varOptInitOp) {
        openTag("VarOptInitOp");
        varOptInitOp.identifier.accept(this);
        if (varOptInitOp.expr != null) {
            varOptInitOp.expr.accept(this);
        }
        closeTag("VarOptInitOp");
    }

    @Override
    public void visit(IfThenElseOp ifThenElseOp) {
        openTag("IfThenElseOp");
        ifThenElseOp.condition.accept(this);
        ifThenElseOp.thenBody.accept(this);
        ifThenElseOp.elseBody.accept(this);
        closeTag("IfThenElseOp");
    }

    @Override
    public void visit(IfThenOp ifThenOp) {
        openTag("IfThenOp");
        ifThenOp.condition.accept(this);
        ifThenOp.thenBody.accept(this);
        closeTag("IfThenOp");
    }

    @Override
    public void visit(LEOp lEOp) {
        // Implementar el código para LEOp
    }

    @Override
    public void visit(LTOp lTOp) {
        // Implementar el código para LTOp
    }

    @Override
    public void visit(MulOp mulOp) {
        // Implementar el código para MulOp
    }

    @Override
    public void visit(NotOp notOp) {
        // Implementar el código para NotOp
    }

    @Override
    public void visit(OrOp orOp) {
        // Implementar el código para OrOp
    }

    @Override
    public void visit(ReadOp readOp) {
        openTag("ReadOp");
        for (Identifier id : readOp.identifiers) {
            id.accept(this);
        }
        closeTag("ReadOp");
    }

    @Override
    public void visit(UMinusOp uMinusOp) {
        // Implementar el código para UMinusOp
    }

    @Override
    public void visit(WhileOp whileOp) {
        openTag("WhileOp");
        whileOp.condition.accept(this);
        whileOp.body.accept(this);
        closeTag("WhileOp");
    }

    @Override
    public void visit(WriteOp writeOp) {
        openTag("WriteOp");
        for(ExprOp exprOp : writeOp.expressions) {
            exprOp.accept(this);
        }
        closeTag("WriteOp");
    }

    @Override
    public void visit(DeclsOp declsOp) {
        openTag("DeclsOp");
        for (ASTNode decl : declsOp.decls) {
            if (decl instanceof VarDeclOp) {
                ((VarDeclOp) decl).accept(this);
            }
            else if (decl instanceof DefDeclOp) {
                ((DefDeclOp) decl).accept(this);
            }
        }
        closeTag("DeclsOp");
    }

    @Override
    public void visit(VarDeclsOp varDeclsOp) {
        openTag("VarDeclsOp");
        for (VarDeclOp varDecl : varDeclsOp.varDecls) {
            varDecl.accept(this);
        }
        closeTag("VarDeclsOp");
    }

    @Override
    public void visit(BinaryOp binaryOp) {
        openTag("BinaryOp");
        binaryOp.left.accept(this);
        writeContent("<operator>" + binaryOp.operator + "</operator>");
        binaryOp.right.accept(this);
        closeTag("BinaryOp");
    }

    @Override
    public void visit(TermTailOp termTailOp) {
        openTag("TermTailOp");
        writeContent("<operator>" + termTailOp.getOperator() + "</operator>");
        termTailOp.factor.accept(this);
        termTailOp.next.accept(this);
        closeTag("TermTailOp");
    }

    @Override
    public void visit(FactorOp factorOp) {
        openTag("FactorOp");
        if (factorOp.expression != null) {
            factorOp.expression.accept(this);
        }
        else if (factorOp.identifier != null) {
            factorOp.identifier.accept(this);
        }
        else if (factorOp.constant != null) {
            factorOp.constant.accept(this);
        }
        else if (factorOp.functionCall != null) {
            factorOp.functionCall.accept(this);
        }
        else if(factorOp.operator != null){
            writeContent("<operator>" + factorOp.operator + "</operator>");
            factorOp.expression.accept(this);
        }
        closeTag("FactorOp");
    }

    @Override
    public void visit(TermOp termOp) {
        openTag("TermOp");
        termOp.factor.accept(this);
        termOp.termTail.accept(this);
        closeTag("TermOp");
    }

    @Override
    public void visit(ReturnOp returnOp) {
        openTag("ReturnOp");
        if (returnOp.returnValue != null) {
            returnOp.returnValue.accept(this);
        }
        closeTag("ReturnOp");
    }

    @Override
    public void visit(ExprTailOp exprTailOp) {
        openTag("ExprTailOp");
        exprTailOp.getLeft().accept(this);
        writeContent("<operator>" + exprTailOp.getOperator() + "</operator>");
        exprTailOp.getRight().accept(this);
        closeTag("ExprTailOp");
    }
}
