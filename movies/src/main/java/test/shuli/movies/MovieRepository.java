package test.shuli.movies;

import org.bson.types.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
//implementing search by imdbId instead of ObjectId
    Optional<Movie> searchByimdbId(String imdbId);
}
