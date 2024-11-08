public class Main {
    public static void main(String[] args) {
        HashTable table = new HashTable(10);

        // Insert several values, including some collisions
        table.put(new Node(0, "red"));
        table.put(new Node(1, "blue"));
        table.put(new Node(2, "green"));
        table.put(new Node(3, "yellow"));
        table.put(new Node(10, "orange"));
        table.put(new Node(9, "purple"));
        table.put(new Node(19, "brown"));

        // Remove a value to ensure proceeding accesses still work
        table.remove(0);

        System.out.println(table);
        System.out.println(table.get(9));
        System.out.println(table.get(10));
        System.out.println(table.get(11));

        // Insert another value using the key from removal
        table.put(new Node(0, "cyan"));

        System.out.println(table);

        // Insert more values, requiring a rehash
        table.put(new Node(5, "magenta"));
        table.put(new Node(6, "peach"));
        table.put(new Node(7, "black"));
        table.put(new Node(8, "white"));

        System.out.println(table);
    }
}