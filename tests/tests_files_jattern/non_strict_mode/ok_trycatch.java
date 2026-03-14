class Main {
    public static void main() {
        try {} catch (Exception e) {} // should not impact the match
        hello_world();
    }

    public static void hello_world() {
        try {} catch (Exception e) {} finally {} // should not impact the match
        System.out.println("Hello, world!");
        try {} finally {} // should not impact the match
    }
}