package model.expressions;

import model.ADTs.myDict;
import model.ADTs.myHeap;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.values.RefValue;
import model.values.myValue;

public class HeapReadExpression implements myExpression{
    private myExpression exp;

    public HeapReadExpression(myExpression exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "rH("  + exp.toString() + ")";
    }

    @Override
    public myValue eval(myDict<String, myValue> table, myHeap<myValue> heap) throws ADTexception, EvalException {
        myValue evaluationValue = this.exp.eval(table,heap);
        if (evaluationValue instanceof RefValue){
            //downcast to ref value first
            int address = ((RefValue) evaluationValue).getAddress();
            //take the value from the heap if it exists
            myValue valueFromHeap = heap.get(address);
            if (valueFromHeap!=null){
                return valueFromHeap;
            }
            else
                throw new ADTexception("Address doesnt have a value.");

        }
        else
            throw new EvalException("Value is not of type reference value.");
    }
}
