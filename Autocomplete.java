import edu.princeton.cs.algs4.Quick3way;
import java.util.Arrays;

public class Autocomplete {
    private Term[] term;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms){
        if(terms == null){
            throw new java.lang.NullPointerException();
        }
        this.term = terms;
        Quick3way.sort(this.term);
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix){
        if(prefix == null){
            throw new java.lang.NullPointerException();
        }
        int FirstIndex = BinarySearchDeluxe.firstIndexOf(term, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        if (FirstIndex == -1){
            return new Term[0];
        }
        int LastIndex = BinarySearchDeluxe.lastIndexOf(term, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        Term[] matches = new Term[LastIndex - FirstIndex + 1];
        for (int i = 0; i < matches.length; i++){
            matches[i] = term[FirstIndex++];
        }
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix){
        if(prefix == null){
            throw new java.lang.NullPointerException();
        }
        int FirstIndex = BinarySearchDeluxe.firstIndexOf(term, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        int LastIndex = BinarySearchDeluxe.lastIndexOf(term, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        return LastIndex - FirstIndex + 1;
    }

    // unit testing (required)
    public static void main(String[] args){
        /*Term[] terms = {new Term("Aaa", 8), new Term("AAb", 5), new Term("Abc", 2), new Term("AAb", 3), new Term("AAa", 3),
                        new Term("Bbb", 3), new Term("Bcd", 4), new Term("Bef", 2),
                        new Term("Ccc", 0), new Term("Cde", 1), new Term("Cgh", 7),
                        new Term("Ddd", 6), new Term("Def", 7), new Term("Dxz", 8),
                        new Term("Eee", 3), new Term("Efg", 3), new Term("Ekm", 3)};
        Autocomplete autocomplete = new Autocomplete(terms);
        for (int i = 0; i < autocomplete.term.length; i++) {
            System.out.println(autocomplete.term[i]);
        }
        System.out.println(autocomplete.numberOfMatches("E"));
        System.out.println(autocomplete.numberOfMatches("AA"));

        Term[] matches = autocomplete.allMatches("A");

        for (int i = 0; i < matches.length; i++) {
            System.out.println(matches[i]);
        }*/

        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}