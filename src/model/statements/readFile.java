package model.statements;

import model.ADTs.myDict;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.expressions.myExpression;
import model.types.Integer;
import model.types.StringType;
import model.types.myType;
import model.values.StringValue;
import model.values.intValue;
import model.values.myValue;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements myStatement{
    private myExpression expression;
    private String id;

    public readFile(myExpression expression, String id) {
        this.expression = expression;
        this.id = id;
    }

    @Override
    public PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException, IOException {
        myDict<String, myValue> symbolsTable = state.getSymbolsDict();
        myValue value = symbolsTable.lookup(id);
        if(value != null)
        {
            myType type = value.getType();
            if(type.equals(new Integer()))
            {
                myValue expressionValue = expression.eval(symbolsTable);
                myType expressionType = expressionValue.getType();
                if(expressionType.equals(new StringType()))
                {
                    StringValue filename = (StringValue)expressionValue;
                    myDict<StringValue, BufferedReader> fileTable = state.getFileTable();
                    BufferedReader reader = fileTable.lookup(filename);

                    myValue readValue;
                    if(reader != null)
                    {
                        String line = reader.readLine();

                        if(line == null) // file is empty
                        {
                            readValue = new intValue(0);
                        }
                        else
                        {
                            int some_value = java.lang.Integer.parseInt(line);
                            readValue = new intValue(some_value);
                        }

                        symbolsTable.update(id, readValue);
                    }
                    else
                        throw new ExecuteException("The file " + filename.toString() + " cannot be found!");

                }
                else
                    throw new EvalException("The expression " + expression.toString() +
                            " does not evaluate to a StringValue ( " + expressionType.toString() + ")!");
            }
            else
                throw new EvalException("The variable " + id + " is not an integer ( " + type.toString() + ")!");
        }
        else
            throw new EvalException("The variable " + id + " does not exist!");
        return null;
    }

    @Override
    public String toString()
    {
        return "read( " + expression.toString() + ", " + id + " ); ";
    }
}
