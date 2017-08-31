/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xyzeelab.omicronlib;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 *
 * @author ENZO
 */
public class MainEntryPoint {
    static boolean loop = true;
    static GpioController gpio;
    static GpioPinDigitalOutput pin;
    
    public static void main(String args[]) throws InterruptedException {
        // Entry point for the system do all the initialization here. 
        
        // Call the setup to routine. 
        Setup();
        
        // Start looping.
        while(loop) {
           Loop();
        }
        
    }
    
    public static void Setup() {
        gpio = GpioFactory.getInstance();
        pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);
    }
    
    public static void Loop() throws InterruptedException {
        pin.toggle();
        Thread.sleep(200);

    }
}
