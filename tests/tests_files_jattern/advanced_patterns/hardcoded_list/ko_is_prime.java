// implementation of isPrime without hardcoded prime list
class IsPrime {
    boolean isPrime(int n) {
        Integer edge_cases[] = new Integer[]{0,1}; // definition of list, but no match because less than 3 elements
        if (edge_cases[0] == n || edge_cases[1] == n) return false;

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}