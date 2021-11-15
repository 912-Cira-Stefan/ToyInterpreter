package model.expressions;

import model.ADTs.myDict;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.values.myValue;

public interface myExpression {
    myValue eval(myDict<String, myValue> dict) throws ADTexception, EvalException;
}
