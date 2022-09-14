package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RatingRepoTest {
    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void ratingTest() {
        Rating rating = new Rating();
        rating.setMoodysRating("MoodysRating");
        rating.setSandPRating("SandPRating");
        rating.setFitchRating("FitchRating");
        rating.setOrderNumber(10);

        // Save
        rating = ratingRepository.save(rating);
        Assertions.assertNotNull(rating.getId());
        Assertions.assertEquals((int) rating.getOrderNumber(), 10);

        // Update
        rating.setOrderNumber(20);
        rating = ratingRepository.save(rating);
        Assertions.assertEquals((int) rating.getOrderNumber(), 20);

        // Find
        List<Rating> listResult = ratingRepository.findAll();
        Assertions.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = rating.getId();
        ratingRepository.delete(rating);
        Optional<Rating> ratingList = ratingRepository.findById(id);
        Assertions.assertFalse(ratingList.isPresent());
    }
}

