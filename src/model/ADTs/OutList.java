package model.ADTs;

import model.values.myValue;

import java.util.List;
import java.util.Vector;

public class OutList<myValue> implements myList<myValue>{
    List<myValue> lst;

    public OutList(){
        this.lst = new Vector<myValue>();
    }

    @Override
    public void add(myValue v){
        this.lst.add(v);
    }

    @Override
    public myValue get(int index){
        return this.lst.get(index);
    }

    @Override
    public String toString() {
        return this.lst.toString();
    }
}
