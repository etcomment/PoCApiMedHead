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
        //déporter le prétraitement sur le repository 
        for (Hospital hopital : hospitalRepository.findAll()) {
            if (hopital.getFreebed() > 0) {
                myHospitalList.add(hopital);
            }
        }
        return myHospitalList;
    }
    
    
    public Iterable<Hospital> getAllBySpec(String speciality){
        ArrayList<Hospital> myHospitalList = new ArrayList<>();
        //déporter le prétraitement sur le repository 
        for (Hospital hopital : hospitalRepository.findAll()) {
            if (hopital.getSpecialities().toLowerCase().contains(speciality)) {
                myHospitalList.add(hopital);
            }
        }
        return myHospitalList;
    }
    
    public Iterable<Hospital> getAllHospitalInRange(float latCentre, float longCentre, int distance){
        ArrayList<Hospital> myHospitalList = new ArrayList<>();
        for (Hospital hopital : getAllFreeHospital()) {
            if (GisOperations.distFrom(latCentre, longCentre, hopital.getLatitude(), hopital.getLongitude()) < distance) {
                myHospitalList.add(hopital);
            }
        }
        return myHospitalList;
    }
    
    
    public Iterable<Hospital> getAllFreeHospitalInRangeWithSpeciality(float latCentre, float longCentre, int distance, String speciality){
        ArrayList<Hospital> myHospitalList = new ArrayList<>();
        ArrayList<Hospital> myHospitalFreeList = new ArrayList<>();
        for (Hospital hopital : getAllBySpec(speciality)) {
            if (hopital.getFreebed() > 0) {
                myHospitalFreeList.add(hopital);
            }
        }
        for (Hospital hopital : myHospitalFreeList){
            if (GisOperations.distFrom(latCentre, longCentre, hopital.getLatitude(), hopital.getLongitude()) < distance) {
                myHospitalList.add(hopital);
            }
        }      
        
        return myHospitalList;
    }
    
    
    
}
