package ast;

import visitor.ASTVisitor;
import java.util.List;

public class VarDeclOp extends VarDefDeclOp {
    public TypeConstant type;  // Tipo de la variable (integer, boolean, etc.)
    public List<VarOptInitOp> vars;  // Lista de identificadores con posible inicialización

    public VarDeclOp(TypeConstant type, List<VarOptInitOp> vars) {
        this.type = type;
        this.vars = vars;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
