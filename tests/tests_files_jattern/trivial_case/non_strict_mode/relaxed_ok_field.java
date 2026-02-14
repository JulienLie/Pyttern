class Main {
    String name; // should not impact the match
    int answer_to_universe_and_life = 42; // should not impact the match

    public static void main() {
        hello_world();
    }

    public static float temperature; // should not impact the match

    public static void hello_world() {
        System.out.println("Hello, world!");
    }

    Object cat; // should not impact the match
}