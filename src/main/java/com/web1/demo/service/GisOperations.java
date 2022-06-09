/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web1.demo.service;

import com.web1.demo.model.Hospital;
import com.web1.demo.model.Itineraire;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author stiven
 */
@Service
public class GisOperations {
    
    public static double distFrom(double longAmbulance, double latAmbulance, double longHopital, double latHopital) {
        double earthRadius = 6371.0; // 3958.75 miles (ou 6371.0 kilometers)
        double dLat = Math.toRadians(latHopital-latAmbulance);
        double dLng = Math.toRadians(longHopital-longAmbulance);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(latAmbulance)) * Math.cos(Math.toRadians(latHopital));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;
        return dist;
    }
    
    /**
     *
     * @param latCentre
     * @param longCentre
     * @param listHospital
     * @return
     */
    public static Iterable<Itineraire> getItineraireOSRM(float longCentre, float latCentre, Iterable<Hospital> listHospital) {
        ArrayList<Itineraire> myItineraireList = new ArrayList<>();
        
        String apiKey = "5b3ce3597851110001cf6248dc9db6fb880d46b7b63a907d42d826e5";
        String apiUrl = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=";
        String origin = latCentre+","+longCentre;
        String requestUrl = apiUrl+apiKey+"&start="+origin+"&end=";
        RestTemplate restTemplate = new RestTemplate();
        float distanceTrajet=0;
        float tempsTrajet=0;
        JSONObject reponseParsed = null;
        JSONArray reponseParsedArray = null;
        for (Hospital hopital : listHospital) {
               //get reponse from API
               ResponseEntity<String> reponse = restTemplate.getForEntity(requestUrl+hopital.getLongitude()+","+hopital.getLatitude(), String.class);
               
            try {
                reponseParsed = new JSONObject(reponse.getBody());
                reponseParsedArray = reponseParsed.getJSONArray("features");
                reponseParsed = reponseParsedArray.getJSONObject(0);
                reponseParsed = reponseParsed.getJSONObject("properties");
                reponseParsed = reponseParsed.getJSONObject("summary");
                distanceTrajet = (float)reponseParsed.getFloat("distance")/1000;
                tempsTrajet = (float)reponseParsed.getFloat("duration");
                
                myItineraireList.add(new Itineraire(hopital, distanceTrajet, tempsTrajet));
                //System.out.println(toto);
            } catch (Exception ex) {
                Logger.getLogger(GisOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
               
               //String toto = reponse.getBody();
               //myItineraireList.add(reponse);
               System.out.println("Duree : "+tempsTrajet+" -- "+hopital.getLongitude()+","+hopital.getLatitude()+"--"+distanceTrajet+" Km");
               
               //myItineraireList.add(new Itineraire(hopital, latCentre, latCentre))
        }
        return myItineraireList;   
    }   
}

