package model.values;
import model.types.myType;
import model.types.Boolean;

public class booleanValue implements myValue{
    boolean value;
    public booleanValue(boolean some_bool){
        this.value = some_bool;
    }

    @Override
    public boolean equals(Object other)
    {
        return other instanceof booleanValue;
    }

    public booleanValue(){
        this.value = true;
    }
    public boolean getValue(){
        return this.value;
    }
    public myType getType(){
        return new Boolean();
    }

    public String toString(){
        return String.valueOf(value);
    }
}
