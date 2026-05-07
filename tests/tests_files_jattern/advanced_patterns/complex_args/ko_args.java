// doesn't directly work with varargs (because 3 elements instead of 2)
class Calculator {
    int inc(int ... a) {
        return a[0] + 1;
    }
}