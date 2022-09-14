package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Boolean updateRating(int id, Rating rating) {
        boolean updated = false;
        Optional<Rating> optionalRating = ratingRepository.findById(id);
        if (optionalRating.isPresent()) {
            Rating newRating = optionalRating.get();
            newRating.setMoodysRating(rating.getMoodysRating());
            newRating.setSandPRating(rating.getSandPRating());
            newRating.setFitchRating(rating.getFitchRating());
            newRating.setOrderNumber(rating.getOrderNumber());
            ratingRepository.save(newRating);
            updated = true;
            logger.info("Rating with id " + id + " is updated as " + newRating);
        } else {
            logger.info("Failed to update Rating with id " + id + " as " + rating);
        }
        return updated;
    }

    public Rating findById(int id) {
        Optional<Rating> optionalRating = ratingRepository.findById(id);
        if (optionalRating.isPresent()) {
            logger.info("Query to find Rating by id " + id);
            return optionalRating.get();
        } else {
            logger.error("Failed to find Rating with id " + id);
        }
        return null;
    }

    public void deleteById(int id) {
        Optional<Rating> optionalRating = ratingRepository.findById(id);
        if (optionalRating.isPresent()) {
            ratingRepository.delete(optionalRating.get());
            logger.info("Rating with id " + id + " is deleted!");
        } else {
            logger.error("Failed to delete Rating with id " + id);
        }
    }

    public void insertRating(Rating rating) {
        ratingRepository.save(rating);
        logger.info("New Rating " + rating + " is created!");
    }
}
