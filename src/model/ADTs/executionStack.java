package model.ADTs;
import java.util.Stack;

public class executionStack<TElem> implements myStack<TElem>{
    Stack<TElem> stack;

    public executionStack(){stack = new Stack<TElem>();}

    public TElem pop(){
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void push(TElem element) {
        stack.push(element);
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
