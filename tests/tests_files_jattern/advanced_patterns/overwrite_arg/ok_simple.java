class Calculator {
    int half(int n) {
        n = n / 2; // overwrite n (bad idea)
        return n;
    }
}