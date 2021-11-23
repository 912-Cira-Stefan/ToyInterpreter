package model.statements;

import model.ADTs.myDict;
import model.ADTs.myStack;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.expressions.myExpression;
import model.types.myType;
import model.values.myValue;

public class AssigStatement implements myStatement{
    String variableName;
    myExpression expression;

    public AssigStatement(String variableName, myExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }
    //    ...

    public String toString(){
        return variableName + "=" + expression.toString();
    }

    public PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException {
        myStack<myStatement> executionStack = state.getExecStack();
        myDict<String, myValue> symbolsDict = state.getSymbolsDict();

        if(symbolsDict.isOkay(variableName)){
            myValue expressionValue = expression.eval(symbolsDict, state.getHeap());
            myType variableType = symbolsDict.lookup(variableName).getType();

            if(expressionValue.getType().equals(variableType)){
                symbolsDict.update(variableName, expressionValue);
            } else {
                throw new EvalException( "declared type of variable" + variableName + " and current type of the assigned expression do not match"
                );
            }
        } else{
            throw new EvalException("the used variable" + variableName + " was not declared before");
        }
        return state;
    }
}
