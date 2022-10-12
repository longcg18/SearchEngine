package engine;

import java.util.ArrayList;
import java.util.List;

public class Match implements Comparable<Match> {

    private Doc d;

    private Word w;

    private int freq = 0;

    private int firstIndex;

    private List<Integer> index;

    public Match(Doc d, Word w) {
        this.d = d;
        this.w = w;
        this.freq = getFreq();
        this.firstIndex = getFirstIndex();
        this.index = new ArrayList<>();
    }

    /**A constructor to initialize a Engine.Match object with the document, the word, the
     frequency of the word in the document and the first position of the word in the
     document.
     */
    public Match(Doc d, Word w, int freq, int firstIndex) {
        this.d = d;
        this.w = w;
        this.freq = freq;
        this.firstIndex = firstIndex;
        this.index = new ArrayList<>();
    }

    public List<Integer> getIndex() {
        return index;
    }

    /**
     * Returns the frequency of the match (as explained above).
     */
    public int getFreq() {
        this.index = new ArrayList<>();
        this.freq = 0;
        List<Word> docWords = new ArrayList<>(d.getAllWords());
        for (int i = 0; i < d.titleLength(); i++) {
            //System.out.println(1);
            if (this.w.equals(d.getTitle().get(i))) {
                this.index.add(i);
                this.freq++;
            }
        }

        for (int i = 0; i < d.bodyLength(); i++) {
            if (this.w.equals(d.getBody().get(i))) {
                this.index.add(i + d.titleLength());
                this.freq++;
            }
        }

        return this.freq;
    }

    /**
     * Returns the first index of the match (as explained above).
     */
    public int getFirstIndex() {
        List<Word> docWords = new ArrayList<>(d.getAllWords());
        for (int i = 0; i < docWords.size(); i++) {
            if (this.w.equals(docWords.get(i))) {
                this.firstIndex = i;
                break;
            }
        }
        return firstIndex;
    }

    /**
     * Compare this with another Engine.Match object by the first index. This method obeys
     * the standard behavior specified by Java.
     * Engine.Match object A is greater than Engine.Match
     *  object B if the first index of A is greater than the first index of B.
     */
    @Override
    public int compareTo(Match o) {
        if (this.getFirstIndex() > o.getFirstIndex()) {
            return 0;
        }
        return -1;
    }

    public Word getWord() {
        return this.w;
    }
}