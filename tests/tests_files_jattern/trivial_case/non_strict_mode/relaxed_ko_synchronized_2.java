class Main {
    public static void main() {
        synchronized (x) {
            hello_world(); // this statement should be outside the sync block, not inside
        }
    }

    public static void hello_world() {
        System.out.println("Hello, world!");
    }
}