/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web1.demo.repository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;


/**
 *
 * @author stiven
 */
@Configuration
@ConfigurationProperties(prefix = "com.etcomment.web2")
@Data
public class customProperty {
    private String webApi;
}
