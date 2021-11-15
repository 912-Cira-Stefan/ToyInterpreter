package model.statements;

import model.ADTs.myStack;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;

public class CompdStatement implements myStatement{
    myStatement stmt1, stmt2;

    public CompdStatement(myStatement s1, myStatement s2){
        this.stmt1 = s1;
        this.stmt2 = s2;
    }

    public String toString(){
        return "(" + stmt1.toString() + ";" + stmt2.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException {
        myStack<myStatement> execStack = state.getExecStack();
        execStack.push(stmt2);
        execStack.push(stmt1);
        return state;
    }
}
