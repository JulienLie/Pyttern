import java.lang.Math;

class Calculator {
    double pi(int n) {
        double x = 0;
        for (int i = 0; i < n+1; i++) {
            System.out.println((Math.pow((-1),i))/(2*i+1)); // x isn't updated
        }
        return x;
    }
}