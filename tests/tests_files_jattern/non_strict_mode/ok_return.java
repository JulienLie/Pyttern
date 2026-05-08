class Main {
    public static void main() {
        hello_world();
        return; // should not impact the match
    }

    public static void hello_world() {
        return; // should not impact the match
        System.out.println("Hello, world!");
    }
}