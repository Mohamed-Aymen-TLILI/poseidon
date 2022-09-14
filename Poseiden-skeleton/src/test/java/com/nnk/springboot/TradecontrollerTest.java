package com.nnk.springboot;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "Aymen", authorities = {"USER"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TradecontrollerTest {

    private int id =0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeService tradeService;

    @BeforeAll
    public void init() {
        Trade trade = new Trade();
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(20.0);
        tradeService.insertTrade(trade);
        for (Trade tradeList : tradeService.findAll()) {
            if (tradeList.getAccount().equals("Account")) {
                id = tradeList.getTradeId();
                break;
            }
        }
    }

    @AfterAll
    public void deleteTrade() {
        tradeRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void addTrade() throws Exception {
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void validateTrade() throws Exception {
        mockMvc.perform(post("/trade/validate")
                .param("account", "Account")
                .param("type", "Type")
                .param("buyQuantity", "20.0")
                .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/trade/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void updateById() throws Exception {
        mockMvc.perform(get("/trade/update/{id}", id)
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTrade() throws Exception {
        mockMvc.perform(post("/trade/update/{id}", id)
                .param("account", "newAccount")
                .param("type", "newType")
                .param("buyQuantity", "50.0")
                .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/trade/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(get("/trade/delete/{id}", id))
                .andDo(print())
                .andExpect(redirectedUrl("/trade/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

}
