
package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TradeTests {

    private Trade trade = new Trade();

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeService tradeService;

    @BeforeAll
    public void initTrade() {
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(200.90);
        tradeService.insertTrade(trade);
    }

    @AfterAll
    public void deleteTrade() {
        tradeRepository.deleteAll();
    }

    @Test
    public void updateById() {
        Integer tradeId = trade.getTradeId();
        Trade tradeById = tradeService.findById(tradeId);
        tradeById.setAccount("NewAccount");
        tradeById.setType("NewType");
        tradeById.setBuyQuantity(800.55);
        Boolean updateTrade = tradeService.updateTrade(tradeId, tradeById);
        Assertions.assertTrue(updateTrade);
    }

    @Test
    public void findAll() {
        List<Trade> tradeList = tradeService.findAll();
        Assertions.assertTrue(tradeList.size() > 0);
    }

    @Test
    public void findById() {
        Integer tradeId = trade.getTradeId();
        Trade tradeById = tradeService.findById(tradeId);
        Assertions.assertNotNull(tradeById);
    }

    @Test
    public void deleteById() {
        Integer tradeId = trade.getTradeId();
        tradeService.deleteById(tradeId);
        Trade tradeById = tradeService.findById(tradeId);
        Assertions.assertNull(tradeById);
    }
}
