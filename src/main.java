import controller.controller;
import model.ADTs.*;
import model.PrgState;
import model.exceptions.ADTexception;
import model.exceptions.EvalException;
import model.exceptions.ExecuteException;
import model.expressions.*;
import model.statements.*;
import model.types.Boolean;
import model.types.Integer;
import model.types.RefType;
import model.types.StringType;
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
        HeapTable<myValue> heap = new HeapTable<myValue>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, heap, fileTable, lst, ex1);
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
        HeapTable<myValue> heap = new HeapTable<myValue>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, heap, fileTable, lst, ex2);
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
        HeapTable<myValue> heap = new HeapTable<myValue>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, heap, fileTable, lst,  ex3);
    }
    static PrgState p4(){
        myStatement ex4 = new CompdStatement(
                new VarDeclStatement("varf",new StringType()),new CompdStatement(
                new AssigStatement("varf",new ValueExpr(new StringValue("test.in"))),new CompdStatement(
                new OpenRFile(new VarExpr("varf")),new CompdStatement(
                new VarDeclStatement("varc",new Integer()),new CompdStatement(
                new readFile(new VarExpr("varf"),"varc"),new CompdStatement(
                new PrintStatement(new VarExpr("varc")),new CompdStatement(
                new readFile(new VarExpr("varf"),"varc") ,new CompdStatement(new PrintStatement(new VarExpr("varc")),new closeRFileStatement(new VarExpr("varf"))))))))));

        SymbolsDict<String, myValue> dict = new SymbolsDict<String, myValue>();
        FileTable<StringValue, BufferedReader> fileTable = new FileTable<StringValue, BufferedReader>();
        HeapTable<myValue> heap = new HeapTable<myValue>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, heap, fileTable, lst,  ex4);
    }

    static PrgState p5(){
        myStatement ex5 = new CompdStatement(
                new VarDeclStatement("v",new Integer()),
                new CompdStatement(
                        new AssigStatement("v",new ValueExpr(new intValue(10))),
                        new WhileStatement(
                                new RelationalExpression(new VarExpr("v"),new ValueExpr(new intValue(0)),">"),
                                new CompdStatement(new PrintStatement(new VarExpr("v")),
                                        new AssigStatement( "v",new ArtihmeticExpr(new VarExpr("v"),new ValueExpr(new intValue(1)), '-'))
                                )
                        )));
        SymbolsDict<String, myValue> dict = new SymbolsDict<String, myValue>();
        FileTable<StringValue, BufferedReader> fileTable = new FileTable<StringValue, BufferedReader>();
        HeapTable<myValue> heap = new HeapTable<myValue>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, heap, fileTable, lst,  ex5);
    }
    static PrgState p6(){
        myStatement ex6 = new CompdStatement(
                new VarDeclStatement("v",new RefType(new Integer())),
                new CompdStatement(
                        new NewStatement("v",new ValueExpr(new intValue(20))),
                        new CompdStatement(
                                new PrintStatement(new HeapReadExpression(new VarExpr("v"))), new CompdStatement(
                                new VarDeclStatement("a",new RefType(new RefType(new Integer()))), new CompdStatement(
                                new NewStatement("a",new VarExpr("v")),new CompdStatement(
                                new HeapWriteStatement("v",new ValueExpr(new intValue(30))),
                                new PrintStatement(new ArtihmeticExpr(new HeapReadExpression(new HeapReadExpression( new VarExpr("a"))),new ValueExpr(new intValue(5)), '+'))))))));
        SymbolsDict<String, myValue> dict = new SymbolsDict<String, myValue>();
        FileTable<StringValue, BufferedReader> fileTable = new FileTable<StringValue, BufferedReader>();
        HeapTable<myValue> heap = new HeapTable<myValue>();
        OutList<myValue> lst = new OutList<myValue>();
        executionStack<myStatement> stk = new executionStack<myStatement>();
        return new PrgState(stk, dict, heap, fileTable, lst,  ex6);
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
        String logFilePath = "C:\\Users\\paulu\\IdeaProjects\\logFile.txt";
        Repo r = new Repo(logFilePath);
        Repo r1 = new Repo(logFilePath);
        Repo r2 = new Repo(logFilePath);
        Repo r3 = new Repo(logFilePath);
        Repo r4 = new Repo(logFilePath);
        Repo r5 = new Repo(logFilePath);

        controller c = new controller(r);
        controller c1 = new controller(r1);
        controller c2 = new controller(r2);
        controller c3 = new controller(r3);
        controller c4 = new controller(r3);
        controller c5 = new controller(r4);

        r.add(p1());
        r1.add(p2());
        r2.add(p3());
        //r3.add(p4());
        r3.add(p5());
        r4.add(p6());

        TextClass menu = new TextClass();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1","int v; ;(v=2  ;Print{v })", c));
        menu.addCommand(new RunExample("2","(int a; ;(int b; ;(a=2   + 3   * 5    ;(b=a  + 1   ;Print{b }))))", c1));
        menu.addCommand(new RunExample("3","(boolean a; ;(int v; ;(a=true ;(If a  then v=2  else v=3  };Print{a }))))", c2));
        menu.addCommand(new RunExample("4","(int v; ;(v=10  ;(while(v >0  )(Print{v };v=v  - 1   ))))", c4));
        menu.addCommand(new RunExample("5","(Ref(int) v; ;(new(v,20  );(Print{rH(v )};(Ref(Ref(int)) a; ;(new(a,v );(wH(v,30  );Print{rH(rH(a )) + 5   })))))) ", c5));
        menu.show();

    }
}
