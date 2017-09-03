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
import com.pi4j.io.serial.*;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.util.Console;

/**
 *
 * @author ENZO
 */
public class MainEntryPoint {

    static boolean loop = true;
    static GpioController gpio;
    static GpioPinDigitalOutput pin;

    public static void main(String args[]) throws InterruptedException {
        final Console console = new Console();
        
        console.title("<-- The Pi4J Project -->", "Serial Communication Example");
        console.promptForExit();
        
        final Serial serial = SerialFactory.createInstance();
        
        serial.addListener(new SerialDataEventListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {
                try {
                    console.println("[HEX DATA]   " + event.getHexByteString());
                    console.println("[ASCII DATA] " + event.getAsciiString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        try {
            SerialConfig config = new SerialConfig();
            
            config.device("/dev/ttyS0")
                    .baud(Baud._115200)
                    .dataBits(DataBits._8)
                    .parity(Parity.NONE)
                    .stopBits(StopBits._1)
                    .flowControl(FlowControl.NONE);
            
            console.println(SerialPort.getDefaultPort());
            
            if (args.length > 0) {
                config = CommandArgumentParser.getSerialConfig(config, args);
            }
            
            console.box("Connecting to:" + config.toString(),
                " We are sending ASCII data on the serial port every 1 second.",
                " Data received on serial port will be displayed below.");
            
            serial.open(config);
            
            while(console.isRunning()) {
                try {
                    serial.write ("test");
                
                } catch (IllegalStateException ex) {
                    ex.printStackTrace();
                }
                
                Thread.sleep(1000);
            }
                
        } catch (Exception ex) {
            console.println("Serial Setup Failed " + ex.getMessage());
            return;
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
