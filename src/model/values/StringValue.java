package model.values;

import model.types.StringType;
import model.types.myType;

public class StringValue implements myValue{
    private final String value;
    public StringValue(String value)
    {
        this.value = value;
    }

    @Override
    public myType getType() {
        return new StringType();
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public boolean equals(Object other)
    {
        return other instanceof StringValue;
    }

    @Override
    public String toString()
    {
        return "\"" + value + "\"";
    }
}
