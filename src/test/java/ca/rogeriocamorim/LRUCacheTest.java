package ca.rogeriocamorim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {
    @Test
    @DisplayName("Add one element to cache")
    void addOneElementToCache() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1); // cache is {1=1}

        assertEquals(1, lruCache.cache.size());
        assertEquals(1, lruCache.elementNoInUse.size());
        assertEquals("[1]", lruCache.elementNoInUse.toString());
        assertEquals("{1=1}", lruCache.cache.toString());
    }

    @Test
    void addTwoElementsToCache() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}

        assertEquals(2, lruCache.cache.size());
        assertEquals(2, lruCache.elementNoInUse.size());
        assertEquals("[1, 2]", lruCache.elementNoInUse.toString());
        assertEquals("{1=1, 2=2}", lruCache.cache.toString());
    }

    @Test
    void addTreeElementsToCache_shouldRemoveElementThatWasNotInUse() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        lruCache.put(3, 3); // cache is {2=2, 3=3}

        assertEquals(2, lruCache.cache.size());
        assertEquals(2, lruCache.elementNoInUse.size());
        assertEquals("[2, 3]", lruCache.elementNoInUse.toString());
        assertEquals("{2=2, 3=3}", lruCache.cache.toString());
    }

    @Test
    void getFirstElement_addTreeElementsToCache_shouldRemoveElementThatWasNotInUse() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        lruCache.get(1); // elementNoInUse is [2, 1]
        lruCache.put(3, 3); // cache is {1=1, 3=3}

        assertEquals(2, lruCache.cache.size());
        assertEquals(2, lruCache.elementNoInUse.size());
        assertEquals("[1, 3]", lruCache.elementNoInUse.toString());
        assertEquals("{1=1, 3=3}", lruCache.cache.toString());
    }

    @Test
    void getElementNoInCache_addTreeElementsToCache_shouldReturnMinus1() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        lruCache.get(1); // elementNoInUse is [2, 1]
        lruCache.put(3, 3); // cache is {1=1, 3=3}

        assertEquals(2, lruCache.cache.size());
        assertEquals(2, lruCache.elementNoInUse.size());
        assertEquals("[1, 3]", lruCache.elementNoInUse.toString());
        assertEquals("{1=1, 3=3}", lruCache.cache.toString());
        assertEquals(-1, lruCache.get(2));
    }

    @Test
    void throwException_whenCacheIsOverCapacity() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            LRUCache lruCache = new LRUCache(4000);
        });
    }

    @Test
    void throwException_whenCacheIsBelowMinimal() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            LRUCache lruCache = new LRUCache(-3);
        });
    }


}