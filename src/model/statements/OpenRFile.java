package model.statements;

import model.ADTs.FileTable;
import model.ADTs.myDict;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.expressions.myExpression;
import model.types.StringType;
import model.types.myType;
import model.values.StringValue;
import model.values.myValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements myStatement{
    myExpression expression;

    public OpenRFile(myExpression expression)
    {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws IOException, EvalException, ADTexception {
        myValue value = expression.eval(state.getSymbolsDict());
        myType type = value.getType();
        if(type.equals(new StringType()))
        {
            myDict<StringValue, BufferedReader> fileTable = state.getFileTable();

            StringValue stringValue = (StringValue)value;
            BufferedReader reader = fileTable.lookup(stringValue);
            if (reader == null)
            {
                String filename = stringValue.getValue();
                reader = new BufferedReader(new FileReader(filename));
                fileTable.update(stringValue, reader);
            }
            else
                throw new EvalException("A file named " + stringValue + " already exists!");
        }
        else
            throw new ADTexception("The filename shall be a String!");

        return null;
    }

    @Override
    public String toString()
    {
        return "openRead( " + expression.toString() + " ); ";
    }
}
