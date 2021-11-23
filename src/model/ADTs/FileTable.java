package model.ADTs;

import model.exceptions.ADTexception;
import model.values.myValue;

import java.util.HashMap;
import java.util.Map;

public class FileTable<String, myValue> implements myDict<String, myValue>{
    private final Map<String, myValue> fileTable;

    public FileTable(){
        this.fileTable = new HashMap<>();
    }

    @Override
    public myValue lookup(String var_name) throws ADTexception {
        if (fileTable.get(var_name) != null) {
            return this.fileTable.get(var_name);
        }
        throw new ADTexception("variable name not found");
    }

    @Override
    public boolean isOkay(String var_name) {
        return this.fileTable.get(var_name) != null;
    }

    @Override
    public void update(String var_name, myValue myValue) {
        this.fileTable.put(var_name, myValue);
    }

    @Override
    public Map<String, myValue> getDict() {
        return this.fileTable;
    }
}
