package com.nnk.springboot;


import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RuleRepoTest {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Test
    public void ruleNameTest() {
        RuleName rule = new RuleName();
        rule.setName("RuleName");
        rule.setDescription("Description");
        rule.setJson("Json");
        rule.setTemplate("Template");
        rule.setSqlStr("SqlStr");
        rule.setSqlPart("SqlPart");

        // Save
        rule = ruleNameRepository.save(rule);
        Assertions.assertNotNull(rule.getId());
        Assertions.assertEquals(rule.getName(), "RuleName");

        // Update
        rule.setName("RuleNameUpdate");
        rule = ruleNameRepository.save(rule);
        Assertions.assertEquals(rule.getName(), "RuleNameUpdate");

        // Find
        List<RuleName> listResult = ruleNameRepository.findAll();
        Assertions.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = rule.getId();
        ruleNameRepository.delete(rule);
        Optional<RuleName> ruleList = ruleNameRepository.findById(id);
        Assertions.assertFalse(ruleList.isPresent());
    }
}
