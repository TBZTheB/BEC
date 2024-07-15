package mark;

import mark.statement.*;
import org.eclipse.jdt.core.dom.*;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class BlockMark {
    private List<Statement> statements;

    private Method method;

    private boolean openingFunction;

    private SortedMap<Integer, Boolean> lineStartBlockMap;

    private String coverType;


    public BlockMark(Block block, Method method, SortedMap<Integer, Boolean> lineStartBlockMap, boolean isLoop, String coverType){
        statements = block.statements();
        this.method = method;
        this.openingFunction = false;
        this.coverType = coverType;
        this.lineStartBlockMap = new TreeMap<>(lineStartBlockMap);
        this.lineStartBlockMap.put(method.getLineInMethod(block.getStartPosition() - method.getStartPosition()), isLoop);
    }

    public BlockMark(Block block, Method method, boolean openingFunction, String coverType){
        statements = block.statements();
        this.method = method;
        this.openingFunction = openingFunction;
        this.coverType = coverType;
        lineStartBlockMap = new TreeMap<>();
        lineStartBlockMap.put(method.getLineInMethod(block.getStartPosition() - method.getStartPosition()), false);
    }

    public String addMark(){
        LineMark openingBlock = new LineMark();
        openingBlock.setStatement("{");
        openingBlock.setLineOfBlockInFunction(lineStartBlockMap.lastKey());
        openingBlock.setOpeningFunction(openingFunction);
        String mark = "\t" + openingBlock.print() + ";\n";
        for (Statement statement : statements){
            if(statement instanceof IfStatement){
                IfMark ifMark = new IfMark(method, (IfStatement) statement, lineStartBlockMap, coverType);
                mark += ifMark.addMark();
            } else if(statement instanceof ForStatement){
                ForMark forMark = new ForMark(method, (ForStatement) statement, lineStartBlockMap, coverType);
                mark += forMark.addMark();
            }else if(statement instanceof WhileStatement){
                WhileMark whileMark = new WhileMark(method, (WhileStatement) statement, lineStartBlockMap, coverType);
                mark += whileMark.addMark();
            } else if(statement instanceof DoStatement){
                DoMark doMark = new DoMark(method, (DoStatement) statement, lineStartBlockMap, coverType);
                mark += doMark.addMark();
            } else if (statement instanceof SwitchStatement){
                SwitchMark switchMark = new SwitchMark(method, (SwitchStatement) statement, lineStartBlockMap,
                        coverType);
                mark += switchMark.addMark();
            } else if (statement instanceof EnhancedForStatement){
                ForEachMark forEachMark = new ForEachMark(method, (EnhancedForStatement) statement, lineStartBlockMap
                        , coverType);
                mark += forEachMark.addMark();
            } else if (statement instanceof TryStatement) {
                TryMark tryMark = new TryMark(method, (TryStatement) statement, lineStartBlockMap, coverType);
                mark += tryMark.addMark();
            } else if (statement instanceof ReturnStatement){
                ReturnMark returnMark = new ReturnMark(method, statement, lineStartBlockMap, coverType);
                mark += returnMark.addMark();
            } else if (statement instanceof BreakStatement){
                BreakMark breakContinueMark = new BreakMark(method,(BreakStatement) statement, lineStartBlockMap, coverType);
                mark += breakContinueMark.addMark();
            } else if (statement instanceof ContinueStatement){
                ContinueMark continueMark = new ContinueMark(method, (ContinueStatement) statement, lineStartBlockMap
                        , coverType);
                mark += continueMark.addMark();
            } else if (statement instanceof ThrowStatement) {
                ThrowMark throwMark = new ThrowMark(method, (ThrowStatement) statement, lineStartBlockMap, coverType);
                mark += throwMark.addMark();
            } else if (statement instanceof VariableDeclarationStatement){
                System.out.println(statement);
                System.out.println("câu lênh khởi tạo\n\n\n\n\n\n\n\n");
            }
            else  {
                SequentialMark sequentialMark = new SequentialMark(method, statement, lineStartBlockMap, coverType);
                mark += sequentialMark.addMark();
            }
        }

        if (!statements.isEmpty()) {
            Statement statement = statements.get(statements.size() - 1);
            if(!((statement instanceof ReturnStatement) || (statement instanceof BreakStatement)
                    || (statement instanceof ContinueStatement) || (statement instanceof ThrowStatement))){
                LineMark closeBlock = new LineMark();
                closeBlock.setStatement("}");
                closeBlock.setLineOfBlockInFunction(lineStartBlockMap.lastKey());
                mark += "\t" + closeBlock.print() + ";\n";
            }
        }
        return mark;
    }
    public List<Statement> getStatements() {
        return statements;
    }

    public Method getMethod() {
        return method;
    }

    public void setOpeningFunction(boolean openingFunction) {
        this.openingFunction = openingFunction;
    }

    public boolean isOpeningFunction() {
        return openingFunction;
    }
}
