import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Mandelbrot {

    private static final int MAX = 255;
    private static final int gridSize = 512;

    public static void main(String[] args) throws FileNotFoundException {
        Complex[][] gridArray = getGrid();
        showRandomColors(gridArray);
        // showNoColors(gridArray);

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

        for (int j = 0; j < gridSize; j++) {
            for (int k = 0; k < gridSize; k++) {
                griddyRe = (centerX - (sideLength / 2) + ((sideLength * j) / (gridSize - 1)));
                griddyIm = (centerY - (sideLength / 2) + ((sideLength * k) / (gridSize - 1)));
                griddy = new Complex(griddyRe, griddyIm);
                gridArray[j][k] = griddy;
            }
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

    public static void showNoColors(Complex[][] a) {
        double pointRadius = 512 / a.length * 0.5;
        double pointDiameter = 512 / a.length;
        double iterateValue;
        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(0, 512);
        StdDraw.show(0);
        for (int j = 0; j < gridSize; j++) {
            for (int k = 0; k < gridSize; k++) {
                iterateValue = iterate(a[j][k]);
                if (iterateValue == MAX) {
                    StdDraw.filledCircle(j * pointDiameter + pointRadius, (k * pointDiameter + pointRadius),
                            pointRadius);
                }

            }
        }
        StdDraw.show(0);
    }

    public static void showRandomColors(Complex[][] a) throws FileNotFoundException {
        double pointRadius = 512 / a.length * 0.5;
        double pointDiameter = 512 / a.length;
        int iterateValue;
        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(0, 512);
        StdDraw.show(0);
        int[][] colors = null;
        colors = getUserColors();
        for (int j = 0; j < gridSize; j++) {
            for (int k = 0; k < gridSize; k++) {
                iterateValue = iterate(a[j][k]);
                if (iterateValue == MAX) {
                    // StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.filledCircle(j * pointDiameter + pointRadius, (k * pointDiameter + pointRadius),
                            pointRadius);
                } else {
                    StdDraw.setPenColor(colors[iterateValue][0], colors[iterateValue][1],
                            colors[iterateValue][2]);
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
        System.out.println("Enter file name: ");
        Scanner fileLocationReturner = new Scanner(System.in);
        fileLocation = fileLocationReturner.nextLine();
        int[][] colorsInFile = new int[256][3];
        Scanner newScanner = new Scanner(new File(fileLocation));
        for (int row = 0; row < 256; row++) {
            for (int column = 0; column < 3; column++) {
                colorsInFile[row][column] = newScanner.nextInt();
            }
        }
        return colorsInFile;
    }
}
