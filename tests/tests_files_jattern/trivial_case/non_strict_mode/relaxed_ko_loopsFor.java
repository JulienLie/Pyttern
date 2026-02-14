class Main {
    public static void main() {
        for (int x = 0; x < 0; x++) {
            hello_world(); // this statement should be outside the loop, not inside
        }
    }

    public static void hello_world() {
        System.out.println("Hello, world!");
    }
}