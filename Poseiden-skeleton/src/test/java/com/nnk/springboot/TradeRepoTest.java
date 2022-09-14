package com.nnk.springboot;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TradeRepoTest {

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    public void tradeTest() {
        Trade trade = new Trade();
        trade.setAccount("TradeAccount");
        trade.setType("Type");
        trade.setBuyQuantity(40.0);

        // Save
        trade = tradeRepository.save(trade);
        Assertions.assertNotNull(trade.getTradeId());
        Assertions.assertEquals(trade.getAccount(), "TradeAccount");

        // Update
        trade.setAccount("TradeAccountUpdate");
        trade = tradeRepository.save(trade);
        Assertions.assertEquals(trade.getAccount(), "TradeAccountUpdate");

        // Find
        List<Trade> listResult = tradeRepository.findAll();
        Assertions.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = trade.getTradeId();
        tradeRepository.delete(trade);
        Optional<Trade> tradeList = tradeRepository.findById(id);
        Assertions.assertFalse(tradeList.isPresent());
    }
}
