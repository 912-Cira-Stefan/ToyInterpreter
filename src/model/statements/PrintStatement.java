package model.statements;

import model.ADTs.myDict;
import model.ADTs.myList;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.expressions.myExpression;
import model.values.myValue;

public class PrintStatement implements myStatement{
    myExpression express1;

    public PrintStatement(myExpression ex1){
        this.express1 = ex1;
    }

    @Override
    public String toString() {
        return "Print{" +
                express1 +
                '}';
    }

    @Override
    public PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException {
        myList<myValue> out = state.getOutput();
        myDict<String, myValue> symTable = state.getSymbolsDict();
        out.add(express1.eval(symTable));
        return state;
    }
}
