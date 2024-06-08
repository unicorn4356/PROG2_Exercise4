package at.ac.fhcampuswien.fhmdb.controllers;

import at.ac.fhcampuswien.fhmdb.database.*;
import at.ac.fhcampuswien.fhmdb.statePattern.MovieSorter;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainController implements Observer {
    private MovieSorter movieSorter;

    @FXML
    private ListView<Movie> movieListView;

    public MainController() {
        this.movieSorter = new MovieSorter();
        WatchlistRepository.getInstance().addObserver(this);
    }

    @FXML
    public void initialize() {
        // Initialize movie list and other UI components
    }

    @FXML
    public void sortAscending() {
        movieSorter.sortAscending();
        updateMovieList();
    }

    @FXML
    public void sortDescending() {
        movieSorter.sortDescending();
        updateMovieList();
    }

    @FXML
    public void resetSort() {
        movieSorter.resetSort();
        updateMovieList();
    }

    private void updateMovieList() {
        List<Movie> movies = getMoviesFromRepository(); // Fetch the list of movies
        movieSorter.sort(movies);
        movieListView.setItems(FXCollections.observableArrayList(movies));
    }
}
