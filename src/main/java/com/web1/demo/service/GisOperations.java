/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web1.demo.service;

/**
 *
 * @author stiven
 */
public class GisOperations {
    
    public static double distFrom(double latAmbulance, double longAmbulance, double latHopital, double longHopital) {
        double earthRadius = 6371.0; // miles (or 6371.0 kilometers)
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
    
}
