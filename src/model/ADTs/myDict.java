package model.ADTs;

import model.exceptions.ADTexception;

import java.util.Map;

public interface myDict <String, myValue>{
    myValue lookup(String var_name) throws ADTexception;
    boolean isOkay(String var_name);
    void update(String var_name, myValue value);

    Map<String, myValue> getDict();
}
