import java.lang.Math;

class Calculator {
    double pi(int n) {
        double x = 0;
        for (int i = 0; i < n+1; i++) {
            x = x + (Math.pow((-1),i))/(2*i+1);
        }
        return 4*x;
    }
}