import cfg.nodes.ICfgNode;

import java.util.ArrayList;
import java.util.List;

public class NodeC1 {
    private ICfgNode node;
    private List<ICfgNode> subconditionsNode;
    boolean covered;

    public NodeC1(ICfgNode node){
        this.node = node;
        subconditionsNode = new ArrayList<>();
    }

    public NodeC1(ICfgNode node, List<ICfgNode> subconditionsNode){
        this.node = node;
        this.subconditionsNode = subconditionsNode;
    }

    public ICfgNode getNode() {
        return node;
    }

    public void setNode(ICfgNode node) {
        this.node = node;
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }

    public List<ICfgNode> getSubconditionsNode() {
        return subconditionsNode;
    }

    public void setSubconditionsNode(List<ICfgNode> subconditionsNode) {
        this.subconditionsNode = subconditionsNode;
    }
}
