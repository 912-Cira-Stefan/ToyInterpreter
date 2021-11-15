package model.ADTs;
import model.exceptions.ADTexception;
import model.values.myValue;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SymbolsDict<String, myValue> implements myDict <String, myValue>{
    Map<String, myValue> dict;

    public SymbolsDict(Map<String, myValue> some_dict){
        this.dict = new HashMap<>();
    }

    public SymbolsDict() {
        this.dict = new HashMap<>();
    }
//    public SymbolsDict(){this.dict = new Dictionary<String, myValue>() {
//        @Override
//        public int size() {
//            return dict.size();
//        }
//
//        @Override
//        public boolean isEmpty() {
//            return dict.isEmpty();
//        }
//
//        @Override
//        public Enumeration<String> keys() {
//            return dict.keys();
//        }
//
//        @Override
//        public Enumeration<myValue> elements() {
//            return dict.elements();
//        }
//
//        @Override
//        public myValue get(Object key) {
//            if (dict.get(key) != null)
//                return dict.get(key);
//            else
//                return null;
//        }
//
//        @Override
//        public myValue put(String key, myValue value) {
//            return dict.put(key, value);
//        }
//
//        @Override
//        public myValue remove(Object key) {
//            return dict.remove(key);
//        }
//    };}

    public myValue lookup(String var_name) throws ADTexception {
        if (dict.get(var_name) != null) {
            return this.dict.get(var_name);
        }
        throw new ADTexception("variable name not found");
    }

    public boolean isOkay(String var_name){
        return this.dict.get(var_name) != null;
    }
    public void update(String var_name, myValue some_val){
        this.dict.put(var_name, some_val);
    }

    @Override
    public java.lang.String toString() {
        return this.dict.toString();
    }
}
