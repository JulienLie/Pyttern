class Main {
    public static void main() {
        switch ("a") { // should not impact the match
            case "b":
                break;
            case null:
                break;
            default:
                break;
        }
        hello_world();
    }

    public static void hello_world() {
        switch ("a") { // should not impact the match
            default:
                break;
        }
        System.out.println("Hello, world!");
    }
}