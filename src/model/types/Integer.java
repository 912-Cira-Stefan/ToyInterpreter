package model.types;

import com.sun.jdi.IntegerType;
import model.values.intValue;
import model.values.myValue;

public class Integer implements myType{
    public boolean equals(Object some_obj){
        return some_obj instanceof Integer;
    }
    public String toString(){
        return "int";
    }

    @Override
    public myValue defaultValue() {
        return new intValue(0);
    }
}
