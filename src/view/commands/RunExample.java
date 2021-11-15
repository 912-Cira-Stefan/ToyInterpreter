package view.commands;

import controller.controller;

public class RunExample extends Command{
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
