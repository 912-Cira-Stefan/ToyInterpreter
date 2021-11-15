package repository;

import model.ADTs.OutList;
import model.PrgState;
import model.exceptions.ExecuteException;
import model.expressions.ArtihmeticExpr;
import model.types.Integer;
import model.types.Boolean;
import model.expressions.ValueExpr;
import model.expressions.VarExpr;
import model.statements.*;
import model.values.booleanValue;
import model.values.intValue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repo implements repoInterface{

    OutList<PrgState> lst;
    int index;
    String logFile_path;

    public Repo(){
        this.lst = new OutList<PrgState>();
        this.logFile_path = "";
        index = 0;
    }

    public Repo(String logFile_path) {
        this.lst = new OutList<PrgState>();
        this.logFile_path = logFile_path;
        index = 0;
    }

    public void add(PrgState p){
        lst.add(p);
    }

    @Override
    public PrgState getProgram() {
        return lst.get(index);
    }

    @Override
    public void addPrgState(PrgState some_prgstate) {
        lst.add(some_prgstate);
    }

    @Override
    public void logPrgState(PrgState some_prgstate) throws IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFile_path, true)));

        logFile.write(some_prgstate.toString());
        logFile.close();
    }

    @Override
    public void clearLog() throws Exception {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFile_path, false)));
        logFile.write("");
        logFile.close();
    }
}
