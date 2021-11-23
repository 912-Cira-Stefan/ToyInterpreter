package model.statements;

import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.expressions.myExpression;
import model.values.RefValue;
import model.values.myValue;

import java.io.IOException;

public class NewStatement implements myStatement{
    private String var_name;
    private myExpression exp;

    public NewStatement(String var_name, myExpression exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "new(" + var_name + "," + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException, IOException {
        if (state.getSymbolsDict().isOkay(var_name)){
            myValue value = state.getSymbolsDict().lookup(var_name);
            if (value instanceof RefValue){
                myValue expressionValue = exp.eval(state.getSymbolsDict(),state.getHeap());
                if(expressionValue.getType().equals(((RefValue) value).getLocationType())){ // check if location type of value associated to var_name is the same as the expression evaluation type
                    int location = state.getHeap().allocate(expressionValue);
                    state.getSymbolsDict().update(var_name,new RefValue(location,expressionValue.getType()));
                }
                else throw new ADTexception("Types are not equal");
            }
            else
                throw new EvalException("Value isn't of type ReferenceType");
        }
        else throw new ADTexception("Variable not defined.");
        return null;
    }
}
