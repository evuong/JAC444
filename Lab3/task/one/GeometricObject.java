package task.one;

import java.util.Scanner;

public class GeometricObject {
    String color;
    boolean filled = false;
    java.util.Date dateCreated;

    GeometricObject() {
        Scanner input = new Scanner(System.in);
        System.out.print("[R]ed, [Y]ellow, [B]lue\nChoose a color for your shape (R/Y/B): ");

        while(!input.hasNext("[RYBryb]")) {
            input.nextLine();
            System.out.print("Try again. R/Y/B? ");
        }

        switch(input.next().toLowerCase()) {
            case "r": setColor("Red"); break;
            case "b": setColor("Blue"); break;
            case "y": setColor("Yellow"); break;
        }

        System.out.print("Is the shape filled or not? (Y/N): ");

        while(!input.hasNext("[YyNn]")) {
            input.nextLine();
            input.next();
            System.out.print("Try again. Y/N? ");
        }

        if (input.hasNext("[Yy]")) {
            setFilled(true);
        }

        dateCreated = new java.util.Date();
    }

    GeometricObject(String c, boolean f) {
        color = c;
        filled = f;
    }

    String getColor() { return color; }

    void setColor(String c) {
        color = c;
    }

    boolean isFilled() {
        return filled;
    }

    void setFilled(boolean f) {
        filled = f;
    }

    java.util.Date getDateCreated() {
        return dateCreated;
    }

    public String toString() {
         return "\nObject Color: " + getColor() + "\nFilled?: " + isFilled() + "\nDate Created: " + getDateCreated();
    }
}
