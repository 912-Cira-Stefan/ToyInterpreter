package model.statements;

import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;

public class nopStatement implements myStatement{
    @Override
    public PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException {
        return state;
    }
}
