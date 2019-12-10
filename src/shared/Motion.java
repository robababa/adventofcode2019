package shared;

public class Motion {
    private String direction;
    private int length;

    Motion(String code) {
//        System.out.println("code is " + code);
        direction = code.substring(0, 1);
        length = Integer.parseInt(code.substring(1));
    }

    public void move(Trail trail) {
        for (int i = 0; i < length; i++) {
            if (direction.equals("L")) { trail.moveLeft(); }
            else if (direction.equals("R")) { trail.moveRight(); }
            else if (direction.equals("U")) { trail.moveUp(); }
            else if (direction.equals("D")) {trail.moveDown(); }
        }
    }

    public String toString() {
        return String.format("direction = %s, length = %d", direction, length);
    }
}
