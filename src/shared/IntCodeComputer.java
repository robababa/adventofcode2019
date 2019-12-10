package shared;

import java.util.ArrayList;
import java.util.List;

public class IntCodeComputer {
    private int input;
    private int position = 0;
    private List<Integer> opcodes = new ArrayList<>();

    public IntCodeComputer(Integer input) {
        this.input = input;
    }

    public IntCodeComputer(IntCodeComputer intCodeComputer, int input) {
        position = intCodeComputer.position;
        opcodes = new ArrayList<>(intCodeComputer.opcodes);
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

    private void applyOperation1Add(int operation) throws Exception {
        ParameterMode firstArgumentMode = determineParameterMode(operation, 1);
        ParameterMode secondArgumentMode = determineParameterMode(operation, 2);
        int firstArgument = determineArgument(firstArgumentMode, 1);
        int secondArgument = determineArgument(secondArgumentMode, 2);
        int resultLocation = opcodes.get(position + 3);
        opcodes.set(resultLocation, firstArgument + secondArgument);
        position = position + 4;
    }

    private void applyOperation2Multiply(int operation) throws Exception {
        ParameterMode firstArgumentMode = determineParameterMode(operation, 1);
        ParameterMode secondArgumentMode = determineParameterMode(operation, 2);
        int firstArgument = determineArgument(firstArgumentMode, 1);
        int secondArgument = determineArgument(secondArgumentMode, 2);
        int resultLocation = opcodes.get(position + 3);
        opcodes.set(resultLocation, firstArgument * secondArgument);
        position = position + 4;
    }

    private void applyOperation3Save() {
        int firstArgument = opcodes.get(position + 1);
        opcodes.set(firstArgument, input);
        position = position + 2;
    }

    private void applyOperation4Output(int operation) throws Exception {
        ParameterMode firstArgumentMode = determineParameterMode(operation, 1);
        int firstArgument = determineArgument(firstArgumentMode, 1);
        System.out.println(String.format("Operation 4 output is %d", firstArgument));
        position = position + 2;
    }

    private void applyOperation5JumpIfTrue(int operation) throws Exception {
        ParameterMode firstArgumentMode = determineParameterMode(operation, 1);
        ParameterMode secondArgumentMode = determineParameterMode(operation, 2);
        int firstArgument = determineArgument(firstArgumentMode, 1);
        int secondArgument = determineArgument(secondArgumentMode, 2);
        if (firstArgument != 0) {
            position = secondArgument;
        } else {
            position = position + 3;
        }
    }

    private void applyOperation6JumpIfFalse(int operation) throws Exception {
        ParameterMode firstArgumentMode = determineParameterMode(operation, 1);
        ParameterMode secondArgumentMode = determineParameterMode(operation, 2);
        int firstArgument = determineArgument(firstArgumentMode, 1);
        int secondArgument = determineArgument(secondArgumentMode, 2);
        if (firstArgument == 0) {
            position = secondArgument;
        } else {
            position = position + 3;
        }
    }

    private void applyOperation7LessThan(int operation) throws Exception {
        ParameterMode firstArgumentMode = determineParameterMode(operation, 1);
        ParameterMode secondArgumentMode = determineParameterMode(operation, 2);
        ParameterMode thirdArgumentMode = determineParameterMode(operation, 3);
        int firstArgument = determineArgument(firstArgumentMode, 1);
        int secondArgument = determineArgument(secondArgumentMode, 2);
        int thirdArgument = determineArgument(thirdArgumentMode, 3);
        opcodes.set(thirdArgument, firstArgument < secondArgument ? 1 : 0);
        position = position + 4;
    }

    private void applyOperation8Equals(int operation) throws Exception {
        ParameterMode firstArgumentMode = determineParameterMode(operation, 1);
        ParameterMode secondArgumentMode = determineParameterMode(operation, 2);
        ParameterMode thirdArgumentMode = determineParameterMode(operation, 3);
        int firstArgument = determineArgument(firstArgumentMode, 1);
        int secondArgument = determineArgument(secondArgumentMode, 2);
        int thirdArgument = determineArgument(thirdArgumentMode, 3);
        opcodes.set(thirdArgument, firstArgument == secondArgument ? 1 : 0);
        position = position + 4;
    }


    public void runCommand() throws Exception {
        if (finished()) {
            return;
        }
        int operation = opcodes.get(position);

        System.out.println(String.format("Applying operation %d at position %d", operation, position));

        switch (operation % 100) {
            case 1:
                applyOperation1Add(operation);
                break;
            case 2:
                applyOperation2Multiply(operation);
                break;
            case 3:
                applyOperation3Save();
                break;
            case 4:
                applyOperation4Output(operation);
                break;
            case 5:
                applyOperation5JumpIfTrue(operation);
                break;
            case 6:
                applyOperation6JumpIfFalse(operation);
                break;
            case 7:
                applyOperation7LessThan(operation);
                break;
            case 8:
                applyOperation8Equals(operation);
                break;
            default:
                throw new Exception(
                    String.format("Could not interpret operation %d at position %d", operation, position)
                );
        }
    }
}
