class Calculator {
    int print_message(int a, float b, float c, String message) {
        if (a == 0) {
            return 0;
        }

        System.out.println(message);
        print_message(a - 1, b, c, message);
        return a;
    }
}