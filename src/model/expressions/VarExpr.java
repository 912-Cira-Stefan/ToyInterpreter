package model.expressions;

import model.ADTs.myDict;
import model.exceptions.ADTexception;
import model.values.myValue;

public class VarExpr implements myExpression{
    String variableName;

    public VarExpr(String variableName) {
        this.variableName = variableName;
    }

    public myValue eval(myDict<String, myValue> table) throws ADTexception {
        return table.lookup(variableName);
    }

    public String toString(){
        return variableName + " ";
    }
}
