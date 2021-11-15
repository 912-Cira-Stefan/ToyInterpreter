package model.types;

import com.sun.jdi.IntegerType;

public class Integer implements myType{
    public boolean equals(Object some_obj){
        return some_obj instanceof Integer;
    }
    public String toString(){
        return "int";
    }
}
