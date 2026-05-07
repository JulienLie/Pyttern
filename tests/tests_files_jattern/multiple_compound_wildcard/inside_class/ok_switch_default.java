class Compound {
    public static void main() {
        String a = "a";
        switch (a) {
            case "a":
                System.out.println("Nope!");
            default:
                System.out.println("Hello, World!"); // should explore every body
        }
    }
}