package controller;

import model.ADTs.myStack;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.statements.myStatement;
import model.values.RefValue;
import model.values.myValue;
import repository.Repo;
import repository.repoInterface;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class controller implements myController{
    repoInterface repository;
    public controller(Repo r){this.repository = r;}
    public controller(){
        this.repository = new Repo();
    }

    public void addPrgState(PrgState some_prgstate){
        repository.addPrgState(some_prgstate);
    }
    public PrgState one_at_a_time(PrgState state) throws ExecuteException, EvalException, ADTexception, IOException {
        myStack<myStatement> stack = state.getExecStack();
        if(stack.isEmpty())
            throw new ExecuteException("Empty state stack");
        myStatement currentStmt = stack.pop();
        return currentStmt.execute(state);
    }
    public void do_all_at_once() throws ExecuteException, EvalException, ADTexception, Exception {
        repository.clearLog();
        PrgState something = repository.getProgram();
        repository.logPrgState(something);
        //System.out.println(something);
        while (!something.getExecStack().isEmpty()){
            one_at_a_time(something);
            something.getHeap().setContent(safeGarbageCollector
                    (getAddrFromSymTable(something.getSymbolsDict().getDict().values(),
                            something.getHeap().getContent().values()),something.getHeap().getContent()));
            repository.logPrgState(something);
            //System.out.println(something);
        }
    }

    Map<Integer, myValue> safeGarbageCollector(List<Integer> addressesFromSymbolTable, Map<Integer,myValue> heap)
    {
        return heap.entrySet().stream()
                .filter(e->addressesFromSymbolTable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<myValue> symTableValues, Collection<myValue> heap){
        return  Stream.concat(
                        heap.stream()
                                .filter(v-> v instanceof RefValue)
                                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                        ,symTableValues.stream()
                                .filter(v-> v instanceof RefValue)
                                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();}))
                .collect(Collectors.toList());
    }
}
