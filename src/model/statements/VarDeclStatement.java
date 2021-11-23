package model.statements;

import model.ADTs.myDict;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.types.Boolean;
import model.types.Integer;
import model.types.myType;
import model.values.booleanValue;
import model.values.intValue;
import model.values.myValue;

public class VarDeclStatement implements myStatement{
    String name;
    myType type;

    public VarDeclStatement(String n, myType t){
        this.name = n;
        this.type = t;
    }
    @Override
    public PrgState execute(PrgState state) throws ExecuteException, ADTexception, EvalException {
        myDict<String, myValue> symTable = state.getSymbolsDict();
        if(symTable.isOkay(name))
            throw new ExecuteException("Symbol " + name + " is already defined");
        else{
            //NOTE - here is the defaultValue()
            myValue val = type.defaultValue();
            symTable.update(name, val);
        }
        return state;
    }

    public String toString(){
        return type.toString() + " " + name + "; ";
    }
}
