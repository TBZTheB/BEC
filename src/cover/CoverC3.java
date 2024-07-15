package cover;

import cfg.nodes.*;
import mark.Method;

import java.util.ArrayList;
import java.util.List;

public class CoverC3 extends Cover{

    List<Node> nodeConditions = new ArrayList<>();
    public CoverC3(List<String> pathMarks, String filePath, Method method){
        super(pathMarks,filePath, method);
    }

    @Override
    public String getCover() {
        int numberBranchPass = 0;
        boolean checkCondition = false;
        int offset = 0;
        for (String mark : marks) {
            if (checkCondition) {
                if (findIsCondition(mark) != null && findIsCondition(mark).equals("true")) {
                    for (Node node : nodeConditions) {
                        if (node.getOffset() == offset) {
                            node.setTrueNode(true);
                        }
                    }
                } else {
                    for (Node node : nodeConditions) {
                        if (node.getOffset() == offset) {
                            node.setFalseNode(true);
                        }
                    }
                }
                checkCondition = false;
            }

            if (findIsCondition(mark) != null && findIsCondition(mark).equals("false")) {
                Node node = new Node(findLineInFunction(mark), findOffset(mark));
                offset = findOffset(mark);
                if (!containsNodes(node)) {
                    nodeConditions.add(node);
                }
                checkCondition = true;
            }
            if (!lineInFunctions.contains(findLineInFunction(mark)) && !offsets.contains(findOffset(mark))) {
                if (markIsNode(mark)) {
                    lineInFunctions.add(findLineInFunction(mark));
                    offsets.add(findOffset(mark));
                    System.out.println(mark);
                }
            }
        }

        for (Node nodeCondition : nodeConditions) {
            System.out.println(nodeCondition.isTrueNode());
            System.out.println(nodeCondition.isFalseNode());
            System.out.println();
            if (nodeCondition.isTrueNode()) {
                numberBranchPass++;
            }
            if (nodeCondition.isFalseNode()) {
                numberBranchPass++;
            }
        }
        return  numberBranchPass + "/" + getBranchNumber();
    }

    public int getBranchNumber(){
        int count = 0;
        for(ICfgNode node : cfgsubcondition.getAllNodes()){
//            if(node instanceof ConditionElementIfCfgNode || node instanceof ConditionElementForCfgNode || node instanceof ConditionElementDoCfgNode || node instanceof ConditionElementWhileCfgNode || node instanceof ConditionCfgNode){
            if (node instanceof ConditionCfgNode){
                count ++;
            }
        }
        return count * 2;
    }

    private boolean containsNodes(Node node){
        for(Node node1 : nodeConditions){
            if(node1.getOffset() == node.getOffset()){
                return true;
            }
        }
        return false;
    }
}
