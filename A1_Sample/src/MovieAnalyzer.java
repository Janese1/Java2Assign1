import javax.xml.transform.Result;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieAnalyzer {
    private ArrayList<Movie> list;

    public MovieAnalyzer(String dataset_path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dataset_path), StandardCharsets.UTF_8));
        reader.readLine();
        String movie = null;
        ArrayList<Movie> list = new ArrayList<>();
        while ((movie = reader.readLine()) != null) {
            String[] strArray = movie.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            if (strArray[2].equals("")) {
                list.add(new Movie(strArray[1], 0, strArray[3], strArray[4], strArray[5], strArray[6], strArray[7],
                        strArray[8], strArray[9], strArray[10], strArray[11], strArray[12]));
            } else {
                list.add(new Movie(strArray[1], Integer.parseInt(strArray[2]), strArray[3], strArray[4], strArray[5], strArray[6], strArray[7],
                        strArray[8], strArray[9], strArray[10], strArray[11], strArray[12]));
            }
        }
        this.list=new ArrayList<>();
        this.list.addAll(list);
    }

    public Map<Integer, Integer> getMovieCountByYear() {
        Map<Integer,Integer> result=new LinkedHashMap<>();
                list.stream()
                .filter(a->(a.getYear()!=0))
                .collect(Collectors.toMap(Movie::getYear, s->1,Integer::sum))
                .entrySet().stream()
                .sorted(Map.Entry.<Integer,Integer>comparingByKey().reversed())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
                System.out.println(result);
                return result;
    }

    public Map<String, Integer> getMovieCountByGenre(){
        
    }

    public static void main(String[] args) throws IOException {
        MovieAnalyzer a = new MovieAnalyzer("A1_Sample/resources/imdb_top_500.csv");
        Map<Integer, Integer> b = a.getMovieCountByYear();

    }
}
