package model.types;
import com.sun.jdi.BooleanType;
import model.values.booleanValue;
import model.values.myValue;

public class Boolean implements myType{
    public boolean equals(Object some_obj){
        return some_obj instanceof Boolean;
    }
    public String toString(){
        return "boolean";
    }

    @Override
    public myValue defaultValue() {
        return new booleanValue(false);
    }
}
