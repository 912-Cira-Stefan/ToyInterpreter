package controller;

import model.ADTs.myStack;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.statements.myStatement;
import repository.Repo;
import repository.repoInterface;

import java.io.IOException;

public class controller implements myController{
    repoInterface repository;
    public controller(Repo r){this.repository = r;}
    public controller(){
        this.repository = new Repo();
    }

    public void addPrgState(PrgState some_prgstate){
        repository.addPrgState(some_prgstate);
    }
    public PrgState one_at_a_time(PrgState state) throws ExecuteException, EvalException, ADTexception {
        myStack<myStatement> stack = state.getExecStack();
        if(stack.isEmpty())
            throw new ExecuteException("Empty state stack");
        myStatement currentStmt = stack.pop();
        try{
            return currentStmt.execute(state);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void do_all_at_once() throws ExecuteException, EvalException, ADTexception, Exception {
        repository.clearLog();
        PrgState something = repository.getProgram();
        repository.logPrgState(something);
        //System.out.println(something);
        while (!something.getExecStack().isEmpty()){
            one_at_a_time(something);
            repository.logPrgState(something);
            //System.out.println(something);
        }
    }
}
