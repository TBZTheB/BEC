package cfg.generation.testpath.Check;

import cfg.nodes.BeginFlagCfgNode;
import cfg.nodes.EndFlagCfgNode;
import cfg.nodes.ICfgNode;
import cfg.nodes.ScopeCfgNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.List;

public interface ITestpath {
    String SEPARATE_BETWEEN_NODES = "=>";

    String[] SPECIAL_SATEMENTS = { BeginFlagCfgNode.BEGIN_FLAG, EndFlagCfgNode.END_FLAG, ScopeCfgNode.CLOSE_SCOPE,
            ScopeCfgNode.OPEN_SCOPE };

    /**
     * Count the number of occurrences of a node in test path
     *
     * @param stm
     * @return
     */
    int count(ICfgNode stm);

    /**
     * Tráº£ vá»� Ä‘Æ°á»�ng thi hÃ nh rÃºt gá»�n (dÃ¹ Ä‘Ãºng hay sai, Ä‘iá»ƒm
     * quyáº¿t Ä‘á»‹nh lÃ  giá»‘ng nhau)
     *
     * @return
     */
    String toReducedString();

    /**
     * Tráº£ vá»� Ä‘Æ°á»�ng thi hÃ nh mÃ´ táº£ Ä‘iá»ƒm quyáº¿t Ä‘á»‹nh thá»ƒ hiá»‡n
     * rÃµ rÃ ng Ä‘Ãºng hay sai
     */
    @Override
    String toString();

    /**
     * Return true if the next node is belonged to the true branch
     *
     * @param currentNode
     * @param indexofCurrentNode
     * @return
     */
    boolean nextIsTrueBranch(ICfgNode currentNode, int indexofCurrentNode);

    List<ICfgNode> getAllCfgNodes();

    /**
     * Generate test data using symbolic execution
     *
     * @return
     */
//    com.fit.cfg.testpath.IStaticSolutionGeneration generateTestdata();

    /**
     * Get the function node containing this test path
     *
     * @return
     */
//    IFunctionNode getFunctionNode();

    /**
     *            the function contains the current test path
     */
    void setFunctionNode(MethodDeclaration Node);

    /**
     * Get number of statements in the test path, not including "{","}", begin node,
     * end node
     *
     * @return
     */
    int getRealSize();

    /**
     * Get number of conditions
     * <p>
     * Ex: Testpath: (a>b) => { => ... => } => (a>b)
     * <p>
     * where (a>b) is condition of loop. The test path includes the start node of
     * loop, end the end node of the loop.
     * <p>
     * Therefore, the return value is 2
     *
     * @return
     */
    int getNumConditionsIncludingNegativeConditon();

    /**
     * Get number of loop conditions
     * <p>
     * Ex: Testpath: (a>b) => { => ... => } => (a>b)
     * <p>
     * where (a>b) is condition of loop. The test path includes the start node of
     * loop, end the end node of the loop.
     * <p>
     * We only consider the start of loop. Therefore, the return value is 1
     *
     * @return
     */
    int getNumLoopConditions();

    /**
     * Get condition at specified location
     *
     * @param idCondition
     * @return
     */
    ICfgNode getConditionAt(int idCondition);

    AbstractTestpath cast();

    /**
     * Return content of test path. This content reveals the boolean of condition.
     * <br/>
     * For example, A->B->C, B is condition, C is executed when B is false. Full
     * path here: A->!(B)->C
     */
    String getFullPath();

//    String getDescription();
//
//    void setDescription(String description);
}
