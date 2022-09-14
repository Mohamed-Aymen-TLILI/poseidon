
package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RuleTests {


    private RuleName ruleName = new RuleName();

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Autowired
    private RuleNameService ruleNameService;

    @BeforeAll
    public void initRuleName() {
        ruleName.setName("Name");
        ruleName.setDescription("Description");
        ruleName.setJson("Json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("SqlStr");
        ruleName.setSqlPart("SqlPart");
        ruleNameService.insertRuleName(ruleName);
    }

    @AfterAll
    public void deleteRuleName() {
        ruleNameRepository.deleteAll();
    }

    @Test
    public void updateById() {
        Integer ruleNameId = ruleName.getId();
        RuleName ruleNameById = ruleNameService.findById(ruleNameId);
        ruleNameById.setName("Newname");
        ruleNameById.setDescription("Newdescription");
        ruleNameById.setJson("NewJson");
        ruleNameById.setTemplate("Newtemplate");
        ruleNameById.setSqlStr("NewSqlStr");
        ruleNameById.setSqlPart("NewSqlPart");
        Boolean updateRuleName = ruleNameService.updateRuleName(ruleNameId, ruleNameById);
        Assertions.assertTrue(updateRuleName);
    }

    @Test
    public void findAll() {
        List<RuleName> ruleNameList = ruleNameService.findAll();
        Assertions.assertTrue(ruleNameList.size() > 0);
    }

    @Test
    public void findById() {
        Integer ruleNameId = ruleName.getId();
        RuleName ruleNameById = ruleNameService.findById(ruleNameId);
        Assertions.assertNotNull(ruleNameById);
    }

    @Test
    public void deleteById() {
        Integer ruleNameId = ruleName.getId();
        ruleNameService.deleteById(ruleNameId);
        RuleName ruleNameById = ruleNameService.findById(ruleNameId);
        Assertions.assertNull(ruleNameById);
    }
}
