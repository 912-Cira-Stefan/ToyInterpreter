package model.values;

import model.types.RefType;
import model.types.myType;

public class RefValue implements myValue{
    int address;
    myType locationType;

    public RefValue(int address, myType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public String toString() {
        return "("+ address + "," + locationType.toString() +  ")";
    }

    public int getAddress() {
        return address;
    }
    public myType getLocationType(){return locationType;}
    @Override
    public myType getType() {
        return new RefType(locationType);
    }

    @Override
    public myValue clone() {
        return new RefValue(address,locationType);
    }
}
