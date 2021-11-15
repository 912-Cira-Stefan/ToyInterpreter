package model.expressions;

import model.ADTs.myDict;
import model.exceptions.EvalException;
import model.values.myValue;

public class ValueExpr implements myExpression{
    myValue value;

    public ValueExpr(myValue value) {
        this.value = value;
    }

    public myValue eval(myDict<String, myValue> table) throws EvalException {
        return value;
    }
    public String toString(){
        return value.toString() + " ";
    }
}
