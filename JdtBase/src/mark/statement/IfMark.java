package mark.statement;

import mark.BlockMark;
import mark.LineMark;
import mark.Method;
import org.eclipse.jdt.core.dom.*;

import java.util.SortedMap;

public class IfMark extends StatementMark {
    private Expression condition;

    private Statement thenStatement;

    private Statement elseStatement;

    public IfMark(Method method, IfStatement ifStatement, SortedMap<Integer, Boolean> lineStartBlockMap, String coverType) {
        super(method, ifStatement, lineStartBlockMap, coverType);
        condition = ifStatement.getExpression();
        thenStatement = ifStatement.getThenStatement();
        elseStatement = ifStatement.getElseStatement();


    }

    public IfMark(Expression condition, Statement thenStatement, Statement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public IfMark() {
        condition = null;
        thenStatement = null;
        elseStatement = null;
    }

    @Override
    public String addMark() {
        int offset = statement.getStartPosition() - method.getStartPosition();
        LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset);
        lineMark.setSurroundingControlBlock("if");
        String mark = '\t' + lineMark.print() + ";\n\tif (" + addConditionMark(condition, method, coverType) + ") {\n"
                + addThenStatementMark(thenStatement) + "\t}";
        if (elseStatement == null) {
            mark += '\n';
        } else {
            mark += " else {\n" + addElseStatementMark(elseStatement) + "\t}\n";
        }
        return mark;
    }

    public static String addConditionMark(Expression condition, Method method, String coverType) {
        String mark = "";
        int offset = condition.getStartPosition() - method.getStartPosition();
        int lineOfMethod = method.getLineInMethod(offset);
        boolean check = false;
        if(coverType.equals("c3")){
            if (condition instanceof InfixExpression) {
                InfixExpression infixExpression = (InfixExpression) condition;
                Expression leftExpression = infixExpression.getLeftOperand();
                Expression rightExpression = infixExpression.getRightOperand();
                InfixExpression.Operator operator = infixExpression.getOperator();

                if (operator.toString().equals("&&") || operator.toString().equals("||")) {
                    mark += addConditionMark(leftExpression, method, coverType) + " " + operator + " "
                            + addConditionMark(rightExpression, method, coverType);
                    check = true;
                }
            } else if (condition instanceof ParenthesizedExpression) {
                Expression innerExpression = ((ParenthesizedExpression) condition).getExpression();
                mark += " (" + addConditionMark(innerExpression, method, coverType) + ")";
                check = true;
            }

            if (!check) {
                LineMark lineMark = new LineMark(lineOfMethod, offset, condition.toString());
                lineMark.setIsCondition("false");
                mark += "(" + lineMark.print() + " && " + condition ;
                lineMark.setIsCondition("true");
                mark += " && " + lineMark.print() + ")";
            }
        }
        if(coverType.equals("c2") || coverType.equals("c1")){
            LineMark lineMark = new LineMark(lineOfMethod, offset, condition.toString());
            lineMark.setIsCondition("false");
            mark += "(" + lineMark.print() + " && " + condition ;
            lineMark.setIsCondition("true");
            mark += " && " + lineMark.print() + ")";
        }
        return mark;
    }

    private String addThenStatementMark(Statement thenStatement) {
        if (thenStatement instanceof Block) {
            BlockMark blockMark = new BlockMark((Block) thenStatement, method, lineStartBlockMap, false, coverType);
            return blockMark.addMark();
        } else {
            int offset = thenStatement.getStartPosition() - method.getStartPosition();
            LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset, thenStatement.toString());
            return "\t" + lineMark.print() + ";\n\t" + thenStatement;
        }
    }

    private String addElseStatementMark(Statement elseStatement) {
        System.out.println(elseStatement);
        if(elseStatement instanceof IfStatement){
            IfMark ifMark = new IfMark(method, (IfStatement) elseStatement, lineStartBlockMap, coverType);
            return ifMark.addMark();
        }else if (elseStatement instanceof Block) {
            BlockMark blockMark = new BlockMark((Block) elseStatement, method, lineStartBlockMap, false, coverType);
            return blockMark.addMark();
        }
        return "";
    }


    public Expression getCondition() {
        return condition;
    }

    public Statement getThenStatement() {
        return thenStatement;
    }

    public Statement getElseStatement() {
        return elseStatement;
    }
}
