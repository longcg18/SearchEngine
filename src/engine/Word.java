package engine;

import java.io.*;
import java.util.*;

public class Word {

    private final String prefix;

    private final String text;

    private final String suffix;

    /**Default constructor for testing */
    public Word (String pre, String txt, String suf) {
        this.prefix = pre;
        this.suffix = suf;
        this.text = txt;
    }
    /**
     * A set of stop words loaded by the loadStopWords() method.
     */
    public static Set<String> stopWords;

    /**
     * Returns true if the word is a keyword.
     */
    boolean isKeyword() {


        if(this.getText().isEmpty()) {
            return false;
        }

        if (stopWords.contains(this.getText().toLowerCase())) {
            return false;
        }

        for (int i = 0; i < this.getText().length(); i++) {
            if (this.getText().charAt(i) <= '9' && this.getText().charAt(i) >= '0') {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the prefix part of the word.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Returns the suffix part of the word.
     */
    public String getSuffix() {
        return this.suffix;
    }



    /**
     * Returns the text part of the word.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Two words are considered equal if their text parts are equal, case-insensitively.
     */
    public boolean equals(Object o) {
        if (o instanceof Word) {
            Word w = (Word) o;
            return this.getText().toLowerCase().equals(w.getText().toLowerCase());
        }
        return false;
    }

    /**
     * Returns the raw text of the word.
     */
    public String toString() {
        return getPrefix() + getText() + getSuffix();
    }

    /**
     * Construct and return a complete Engine.Word object from raw text.
     */
    public static Word createWord(String rawText) {
        String pre = "";
        String txt = "";
        String suf = "";
        int i;
        for (i = 0; i < rawText.length(); i++) {
            //This character is not a alphabet letter
            if ((!isALetter(rawText.charAt(i)))) {
                pre += rawText.charAt(i);
            } else {
                break;
            }
        }
        int j;
        for (j = i; j < rawText.length(); j++) {
            if (isALetter(rawText.charAt(j))) {
                if ((rawText.charAt(j) == ',' && j > 1)) {
                    break;
                } else if(rawText.charAt(j) == '.'
                        && (rawText.charAt(j-1) > '9' || rawText.charAt(j-1) < '0')
                        && (rawText.length() - j < 3)) {
                    break;
                } else {
                    txt += rawText.charAt(j);
                }
            } else {
                break;
            }
        }
        int k;
        for (k = j; k < rawText.length(); k++) {
            suf += rawText.charAt(k);
        }

        return new Word(pre, txt, suf);
    }

    public static boolean isALetter(char i) {
        if ((i <= 'z' && i >= 'a') || (i <= 'Z' && i >= 'A') || (i == ',') ||
                (i == '.') || (i == '-') || ('0' <= i && '9' >= i)) {
            return true;
        }
        return false;
    }
    /**
     * Load stop words into the set Engine.Word.stopWords from the text file whose name is
     * specified by fileName (which resides under the project’s root directory). This
     * method returns true if the task is completed successfully and false otherwise.
     */
    public static boolean loadStopWords(String fileName) throws FileNotFoundException{
        stopWords = new HashSet<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int count = 0;
            while (scanner.hasNext()) {
                String word = scanner.next();
                stopWords.add(word);
                count++;
            }
        } catch (FileNotFoundException e) {

        }
        if (stopWords.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}

