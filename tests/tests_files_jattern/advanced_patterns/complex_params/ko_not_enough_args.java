class Calculator {
    int print_message(int a, String message) {
        if (a == 0) {
            return 0;
        }

        System.out.println(message);
        print_message(a - 1, message);
        return a;
    }
}