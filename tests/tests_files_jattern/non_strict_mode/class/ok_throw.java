class Main {
    public static void main() {
        hello_world();
        throw new Exception("Cat too adorable <3"); // should not impact the match
    }

    public static void hello_world() {
        System.out.println("Hello, world!");
    }
}