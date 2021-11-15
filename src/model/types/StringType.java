package model.types;

import model.values.StringValue;
import model.values.myValue;

public class StringType implements myType{
     @Override
    public boolean equals(Object other)
    {
        return other instanceof StringType;
    }

    @Override
    public myValue defaultValue()
    {
        return new StringValue("");
    }

    @Override
    public String toString()
    {
        return "str";
    }
}
