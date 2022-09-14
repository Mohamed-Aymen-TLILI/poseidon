
package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RatingTests {

    private Rating rating = new Rating();

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    @BeforeAll
    public void initRating() {
        rating.setMoodysRating("MoodysRating");
        rating.setSandPRating("SandPrating");
        rating.setFitchRating("FitchRating");
        rating.setOrderNumber(30);
        ratingService.insertRating(rating);
    }

    @AfterAll
    public void deleteRating() {
        ratingRepository.deleteAll();
    }

    @Test
    public void updateById() {
        Integer ratingId = rating.getId();
        Rating ratingById = ratingService.findById(ratingId);
        ratingById.setMoodysRating("NewmoodysRating");
        ratingById.setSandPRating("NewsandPrating");
        ratingById.setFitchRating("NewFitchRating");
        ratingById.setOrderNumber(100);
        Boolean updateRating = ratingService.updateRating(ratingId, ratingById);
        Assertions.assertTrue(updateRating);
    }

    @Test
    public void findAll() {
        List<Rating> ratingList = ratingService.findAll();
        Assertions.assertTrue(ratingList.size() > 0);
    }

    @Test
    public void findById() {
        Integer ratingId = rating.getId();
        Rating ratingById = ratingService.findById(ratingId);
        Assertions.assertNotNull(ratingById);
    }

    @Test
    public void deleteById() {
        Integer ratingId = rating.getId();
        ratingService.deleteById(ratingId);
        Rating ratingById = ratingService.findById(ratingId);
        Assertions.assertNull(ratingById);
    }
}
