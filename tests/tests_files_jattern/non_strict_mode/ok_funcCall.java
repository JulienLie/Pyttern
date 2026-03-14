class Main {
    public static void main() {
        hello_world();
    }

    public static void hello_world() {
        System.out.println("Bye, world!"); // should not impact the match
        System.out.println("Hello, world!");
        hello_world(); // should not impact the match
    }
}