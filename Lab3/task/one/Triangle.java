package task.one;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Triangle extends GeometricObject {
    int side1=0, side2=0, side3=0;
    DecimalFormat df = new DecimalFormat(".0000");

    public Triangle() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the 3 side lengths of a triangle using ints.\nSide 1: ");
        while (side1 < 1) {
            try {
                side1 = input.nextInt();
                if (side1 < 1) {
                    input.nextLine();
                    System.out.print("Must be a positive number: ");
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.print("Try again (must be an int): ");
            }
        }

        System.out.print("Side 2: ");
        while (side2 < 1) {
            try {
                side2 = input.nextInt();
                if (side2 < 1) {
                    input.nextLine();
                    System.out.print("Must be a positive number: ");
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.print("Try again (must be an int): ");
            }
        }

        System.out.print("Side 3: ");
        while (side3 < 1) {
            try {
                side3 = input.nextInt();
                if (side3 < 1) {
                    input.nextLine();
                    System.out.print("Must be a positive number: ");
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.print("Try again (must be an int): ");
            }
        }
        input.close();

        if (side1 > side2+side3 || side2 > side1+side3 || side3 > side1+side2) {
            System.out.println("Impossible triangle when one side is bigger than the other two combined.");
            System.exit(1);
        }

        System.out.println("\n\nThis sides of this triangle are: " + side1 + " " + side2 + " " + side3);
    }

    int getPerimeter() {
        return side1+side2+side3;
    }

    double getArea() {
        double s = (double)getPerimeter()/2;
        return java.lang.Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
    }

    public String toString() {
        return "Triangle Area: " + df.format(getArea()) + "\nTriangle Perimeter: " + getPerimeter() + super.toString();
    }
}
