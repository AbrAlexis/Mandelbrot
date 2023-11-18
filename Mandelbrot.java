import java.util.*;

public class Mandelbrot {

    private static final int MAX = 20;
    private static final int gridSize = 5;

    public static void main(String[] args) {
        Complex[][] gridArray = getGrid();
        show(gridArray);
    }

    public static int iterate(Complex z0) {
        Complex z = new Complex(z0);
        for (int i = 0; i < MAX; i++) {
            if (z.abs() > 2.0) {
                return i;
            }
            z = z.times(z).plus(z0);
        }
        return MAX;
    }

    public static Complex[][] getGrid() {
        double centerX = numberValidation("Enter value for centrum X-coordinate: ");
        double centerY = numberValidation("Enter value for centrum Y-coordinate: ");
        double sideLength = numberValidation("Enter sidelength of coordinate system: ");
        Complex gridArray[][] = new Complex[gridSize][gridSize];
        Complex griddy;
        double griddyRe;
        double griddyIm;
        int counter = 0;
        for (int j = 0; j < gridSize; j++) {
            for (int k = 0; k < gridSize; k++) {
                counter++;
                griddyRe = (centerX - (sideLength / 2) + ((sideLength * j) / (gridSize - 1)));
                griddyIm = (centerY - (sideLength / 2) + ((sideLength * k) / (gridSize - 1)));
                griddy = new Complex(griddyRe, griddyIm);
                gridArray[j][k] = griddy;
                System.out.println(counter + " " + gridArray[j][k]);
            }
            System.out.print("");
        }
        return gridArray;
    }

    private static Double numberValidation(String b) { // Private helping function that handles userErrors.
        double a = 0.0;
        String userInputNumber;
        Scanner console = new Scanner(System.in);
        System.out.println(b);

        while (true) {
            userInputNumber = console.nextLine();
            try {
                a = Double.parseDouble(userInputNumber);
                break;
            } catch (Exception e) {
                System.out.println("Not a number. try again: ");
            }
        }
        return a;
    }

    public static void show(Complex[][] a) {

        for (int j = 0; j < gridSize; j++) {
            for (int k = 0; k < gridSize; k++) {
                if (iterate(a[j][k]) == MAX) {

                }
            }
        }
    }
}
