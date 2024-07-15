package mark.statement;

import mark.LineMark;
import mark.Method;
import org.eclipse.jdt.core.dom.Statement;

import java.util.SortedMap;

public class ReturnMark extends StatementMark {

    public ReturnMark(Method method, Statement statement, SortedMap<Integer, Boolean> lineStartBlockMap, String coverType) {
        super(method, statement, lineStartBlockMap, coverType);
    }

    @Override
    public String addMark() {
        int offset = statement.getStartPosition() - method.getStartPosition();
        LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset, statement.toString());
        String mark = "\t" + lineMark.print() + ";\n\t";
        LineMark closeBlock = new LineMark();
        closeBlock.setStatement("}");
        while (!lineStartBlockMap.isEmpty()){
            closeBlock.setLineOfBlockInFunction(lineStartBlockMap.lastKey());
            mark += closeBlock.print() + ";\n\t";
            lineStartBlockMap.remove(lineStartBlockMap.lastKey());
        }
        mark += statement;
        return mark;
    }
}
