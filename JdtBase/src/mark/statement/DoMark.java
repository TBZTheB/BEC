package mark.statement;

import mark.BlockMark;
import mark.LineMark;
import mark.Method;
import org.eclipse.jdt.core.dom.*;

import java.util.SortedMap;

public class DoMark extends StatementMark{
    private Expression condition;
    private Statement body;
    public DoMark(Method method, DoStatement doStatement, SortedMap<Integer, Boolean> lineStartBlockMap,
             String coverType){
        super(method, doStatement, lineStartBlockMap, coverType);
    }
    @Override
    public String addMark() {
        int offset = statement.getStartPosition() - method.getStartPosition();
        LineMark lineMark = new LineMark(method.getLineInMethod(offset), offset);
        lineMark.setSurroundingControlBlock("do");
        return '\t' + lineMark.print() + ";\n\tdo {\n"
                + addBodyMark(coverType) + "\t} while (" + IfMark.addConditionMark(condition, method, coverType) + ");\n";
    }

    private String addBodyMark(String coverType){
        if(body instanceof Block){
            BlockMark blockMark = new BlockMark((Block) body, method, lineStartBlockMap, true, coverType);
            return blockMark.addMark();
        }
        return "";
    }
}
