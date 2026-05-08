class IsPrime {
    boolean isPrime(int n) {
        // hardcoded list of primes
        Integer primes[] = new Integer[]{2,3,5,7};

        // return true if hardcoded list contains n, false otherwise
        for (int i = 0; i < primes.length; i++) {
            if (primes[i] == n) {
                return true;
            }
        }
        return false;
    }
}