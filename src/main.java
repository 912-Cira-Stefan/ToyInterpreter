import controller.controller;
import model.ADTs.OutList;
import model.ADTs.SymbolsDict;
import model.ADTs.executionStack;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.expressions.ArtihmeticExpr;
import model.expressions.ValueExpr;
import model.expressions.VarExpr;
import model.statements.*;
import model.types.Boolean;
import model.types.Integer;
import model.values.booleanValue;
import model.values.intValue;
import model.values.myValue;
import repository.Repo;

import java.util.Scanner;

public class main {
    static PrgState p1(){
        // ex 1
        // int v; v = 2; Print(v)
        myStatement ex1 = new CompdStatement(
                new VarDeclStatement("v",new Integer()),
                new CompdStatement(
                        new AssigStatement("v", new ValueExpr(new intValue(2))),
                        new PrintStatement(new VarExpr("v"))
                )
        );
        SymbolsDict<String, myValue> dict = new SymbolsDict<String, myValue>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, lst, ex1);
    }
    static PrgState p2(){
        // ex2
        // int a; int b; a = 2 + 3*5; b = a + 1; Print(b)
        myStatement ex2 = new CompdStatement(
                new VarDeclStatement("a",new Integer()),
                new CompdStatement(
                        new VarDeclStatement("b", new Integer()),
                        new CompdStatement(
                                new AssigStatement(
                                        "a",
                                        new ArtihmeticExpr(
                                                new ValueExpr(new intValue(2)),
                                                new ArtihmeticExpr(
                                                        new ValueExpr(new intValue(3)),
                                                        new ValueExpr(new intValue(5)),
                                                        '*'
                                                ),
                                                '+'
                                        )
                                ),
                                new CompdStatement(
                                        new AssigStatement(
                                                "b",
                                                new ArtihmeticExpr(
                                                        new VarExpr("a"),
                                                        new ValueExpr(new intValue(1)),
                                                        '+'
                                                )
                                        ),
                                        new PrintStatement(new VarExpr("b"))
                                )
                        )
                )
        );
        SymbolsDict<String, myValue> dict = new SymbolsDict<String, myValue>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, lst, ex2);
    }
    static PrgState p3(){
        // ex 3
        // bool a; int v; a = true;
        // If a Then v = 2 Else v = 3;
        // Print(v)
        myStatement ex3 = new CompdStatement(
                new VarDeclStatement("a", new Boolean()),
                new CompdStatement(
                        new VarDeclStatement("v", new Integer()),
                        new CompdStatement(
                                new AssigStatement("a", new ValueExpr(new booleanValue(true))),
                                new CompdStatement(
                                        new IfStatement(
                                                new VarExpr("a"),
                                                new AssigStatement("v", new ValueExpr(new intValue(2))),
                                                new AssigStatement("v", new ValueExpr(new intValue(3)))
                                        ),
                                        new PrintStatement(new VarExpr("a"))
                                )
                        )
                )
        );
        SymbolsDict<String, myValue> dict = new SymbolsDict<String, myValue>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, lst, ex3);
    }
    static int print_menu(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please input 1, 2 or 3\n");
        int myint = keyboard.nextInt();
        if (myint != 1 && myint != 2 && myint != 3) {
            System.out.println("If you can't choose between 3 numbers, I'm done");
            System.exit(0);
        }
        return myint;
    }
    public static void main(String[] args){
        Repo r = new Repo();
        controller c = new controller(r);
        int option = print_menu();
        switch(option){
            case 1:
                r.add(p1());
            case 2:
                r.add(p2());
            case 3:
                r.add(p3());
        }
//        r.add(p3());
        try {
            c.do_all_at_once();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
