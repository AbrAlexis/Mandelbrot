import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Mandelbrot {
    private static final int MAX = 255;
    private static final int GRID_SIZE = 500;

    public static void main(String[] args) throws FileNotFoundException {
        Complex[][] gridArray = getGrid();
        showRandomColors(gridArray);
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

    private static Complex[][] getGrid() {
        double centerX = numberValidation("Enter value for centrum X-coordinate: ");
        double centerY = numberValidation("Enter value for centrum Y-coordinate: ");
        double sideLength = numberValidation("Enter sidelength of coordinate system: ");

        Complex gridArray[][] = new Complex[GRID_SIZE][GRID_SIZE];

        for (int j = 0; j < GRID_SIZE; j++) {
            for (int k = 0; k < GRID_SIZE; k++) {
                double coordinateRe = (centerX - (sideLength / 2) + ((sideLength * j) / (GRID_SIZE - 1)));
                double CoordinateIm = (centerY - (sideLength / 2) + ((sideLength * k) / (GRID_SIZE - 1)));
                Complex complexCoordinate = new Complex(coordinateRe, CoordinateIm);
                gridArray[j][k] = complexCoordinate;
            }
        }
        return gridArray;
    }

    private static Double numberValidation(String message) { // Private helping function that handles userErrors.
        double a = 0.0;
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            String userInputNumber = console.nextLine();
            try {
                a = Double.parseDouble(userInputNumber);
                break;
            } catch (Exception e) {
                System.out.println("Not a number. try again: ");
            }
        }

        return a;
    }

    private static void showNoColors(Complex[][] a) {
        double pointRadius = GRID_SIZE / (double) a.length * 0.5;
        double pointDiameter = GRID_SIZE / (double) a.length;
        double iterateValue;
        StdDraw.setXscale(0, GRID_SIZE);
        StdDraw.setYscale(0, GRID_SIZE);
        StdDraw.show(0);
        for (int j = 0; j < GRID_SIZE; j++) {
            for (int k = 0; k < GRID_SIZE; k++) {
                iterateValue = iterate(a[j][k]);
                if (iterateValue == MAX) {
                    StdDraw.filledCircle(j * pointDiameter + pointRadius, (k * pointDiameter + pointRadius),
                            pointRadius);
                }
            }
        }
        StdDraw.show(0);
    }

    private static void showRandomColors(Complex[][] a) throws FileNotFoundException {
        double pointRadius = GRID_SIZE / (double) a.length * 0.5;
        double pointDiameter = GRID_SIZE / (double) a.length;
        int iterateValue;
        StdDraw.setXscale(0, GRID_SIZE);
        StdDraw.setYscale(0, GRID_SIZE);
        StdDraw.show(0);
        StdDraw.clear();
        int[][] colors = null;
        colors = getUserColors();
        for (int j = 0; j < GRID_SIZE; j++) {
            for (int k = 0; k < GRID_SIZE; k++) {
                iterateValue = iterate(a[j][k]);
                if (iterateValue == MAX) {
                    StdDraw.setPenColor(colors[iterateValue - 1][0], colors[iterateValue - 1][1],
                            colors[iterateValue - 1][2]);
                    StdDraw.filledCircle(j * pointDiameter + pointRadius, (k * pointDiameter + pointRadius),
                            pointRadius);
                } else {
                    StdDraw.setPenColor(colors[iterateValue - 1][0], colors[iterateValue - 1][1],
                            colors[iterateValue - 1][2]);
                    StdDraw.filledCircle(j * pointDiameter + pointRadius, (k * pointDiameter + pointRadius),
                            pointRadius);
                }
            }
        }
        StdDraw.show(0);
    }

    private static int[][] getUserColors() throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        String userInputNumber;
        int randOrFile;

        System.out.println("Type 1 for random colors or type 2 to read colors from file: ");
        while (true) {
            userInputNumber = console.nextLine();
            try {
                randOrFile = Integer.valueOf(userInputNumber);
                if (randOrFile != 1 && randOrFile != 2) {
                    System.out.println("Please enter either 1 or 2:  ");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter either 1 or 2: ");
            }
        }
        if (randOrFile == 1) {
            return randomRGBGenerator();
        } else {
            return fileToArray();
        }
    }

    private static int[][] randomRGBGenerator() {
        Random random = new Random();
        int[][] colors = new int[256][3];
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 3; j++) {
                colors[i][j] = random.nextInt(256);
            }
        }
        return colors;
    }

    private static int[][] fileToArray() throws FileNotFoundException {
        String fileLocation;
        int[][] colorsInFile = new int[256][3];
        System.out.println("Enter file Path: ");
        Scanner fileLocationReturner = new Scanner(System.in);
        Scanner newScanner;
        while (true) {
            fileLocation = fileLocationReturner.nextLine();
            try {
                newScanner = new Scanner(new File(fileLocation));
                break;
            } catch (Exception e) {
                System.out.println("Cannot find file. Try again: ");
            }
        }
        for (int row = 0; row < 256; row++) {
            for (int column = 0; column < 3; column++) {
                colorsInFile[row][column] = newScanner.nextInt();
            }
        }
        return colorsInFile;
    }
}
