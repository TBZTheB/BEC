package mark;

public class LineMark {
    private int lineInFunction = 0;
    private int offset = 0;
    private String statement = null;
    private boolean openingFunction = false;
    private int lineOfBlockInFunction = 0;
    private boolean additionalCode = false;
    private String surroundingControlBlock = null;
    private String isCondition = null;
    private String cover = null;

    public LineMark(int lineInFunction, int offset, String statement) {
        this.lineInFunction = lineInFunction;
        this.offset = offset;
        this.statement = statement;
    }

    public LineMark(int lineInFunction, int offset) {
        this.lineInFunction = lineInFunction;
        this.offset = offset;
    }

    public LineMark() {
    }

    @Override
    public String toString() {
        String mark = "";
        if (lineInFunction != 0) {
            mark += "line-in-function$" + lineInFunction;
        }
        if (offset != 0) {
            mark += "#offset$" + offset;
        }
        if (statement != null) {
            if(statement.charAt(statement.length() - 1) == '\n'){
                statement = statement.substring(0, statement.length() - 1);
            }
            mark += "#statement$" + statement;
        }
        if (lineOfBlockInFunction != 0) {
            mark += "#line-of-block-in-function$" + lineOfBlockInFunction;
        }
        if (openingFunction) {
            mark += "#opening-function$true";
        }
        if (surroundingControlBlock != null){
            mark += "#surrounding-control-block$" + surroundingControlBlock;
        }
        if (additionalCode) {
            mark += "#additional-code$true";
        }
        if (cover != null){
            mark += "#cover$" + cover;
        }

        if (isCondition != null){
            mark += "#is_condition$" + isCondition;
        }
        if(mark.charAt(0) == '#'){
            mark = mark.substring(1, mark.length());
        }
        mark = mark.replaceAll("\"", "\\\\\"");
        return mark;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String print() {
        return "Mark.print(\"" + toString() + "\")";
    }

    public String getIsCondition() {
        return isCondition;
    }

    public void setIsCondition(String isCondition) {
        this.isCondition = isCondition;
    }

    public int getLineOfBlockInFunction() {
        return lineOfBlockInFunction;
    }

    public int getLineInFunction() {
        return lineInFunction;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public boolean isOpeningFunction() {
        return openingFunction;
    }

    public void setOpeningFunction(boolean openingFunction) {
        this.openingFunction = openingFunction;
    }

    public void setLineInFunction(int lineInFunction) {
        this.lineInFunction = lineInFunction;
    }

    public void setLineOfBlockInFunction(int lineOfBlockInFunction) {
        this.lineOfBlockInFunction = lineOfBlockInFunction;
    }

    public boolean isAdditionalCode() {
        return additionalCode;
    }

    public void setAdditionalCode(boolean additionalCode) {
        this.additionalCode = additionalCode;
    }

    public String getSurroundingControlBlock() {
        return surroundingControlBlock;
    }

    public void setSurroundingControlBlock(String surroundingControlBlock) {
        this.surroundingControlBlock = surroundingControlBlock;
    }
}
