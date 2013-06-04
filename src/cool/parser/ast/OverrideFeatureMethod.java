/**
* *
 * Created with IntelliJ IDEA.
 * User: azada
 * Date: 6/2/13
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
package cool.parser.ast;
import cool.symbol.Exeption;
import cool.symbol.SymbolNode;

import java.util.ArrayList;



/**
 * Created with IntelliJ IDEA.
 * User: pedram
 * Date: 5/4/13
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class OverrideFeatureMethod extends FeatureMethod {

    public OverrideFeatureMethod(String id, ArrayList formals, String type, Expr expr ) {
        super(id, formals, type, expr);

    }
    @Override
    public boolean check(SymbolNode pTable) {
        boolean result = true;
        // we should check if this current class has a supertype.
        String superType = pTable.lookup("SUPER").getType();
        if(superType == null){
            Program.addError(new Exeption("this class doesn't have a super class so there is no override method acceptale",this));
            result = false;
            return result;
        }
        // now we should check if the super type has this method
        FeatureMethod temp = Program.fetchMethod(superType,id);
        if(temp == null){
            Program.addError(new Exeption("there is no such method in this type's supper classes ",this));
            return result;
        }
        if (temp.formals.size() != this.formals.size()){
            Program.addError(new Exeption(temp.formals.size()+ " number of argument needed and " + this.formals + " are given",this));
            result = false;
        }
        for (int i = 0 ; i< temp.formals.size() ; i++){
            if (!Program.getInstance().isConsistant(((Formal) (temp.formals.get(i))).type, ((Formal)formals.get(i)).type)){
                Program.addError(new Exeption("type of actuals doesn't match argument list defined in the method "+ id,this));
                result = false;
                break;
            }
        }
        if (Program.typeTableContains(pTable.type)){
            if (Program.getInstance().getTableRow(pTable.type).containsKey(id)){
                Program.addError(new Exeption("method "+ this.id + " has duplicate definitions " , this));
                result = false;
            }
            else{
                Program.getInstance().getTableRow(pTable.type).put(id, this);
            }
        }
        else{
            Program.addError(new Exeption("the scope for this class has not been defined",this));
        }
        //we set the parent node to be the pTable

        this.symbolNode.setParent(pTable);
        for (Object formal : formals) {
            boolean fml = ((Formal) formal).check(this.symbolNode);
            result = result && fml;
        }
        boolean express =  expr.check(this.symbolNode);
        result = result &&  express;
        if(!Program.isConsistant(expr.expType,type)){
            Program.addError(new Exeption("the return type of this expression is not consistant with " + type ,this));
            result = false;
        }
        return result ;



        ///////////////////////here we check if we return the correct type in methods ///////////////////////////////
//        System.out.println("expression type is" + expr.expType);

        ////////////////////////////////////////////////////////////////

    }
}
