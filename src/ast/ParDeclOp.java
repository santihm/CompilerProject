package ast;

import visitor.ASTVisitor;
import java.util.List;

public class ParDeclOp extends ASTNode {
    public List<PVarOp> parameters;  // Lista de parámetros
    public Type type;  // Tipo de los parámetros (integer, boolean, double, string, char)

    public ParDeclOp(List<PVarOp> parameters, Type type) {
        this.parameters = parameters;
        this.type = type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
