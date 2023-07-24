package org.di.controller;

import org.di.injector.Controller;
import org.di.service.MovieService;

@Controller
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
}
