package mark.statement;

import mark.BlockMark;
import mark.LineMark;
import mark.Method;
import org.eclipse.jdt.core.dom.*;

import java.util.SortedMap;

public class ForEachMark extends StatementMark{

    private SingleVariableDeclaration parameter;
    private Expression condition;
    private Statement body;

    public ForEachMark (Method method, EnhancedForStatement statement, SortedMap<Integer, Boolean> lineStartBlockMap,
                        String coverType                   ) {
        super(method, statement, lineStartBlockMap, coverType);
        parameter = statement.getParameter();
        condition = statement.getExpression();
        body = statement.getBody();
    }
    @Override
    public String addMark() {
        int offset = statement.getStartPosition() - method.getStartPosition();
        LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset);
        lineMark.setSurroundingControlBlock("forEach");
        return '\t' + lineMark.print() + ";\n\t" + addConditionMark() + ";\n\tfor(" + parameter + " : "
                + condition + ") {\n" + addBodyMark(coverType) + '\t' + addConditionMark() + ";\n\t}\n";
    }

    private String addConditionMark(){
        int offset = parameter.getStartPosition() - method.getStartPosition();
        LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset, parameter + " : " + condition);
        return lineMark.print();
    }

    private String addBodyMark(String coverType){
        if(body instanceof Block) {
            BlockMark blockMark = new BlockMark((Block) body, method, lineStartBlockMap, true, coverType);
            return blockMark.addMark();
        }
        return "";
    }

    public SingleVariableDeclaration getParameter() {
        return parameter;
    }

    public void setParameter(SingleVariableDeclaration parameter) {
        this.parameter = parameter;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getBody() {
        return body;
    }

    public void setBody(Statement body) {
        this.body = body;
    }
}
