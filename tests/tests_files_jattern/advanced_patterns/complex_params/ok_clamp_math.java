import java.lang.Math;

class Calculator {
    int clamp() {
        clamp(1, 2, 3);
        return 0;
    }

    int clamp(long a, int b, int c) {
        return Math.clamp(a, b, c);
    }
}