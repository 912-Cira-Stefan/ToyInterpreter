package model.expressions;

import model.ADTs.myDict;
import model.ADTs.myHeap;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.values.myValue;

public class ValueExpr implements myExpression{
    myValue value;

    public ValueExpr(myValue value) {
        this.value = value;
    }

    public String toString(){
        return value.toString() + " ";
    }

    @Override
    public myValue eval(myDict<String, myValue> dict, myHeap<myValue> heap) throws ADTexception, EvalException {
        return value;
    }
}
