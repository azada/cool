package cool.parser.ast;

import cool.symbol.Exeption;
import cool.symbol.SymbolNode;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: azada
 * Date: 5/21/13
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssignmentOperation extends UnitOperation{

    public AssignmentOperation(ArrayList operands) {
        operandsList = operands;
    }

    @Override
    public boolean check(SymbolNode pTable) {
        boolean result = true;
        for (Object operand : operandsList) {
            boolean fml = ((Expr) operand).check(pTable);
            result = result && fml;
        }
        if (!Program.isConsistant(((Expr)(operandsList.get(1))).expType,((Expr)(operandsList.get(0))).expType)){
             Program.addError(new Exeption("expression on the right do not match the expression on the left",this));
            result = false;
        }
        this.expType = UNIT_TYPE;
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void accept() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
