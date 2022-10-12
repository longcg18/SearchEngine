package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Query {

    private final List<Word> keyWords = new ArrayList<>();

    /**A constructor which receives the raw search phrase from user, then extract only
     keywords from it. */
    public Query(String searchPhrase) {
        Scanner scanner = new Scanner(searchPhrase);
        while (scanner.hasNext()) {
            Word newWord = Word.createWord(scanner.next());
            if (isKeyWord(newWord)) {
                keyWords.add(newWord);
            }
        }
    }

    boolean isKeyWord(Word word) {
        return (word.isKeyword());
    }

    /**
     * Returns a list of the query’s keywords in the same order as they appear in the
     * raw search phrase.
     */
    public List<Word> getKeywords() {
        return keyWords;
    }

    /**
     * Returns a list of matches against the input document. Sort matches by position
     * where the keyword first appears in the document. See the Engine.Match class for more
     * information about search matches.
     */
    public List<Match> matchAgainst(Doc d) {
        int cnt = 0;
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < keyWords.size(); i++) {
            int freq = 0, firstIndex = 0;
            if (d != null && d.getAllWords().contains(keyWords.get(i))) {
                for (int j = 0; j < d.getAllWords().size(); j++) {
                    if (d.getAllWords().get(j).equals(keyWords.get(i))) {
                        freq++;
                        firstIndex = j;
                    }
                }
            }
            if (freq != 0 || firstIndex != 0) {
                matches.add(new Match(d, keyWords.get(i), freq, firstIndex));
            }
        }
        matches.sort(Match::compareTo);
        return matches;
    }

}