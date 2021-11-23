package model.expressions;

import model.ADTs.myDict;
import model.ADTs.myHeap;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.types.Boolean;
import model.values.booleanValue;
import model.values.myValue;

public class LogicExpr implements myExpression{
    private myExpression express1, express2;
    private String operator;

    public LogicExpr(String operator){
        this.operator = operator;
    }

    @Override
    public myValue eval(myDict<String, myValue> dict, myHeap<myValue> heap) throws ADTexception, EvalException {
        myValue value1 = express1.eval(dict, heap);
        if (!value1.getType().equals(new Boolean()))
            throw new EvalException("invalid boolean operand 1");

        myValue value2 = express2.eval(dict, heap);
        if (!value2.getType().equals(new Boolean()))
            throw new EvalException("invalid boolean operand 2");

        booleanValue bool1 = (booleanValue) value1;
        booleanValue bool2 = (booleanValue) value2;

        return switch (operator) {
            case "and" -> new booleanValue(bool1.getValue() && bool2.getValue());
            case "or" -> new booleanValue(bool1.getValue() || bool2.getValue());
            default -> throw new EvalException("invalid boolean operator");
        };
    }
    public String toString(){
        return express1.toString() + " " + operator + "  " + express2.toString() + " ";
    }
}
