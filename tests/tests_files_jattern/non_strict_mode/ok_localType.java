class Main {
    public static void main() {
        class SubClass {} // should not impact the match
        hello_world();
    }

    public static void hello_world() {
        record SubRecord() {} // should not impact the match
        System.out.println("Hello, world!");
        interface SubInterface {} // should not impact the match
    }
}