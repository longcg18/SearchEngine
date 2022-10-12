package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Doc {
    private final List<Word> title = new ArrayList<>();

    private final List<Word> body = new ArrayList<>();

    private final List<Word> allWords = new ArrayList<>();

    private Word[] words = new Word[allWords.size()];

    /**A constructor which receives the raw text of a document and extracts the title
     and body parts from that. Documents are provided as text files (.txt) in the
     docs directory under the project’s root directory. To reduce the difficulty of this
     assignment, each text file contain two lines. The first line is the title and the
     second line is the body. */
    public Doc(String content) {
        String line1 = "", line2 = "";
        String[] lines = content.split("\n");
        line1 = lines[0];
        line2 = lines[1];
        Scanner scanner1 = new Scanner(line1);
        Scanner scanner2 = new Scanner(line2);
        while (scanner1.hasNext()) {
            Word temp = Word.createWord(scanner1.next());
            title.add(temp);
            allWords.add(temp);
        }
        while (scanner2.hasNext()) {
            Word temp = Word.createWord(scanner2.next());
            body.add(temp);
            allWords.add(temp);
        }
    }

    /**Returns the document’s title as a list of Engine.Word objects.
     */
    public List<Word> getTitle() {
        return title;
    }

    /**
     * Returns the document’s title as a list of Engine.Word objects.
     */
    public List<Word> getBody() {
        return body;
    }

    public List<Word> getAllWords() {
        return allWords;
    }

    /**
     Two Engine.Doc objects are equal if their titles and bodies contain the same words in the
     same order. To determine if two words are equal, use the equals() method
     from the Engine.Word class.
     */
    public boolean equals(Object o) {
        if (o instanceof Doc) {
            Doc temp = (Doc) o;
            if ((temp.bodyLength() == this.bodyLength() && temp.titleLength() == this.titleLength())) {
                for (int i = 0; i < titleLength(); i++) {
                    if (!temp.title.get(i).equals(this.title.get(i))) {
                        break;
                    }
                }
                for (int i = 0; i < bodyLength(); i++) {
                    if (!temp.body.get(i).equals(this.body.get(i))) {
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }


    public int bodyLength() {
        return body.size();
    }

    public int titleLength() {
        return title.size();
    }


}