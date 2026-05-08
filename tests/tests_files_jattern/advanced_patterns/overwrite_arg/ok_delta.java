class Calculator {
    int delta(int a, int b, int c) {
        b = b*b; // overwrite b (bad idea)
        int delta = b - 4*a*c;
        return delta;
    }
}