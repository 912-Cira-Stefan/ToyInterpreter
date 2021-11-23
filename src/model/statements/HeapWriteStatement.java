package model.statements;

import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.expressions.myExpression;
import model.types.myType;
import model.values.RefValue;
import model.values.myValue;

import java.io.IOException;

public class HeapWriteStatement implements myStatement{
    private String var_name;
    private myExpression exp;

    public HeapWriteStatement(String var_name, myExpression exp) {
        this.var_name = var_name;
        this.exp = exp;
    }
    @Override
    public String toString() {
        return "wH(" + var_name + "," + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException, IOException {
        if (state.getSymbolsDict().isOkay(this.var_name)){
            myValue val = state.getSymbolsDict().lookup(this.var_name);
            if (val instanceof RefValue){
                int address = ((RefValue)val).getAddress();
                if (state.getHeap().get(address)!=null){ // check if there's anything at that address
                    myValue evaluationValue = this.exp.eval(state.getSymbolsDict(),state.getHeap());
                    if (evaluationValue.getType().equals(((RefValue) val).getLocationType())){ //check if the types are equal and update the value at that address in the heap
                        state.getHeap().put(address,evaluationValue);
                    }
                    else
                        throw new EvalException("Incompatible types.");
                }
                else
                    throw new EvalException("Address is not a key in the heap.");
            }
            else
                throw new IOException("Value not of type Reference type");
        }
        else
            throw new EvalException("Variable not defined.");
        return null;
    }
}
