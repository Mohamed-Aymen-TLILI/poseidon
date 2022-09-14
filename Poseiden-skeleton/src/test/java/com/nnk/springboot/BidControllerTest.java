package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
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
public class BidControllerTest {

    private int id = 0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BidListService bidListService;

    @Autowired
    private BidListRepository bidListRepository;

    @BeforeAll
    public void init() {
        BidList bidList = new BidList();
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(50.01);
        bidListService.addBidList(bidList);
        for (BidList list : bidListService.findAll()) {
            if (list.getAccount().equals("Account")) {
                id = list.getBidListId();
                break;
            }
        }
    }

    @AfterAll
    public void deleteBidList() {
        bidListRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void addBidList() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void validateBidList() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                .param("account", "Account")
                .param("type", "Type")
                .param("bidQuantity", "80.99")
                .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void updateById() throws Exception {
        mockMvc.perform(post("/bidList/update/{id}", id)
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBidList() throws Exception {
        mockMvc.perform(post("/bidList/update/{id}", id)
                .param("account", "AccountTest")
                .param("type", "TypeTest")
                .param("bidQuantity", "120.0")
                .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/bidList/list"))
                .andReturn();
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(get("/bidList/delete/{id}", id))
                .andDo(print())
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().isFound())
                .andReturn();
    }
}
