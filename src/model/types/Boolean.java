package model.types;
import com.sun.jdi.BooleanType;

public class Boolean implements myType{
    public boolean equals(Object some_obj){
        return some_obj instanceof Boolean;
    }
    public String toString(){
        return "boolean";
    }
}
