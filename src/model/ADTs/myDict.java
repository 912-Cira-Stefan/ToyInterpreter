package model.ADTs;

import model.exceptions.ADTexception;

public interface myDict <String, myValue>{
    myValue lookup(String var_name) throws ADTexception;
    boolean isOkay(String var_name);
    void update(String var_name, myValue value);
}
