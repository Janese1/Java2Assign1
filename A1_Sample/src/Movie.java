public class Movie {
    private String title;
    private int year;
    private String certificate;
    private int runtime;
    private String genre;
    private String rating;
    private float rating2;
    private String overview;
    private String score;
    private String director;
    private String star;
    private String star1;
    private String star2;
    private String star3;
    private String star4;
    private String noofvotes;
    private String gross;
    private int gorss2;

    public String getStar() {
        return star;
    }

    public float getRating2() {
        return rating2;
    }
    public int getGorss2() {
        return gorss2;
    }
    public Movie getSelf() {
        return this;
    }
    public String getGross() {
        return gross;
    }

    public String getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getStar1() {
        return star1;
    }

    public String getStar3() {
        return star3;
    }

    public String getStar4() {
        return star4;
    }

    public String getStar2() {
        return star2;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public Movie(String title, int year, String certificate, int runtime, String genre, String rating,
                 String overview, String score, String director, String star1, String star2, String star3,
                 String star4, String noofvotes, String gross) {
        this.title = title;
        this.year = year;
        this.certificate = certificate;
        this.runtime = runtime;
        this.genre = genre;
        this.rating = rating;
        this.overview = overview;
        this.score = score;
        this.director = director;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.noofvotes = noofvotes;
        this.gross = gross;
    }
    public Movie(String star, float rating) {
        this.star = star;
        this.rating2 = rating;
    }
    public Movie(String star,int gross){
        this.star=star;
        this.gorss2=gross;
    }
}
