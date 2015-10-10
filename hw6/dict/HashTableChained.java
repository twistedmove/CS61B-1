/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/

  int size;
  SList[] table;

  /**
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  // helper: isPrime
  public static boolean isPrime(int x) {
      for (int i = 2; i < x; i++) {
          if (x % i == 0) {
              return false;
          }
      }
      return true;
  }

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    for (int i = sizeEstimate; i < Integer.MAX_VALUE; i++) {
      if (isPrime(i)) {
        this.table = new SList[i];
        // System.out.println("this time input: " + sizeEstimate +
        // "actual: " + i);
        return;
      }
    }
  }

  /**
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    this.table = new SList[101];
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    int remainder = (((127 * code) + 17) % 997) % this.table.length;
    if (remainder < 0) {
      remainder += this.table.length;
    }
    return remainder;
  }

  /**
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return this.size;
  }

  /**
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return this.size() == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry temp = new Entry();
    temp.key = key;
    temp.value = value;
    int location = compFunction(key.hashCode());
    if (table[location] == null) {
      table[location] = new list.SList();
    }
    table[location].insertFront(temp);
    this.size++;
    return temp;
  }

  /**
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int location = compFunction(key.hashCode());
    if (location >= table.length) {
      return null;
    }
    SList entries = table[location];
    SListNode curr = entries.front();
    while (curr.next() != null) {
      if (((Entry) curr.item()).key().equals(key)) {
        return (Entry) curr.item();
      } else {
        curr = curr.next();
      }
    }
    if (((Entry) curr.item()).key().equals(key)) {
      return (Entry) curr.item();
    } else {
      return null;
    }
  }

  /**
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int location = compFunction(key.hashCode());
    if (location >= table.length) {
      return null;
    }
    SList entries = table[location];
    SListNode curr = (SListNode) entries.front();
    while (curr.next() != null) {
      if (((Entry)curr.item()).key().equals(key)) {
        Entry found = (Entry) curr.item();
        entries.remove(curr);
        this.size--;
        return found;
      } else {
        curr = (SListNode) curr.next();
      }
    }
    if (((Entry)curr.item()).key().equals(key)) {
      Entry found = (Entry) curr.item();
      entries.remove(curr);
      this.size--;
      return found;
    } else {
      return null;
    }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    this.size = 0;
    this.table = new SList[table.length];
  }

  public String toString() {
    String str = "";
    for (int i = 0; i < this.table.length; i++) {
      if (this.table[i] != null) {
        str += ("i = " + i + "\n");
        str += (this.table[i]);
        str += "\n";
      }
    }
    return str;
  }


  public static void main(String[] args) {
    HashTableChained tester = new HashTableChained(4);
    System.out.println(tester.table.length);
    tester.insert("hs", "123");
    tester.insert("hs", "123");
    tester.insert("hs", "123");
    tester.insert("hs", "1a23");
    System.out.println(tester);
    System.out.println(tester.size);
    tester.insert(12, 100);
    tester.insert(20, "cool");
    Entry t = tester.insert("jrs", 3);
    System.out.println(tester);
    tester.insert(150, 150);
    tester.insert("WWW", "c++");
    Entry a = tester.find("hs");
    System.out.println(tester.size);
    System.out.println(a.key() + "  " + a.value());
    Entry b = tester.find(12);
    System.out.println(b.key() + "  " + b.value());
    tester.remove("WWW");
    tester.remove(12);
    System.out.println(tester);
    System.out.println(tester.size);
  }

}
