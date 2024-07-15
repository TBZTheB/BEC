package mark.statement;

import mark.LineMark;
import mark.Method;
import org.eclipse.jdt.core.dom.BreakStatement;

import java.util.SortedMap;

public class BreakMark extends StatementMark{

    public BreakMark(Method method, BreakStatement breakStatement, SortedMap<Integer, Boolean> lineStartBlockMap, String coverType){
        super(method, breakStatement, lineStartBlockMap, coverType);
    }
    @Override
    public String addMark() {
        int offset = statement.getStartPosition() - method.getStartPosition();
        LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset, statement.toString());
        String mark = "\t" + lineMark.print() + ";\n\t";
        LineMark closeBlock = new LineMark();
        closeBlock.setStatement("}");
        closeBlock.setLineOfBlockInFunction(lineStartBlockMap.lastKey());
        mark += closeBlock.print() + ";\n\t";
        while (!lineStartBlockMap.get(lineStartBlockMap.lastKey())) {
            lineStartBlockMap.remove(lineStartBlockMap.lastKey());
            closeBlock.setLineOfBlockInFunction(lineStartBlockMap.lastKey());
            mark += closeBlock.print() + ";\n\t";
        }
        mark += statement;
        return mark;
    }
}
