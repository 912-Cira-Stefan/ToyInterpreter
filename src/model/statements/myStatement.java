package model.statements;

import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;

import java.io.IOException;

public interface myStatement {
    PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException, IOException;
}
