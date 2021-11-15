import controller.controller;
import model.ADTs.FileTable;
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
import model.values.StringValue;
import model.values.booleanValue;
import model.values.intValue;
import model.values.myValue;
import repository.Repo;
import view.TextClass;
import view.commands.Command;
import view.commands.ExitCommand;

import java.io.BufferedReader;
import java.util.Scanner;

public class main {
    //TODO create a wrapper for a method that returns a controller -> not hardcoded

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
        FileTable<StringValue, BufferedReader> fileTable = new FileTable<StringValue, BufferedReader>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, fileTable, lst, ex1);
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
        FileTable<StringValue, BufferedReader> fileTable = new FileTable<StringValue, BufferedReader>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, fileTable, lst, ex2);
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
        FileTable<StringValue, BufferedReader> fileTable = new FileTable<StringValue, BufferedReader>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, fileTable, lst,  ex3);
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

    public static class RunExample extends Command {
        private final controller control;

        public RunExample(String key, String description, controller controller)
        {
            super(key, description);
            this.control=controller;
        }
        @Override
        public void execute()
        {
            try
            {
                control.do_all_at_once();
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
        }
    }

    public static void main(String[] args){
        String logFilePath = "C:\\Users\\Stefan Cira\\IdeaProjects\\logFile.txt";
        Repo r = new Repo(logFilePath);
        Repo r1 = new Repo(logFilePath);
        Repo r2 = new Repo(logFilePath);
        controller c = new controller(r);
        controller c1 = new controller(r1);
        controller c2 = new controller(r2);
        r.add(p1());
        r1.add(p2());
        r2.add(p3());
        TextClass menu = new TextClass();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1","example0", c));
        menu.addCommand(new RunExample("2","example1", c1));
        menu.addCommand(new RunExample("3","example2", c2));
        menu.show();

    }
}
