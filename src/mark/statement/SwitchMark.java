package mark.statement;

import mark.LineMark;
import mark.Method;
import org.eclipse.jdt.core.dom.*;

import java.util.List;
import java.util.SortedMap;

public class SwitchMark extends StatementMark{
    private Expression condition;
    private List<Statement> statements;
    public SwitchMark(Method method, SwitchStatement switchStatement, SortedMap<Integer, Boolean> lineStartBlockMap,
                      String coverType){
        super(method, switchStatement, lineStartBlockMap, coverType);
        condition = switchStatement.getExpression();
        statements = switchStatement.statements();
    }
    @Override
    public String addMark() {
        int offset = statement.getStartPosition() - method.getStartPosition();
        LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset);
        lineMark.setSurroundingControlBlock("switch");
        return '\t' + lineMark.print() + ";\n\t" + addConditionMark() + ";\n\tswitch (" + condition + ") {\n"
                + addStatementsMark() + "\t}\n";
    }


    private String addStatementsMark() {
        lineStartBlockMap.put(statements.get(0).getStartPosition(), true);
        int offsetStart = statements.get(0).getStartPosition() - method.getStartPosition();
        LineMark openingBlock = new LineMark();
        openingBlock.setStatement("{");
        openingBlock.setLineOfBlockInFunction(method.getLineInMethod(offsetStart));
        String mark = "";
        for(Statement s : statements){
            int offset = s.getStartPosition() - method.getStartPosition();
            if (s instanceof SwitchCase) {
                mark += "\t" + s + "\t" + openingBlock.print() + ";\n\t";;
                LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset, s.toString().substring(0, s.getLength()));
                mark += lineMark.print() + ";\n";

            } else if (s instanceof BreakStatement) {
                LineMark breakMark = new LineMark(method.getLineInMethod(offset), offset, s.toString());
                openingBlock.setStatement("}");
                mark += "\t" + breakMark.print() + ";\n\t" + openingBlock.print() + ";\n\t" + s;
            } else {
                mark += addStatementMark(s);
            }
        }

        return mark;
    }

    private String addStatementMark(Statement statement) {
        if(statement instanceof IfStatement){
            IfMark ifMark = new IfMark(method, (IfStatement) statement, lineStartBlockMap, coverType);
            return ifMark.addMark();
        } else if(statement instanceof ForStatement){
            ForMark forMark = new ForMark(method, (ForStatement) statement, lineStartBlockMap, coverType);
            return forMark.addMark();
        }else if(statement instanceof WhileStatement){
            WhileMark whileMark = new WhileMark(method, (WhileStatement) statement, lineStartBlockMap, coverType);
            return whileMark.addMark();
        } else if(statement instanceof DoStatement){
            DoMark doMark = new DoMark(method, (DoStatement) statement, lineStartBlockMap, coverType);
            return doMark.addMark();
        } else if (statement instanceof SwitchStatement){
            SwitchMark switchMark = new SwitchMark(method, (SwitchStatement) statement, lineStartBlockMap, coverType);
            return switchMark.addMark();
        } else if (statement instanceof EnhancedForStatement){
            ForEachMark forEachMark = new ForEachMark(method, (EnhancedForStatement) statement, lineStartBlockMap, coverType);
            return forEachMark.addMark();
        } else if (statement instanceof TryStatement) {
            TryMark tryMark = new TryMark(method, (TryStatement) statement, lineStartBlockMap, coverType);
            return tryMark.addMark();
        } else if (statement instanceof ReturnStatement){
            ReturnMark returnMark = new ReturnMark(method, statement, lineStartBlockMap, coverType);
            return returnMark.addMark();
        } else if (statement instanceof BreakStatement){
            BreakMark breakContinueMark = new BreakMark(method,(BreakStatement) statement, lineStartBlockMap, coverType);
            return breakContinueMark.addMark();
        } else if (statement instanceof ContinueStatement){
            ContinueMark continueMark = new ContinueMark(method, (ContinueStatement) statement, lineStartBlockMap, coverType);
            return continueMark.addMark();
        } else if (statement instanceof ThrowStatement) {
            ThrowMark throwMark = new ThrowMark(method, (ThrowStatement) statement, lineStartBlockMap, coverType);
            return throwMark.addMark();
        } else  {
            SequentialMark sequentialMark = new SequentialMark(method, statement, lineStartBlockMap, coverType);
            return sequentialMark.addMark();
        }
    }

    private String addConditionMark(){
        String statement = "switch (" + condition + ")";
        int offset = this.statement.getStartPosition() - method.getStartPosition();
        LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset, statement);
        return lineMark.print();
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
