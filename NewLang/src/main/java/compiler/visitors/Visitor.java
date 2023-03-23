package compiler.visitors;

import compiler.nodi.*;
import compiler.nodi.statement.*;
import compiler.nodi.expr.*;

public interface Visitor {

    public Object visit(ProgramOp programOp) throws Exception;
    public Object visit(VarDeclOp varDecl) throws Exception;
    public Object visit(FunOp funOp) throws Exception;
    public Object visit(ParDeclOp parDeclOp) throws Exception;
    public Object visit(BodyOp bodyOp) throws Exception;
    public Object visit(AssignOp assignOp) throws Exception;
    public Object visit(CallFunOpStat callFunOpStat) throws Exception;
    public Object visit(ForOp forOp) throws Exception;
    public Object visit(IfStatOp ifStatOp) throws Exception;
    public Object visit(ReadOp readOp) throws Exception;
    public Object visit(ReturnOp returnOp) throws Exception;
    public Object visit(Statement statement) throws Exception;
    public Object visit(WhileOp whileOp) throws Exception;
    public Object visit(WriteOp writeOp) throws Exception;
    public Object visit(AritAndRelOp aritAndRelOp) throws Exception;
    public Object visit(CallFunOpExpr callFunOpExpr) throws Exception;
    public Object visit(ConstOp constOp) throws Exception;
    public Object visit(Expr expr) throws Exception;
    public Object visit(Identifier identifier) throws Exception;
    public Object visit(IdInitObbOp idInitObbOp) throws Exception;
    public Object visit(IdInitOp idInitOp) throws Exception;
    public Object visit(UnaryOp unaryOp) throws Exception;

}
