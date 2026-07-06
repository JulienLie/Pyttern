class Main {
    public static void main() {
        hello_world();
    }

    public static void hello_world() {
        synchronized (x) {
            
        } // should not impact the match
        System.out.println("Hello, world!");
    }
}