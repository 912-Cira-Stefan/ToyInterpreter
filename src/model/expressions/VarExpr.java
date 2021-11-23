package model.expressions;

import model.ADTs.myDict;
import model.ADTs.myHeap;
import model.exceptions.ADTexception;
import model.values.myValue;

public class VarExpr implements myExpression{
    String variableName;

    public VarExpr(String variableName) {
        this.variableName = variableName;
    }

    public myValue eval(myDict<String, myValue> table, myHeap<myValue> heap) throws ADTexception {
        return table.lookup(variableName);
    }

    public String toString(){
        return variableName + " ";
    }
}
