import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
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

            for (int i = 0; i < strArray.length; i++) {
                if (strArray[i].contains("\"")){
                    strArray[i]=strArray[i].substring(1,strArray[i].length()-1);
                }
            }

            if (strArray[2].equals("")) {
                list.add(new Movie(strArray[1], 0, strArray[3],
                        Integer.parseInt(strArray[4].substring(0,strArray[4].length()-4)),
                        strArray[5], strArray[6], strArray[7],
                        strArray[8], strArray[9], strArray[10], strArray[11], strArray[12],strArray[13],
                        strArray[14],strArray[15]));
            } else {
                list.add(new Movie(strArray[1], Integer.parseInt(strArray[2]), strArray[3],
                        Integer.parseInt(strArray[4].substring(0,strArray[4].length()-4)),
                        strArray[5], strArray[6], strArray[7],
                        strArray[8], strArray[9], strArray[10], strArray[11], strArray[12],strArray[13],
                        strArray[14],strArray[15]));
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
               // System.out.println(result);
                return result;
    }

    public Map<String, Integer> getMovieCountByGenre(){
        ArrayList<String> genre =new ArrayList<>();
        List<Movie> newList= list.stream().filter(a->(!a.getGenre().equals("")))
                .collect(Collectors.toList());
        for (Movie i:
             newList) {
            if (i.getGenre().contains(",")){
                String[] array=i.getGenre().split(",");
                for (String e:
                     array) {
                    genre.add(e.replaceAll(" ",""));
                }
            } else{
                genre.add(i.getGenre());
            }
        }
        Map<String,Integer> result=new LinkedHashMap<>();
       genre.stream().collect(Collectors.toMap(p->p,s->1,Integer::sum))
               .entrySet().stream()
               .sorted(Map.Entry.<String,Integer>comparingByValue().reversed()
                       .thenComparing(Map.Entry.comparingByKey()))
               .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        //System.out.println(result);
        return result;
    }

    public Map<List<String>, Integer> getCoStarCount(){
        ArrayList<List<String>> stars=new ArrayList<>();
        for (Movie i:
             list) {
            List<String> s1=Arrays.asList(i.getStar1(),i.getStar2());
            Collections.sort(s1);
            List<String> s2=Arrays.asList(i.getStar1(),i.getStar3());
            Collections.sort(s2);
            List<String> s3=Arrays.asList(i.getStar1(),i.getStar4());
            Collections.sort(s3);
            List<String> s4=Arrays.asList(i.getStar2(),i.getStar3());
            Collections.sort(s4);
            List<String> s5=Arrays.asList(i.getStar2(),i.getStar4());
            Collections.sort(s5);
            List<String> s6=Arrays.asList(i.getStar3(),i.getStar4());
            Collections.sort(s6);
            stars.add(s1);
            stars.add(s2);
            stars.add(s3);
            stars.add(s4);
            stars.add(s5);
            stars.add(s6);
        }
        Map<List<String>,Integer> result=new LinkedHashMap<>();
        stars.stream().collect(Collectors.toMap(p->p,s->1,Integer::sum))
                .entrySet().stream()
                .sorted(Map.Entry.<List<String>, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        //result.forEach((k,v)->System.out.println(k+"="+v));
        return result;
    }

    public List<String> getTopMovies(int top_k, String by){
        Map<String,Integer> result=new LinkedHashMap<>();
        switch (by){
            case "runtime":
                list.stream().filter(a->(!a.getRating().equals("")))
                        .collect(Collectors.toMap(Movie::getTitle,Movie::getRuntime,(p1,p2)->p2))
                        .entrySet().stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                                .thenComparing(Map.Entry.comparingByKey()))
                        .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
                //System.out.println(new ArrayList<>(result.keySet()).subList(0,top_k+1));
                return new ArrayList<>(result.keySet()).subList(0,top_k);
            case "overview":
                list.stream().filter(a->(!a.getOverview().equals("")))
                    .collect(Collectors.toMap(Movie::getTitle, p->p.getOverview().length(),(p1,p2)->p2))
                        .entrySet().stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                                .thenComparing(Map.Entry.comparingByKey()))
                        .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
               //  System.out.println(new ArrayList<>(result.keySet()).subList(0,top_k));
                return new ArrayList<>(result.keySet()).subList(0,top_k);
        }
        return null;
    }

    public List<String> getTopStars(int top_k, String by){
        switch (by){
            case "rating":
                Map<String,Double> map1=new HashMap<>();
                Map<String,Double> result1 =new LinkedHashMap<>();
                for (Movie i:
                     list) {
                    map1.put(i.getStar1(),Double.parseDouble(i.getRating()));
                    map1.put(i.getStar2(),Double.parseDouble(i.getRating()));
                    map1.put(i.getStar3(),Double.parseDouble(i.getRating()));
                    map1.put(i.getStar4(),Double.parseDouble(i.getRating()));
                }
                map1.entrySet().stream().
                        collect(Collectors.groupingBy(Map.Entry::getKey,Collectors.averagingDouble(Map.Entry::getValue)))
                        .entrySet().stream()
                        .sorted(Map.Entry.<String,Double>comparingByValue().reversed()
                                .thenComparing(Map.Entry.comparingByKey()))
                        .forEachOrdered(x-> result1.put(x.getKey(),x.getValue()));
                System.out.println(new ArrayList<>(result1.keySet()).subList(0,top_k));
                return new ArrayList<>(result1.keySet()).subList(0,top_k);
            case "gross":
                Map<String,Integer> map2=new HashMap<>();
                Map<String,Integer> result2=new LinkedHashMap<>();
                for (Movie i:
                        list) {
                    map2.put(i.getStar1(),Integer.parseInt(i.getGross()));
                    map2.put(i.getStar2(),Integer.parseInt(i.getGross()));
                    map2.put(i.getStar3(),Integer.parseInt(i.getGross()));
                    map2.put(i.getStar4(),Integer.parseInt(i.getGross()));
                }
                map2.entrySet().stream().
                        collect(Collectors.groupingBy(Map.Entry::getKey,Collectors.summingInt(Map.Entry::getValue)))
                        .entrySet().stream()
                        .sorted(Map.Entry.<String,Integer>comparingByValue().reversed()
                                .thenComparing(Map.Entry.comparingByKey()))
                        .forEachOrdered(x-> result2.put(x.getKey(),x.getValue()));
                System.out.println(new ArrayList<>(result2.keySet()).subList(0,top_k));
                return new ArrayList<>(result2.keySet()).subList(0,top_k);
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        MovieAnalyzer a = new MovieAnalyzer("A1_Sample/resources/imdb_top_500.csv");
        //Map<String,Integer> x=a.getMovieCountByGenre();
        //Map<List<String>, Integer> b = a.getCoStarCount();
        List<String> x=a.getTopStars(10,"rating");
        /*for (Movie i:
                a.list) {
            System.out.println(i.getYear());
        }*/
    }
}
