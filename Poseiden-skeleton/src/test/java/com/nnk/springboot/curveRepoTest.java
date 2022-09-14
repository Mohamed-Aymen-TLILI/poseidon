package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class curveRepoTest {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Test
    public void curvePointTest() {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurvePointId(20);
        curvePoint.setTerm(3.0);
        curvePoint.setValue(40.0);

        // Save
        curvePoint = curvePointRepository.save(curvePoint);
        Assertions.assertNotNull(curvePoint.getId());
        Assertions.assertEquals(curvePoint.getCurvePointId(), 20);

        // Update
        curvePoint.setCurvePointId(20);
        curvePoint = curvePointRepository.save(curvePoint);
        Assertions.assertEquals((int) curvePoint.getCurvePointId(), 20);

        // Find
        List<CurvePoint> listResult = curvePointRepository.findAll();
        Assertions.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = curvePoint.getId();
        curvePointRepository.delete(curvePoint);
        Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
        Assertions.assertFalse(curvePointList.isPresent());
    }
}
