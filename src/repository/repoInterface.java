package repository;

import model.PrgState;
import model.exceptions.ExecuteException;

import java.io.IOException;

public interface repoInterface {
    PrgState getProgram();
    void addPrgState(PrgState some_prgstate);
    void logPrgState(PrgState some_prgstate) throws IOException;
    public void clearLog() throws Exception;
}
