package mark.statement;

import mark.Method;
import org.eclipse.jdt.core.dom.Statement;

import java.util.SortedMap;
import java.util.TreeMap;

public abstract class StatementMark {
    // Câu lệnh cần thêm mã đánh dấu
    protected Statement statement;
    // Method chứa câu lệnh
    protected Method method;

    protected String coverType;
    // Danh sách vị trí dòng bắt đầu của các khối bao quanh câu lệnh, và khối lệnh đó có phải khổi lặp hay không
    protected SortedMap<Integer, Boolean> lineStartBlockMap;

    public StatementMark(Method method, Statement statement, SortedMap<Integer, Boolean> lineStartBlockMap, String coverType){
        this.method = method;
        this.statement = statement;
        this.lineStartBlockMap = new TreeMap<>(lineStartBlockMap);
        this.coverType = coverType;
    }

    public StatementMark (){}

    public abstract String addMark();

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public SortedMap<Integer, Boolean> getLineStartBlockMap() {
        return lineStartBlockMap;
    }

    public void setLineStartBlockMap(SortedMap<Integer, Boolean> lineStartBlockMap) {
        this.lineStartBlockMap = lineStartBlockMap;
    }
}
