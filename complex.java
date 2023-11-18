public class Complex {

    private double re;
    private double im;
    
    public Complex() {
        this.re = 0;
        this.im = 0;
    }
    
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex(Complex z) {

    }

    public double getRe() {
        return this.re;
    }

    public double getIm() {
        return this.im;
    }

    public double abs() {
        return Math.sqrt(Math.pow(this.re, 2) + Math.pow(this.im, 2));
    }

    public Complex plus(Complex other) {

        return new Complex(this.re + other.re, this.im + other.im);
    }

    public Complex times(Complex other) {
        return new Complex((this.re * other.re) - (this.im * other.im), (this.im * other.re) + (this.re * other.im));
    }

    public String toString() {
        if (this.im < 0) {
            return ""+this.re +" "+this.im+"i";
        }
        else{
        return ""+this.re+" + "+this.im+"i";
        }
}
}
