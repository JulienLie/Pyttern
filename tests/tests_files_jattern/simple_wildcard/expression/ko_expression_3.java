class Calculator {
    public static int compute(int a, int b) {
        return (a + b) - 2; // won't match here because in the AST it is first (X - Y), then X = a+b, and the matcher first sees that X-Y doesn't fit the pattern
    }
}