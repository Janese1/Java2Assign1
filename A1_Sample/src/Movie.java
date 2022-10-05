public class Movie {
    private String title;
    private int year;
    private String certificate;
    private String runtime;
    private String genre;
    private String rating;
    private String overview;
    private String score;
    private String director;
    private String star1;
    private String star2;
    private String star3;
    private String star4;
    private String noofvotes;
    private String gross;

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public Movie(String title, int year, String certificate, String runtime, String genre, String rating,
                 String overview, String score, String director, String star1,String star2,String star3,
                 String star4,String noofvotes, String gross){
        this.title=title;
        this.year=year;
        this.certificate=certificate;
        this.runtime=runtime;
        this.genre=genre;
        this.rating=rating;
        this.overview=overview;
        this.score=score;
        this.director=director;
        this.star1=star1;
        this.star2=star2;
        this.star3=star3;
        this.star4=star4;
        this.noofvotes=noofvotes;
        this.gross=gross;
    }
}
