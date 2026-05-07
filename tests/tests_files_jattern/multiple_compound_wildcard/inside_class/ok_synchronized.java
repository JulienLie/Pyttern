class Compound {
    public static void main() {
        String a;
        synchronized (a) {
            System.out.println("Hello, World!");
        }
    }
}