package model.statements;

import model.ADTs.myDict;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.expressions.myExpression;
import model.types.Boolean;
import model.values.booleanValue;
import model.values.myValue;

import java.io.IOException;

public class WhileStatement implements myStatement{
    private myExpression exp;
    private myStatement statement;

    public WhileStatement(myExpression exp, myStatement statement) {
        this.exp = exp;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "(while(" + exp.toString() + ")" + statement.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException, IOException {
        myDict<String, myValue> symbolTable = state.getSymbolsDict();
        myValue result = exp.eval(symbolTable, state.getHeap());
        if (result.getType().equals(new Boolean())){
            booleanValue downcastedResult = (booleanValue) result;
            if (downcastedResult.getValue()){
                state.getExecStack().push(new WhileStatement(exp,statement));
                state.getExecStack().push(statement);
            }
        }
        else
            throw new EvalException("Condition exp is not a boolean.");
        return state;
    }
}
