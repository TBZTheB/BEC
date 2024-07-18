package cover;

public class Node {
    private String content;
    private int lineInFunction;
    private int offset;

    private boolean trueNode = false;
    private boolean falseNode = false;

    public Node(int lineInFunction, int offset){
        this.lineInFunction = lineInFunction;
        this.offset = offset;
    }

    @Override
    public boolean equals(Object o) {
        Node node = (Node) o;
        return false;
    }






    public Node(){}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLineInFunction(int lineInFunction) {
        this.lineInFunction = lineInFunction;
    }

    public int getLineInFunction() {
        return lineInFunction;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isTrueNode() {
        return trueNode;
    }

    public void setTrueNode(boolean trueNode) {
        this.trueNode = trueNode;
    }

    public boolean isFalseNode() {
        return falseNode;
    }

    public void setFalseNode(boolean falseNode) {
        this.falseNode = falseNode;
    }
}

