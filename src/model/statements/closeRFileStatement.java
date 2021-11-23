package model.statements;

import model.ADTs.myDict;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.expressions.myExpression;
import model.types.StringType;
import model.types.myType;
import model.values.StringValue;
import model.values.myValue;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFileStatement implements myStatement{
    myExpression expression;

    public closeRFileStatement(myExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException, IOException {
        myValue value = expression.eval(state.getSymbolsDict(), state.getHeap());
        myType type = value.getType();
        if(type.equals(new StringType()))
        {
            myDict<StringValue, BufferedReader> fileTable = state.getFileTable();

            StringValue stringValue = (StringValue)value;
            BufferedReader reader = fileTable.lookup(stringValue);
            if (reader != null)
            {
                reader.close();
            }
            else
                throw new ExecuteException("The file " + stringValue.toString() + " cannot be found!");
        }
        else
            throw new ADTexception("The filename shall be a String!");

        return null;
    }

    @Override
    public String toString()
    {
        return "close( " + expression.toString() + " ); ";
    }
}
