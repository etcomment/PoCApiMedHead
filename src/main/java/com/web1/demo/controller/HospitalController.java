package com.web1.demo.controller;

import com.web1.demo.model.Hospital;
import com.web1.demo.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    /**
     * Read - Get all hospitals
     * @return - An Iterable object of Hospital full filled
     */
    @GetMapping("/hospital")
    public Iterable<Hospital> getHospital(){
        return hospitalService.getAllHospital();
    }
    
    @GetMapping("/hospital/free")
    public Iterable<Hospital> getAllFreeHospital(){
        return hospitalService.getAllFreeHospital();
    }

    /**
     * Read - Get hospitals by Id
     * @return - An object of Hospital full filled with given Id
     */
    @GetMapping("/hospital/{id}")
    public Optional<Hospital> getHospital(@PathVariable("id") final Integer id){
        Optional<Hospital> hospital = hospitalService.getHospital(id);
        if (hospital.isPresent()){
            return Optional.of(hospital.get());
        }
        else {
            return null;
        }
    }

    @GetMapping("/hospital/name/{id}")
    public Comparable<String> getHospitalName(@PathVariable("id") final Integer id){
        return hospitalService.getHospitalName(id);
    }

    /*@GetMapping("/hospital/{range}")
    public Iterable<Hospital> getHospitalInRange(@PathVariable("range") final Integer range){
        return hospitalService.getAllHospitalInRange(range, 52.0333256, -1.2256890);
    }*/


}
