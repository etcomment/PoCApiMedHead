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
import java.util.ArrayList;

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

    public Optional<Hospital> getHospital(final Integer id) {
        return hospitalRepository.findById(id);
    }

    public Iterable<Hospital> getAllHospital() {
        return hospitalRepository.findAll();
    }

    /*public Iterable<Hospital> getAllHospitalInRange(final Integer distance, final Double longiSource, final Double latiSource) {
        return  hospitalRepository.findAllByRange(distance, longiSource, latiSource);
    }*/
    public String getHospitalName(final Integer id) {
        return hospitalRepository.findById(id).get().getName();
    }

    public void deleteHospital(final Integer id) {
        hospitalRepository.deleteById(id);
    }

    public Hospital saveHospital(Hospital hopital) {
        Hospital savedHospital = hospitalRepository.save(hopital);
        return savedHospital;
    }

    public Iterable<Hospital> getAllFreeHospital() {
        ArrayList<Hospital> myHospitalList = new ArrayList<>();
        for (Hospital hopital : hospitalRepository.findAll()) {
            if (hopital.getFreebed() > 0) {
                myHospitalList.add(hopital);
            }
        }
        return myHospitalList;
    }
    
    public Iterable<Hospital> getAllAroundCoord(float longitude, float latitude){
        ArrayList<Hospital> myHospitalList = new ArrayList<>();
        for (Hospital hopital : hospitalRepository.findAll()) {
            if (hopital.getLatitude()> longitude && hopital.getLatitude()>latitude) {
                myHospitalList.add(hopital);
                //TODO : Trouver algo pour savoir si une coordonnée se trouve dans une zone
            }
        }
        return myHospitalList;
    }
    
    public Iterable<Hospital> getAllBySpec(String speciality){
        ArrayList<Hospital> myHospitalList = new ArrayList<>();
        for (Hospital hopital : hospitalRepository.findAll()) {
            if (hopital.getSpecialities().contains(speciality)) {
                myHospitalList.add(hopital);
            }
        }
        return myHospitalList;
    }
}
