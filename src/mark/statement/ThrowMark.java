package mark.statement;

import mark.LineMark;
import mark.Method;
import org.eclipse.jdt.core.dom.ThrowStatement;

import java.util.SortedMap;

public class ThrowMark extends StatementMark{
    public ThrowMark(Method method, ThrowStatement throwStatement, SortedMap<Integer, Boolean> lineStartBlockMap,
                     String coverType){
        super(method, throwStatement, lineStartBlockMap, coverType);
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
//        while (!lineStartBlockMap.get(lineStartBlockMap.lastKey())) {
//            lineStartBlockMap.remove(lineStartBlockMap.lastKey());
//            closeBlock.setLineOfBlockInFunction(lineStartBlockMap.lastKey());
//            mark += closeBlock.print() + ";\n\t";
//        }
        mark += statement;
        return mark;
    }

}
