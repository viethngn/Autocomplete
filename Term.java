import java.util.Comparator;

public class Term implements Comparable<Term> {

    private String query;
    private long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight){
        if (query == null){
            throw new java.lang.NullPointerException();
        }
        if (weight < 0){
            throw new java.lang.IllegalArgumentException();
        }
        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new Comparator<Term>(){
            public int compare(Term a, Term b){
                if (a.weight < b.weight){
                    return -1;
                }
                else if (a.weight > b.weight){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        };
    }

    // Compares the two terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r){
        return new Comparator<Term>(){
            public int compare (Term a, Term b){
                String a0 = a.query;
                String b0 = b.query;
                int min = (a0.length() < b0.length()) ? a0.length() : b0.length();
                if (min >= r){
                    return a0.substring(0, r).compareTo(b0.substring(0, r));
                }
                else {
                    return a0.substring(0, min).compareTo(b0.substring(0, min));
                }
            }
        };
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that){
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString(){
        return this.weight + "\t" + this.query;
    }

    // unit testing (required)
    public static void main(String[] args){
        String a = "it", b = "its", c = "iteration", d = "iterate";
        Term test1 = new Term(a, 1);
        Term test2 = new Term(c, 1);

        System.out.println(test1.toString());
        System.out.println(test2.toString());
    }
}