package mark.statement;

import mark.BlockMark;
import mark.LineMark;
import mark.Method;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.Statement;

import java.util.List;
import java.util.SortedMap;

public class ForMark extends StatementMark{
    List<Expression> initializers;
    Expression condition;
    List<Expression> updaters;
    Statement body;


    public ForMark(Method method, ForStatement forStatement, SortedMap<Integer, Boolean> lineStartBlockMap, String coverType){
        super(method, forStatement, lineStartBlockMap, coverType);
        initializers = forStatement.initializers().isEmpty() ? null : (List<Expression>) forStatement.initializers();
        condition = forStatement.getExpression();
        updaters = forStatement.updaters().isEmpty() ? null : forStatement.updaters();
        body = forStatement.getBody();
    }
    @Override
    public String addMark() {
        int offset = statement.getStartPosition() - method.getStartPosition();
        LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset);
        lineMark.setSurroundingControlBlock("for");
        String mark = '\t' + lineMark.print() + ";\n\t" + addInitializerMark() + "\tfor(";
        for(Expression initializer : initializers){
            mark += initializer + ",";
        }
        mark = mark.substring(0, mark.length() - 1);
        mark += "; " + IfMark.addConditionMark(condition, method, coverType) + "; ";
        for (Expression e : updaters){
            mark += e + ", ";
        }
        mark = mark.substring(0, mark.length() - 2);
        mark += ") {\n" + addBodyMark(coverType) + "\t" +  addUpdaterMark() + ";\n\t}\n";
        return mark;
    }

    private String addInitializerMark(){
        String mark = "";
        for(Expression initializer : initializers) {
            int offset = initializer.getStartPosition() - method.getStartPosition();
            LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset, initializers.toString());
            mark += lineMark.print() + ";\n";
        }
        return mark;
    }


    private String addUpdaterMark(){
        String mark = "";
        for(Expression s : updaters){
            if(!mark.isEmpty()){
                mark += ";\n\t ";
            }
            int offset = s.getStartPosition() - method.getStartPosition();
            LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset, s.toString());
            mark += lineMark.print();
        }
        return mark;
    }

    private String addBodyMark(String coverType){
        if(body instanceof Block){
            BlockMark blockMark = new BlockMark((Block) body, method, lineStartBlockMap, true, coverType);
            return blockMark.addMark();
        }
        return "";
    }

    public List<Expression> getInitializer() {
        return initializers;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Expression> getUpdaters() {
        return updaters;
    }

    public Statement getBody() {
        return body;
    }
}
