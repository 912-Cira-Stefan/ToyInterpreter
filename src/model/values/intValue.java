package model.values;

import model.types.Boolean;
import model.types.myType;
import model.types.Integer;

public class intValue implements myValue{
    int value;
    public intValue(int some_v){
        this.value = some_v;
    }
    public intValue(){this.value = 0;}
    public int getValue(){

        return this.value;
    }
    public String toString(){
        return value + " ";
    }
    public myType getType(){
        return new Integer();
    }
}
