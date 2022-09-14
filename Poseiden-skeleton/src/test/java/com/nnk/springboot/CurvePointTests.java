
package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import com.nnk.springboot.service.CurvePointService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CurvePointTests {

    private CurvePoint curvePoint = new CurvePoint();

    @Autowired
    CurvePointRepository curvePointRepository;

    @Autowired
    CurvePointService curvePointService;

    @BeforeAll
    public void initCurvePoint() {
        curvePoint.setCurvePointId(10);
        curvePoint.setTerm(25.90);
        curvePoint.setValue(60.17);
        curvePointService.insertCurvePoint(curvePoint);
    }

    @AfterAll
    public void deletedCurvePoint() {
        curvePointRepository.deleteAll();
    }

    @Test
    public void updateCurvePoint() {
        Integer curvePointId = curvePoint.getId();
        CurvePoint curvePointById = curvePointService.findById(curvePointId);
        curvePointById.setCurvePointId(7);
        curvePointById.setTerm(45.90);
        curvePointById.setValue(80.58);
        Boolean updateCurvePoint = curvePointService.updateCurvePoint(curvePointId, curvePointById);
        assertTrue(updateCurvePoint);
    }

    @Test
    public void findAll() {
        List<CurvePoint> curvePointList = curvePointService.findAll();
        assertTrue(curvePointList.size() > 0);
    }

    @Test
    public void findById() {
        Integer curvePointId = curvePoint.getId();
        CurvePoint curvePointById = curvePointService.findById(curvePointId);
        assertNotNull(curvePointById);
    }

    @Test
    public void deleteById() {
        Integer curvePointId = curvePoint.getId();
        curvePointService.deleteById(curvePointId);
        CurvePoint curvePointById = curvePointService.findById(curvePointId);
        assertNull(curvePointById);
    }
}
