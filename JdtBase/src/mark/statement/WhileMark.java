package mark.statement;

import mark.BlockMark;
import mark.LineMark;
import mark.Method;
import org.eclipse.jdt.core.dom.*;

import java.util.SortedMap;

public class WhileMark extends StatementMark {
    private Expression condition;
    private Statement body;

    public WhileMark(Method method, WhileStatement whileStatement, SortedMap<Integer, Boolean> lineStartBlockMap,
                     String coverType){
        super(method, whileStatement, lineStartBlockMap, coverType);
        condition = whileStatement.getExpression();
        body = whileStatement.getBody();
    }
    @Override
    public String addMark() {
        int offset = statement.getStartPosition() - method.getStartPosition();
        LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset);
        lineMark.setSurroundingControlBlock("while");
        return '\t' + lineMark.print() + ";\n\twhile (" + IfMark.addConditionMark(condition, method, coverType) + ") {\n"
                + addBodyMark() + "\t}\n";
    }

    private String addBodyMark(){
        if(body instanceof Block){
            BlockMark blockMark = new BlockMark((Block) body, method, lineStartBlockMap, true, coverType);
            return blockMark.addMark();
        }
        return "";
    }
}
