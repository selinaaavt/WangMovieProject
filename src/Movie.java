public class Movie {
    private int runtime;
    private String cast;
    private String director;
    private String overview;
    private double userRating;
    private String title;
    public Movie(String title, String cast, String director, String overview, int runtime, double userRating) {
        this.cast = cast;
        this.title = title;
        this.director = director;
        this.overview = overview;
        this.runtime = runtime;
        this.userRating = userRating;
    }
    public double getUserRating() {
        return userRating;
    }
    public int getRuntime() {
        return runtime;
    }
    public String getDirector() {
        return director;
    }
    public String getTitle() {
        return title;
    }
    public String getCast() {
        return cast;
    }
    public String getOverview() {
        return overview;
    }
}
