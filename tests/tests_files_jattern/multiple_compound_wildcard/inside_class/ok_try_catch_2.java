class Compound {
    public static void main() {
        try {

        } catch (RuntimeException e) {
            
        } catch (Exception e) { // the wildcard should also check into the body of each catch clause
            System.out.println("Hello, World!");
        }
    }
}