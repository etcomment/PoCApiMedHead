/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.web1.demo.controller;

import com.web1.demo.model.Hospital;
import com.web1.demo.service.HospitalService;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author stiven
 */
@SpringBootTest
public class HospitalControllerTest {
    
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private HospitalController HospitalController;
    
    /**
     * Test of getHospital method, of class HospitalController.
     */
    @Test
    public void testGetHospital_Integer() throws Exception {
        System.out.println("getHospital");
        Integer id = 2;
        
        Optional<Hospital> expResult = hospitalService.getHospital(id);
        Optional<Hospital> result = HospitalController.getHospital(id);
        assertEquals(expResult, result);
    }

}
