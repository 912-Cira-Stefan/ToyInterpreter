package model;

import model.ADTs.myDict;
import model.ADTs.myHeap;
import model.ADTs.myList;
import model.ADTs.myStack;
import model.exceptions.ExecuteException;
import model.statements.myStatement;
import model.values.StringValue;
import model.values.myValue;

import java.io.BufferedReader;

public class PrgState {
    myStack<myStatement> execStack;
    myDict<String, myValue> symbolsDict;
    myDict<StringValue, BufferedReader> fileTable;
    myList<myValue> output;
    myHeap<myValue> Heap;
    myStatement originalProgram;

//    override toString
//    getters / setters
public PrgState(myStack<myStatement> executionStack,
                myDict<String, myValue> symbolsDict,
                myHeap<myValue> heap,
                myDict<StringValue, BufferedReader> fileTable,
                myList<myValue> output,
                myStatement program
){
        this.execStack = executionStack;
        this.symbolsDict = symbolsDict;
        this.Heap = heap;
        this.fileTable = fileTable;
        this.output = output;

//         originalProgram = deepCopy();
        executionStack.push(program);
    }

    public myHeap<myValue> getHeap() {
        return Heap;
    }

    public void setHeap(myHeap<myValue> heap) {
        Heap = heap;
    }

    public myStack<myStatement> getExecStack() {
        return execStack;
    }

    public myDict<String, myValue> getSymbolsDict(){
        return symbolsDict;
    }

    public myDict<StringValue, BufferedReader> getFileTable(){return fileTable;}

    public myList<myValue> getOutput() {
        return output;
    }


    public PrgState execute(PrgState state) throws ExecuteException{
        return state;
    }

    public String toString() {
        return "PrgState{" +
                "\nexecutionStack = " + execStack +
                "\nsymbDict = " + symbolsDict +
                "\nout = " + output +
                "\nHeap = " + Heap.toString() +
                "\n}----------------------------------------------------------\n";
    }
}
