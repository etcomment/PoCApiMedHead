/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web1.demo.model;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
/**
 *
 * @author stiven
 */
@Data
@Entity
@Table(name = "Hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="hospital_name")
    private String name;
    
    private float longitude;
    
    private float latitude;
    
    @Column(name="spec")
    private String specialities;

    //private ArrayList<String> specialitiesSplitted;

    private String address;
    
    private Integer freebed;
    
}
