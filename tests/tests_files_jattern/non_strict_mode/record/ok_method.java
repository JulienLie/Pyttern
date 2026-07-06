record Book(int val) {
    public int sub(Book a, Book b) {
        return a.val - b.val;
    }

    public int add(Book a, Book b) {
        return a.val + b.val;
    }

    public int mult(Book a, Book b) {
        return a.val * b.val;
    }
}