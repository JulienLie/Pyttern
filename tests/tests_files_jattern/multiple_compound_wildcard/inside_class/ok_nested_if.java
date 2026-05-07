class Compound {
    public static void main() {
        // we allow for any amount of nested compound statements
        if (true) {
            if (true) {
                if (0 != 1) {
                    System.out.println("Hello, World!");
                }
            }
        }
    }
}