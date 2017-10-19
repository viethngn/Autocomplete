import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator){
        if (a == null || key == null || comparator == null){
            throw new java.lang.IllegalArgumentException();
        }
        int lo = 0, hi = a.length - 1;
        if (comparator.compare(a[lo], key) == 0){
            return 0;
        }
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if (comparator.compare(key, a[mid]) < 0){
                hi = mid -1;
            }
            else if (comparator.compare(key, a[mid]) > 0) {
                lo = mid + 1;
            }
            else if (comparator.compare(a[mid - 1], a[mid]) == 0){
                hi = mid - 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator){
        if (a == null || key == null || comparator == null){
            throw new java.lang.IllegalArgumentException();
        }
        int lo = 0, hi = a.length - 1;
        if (comparator.compare(a[hi], key) == 0){
            return hi;
        }
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if (comparator.compare(key, a[mid]) < 0){
                hi = mid -1;
            }
            else if (comparator.compare(key, a[mid]) > 0) {
                lo = mid + 1;
            }
            else if (comparator.compare(a[mid + 1], a[mid]) == 0){
                lo = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    // unit testing (required)
    public static void main(String[] args){
        Term[] terms = {new Term("Aaa", 8), new Term("AAb", 5), new Term("Abc", 2), new Term("AAb", 3), new Term("AAa", 3),
                new Term("Bbb", 3), new Term("Bcd", 4), new Term("Bef", 2),
                new Term("Ccc", 0), new Term("Cde", 1), new Term("Cgh", 7),
                new Term("Ddd", 6), new Term("Def", 7), new Term("Dxz", 8),
                new Term("Eee", 3), new Term("Efg", 3), new Term("Ekm", 3)};
        System.out.println(firstIndexOf(terms, new Term("A", 0), Term.byPrefixOrder("A".length())));
        System.out.println(lastIndexOf(terms, new Term("A", 0), Term.byPrefixOrder("A".length())));
        System.out.println(firstIndexOf(terms, new Term("AA", 0), Term.byPrefixOrder("AA".length())));
        System.out.println(lastIndexOf(terms, new Term("AA", 0), Term.byPrefixOrder("AA".length())));
    }
}