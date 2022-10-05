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
    private String stars;
    private String noofvotes;
    private String gross;

    public int getYear() {
        return year;
    }

    public Movie(String title, int year, String certificate, String runtime, String genre, String rating,
                 String overview, String score, String director, String stars, String noofvotes, String gross){
        this.title=title;
        this.year=year;
        this.certificate=certificate;
        this.runtime=runtime;
        this.genre=genre;
        this.rating=rating;
        this.overview=overview;
        this.score=score;
        this.director=director;
        this.stars=stars;
        this.noofvotes=noofvotes;
        this.gross=gross;
    }
}
