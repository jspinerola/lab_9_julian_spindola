import java.util.Arrays;

public class HashTable {
    private Node[] table;
    private int capacity;
    private int occupants;
    private final Node VACANT = new Node(-1, "N/A");

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Node[capacity];
        this.occupants = 0;
    }

    public int hashCode(Integer key) {
        return key % capacity;
    }

    public int doubleHash(Integer key, int i) {
        return (hashCode(key) + Math.abs(13 - key % 13) * i) % capacity;
    }

    public String get(Integer key) {
        int hash = hashCode(key);
        int count = 1;

        while(table[hash] != null && table[hash].getKey() != key) {
            hash = doubleHash(key, count);
            count++;
        }

        if (table[hash] == null) {
            return null;
        }

        return table[hash].getData();
    }

    // Implement put method for in-class assignment
    public void put(Node node) {
        int collisionCount = 0;
        int hash = hashCode(node.getKey());

        if(occupants >= (capacity * (.8))){
            rehash();
        }


        while (table[hash] != null){
            if(table[hash].key == -1){
                table[hash] = node;
                occupants++;
                return;
            }
            collisionCount++;
            hash = doubleHash(node.getKey(), collisionCount);
        }

        table[hash] = node;
        occupants++;
    }

    // Implement remove and rehash methods for lab assignment
    public void remove(Integer key){
        int hash = hashCode(key);
        if (table[hash] != null) {
            table[hash] = VACANT;
            occupants--;
        }
    }

    private void rehash(){
        HashTable newTable = new HashTable(this.capacity * 2);
        for (Node node:
             table) {
            if (node != null){
                newTable.put(node);
            }
        }
        this.table = newTable.table;
        this.capacity = newTable.capacity;
        this.occupants = newTable.occupants;
//        System.out.println(Arrays.toString(newTable.table));
        this.table = Arrays.copyOf(newTable.table, this.capacity * 2);
    }

    public String toString() {
        String s = "[";
        String color;

        for (int index = 0; index < this.capacity - 1; index++) {
            color = table[index] == null ? null : table[index].getData();
            s += color + ", ";

            if (index % 10 == 9) {
                s += "\n";
            }
        }

        color = table[this.capacity - 1] == null ? null :
                table[this.capacity - 1].getData();
        s += color + "]";
        return s;
    }
}

class Node {
    Integer key;
    String data;

    public Node(Integer key, String data) {
        this.key = key;
        this.data = data;
    }

    public Integer getKey() {
        return key;
    }

    public String getData() {
        return data;
    }
}