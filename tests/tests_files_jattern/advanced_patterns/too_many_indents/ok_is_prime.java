// implementation of isPrime with too many indents
class IsPrime {
    boolean isPrime(int n) {
        if (n != 0) {
            if (n != 1) {
                // n != 0 && n != 1, do proper prime verification now
                for (int i = 2; i < n; i++) {
                    if (n % i == 0) {
                        return false;
                    }
                }

                // no divisor, thus prime
                return true;
            }
        }

        // n == 0 or n == 1
        return false;
    }
}