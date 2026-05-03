import java.lang.Math;

class Calculator {
    float pi(int n) {
        int x = 0;
        for (int i = 0; i < n+1; i++) {
            x += (Math.pow((-1),i))/(2*i+1);
            return x; // bad return
        }
        return 4*x;
    }
}