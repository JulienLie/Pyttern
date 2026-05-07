class Main {
    public static void main() {
        try {
            hello_world(); // this statement should be outside the try/catch, not inside
        } catch (Exception e) {

        }
    }

    public static void hello_world() {
        System.out.println("Hello, world!");
    }
}