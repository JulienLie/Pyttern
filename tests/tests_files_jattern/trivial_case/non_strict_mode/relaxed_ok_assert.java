class Main {
    public static void main() {
        assert true; // should not impact the match
        hello_world();
    }

    public static void hello_world() {
        assert true; // should not impact the match
        System.out.println("Hello, world!");
    }
}