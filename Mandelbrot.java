import java.util.*;
public class Mandelbrot {

    private static final int MAX = 20;
    private static final int gridSize = 5;
    public static void main(String[] args) {
        getGrid();
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

    public static void getGrid(){ 
        double centerX = numberValidation("Enter value for centrum X-coordinate: ");
        double centerY = numberValidation("Enter value for centrum Y-coordinate: ");
        double sideLength = numberValidation("Enter sidelength of coordinate system: ");
    }

    private static Double numberValidation( String b) { //Private helping function that handles userErrors.
        double a = 0.0;
        String userInputNumber;
        Scanner console = new Scanner(System.in);
        System.out.println(b);

        while(true) {
            userInputNumber = console.nextLine();
            try {
                a = Double.parseDouble(userInputNumber); 
                break;
            }
            catch(Exception e) {
                System.out.println("Not a number. try again: ");
            }
        }
        return a;
    }
}

