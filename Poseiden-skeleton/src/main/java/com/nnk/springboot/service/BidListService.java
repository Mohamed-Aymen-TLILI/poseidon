package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    private final Logger LOGGER = LoggerFactory.getLogger( BidListService.class);

    @Autowired
    private BidListRepository bidListRepository ;

    public void addBidList(BidList bidList) {
        bidListRepository.save(bidList);
        LOGGER.info("New BidList " + bidList + " is created !");
    }

    public Boolean updateBidList(int id, BidList bidList) {
        boolean updated = false;
        Optional<BidList> list = bidListRepository.findById(id);
        if (list.isPresent()) {
            BidList newBidList = list.get();
            newBidList.setAccount(bidList.getAccount());
            newBidList.setType(bidList.getType());
            newBidList.setBidQuantity(bidList.getBidQuantity());
            bidListRepository.save(newBidList);
            updated = true;
            LOGGER.info("BidList with id " + id + " is updated as " + newBidList);
        } else {
            LOGGER.error("Failed to update BidList with id " + id + " as " + bidList);
        }
        return updated;
    }

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList findById(int id) {
        Optional<BidList> list = bidListRepository.findById(id);
        if (list.isPresent()) {
            LOGGER.info("Query to find BidList with id " + id);
            return list.get();
        } else {
            LOGGER.error("Failed to find BidList with id " + id);
            return null;
        }
    }

    public void deleteById(int id) {
        Optional<BidList> optionalBidList = bidListRepository.findById(id);
        if (optionalBidList.isPresent()) {
            bidListRepository.delete(optionalBidList.get());
            LOGGER.info("BidList with id " + id + " is deleted!");
        } else {
            LOGGER.error("Failed to delete BidList with id " + id);
        }
    }
}
