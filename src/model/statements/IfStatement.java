package model.statements;

import model.ADTs.myDict;
import model.ADTs.myList;
import model.ADTs.myStack;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.expressions.myExpression;
import model.types.Boolean;
import model.values.booleanValue;
import model.values.myValue;

public class IfStatement implements myStatement{
    myExpression expr1;
    myStatement thenStmt, elseStmt;

    public IfStatement(myExpression ex1, myStatement s1, myStatement s2){
        this.expr1 = ex1;
        this.thenStmt = s1;
        this.elseStmt = s2;
    }

    @Override
    public String toString() {
        return "If " + expr1 +
                " then " + thenStmt +
                "else " + elseStmt +
                '}';
    }

    @Override
    public PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException {
        myList<myValue> out = state.getOutput();
        myStack<myStatement> stk = state.getExecStack();
        myDict<String, myValue> symTable = state.getSymbolsDict();
        if (!expr1.eval(symTable, state.getHeap()).getType().equals(new Boolean()))
            throw new ExecuteException("condtitional expression is not a boolean\n");
        booleanValue auxbool = (booleanValue) expr1.eval(symTable, state.getHeap());
        if (auxbool.getValue()){
            stk.push(thenStmt);
        }else{
            stk.push(elseStmt);
        }
        return state;
    }
}
