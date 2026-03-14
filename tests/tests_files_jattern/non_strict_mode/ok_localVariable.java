class Main {
    public static void main() {
        int x; // should not impact the match
        hello_world();
    }

    public static void hello_world() {
        String text = "roses are red, violets are blue, this text is too long already"; // should not impact the match
        System.out.println("Hello, world!");
        text = "a" + "b"; // should not impact the match

    }
}