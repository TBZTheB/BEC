package mark.statement;

import mark.BlockMark;
import mark.LineMark;
import mark.Method;
import org.eclipse.jdt.core.dom.*;

import java.util.List;
import java.util.SortedMap;

public class TryMark extends StatementMark{

    private Block body;
    private List<CatchClause> catchClauses;
    private Block finallyBlock;


    public TryMark(Method method, TryStatement tryStatement, SortedMap<Integer, Boolean> lineStartBlockMap, String coverType){
        super(method, tryStatement, lineStartBlockMap, coverType);
        body = tryStatement.getBody();
        catchClauses = tryStatement.catchClauses();
        finallyBlock = tryStatement.getFinally();
    }

     @Override
    public String addMark() {
         String mark = "\ttry {\n" + addBodyMark() + "\t}";
         for (CatchClause catchClause : catchClauses) {
             mark += addCatchClauseMark(catchClause);
         }
         mark += addFinallyMark();
        return mark;
    }

    private String addBodyMark(){
        BlockMark bodyMark = new BlockMark(body, method, lineStartBlockMap, false, coverType);
        return bodyMark.addMark();
    }

    private String addCatchClauseMark(CatchClause catchClause){
        SingleVariableDeclaration condition = catchClause.getException();
        Block catchBody = catchClause.getBody();
        BlockMark blockMark = new BlockMark(catchBody, method, lineStartBlockMap, false, coverType);
        int offset = condition.getStartPosition() - method.getStartPosition();
        LineMark conditionMark = new LineMark(method.getLineInMethod(offset), offset, condition.toString());
        return " catch (" + condition + ") {\n\t" + conditionMark.print() + ";\n" + blockMark.addMark() + "\t}";
    }

    private String addFinallyMark(){
        LineMark lineMark = new LineMark();
        lineMark.setStatement("finally");
        BlockMark finallyMark = new BlockMark(finallyBlock, method, lineStartBlockMap, false, coverType);
        return "finally {\n\t" + lineMark.print() + ";\n" + finallyMark.addMark() + "\t}";
    }

    public void setBody(Block body) {
        this.body = body;
    }

    public Block getBody() {
        return body;
    }

    public List<CatchClause> getCatchClauses() {
        return catchClauses;
    }

    public void setCatchClauses(List<CatchClause> catchClauses) {
        this.catchClauses = catchClauses;
    }

    public Block getFinallyBlock() {
        return finallyBlock;
    }

    public void setFinallyBlock(Block finallyBlock) {
        this.finallyBlock = finallyBlock;
    }
}
