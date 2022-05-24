/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web1.demo.service;

import com.web1.demo.model.Hospital;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web1.demo.repository.HospitalRepository;

import java.util.Optional;

/**
 *
 * @author stiven
 */
@Data
@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    public Optional<Hospital> getHospital(final Integer id){
        return hospitalRepository.findById(id);
    }

    public Iterable<Hospital> getAllHospital(){
        return hospitalRepository.findAll();
    }

    /*public Iterable<Hospital> getAllHospitalInRange(final Integer distance, final Double longiSource, final Double latiSource) {
        return  hospitalRepository.findAllByRange(distance, longiSource, latiSource);
    }*/

    public String getHospitalName(final Integer id) { return hospitalRepository.findById(id).get().getName();}

    public void deleteHospital(final Integer id){
        hospitalRepository.deleteById(id);
    }

    public Hospital saveHospital(Hospital hopital){
        Hospital savedHospital = hospitalRepository.save(hopital);
        return savedHospital;
    }
}
