/*
 * @(#)Hashtable.java 1.105 03/12/19
 *
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.Map;

public class Hashtable<K, V> {
  /**
   * The hash table data.
   */
  private transient Entry[] table;

  /**
   * The total number of entries in the hash table.
   */
  private transient int count;

  /**
   * The table is rehashed when its size exceeds this threshold. (The value of
   * this field is (int)(capacity * loadFactor).)
   */
  private int threshold;

  /**
   * The load factor for the hashtable.
   */
  private float loadFactor;

  /**
   * Constructs a new, empty hashtable with the specified initial capacity and
   * the specified load factor.
   * 
   * @param initialCapacity
   *          the initial capacity of the hashtable.
   * @param loadFactor
   *          the load factor of the hashtable.
   * @exception IllegalArgumentException
   *              if the initial capacity is less than zero, or if the load
   *              factor is nonpositive.
   */
  public Hashtable(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
      throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
      throw new IllegalArgumentException("Illegal Load: " + loadFactor);

    if (initialCapacity == 0)
      initialCapacity = 1;
    this.loadFactor = loadFactor;
    table = new Entry[initialCapacity];
    threshold = (int) (initialCapacity * loadFactor);
    System.out.println("Hashtable capacity = " + initialCapacity);
  }


  public Hashtable() {
    this(11, 0.75f);
  }


  /**
   * Returns the number of keys in this hashtable.
   */
  public synchronized int size() {
    return count;
  }

  /**
   * Tests if this hashtable maps no keys to values.
   */
  public synchronized boolean isEmpty() {
    return count == 0;
  }

  /**
   * Tests if some key maps into the specified value in this hashtable. This
   * operation is more expensive than the <code>containsKey</code> method.
   * <p>
   */
  public synchronized boolean contains(Object value) {
    if (value == null) {
      throw new NullPointerException();
    }

    Entry tab[] = table;
    for (int i = tab.length; i-- > 0;) {
      for (Entry<K, V> e = tab[i]; e != null; e = e.next) {
        if (e.value.equals(value)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Tests if the specified object is a key in this hashtable.
   */
  public synchronized boolean containsKey(Object key) {
    Entry tab[] = table;
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % tab.length;
    for (Entry<K, V> e = tab[index]; e != null; e = e.next) {
      if ((e.hash == hash) && e.key.equals(key)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the value to which the specified key is mapped in this hashtable.
   */
  public synchronized V get(Object key) {
    Entry tab[] = table;
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % tab.length;
    for (Entry<K, V> e = tab[index]; e != null; e = e.next) {
      if ((e.hash == hash) && e.key.equals(key)) {
        return e.value;
      }
    }
    return null;
  }

  /**
   * Increases the capacity of and internally reorganizes this hashtable, in
   * order to accommodate and access its entries more efficiently. This method
   * is called automatically when the number of keys in the hashtable exceeds
   * this hashtable's capacity and load factor.
   */
  protected void rehash() {
    int oldCapacity = table.length;
    Entry[] oldMap = table;

    int newCapacity = oldCapacity * 2 + 1;
    Entry[] newMap = new Entry[newCapacity];

    threshold = (int) (newCapacity * loadFactor);
    table = newMap;

    for (int i = oldCapacity; i-- > 0;) {
      for (Entry<K, V> old = oldMap[i]; old != null;) {
        Entry<K, V> e = old;
        old = old.next;

        int index = (e.hash & 0x7FFFFFFF) % newCapacity;
        e.next = newMap[index];
        newMap[index] = e;
      }
    }
    System.out.println("\nRehashing done: new capacity = " + newCapacity);
  }

  /**
   * Maps the specified key to the specified value in this
   * hashtable.
   */
  public synchronized V put(K key, V value) {
    // Make sure the value is not null
    if (value == null) {
      throw new NullPointerException();
    }

    // Makes sure the key is not already in the hashtable.
    Entry tab[] = table;
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % tab.length;
    for (Entry<K, V> e = tab[index]; e != null; e = e.next) {
      if ((e.hash == hash) && e.key.equals(key)) {
        V old = e.value;
        e.value = value;
        return old;
      }
    }

    if (count >= threshold) {
      // Rehash the table if the threshold is exceeded
      rehash();

      tab = table;
      index = (hash & 0x7FFFFFFF) % tab.length;
    }

    // Creates the new entry.
    Entry<K, V> e = tab[index];
    tab[index] = new Entry<K, V>(hash, key, value, e);
    count++;
    return null;
  }

  /**
   * Removes the key (and its corresponding value) from this hashtable. This
   * method does nothing if the key is not in the hashtable.
   */
  public synchronized V remove(Object key) {
    Entry tab[] = table;
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % tab.length;
    for (Entry<K, V> e = tab[index], prev = null; e != null; prev = e, e = e.next) {
      if ((e.hash == hash) && e.key.equals(key)) {
        if (prev != null) {
          prev.next = e.next;
        } else {
          tab[index] = e.next;
        }
        count--;
        V oldValue = e.value;
        e.value = null;
        return oldValue;
      }
    }
    return null;
  }

  private static class Entry<K, V> implements Map.Entry<K, V> {
    int hash;
    K key;
    V value;
    Entry<K, V> next;

    protected Entry(int hash, K key, V value, Entry<K, V> next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public V setValue(V value) {
      if (value == null)
        throw new NullPointerException();

      V oldValue = this.value;
      this.value = value;
      return oldValue;
    }

    public boolean equals(Object o) {
      if (!(o instanceof Map.Entry))
        return false;
      Map.Entry e = (Map.Entry) o;

      return (key == null ? e.getKey() == null : key.equals(e.getKey()))
          && (value == null ? e.getValue() == null : value.equals(e.getValue()));
    }

    public int hashCode() {
      return hash ^ (value == null ? 0 : value.hashCode());
    }
  }
  
  public void printEntries()
  {
   for(int i=0; i<table.length; i++){
     Entry e = table[i];
     while( e != null ){ 
       System.out.printf("%5.2f / %d \n", e.value, i);
       e = e.next;
     }
   }
 }
}
