package cfg.nodes;

public interface ICfgNode {
    public String getContent();
    public void setContent(String content);
    public double getId();
    public void setId(double id);
    public void setBranch(ICfgNode cfgNode);
    public ICfgNode getTrueNode();
    public void setTrueNode(ICfgNode cfgNode);
    public ICfgNode getFalseNode();
    public void setFalseNode(ICfgNode cfgNode);
    public boolean isVisited();
    public void setVisited(boolean visited);
    public ICfgNode getParent();
    public void setParent(ICfgNode parent);
    boolean isCondition();
    boolean isNormalNode();

}
