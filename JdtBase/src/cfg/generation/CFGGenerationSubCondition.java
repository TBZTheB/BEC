package cfg.generation;

import cfg.ICFG;
import cfg.Utils;
import cfg.nodes.*;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;


/**
 * Generate control flow graph from source code for sub-condition coverage
 *
 * @author DucAnh
 */

public class CFGGenerationSubCondition implements ICFGGeneration {

    private MethodDeclaration functionNode;
    /**
     * Type of For block analysis
     */
    private int forModel = ICFGGeneration.DONOT_SEPARATE_FOR;

    public CFGGenerationSubCondition(MethodDeclaration fn, int forModel) {
        this.functionNode = fn;
        this.forModel = forModel;
    }

    @Override
    public ICFG generateCFG()  {
        return parse(functionNode);
    }

    private ICFG parse(MethodDeclaration functionNode)  {
        ICFG cfg = new CFGGeneration(functionNode).generateCFG();
//        cfg.setIdforAllNodes();
        boolean containComplexConditions = true;
        while (containComplexConditions) {
            containComplexConditions = false;

            for (ICfgNode cfgNode : cfg.getAllNodes())
                if (cfgNode instanceof ConditionCfgNode && isComplexCondition((ConditionCfgNode) cfgNode)) {
                    createGraphForComplexCondition((ConditionCfgNode) cfgNode, cfg);
                    containComplexConditions = true;
                    break;
                }
        }
        return cfg;
    }

    private void createGraphForComplexCondition(ConditionCfgNode complexConditionNode, ICFG cfg) {
        Expression a = complexConditionNode.getAstCondition();
        if (a instanceof InfixExpression) {
            Expression left = ((InfixExpression) a).getLeftOperand();
            Expression right = ((InfixExpression) a).getRightOperand();
            ConditionCfgNode leftNode = null;
            ConditionCfgNode rightNode = null;
            InfixExpression.Operator x = ((InfixExpression) a).getOperator();
            if (x.equals(InfixExpression.Operator.CONDITIONAL_AND) || x.equals(InfixExpression.Operator.CONDITIONAL_OR)) {
                if (complexConditionNode instanceof DoConditionCfgNode
                        || complexConditionNode instanceof ConditionElementDoCfgNode) {
                    leftNode = new ConditionElementDoCfgNode(Utils.shortenASTCondition(left));
                    leftNode.setContent(Utils.shortenASTCondition(left).toString());
                    rightNode = new ConditionElementDoCfgNode(Utils.shortenASTCondition(right));
                    rightNode.setContent(Utils.shortenASTCondition(right).toString());
                } else if (complexConditionNode instanceof IfConditionCfgNode
                        || complexConditionNode instanceof ConditionElementIfCfgNode) {
                    leftNode = new ConditionElementIfCfgNode(Utils.shortenASTCondition(left));
                    leftNode.setContent(Utils.shortenASTCondition(left).toString());
                    rightNode = new ConditionElementIfCfgNode(Utils.shortenASTCondition(right));
                    rightNode.setContent(Utils.shortenASTCondition(right).toString());
                } else if (complexConditionNode instanceof ForConditionCfgNode
                        || complexConditionNode instanceof ConditionElementForCfgNode) {
                    leftNode = new ConditionElementForCfgNode(Utils.shortenASTCondition(left));
                    leftNode.setContent(Utils.shortenASTCondition(left).toString());
                    rightNode = new ConditionElementForCfgNode(Utils.shortenASTCondition(right));
                    rightNode.setContent(Utils.shortenASTCondition(right).toString());
                } else if (complexConditionNode instanceof WhileConditionCfgNode
                        || complexConditionNode instanceof ConditionElementWhileCfgNode) {
                    leftNode = new ConditionElementWhileCfgNode(Utils.shortenASTCondition(left));
                    leftNode.setContent(Utils.shortenASTCondition(left).toString());
                    rightNode = new ConditionElementWhileCfgNode(Utils.shortenASTCondition(right));
                    rightNode.setContent(Utils.shortenASTCondition(right).toString());
                }
            }

            if (leftNode != null && rightNode != null) {
                ICfgNode parent = complexConditionNode.getParent();
                ICfgNode falseBranch = complexConditionNode.getFalseNode();
                ICfgNode trueBranch = complexConditionNode.getTrueNode();
                if (x.equals(InfixExpression.Operator.CONDITIONAL_AND)) {
                    leftNode.setParent(parent);
                    leftNode.setTrueNode(rightNode);
                    leftNode.setFalseNode(falseBranch);
                    rightNode.setParent(leftNode);
                    rightNode.setTrueNode(trueBranch);
                    rightNode.setFalseNode(falseBranch);
                } else if (x.equals(InfixExpression.Operator.CONDITIONAL_OR)) {
                    leftNode.setParent(parent);
                    leftNode.setTrueNode(trueBranch);
                    leftNode.setFalseNode(rightNode);
                    rightNode.setParent(leftNode);
                    rightNode.setTrueNode(trueBranch);
                    rightNode.setFalseNode(falseBranch);
                }

                // Update all nodes
                cfg.getAllNodes().add(leftNode);
                cfg.getAllNodes().add(rightNode);
                cfg.getAllNodes().remove(complexConditionNode);

                for (ICfgNode node : cfg.getAllNodes())
                    if (node instanceof ScopeCfgNode) {
                        ScopeCfgNode castNode = (ScopeCfgNode) node;
                        if (castNode.getFalseNode().equals(complexConditionNode)
                                || castNode.getTrueNode().equals(complexConditionNode))
                            ((ScopeCfgNode) node).setBranch(leftNode);

                    } else if (node instanceof NormalCfgNode) {

                        if (((NormalCfgNode) node).isCondition()) {
                            ConditionCfgNode castNode = (ConditionCfgNode) node;

                            if (castNode.getTrueNode().equals(complexConditionNode))
                                castNode.setTrueNode(leftNode);
                            else if (castNode.getFalseNode().equals(complexConditionNode))
                                castNode.setFalseNode(leftNode);

                        } else if (node instanceof SimpleCfgNode) {
                            SimpleCfgNode castNode = (SimpleCfgNode) node;
                            if (castNode.getFalseNode().equals(complexConditionNode)
                                    || castNode.getTrueNode().equals(complexConditionNode))
                                ((SimpleCfgNode) node).setBranch(leftNode);
                        }
                    }
            }


        }

    }

    private boolean isComplexCondition(ConditionCfgNode cfgNode) {
        boolean isComplexCon = false;

        ASTNode ast = cfgNode.getAstCondition();
        Expression a = cfgNode.getAstCondition();
        if (ast != null) {
            if (a instanceof InfixExpression) {
                InfixExpression.Operator x = ((InfixExpression) a).getOperator();
                if (x.equals(InfixExpression.Operator.CONDITIONAL_AND) || x.equals(InfixExpression.Operator.CONDITIONAL_OR)) {
                    isComplexCon = true;
                }
            }
        }
        return isComplexCon;
    }



    @Override
    public MethodDeclaration getFunctionNode() {
        return functionNode;
    }

    @Override
    public void setFunctionNode(MethodDeclaration functionNode) {
        this.functionNode = functionNode;
    }
}

