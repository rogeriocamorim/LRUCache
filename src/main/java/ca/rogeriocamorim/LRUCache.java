package ca.rogeriocamorim;
//Example 1:
//
//        Input
//        ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//        [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//        Output
//        [null, null, null, 1, null, -1, null, -1, 3, 4]
//
//        Explanation
//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.put(1, 1); // cache is {1=1}
//        lRUCache.put(2, 2); // cache is {1=1, 2=2}
//        lRUCache.get(1);    // return 1
//        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//        lRUCache.get(2);    // returns -1 (not found)
//        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//        lRUCache.get(1);    // return -1 (not found)
//        lRUCache.get(3);    // return 3
//        lRUCache.get(4);    // return 4
//
//
//        Constraints:
//
//        1 <= capacity <= 3000
//        0 <= key <= 3000
//        0 <= value <= 104
//        At most 3 * 104 calls will be made to get and put.
//

import java.util.HashMap;
import java.util.LinkedList;

class LRUCache {
    static int CAPACITY_MAX =  3000;
    static int CAPACITY_MIN =  1;

    HashMap<Integer, Integer> cache = new HashMap<>();
    LinkedList<Integer> elementNoInUse = new LinkedList();
    int capacity = 0;

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lruCache.get(1));    // return 1
        lruCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lruCache.get(2));    // returns -1 (not found)
        lruCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lruCache.get(1));    // return -1 (not found)
        System.out.println(lruCache.get(3));    // return 3
        System.out.println(lruCache.get(4));    // return 4
    }

    public LRUCache(int capacity){
        if (capacity>=CAPACITY_MIN && capacity<=CAPACITY_MAX) {
            this.capacity = capacity;
        }else{
            throw new IllegalArgumentException("Capacity can not be lower than 1");
        }
    }

    public int get(int key) {
        Integer result = cache.get(key);
        if(result==null){
            result = -1;
        }else {
            elementNoInUse.removeFirst();
            elementNoInUse.add(key);
        }
         return result;
    }

    public void put(int key, int value) {
        if (elementNoInUse.size() >= capacity) {
            int elementToRemove = elementNoInUse.get(0);
            cache.remove(elementToRemove);
            elementNoInUse.remove(0);
        }
        elementNoInUse.add(value);
        cache.put(key, value);
//        System.out.println(cache.toString());
    }
}

