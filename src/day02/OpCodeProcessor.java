package day02;

import java.util.ArrayList;
import java.util.List;

public class OpCodeProcessor {
    private int position = 0;
    private List<Integer> opcodes = new ArrayList<>();

    public OpCodeProcessor() {}

    public void addElement(int element) {
        opcodes.add(element);
        System.out.println("OpCodeProcessor element count after adding " + element + " is " + opcodes.size());
    }

    public int firstElement() {
        return opcodes.get(0);
    }

    public void replaceAtPositionWithValue(int position, int withValue) {
        opcodes.set(position, withValue);
    }

    public boolean finished() {
        if (position > opcodes.size()) {
            return true;
        } else return opcodes.get(position) == 99;
    }

    public void runCommand() {
        if (finished()) {
            return;
        }
        int operation = opcodes.get(position);
        int firstArgument = opcodes.get(position + 1);
        int secondArgument = opcodes.get(position + 2);
        int resultLocation = opcodes.get(position + 3);

        if (operation == 99) {
            return;
        }
        if (operation == 1) {
            opcodes.set(resultLocation, opcodes.get(firstArgument) + opcodes.get(secondArgument));
        } else if (operation == 2) {
            opcodes.set(resultLocation, opcodes.get(firstArgument) * opcodes.get(secondArgument));
        }
        // advance the position
        position = position + 4;
    }
}
