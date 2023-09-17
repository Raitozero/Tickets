package test.shuli.movies;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.*;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String body, String imdbId){
        Review review = reviewRepository.insert(new Review(body));
        //视频里原本是Review review = new Review(body);
        //        reviewRepository.insert(review);
        //原因是reviewRepository.insert() 会return这个inserted review，后面我是对这个已经inserted的review进行操作。
        //如果是上述写法，则应该把reviewRepository.insert(review);放在后续操作的后面。
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        /*
        this code performs an update operation on the "movies" collection. It finds a document where the
        "imdbId" field matches the given imdbId value. Then, it pushes the review object into the "reviewIds"
        array of that document. If there are multiple documents with the same "imdbId," only the first
        matching document will be updated. The actual update is executed when you call the update method
        on the MongoTemplate, and the update returns information about the update operation, such as
        the number of modified documents.
         */
        return review;
    }
}
