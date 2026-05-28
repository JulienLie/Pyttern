class Main {
    public static void main() {
        if (false) { // should not impact the match
            return;
        }
        hello_world();
    }

    public static void hello_world() {
        if (false) System.out.print(">:)"); // should not impact the match
        
        System.out.println("Hello, world!");

        if (true) { // should not impact the match
            return;
        } else {
            return;
        }
    }
}