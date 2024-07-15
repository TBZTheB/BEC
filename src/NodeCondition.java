import cfg.nodes.ICfgNode;

import java.util.List;

public class NodeCondition {
    private ICfgNode node;

    private boolean trueNode = false;
    private boolean falseNode = false;

    private List<ICfgNode> subConditions;

    public NodeCondition(ICfgNode node){
        this.node = node;
    }

    public NodeCondition(ICfgNode node, List<ICfgNode> subConditions){
        this.node = node;
        this.subConditions = subConditions;
    }

    @Override
    public boolean equals(Object o) {
        NodeCondition node = (NodeCondition) o;
        return false;
    }
    public NodeCondition(){}

    public ICfgNode getNode() {
        return node;
    }

    public void setNode(ICfgNode node) {
        this.node = node;
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

    public List<ICfgNode> getSubConditions() {
        return subConditions;
    }

    public void setSubConditions(List<ICfgNode> subConditions) {
        this.subConditions = subConditions;
    }
}

