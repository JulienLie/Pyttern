class Main {
    public static void main() {
        if (true) {
            hello_world(); // this statement should be outside the if, not inside
        }
    }

    public static void hello_world() {
        System.out.println("Hello, world!");
    }
}