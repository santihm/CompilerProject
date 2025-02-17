package ast;

import visitor.ASTVisitor;
import java.util.List;

public class DefDeclOp extends VarDefDeclOp {
    public Identifier identifier;  // Nombre de la función/procedimiento
    public String returnType;  // Tipo de retorno (integer, boolean, etc.) o null
    public List<ParDeclOp> parameters;  // Lista de parámetros
    public BodyOp body;  // Cuerpo de la función

    public DefDeclOp(Identifier identifier, String returnType, List<ParDeclOp> parameters, BodyOp body) {
        this.identifier = identifier;
        this.returnType = returnType;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
