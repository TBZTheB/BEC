package mark.statement;

import mark.LineMark;
import mark.Method;
import org.eclipse.jdt.core.dom.Statement;

import java.util.SortedMap;

public class SequentialMark extends StatementMark{
    public SequentialMark(Method method, Statement statement, SortedMap<Integer, Boolean> lineStartBlockMap, String coverType){
        super(method, statement, lineStartBlockMap, coverType);
    }
    @Override
    public String addMark() {
        int offset = statement.getStartPosition() - method.getStartPosition();
        LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset, statement.toString());
        return "\t" + lineMark.print() + ";\n\t" + statement;
    }

}
