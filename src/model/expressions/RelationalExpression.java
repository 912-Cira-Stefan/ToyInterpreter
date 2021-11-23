package model.expressions;

import model.ADTs.myDict;
import model.ADTs.myHeap;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.types.Integer;
import model.values.booleanValue;
import model.values.intValue;
import model.values.myValue;

public class RelationalExpression implements myExpression{
    private myExpression exp1,exp2;
    private String op;
    @Override
    public String toString() {
        return  this.exp1.toString() + this.op + this.exp2.toString();
    }

    public RelationalExpression(myExpression exp1, myExpression exp2,String op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    @Override
    public myValue eval(myDict<String, myValue> table, myHeap<myValue> heap) throws EvalException, ADTexception {
        myValue value1, value2;
        value1 = exp1.eval(table,heap);
        if (value1.getType().equals(new Integer())){
            value2 = exp2.eval(table,heap);
            if(value2.getType().equals(new Integer())){
                intValue toInteger1 = (intValue)value1;
                intValue toInteger2 = (intValue)value2;
                int number1,number2;
                number1 = toInteger1.getValue();
                number2 = toInteger2.getValue();
                switch (op){
                    case ">=":
                        return new booleanValue(number1>=number2);
                    case ">":
                        return new booleanValue(number1>number2);
                    case "<=":
                        return new booleanValue(number1<=number2);
                    case "<":
                        return new booleanValue(number1<number2);
                    case "==":
                        return new booleanValue(number1==number2);
                    case "!=":
                        return new booleanValue(number1!=number2);
                    default:
                        throw new EvalException("Invalid operator");
                }
            }
            else throw new ADTexception("Second operand is not an int.");
        }
        else throw new ADTexception("First operand is not an int.");
    }
}
