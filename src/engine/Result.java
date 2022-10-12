package engine;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Result implements Comparable<Result> {

    private List<Match> matchList = new ArrayList<>();

    private final Doc doc;

    /**
     * A constructor to initialize a Engine.Result object with the related document and the list
     * of matches
     * @param d: Engine.Doc
     * @param matches: List
     */
    public Result(Doc d, List<Match> matches) {
        this.doc = d;
        this.matchList = matches;
    }

    /**
     * @return matches.
     */
    public List<Match> getMatches() {
        return this.matchList;
    }

    /**
     * @return total Frequency
     */
    public int getTotalFrequency() {
        int totalFreq = 0;
        return getMatches().get(0).getFreq();
       //return totalFreq;
    }

    /**
     *
     * @return Average First Index
     */
    public double getAverageFirstIndex() {
        double sumFirstIndex = 0.0;
        for (Match match : matchList) {
            sumFirstIndex += match.getFirstIndex();
        }
        //System.out.println(matchList.size());
        return sumFirstIndex / (double) matchList.size();
    }

    /**
     Highlight the matched words in the document using HTML markups. For a
     matched word in the document’s title, put the tag <u> and </u> around the
     word’s text part (the <u> tag should not affect the word’s prefix and suffix). For a
     matched word in the document’s body, surround the word’s text part with the tag
     <b> and </b>.
     */
    public String htmlHighlight() {
        String htmlTitle = "";
        String htmlBody = "";
        List<Word> list = new ArrayList<>();
        for (int i = 0; i < matchList.size(); i++) {
            list.add(matchList.get(i).getWord());
        }

        for (int i = 0; i < doc.titleLength()-1; i++) {
            String tmpTitle;
            if (list.contains(doc.getAllWords().get(i))) {
                tmpTitle = doc.getAllWords().get(i).getPrefix()
                        + "<u>" + doc.getAllWords().get(i).getText() + "</u>"
                        + doc.getAllWords().get(i).getSuffix() + " ";
            } else {
                tmpTitle = doc.getAllWords().get(i).getPrefix()
                        + doc.getAllWords().get(i).getText()
                        + doc.getAllWords().get(i).getSuffix() + " ";
            }
            htmlTitle += tmpTitle;
        }

        if (list.contains(doc.getAllWords().get(doc.titleLength()-1))) {
            htmlTitle += doc.getAllWords().get(doc.titleLength()-1).getPrefix() + "<u>"
                    + doc.getAllWords().get(doc.titleLength()-1).getText() + "</u>"
                    + doc.getAllWords().get(doc.titleLength()-1).getSuffix();
        } else {
            htmlTitle += doc.getAllWords().get(doc.titleLength() - 1).getPrefix()
                    + doc.getAllWords().get(doc.titleLength() - 1).getText()
                    + doc.getAllWords().get(doc.titleLength() - 1).getSuffix();
        }

        for (int i = doc.titleLength(); i < doc.getAllWords().size()-1; i++) {
            String tmpBody;
            if (list.contains(doc.getAllWords().get(i))) {
                tmpBody = doc.getAllWords().get(i).getPrefix()
                        + "<b>" + doc.getAllWords().get(i).getText() + "</b>"
                        + doc.getAllWords().get(i).getSuffix() + " ";
            } else {
                tmpBody = doc.getAllWords().get(i).getPrefix()
                        + doc.getAllWords().get(i).getText()
                        + doc.getAllWords().get(i).getSuffix() + " ";
            }
            htmlBody += tmpBody;
        }
        if (list.contains(doc.getAllWords().get(doc.getAllWords().size()-1))) {
            htmlBody += doc.getAllWords().get(doc.getAllWords().size()-1).getPrefix() + "<b>"
                    + doc.getAllWords().get(doc.getAllWords().size()-1).getText() + "</b>"
                    + doc.getAllWords().get(doc.getAllWords().size()-1).getSuffix();
        } else {
            htmlBody += doc.getAllWords().get(doc.getAllWords().size() - 1).getPrefix()
                    + doc.getAllWords().get(doc.getAllWords().size() - 1).getText()
                    + doc.getAllWords().get(doc.getAllWords().size() - 1).getSuffix();
        }

        return "<h3>" + htmlTitle + "</h3>" + "<p>" + htmlBody + "</p>";
    }

    /**
     * These are criteria to determine if Engine.Result A is greater than Engine.Result B
     * (in descending order of priority):
     * Rev 1.1 <2021-dec-14>
     *  A has greater match count than B
     *  A has greater total frequency than B
     *  A has lower average first index than B
     */
    @Override
    public int compareTo(Result o) {
        if(this.getMatches().size() > o.getMatches().size()) {
            return -1;
        } else if (this.getMatches().size() < o.getMatches().size()) {
            return 1;
        } else if (this.getTotalFrequency() > o.getTotalFrequency()) {
            return -2;
        } else if (this.getTotalFrequency() < o.getTotalFrequency()) {
            return 2;
        } else return 0;

    }

    public Doc getDoc() {
        return doc;
    }
}