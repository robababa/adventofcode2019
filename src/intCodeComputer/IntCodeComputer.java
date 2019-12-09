package intCodeComputer;

import java.util.ArrayList;
import java.util.List;

public class IntCodeComputer {
    private int input;
    private int position = 0;
    private List<Integer> opcodes = new ArrayList<>();

    public IntCodeComputer(Integer input) {
        this.input = input;
    }

    public void addElement(int element) {
        opcodes.add(element);
    }

    public boolean finished() {
        if (position > opcodes.size()) {
            return true;
        } else return opcodes.get(position) == 99;
    }

    private ParameterMode determineParameterMode(int operation, int argumentPosition) throws Exception {
        // argument 1 is in 100s position, argument 2 in 1000s position
        long modeValue = operation / Math.round(Math.pow(10 , argumentPosition + 1)) % 10;
        if (modeValue == 0) {
            return ParameterMode.POSITION;
        } else if (modeValue == 1) {
            return ParameterMode.IMMEDIATE;
        }
        throw new Exception(String.format(
                "Could not interpret parameter mode for operation %d in position %d",
                operation,
                argumentPosition
        ));
    }

    private int determineArgument(ParameterMode parameterMode, int positionOffset) {
        return parameterMode == ParameterMode.IMMEDIATE
                ? opcodes.get(position + positionOffset)
                : opcodes.get(opcodes.get(position + positionOffset));
    }

    private void applyOperation1(int operation) throws Exception {
        ParameterMode firstArgumentMode = determineParameterMode(operation, 1);
        ParameterMode secondArgumentMode = determineParameterMode(operation, 2);
        int firstArgument = determineArgument(firstArgumentMode, 1);
        int secondArgument = determineArgument(secondArgumentMode, 2);
        int resultLocation = opcodes.get(position + 3);
        opcodes.set(resultLocation, firstArgument + secondArgument);
        position = position + 4;
    }

    private void applyOperation2(int operation) throws Exception {
        ParameterMode firstArgumentMode = determineParameterMode(operation, 1);
        ParameterMode secondArgumentMode = determineParameterMode(operation, 2);
        int firstArgument = firstArgumentMode == ParameterMode.IMMEDIATE
                ? opcodes.get(position + 1)
                : opcodes.get(opcodes.get(position + 1));
        int secondArgument =  secondArgumentMode == ParameterMode.IMMEDIATE
                ? opcodes.get(position + 2)
                : opcodes.get(opcodes.get(position + 2));
        int resultLocation = opcodes.get(position + 3);
        opcodes.set(resultLocation, firstArgument * secondArgument);
        position = position + 4;
    }

    private void applyOperation3() {
        int firstArgument = opcodes.get(position + 1);
        opcodes.set(firstArgument, input);
        position = position + 2;
    }

    private void applyOperation4() {
        System.out.println(String.format("Operation 4 output is %d", opcodes.get(opcodes.get(position + 1))));
        position = position + 2;
    }


    public void runCommand() throws Exception {
        if (finished()) {
            return;
        }
        int operation = opcodes.get(position);

        System.out.println(String.format("Applying operation %d at position %d", operation, position));

        switch (operation % 100) {
            case 1:
                applyOperation1(operation);
                break;
            case 2:
                applyOperation2(operation);
                break;
            case 3:
                applyOperation3();
                break;
            case 4:
                applyOperation4();
                break;
            default:
                throw new Exception(
                    String.format("Could not interpret operation %d at position %d", operation, position)
                );
        }
    }
}
