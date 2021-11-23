package model.types;

import model.values.RefValue;
import model.values.myValue;

public class RefType implements myType{
    myType inner;

    public RefType(myType some){this.inner = some;}

    public myType getInner() {
        return inner;
    }

    @Override
    public myValue defaultValue() {
        return new RefValue(0, this.inner);
    }

    public boolean equals(Object another){
        if(another instanceof RefType)
            return this.inner.equals(((RefType) another).getInner());
        else
            return false;
    }

    @Override
    public String toString(){
        return "Ref("+this.inner.toString()+")";
    }

}
