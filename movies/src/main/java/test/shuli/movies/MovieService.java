package test.shuli.movies;

import org.bson.types.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
//@Autowired same to the codes below
//    public MovieService(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }

    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }

//    public Optional<Movie> singleMovie(ObjectId id){//optinonal here bc it may not find any movies by this id
//        return movieRepository.findById(id);
    public Optional<Movie> singleMovie(String imdbId){//optinonal here bc it may not find any movies by this id
        return movieRepository.searchByimdbId(imdbId);
    }
}
