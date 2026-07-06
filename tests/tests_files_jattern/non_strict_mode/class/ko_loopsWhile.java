class Main {
    public static void main() {
        while (true) {
            hello_world(); // this statement should be outside the loop, not inside
        }
    }

    public static void hello_world() {
        System.out.println("Hello, world!");
    }
}