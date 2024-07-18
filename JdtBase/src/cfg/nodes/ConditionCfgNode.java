package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;
public class ConditionCfgNode extends NormalCfgNode{
    private Expression _astCondition ;

    public ConditionCfgNode ( Expression condition) {
        super();
        this._astCondition = condition;
    }

    public Expression getAstCondition() {
        return this._astCondition;
    }

    public void setAstCondition(Expression value) {
        this._astCondition = value;
    }
}
