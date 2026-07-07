class Main {
    public static synchronized void main() {
        hello_world();
    }

    public static synchronized void hello_world() { // the synchronized keyword shouldn't be there
        System.out.println("Hello, world!");
    }
}