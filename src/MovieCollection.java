import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MovieCollection {
    private Scanner scanner;
    ArrayList<Movie> movieCollection;


    public MovieCollection() {
        scanner = new Scanner(System.in);
        movieCollection = new ArrayList<>();
        importGoods();
        menuOptions();
    }
    public void importGoods() {
        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String title = splitData[0];
                String cast = splitData[1];
                String director = splitData[2];
                String overview = splitData[3];
                int runtime = Integer.parseInt(splitData[4]);
                double userRating = Double.parseDouble(splitData[5]);
                Movie newMovie = new Movie(title, cast, director, overview, runtime, userRating);
                movieCollection.add(newMovie);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void menuOptions() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();


            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
    public void searchTitles() {
        ArrayList<Movie> moviesThatMatch = new ArrayList<>();
        System.out.print("Enter a title search term: ");
        String search = scanner.nextLine();
        int howMany = 0;
        for (int i =0; i < movieCollection.size(); i++) {
            if (movieCollection.get(i).getTitle().toLowerCase().indexOf(search.toLowerCase()) >= 0) {
                moviesThatMatch.add(movieCollection.get(i));
                howMany++;
            }
        }
        if (howMany == 0 ) {
            System.out.println("No movies titles match that search term!");
        } else {
            moviesThatMatch = sortTheArray(moviesThatMatch);
            for (int x = 0 ; x < moviesThatMatch.size(); x++) {
                int smth = x+1;
                System.out.println(smth + ". " + moviesThatMatch.get(x).getTitle());
            }
            System.out.println("What movie would you like to learn more about?");
            System.out.print("Enter number: ");
            int chosenNumber = scanner.nextInt();
            System.out.println("Title: " + moviesThatMatch.get(chosenNumber - 1).getTitle());
            System.out.println("Runtime: " + moviesThatMatch.get(chosenNumber - 1).getRuntime());
            System.out.println("Directed by: " + moviesThatMatch.get(chosenNumber - 1).getDirector());
            System.out.println("Cast: " + moviesThatMatch.get(chosenNumber - 1).getCast());
            System.out.println("Overview: " + moviesThatMatch.get(chosenNumber - 1).getOverview());
            System.out.println("User rating: " + moviesThatMatch.get(chosenNumber - 1).getUserRating());
            scanner.nextLine();
        }
    }

    public ArrayList<Movie> sortTheArray(ArrayList<Movie> words) {
        for (int i = 1; i < words.size(); i++) {
            int x = i;
            Movie stuff = words.get(i);
            String bruh = words.get(i).getTitle();
            while (x != 0 && bruh.compareTo(words.get(x-1).getTitle()) < 0) {
                words.set(x, words.get(x-1));
                x--;
            }
            words.set(x, stuff);
        }
        return words;
    }
    public ArrayList<String> sortArray(ArrayList<String> words) {
        for (int i = 1; i < words.size(); i++) {
            int x = i;
            String bruh = words.get(i);
            while (x != 0 && bruh.compareTo(words.get(x-1)) < 0) {
                words.set(x, words.get(x-1));
                x--;
            }
            words.set(x, bruh);
        }
        return words;
    }
    public void searchCast() { // not done
        ArrayList<String> castNames = new ArrayList<>();
        ArrayList<String> matchingCast = new ArrayList<>();
        System.out.print("Enter a person to search for (first or last name): ");
        String search = scanner.nextLine();
        int howMany = 0;
        for (int i = 0; i < movieCollection.size(); i++) {
            String castOfAMovie = movieCollection.get(i).getCast();
            String[] splitData = castOfAMovie.split("\\|");
            for (int x = 0; x < splitData.length; x++) {
                castNames.add(splitData[x]);
            }
        }
        for (int i =0; i < castNames.size(); i++) {
            if (castNames.get(i).toLowerCase().indexOf(search.toLowerCase()) >= 0) {
                for (int x =0; x < matchingCast.size(); x++) {
                    if (!(matchingCast.get(x).toLowerCase().equals(castNames.get(i).toLowerCase()))) {
                        matchingCast.add(castNames.get(i));
                        howMany++;
                    }
                }
            }
        }
        if (howMany == 0 ) {
            System.out.println("No results match your search!");
        } else {
            matchingCast= sortArray(matchingCast);
            for (int x = 0 ; x < matchingCast.size(); x++) {
                int smth = x+1;
                System.out.println(smth + ". " + matchingCast.get(x));
            }
            System.out.println("Which would you like to see movies about?");
            System.out.print("Enter number: ");
            int chosenNumber = scanner.nextInt();
            scanner.nextLine();
            for (int x = 0; x < movieCollection.size(); x++) {
                int smth = 1;
                if (movieCollection.get(x).getCast().indexOf(matchingCast.get(chosenNumber - 1)) >= 0) {
                    System.out.println(smth + ". " + movieCollection.get(x).getTitle());
                    smth++;
                }
            }

        }
    }
}
