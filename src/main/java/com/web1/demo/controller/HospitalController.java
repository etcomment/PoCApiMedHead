package com.web1.demo.controller;


import com.web1.demo.exeptions.NoHospitalFound;
import com.web1.demo.exeptions.NotYetImplemented;
import com.web1.demo.model.Hospital;
import com.web1.demo.service.HospitalService;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author stiven
 */
@RestController
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @RequestMapping("/")
    public void handleRequest() {
        throw new RuntimeException("Exeption racine");
    }
    
    @RequestMapping("/app-info")
    public void handleAppInfoRequest() throws NotYetImplemented {
        throw new NotYetImplemented("La requete n'est pas encore implementée");
    }
    
    @RequestMapping("/hospital/speciality")
    public void noSpecEntered() throws NotYetImplemented {
        throw new NotYetImplemented("La requete n'est pas encore implementée");
    }
    
    /**
     * Read - Get all hospitals
     * @return - An Iterable object of Hospital full filled
     * @throws com.web1.demo.exeptions.NoHospitalFound
     */
    @GetMapping("/hospital")
    public Iterable<Hospital> getHospital() throws NoHospitalFound{
        if (hospitalService.getAllHospital()!=null){
            return hospitalService.getAllHospital();
        }else {
            throw new NoHospitalFound("No hospital found in database");
        }
        
    }
    
    /**
     * Read - Get all free hospitals
     * @return - An Iterable object of all Hospital with free beds,  full filled
     * @throws com.web1.demo.exeptions.NoHospitalFound
     */
    @GetMapping("/hospital/free")
    public Iterable<Hospital> getAllFreeHospital()  throws NoHospitalFound{
        if (hospitalService.getAllFreeHospital()!=null){
            return hospitalService.getAllFreeHospital();
        }else {
            throw new NoHospitalFound("No hospital found in database");
        }
    }

    /**
     * Read - Get hospitals by Id
     * @param id
     * @return - An object of Hospital full filled with given Id
     * @throws com.web1.demo.exeptions.NoHospitalFound
     */
    @GetMapping("/hospital/{id}")
    public Optional<Hospital> getHospital(@PathVariable("id") final Integer id)  throws NoHospitalFound{
        Optional<Hospital> hospital = hospitalService.getHospital(id);
        if (hospital.isPresent()){
            return Optional.of(hospital.get());
        }
        else {
            throw new NoHospitalFound("No hospital found in database with ID "+id);
        }
    }
    
    //useless!
    @GetMapping("/hospital/name/{id}")
    public Comparable<String> getHospitalName(@PathVariable("id") final Integer id){
        return hospitalService.getHospitalName(id);
    }

    /*@GetMapping("/hospital/{range}")
    public Iterable<Hospital> getHospitalInRange(@PathVariable("range") final Integer range){
        return hospitalService.getAllHospitalInRange(range, 52.0333256, -1.2256890);
    }*/
    
    
    /**
     * Read - Get hospitals by Specialities
     * @param speciality
     * @return - An object of Hospital full filled with given speciality
     * @throws com.web1.demo.exeptions.NoHospitalFound
     */
    @GetMapping("/hospital/speciality/{spec}")
    public Iterable<Hospital> getAllBySpec(@PathVariable("spec") final String speciality) throws NoHospitalFound{
        ArrayList<Hospital> myHospitalList = (ArrayList<Hospital>) hospitalService.getAllBySpec(speciality);
        if (!myHospitalList.isEmpty()){
            return hospitalService.getAllBySpec(speciality);
        } else {
            throw new NoHospitalFound("No hospital found in database with this speciality");
        }
    }
    
     /**
     * Read - Get hospitals by range
     * @param latInit
     * @param longInit
     * @param distance
     * @return - An object of Hospital full filled with given speciality
     * @throws com.web1.demo.exeptions.NoHospitalFound
     */
    @GetMapping("/hospital/range/{latInit}/{longInit}/{distance}")
    public Iterable<Hospital> getAllHospitalInRange(@PathVariable("latInit") final float latInit, @PathVariable("longInit") final float longInit, @PathVariable("distance") final int distance ) throws NoHospitalFound{
        ArrayList<Hospital> hospitalInRange;
        hospitalInRange = (ArrayList<Hospital>) hospitalService.getAllHospitalInRange(latInit, longInit, distance);
        if (!hospitalInRange.isEmpty()){
            return hospitalInRange;
        } else {
            throw new NoHospitalFound("No hospital found in database in this range");
        }
    }


}

