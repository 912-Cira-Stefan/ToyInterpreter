package model.expressions;

import model.ADTs.myDict;
import model.ADTs.myHeap;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.types.Integer;
import model.values.intValue;
import model.values.myValue;

public class ArtihmeticExpr implements myExpression{
    myExpression express1, express2;
    char operator;

    public ArtihmeticExpr(myExpression ex1, myExpression ex2, char op){
        this.express1 = ex1;
        this.express2 = ex2;
        this.operator = op;
    }

    @Override
    public myValue eval(myDict<String, myValue> dict, myHeap<myValue> heap) throws ADTexception, EvalException {
        myValue val1 = express1.eval(dict, heap);
        if(!val1.getType().equals(new Integer()))
            throw new EvalException("First operand NOT an integer");

        myValue val2 = express2.eval(dict, heap);
        if (!val2.getType().equals(new Integer()))
            throw new EvalException("Second operand NOT an integer");

        intValue value1 = (intValue) val1;
        intValue value2 = (intValue) val2;

        switch (operator){
            case '+':
                return new intValue(value1.getValue() + value2.getValue());
            case '-':
                return new intValue(value1.getValue() - value2.getValue());
            case '*':
                return new intValue(value1.getValue() * value2.getValue());
            case '/':
                if(value2.getValue() == 0)
                    throw new EvalException("tried a division by 0");
                return new intValue(value1.getValue() / value2.getValue());
            default:
                throw new EvalException("What operand are you using?!");
        }
    }

    public String toString(){
        return express1.toString() + " " + operator + " " + this.express2.toString() + " ";
    }

}
