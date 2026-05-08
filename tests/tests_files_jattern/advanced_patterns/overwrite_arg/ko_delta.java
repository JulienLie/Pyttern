class Calculator {
    int delta(int a, int b, int c) {
        int delta = b*b - 4*a*c; // no overwriting, all good
        return delta;
    }
}