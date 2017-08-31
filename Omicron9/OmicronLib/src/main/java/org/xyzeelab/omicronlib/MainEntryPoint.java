/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xyzeelab.omicronlib;

/**
 *
 * @author ENZO
 */
public class MainEntryPoint {
    static boolean loop = true;
    
    public static void main(String args[]) {
        // Entry point for the system do all the initialization here. 
        
        // Call the setup to routine. 
        Setup();
        
        // Start looping.
        while(loop) {
           Loop();
        }
        
    }
    
    public static void Setup() {
        
    }
    
    public static void Loop() {
        
    }
}
