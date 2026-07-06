class Main {
    public static void main() {
        for (int x = 0; x < 0; x++) {} // should not impact the match
        hello_world();
    }

    public static void hello_world() {
        while (true) { break; } // should not impact the match
        System.out.println("Hello, world!");
        do {} while (false); // should not impact the match
    }
}