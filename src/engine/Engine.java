package engine;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Engine {

    private Doc[] docs = new Doc[100];

    private int numDocs = 0;
    /**
     * Loads the documents from the folder specified by dirname (which resides under
     * the project’s root folder) and returns the number of documents loaded. Refer to
     * the Engine.Doc class for more information about a Engine.Doc object.
     */
    public int loadDocs(String dirname) {
        int numberOfDocs = 0;
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of(dirname))) {
            for (Path file:stream) {
                fileInputStream = new FileInputStream(file.toFile().getAbsolutePath());
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String line = bufferedReader.readLine();
                //System.out.println(line);
                String temp = bufferedReader.readLine();
                //System.out.println(temp);
                Doc a = new Doc(line + "\n" +temp);
                docs[numDocs] = a;
                numberOfDocs++;
                numDocs++;
                //System.out.println(numDocs);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return numberOfDocs;
    }

    /**
     * Returns an array of documents in the original order.
     */
    public Doc[] getDocs() {
        return docs;
    }

    /**
     * Performs the search function of the engine. Returns a list of sorted search
     * results. Refer to the classes above to know the expected search results.
     */
    public List<Result> search(Query q) {
        int size = 0;
        List<Result> results = new ArrayList<>();
        for (int i = 0; i < numDocs; i++) {
            if(q.matchAgainst(docs[i]).size() != 0) {
                Result r = new Result(docs[i], q.matchAgainst(docs[i]));
                results.add(r);
            }
        }
        results.sort(Result::compareTo);
        return results;
    }

    /**
     * Converts a list of search results into HTML format. The output of this method is
     * the output of Engine.Result.htmlHighlight() combined together (without any
     * delimiter). Refer to the 3rd line of the file testCases.html for a specific example.
     */
    public String htmlResult(List<Result> results) {
        String html = "";
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).htmlHighlight() != null) {
                html += results.get(i).htmlHighlight();
            }
        }
        return html;
    }
}