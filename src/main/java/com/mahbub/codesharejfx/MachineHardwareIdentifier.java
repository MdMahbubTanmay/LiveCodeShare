/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mahbub.codesharejfx;


/**
 *
 * @author mahbub
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MachineHardwareIdentifier {
    
    



    public static String getUniqueId() {
    String os = System.getProperty("os.name").toLowerCase();
    String id = "unknown-id";

    try {
        if (os.contains("win")) {
         
            String output = executeCommand("powershell.exe (Get-CimInstance Win32_ComputerSystemProduct).UUID");
            id =output.replace("UUID", "").trim();
            
        } else {
           
            String output = executeCommand("cat /etc/machine-id");
            id = output.trim(); 
        }
    } catch (Exception e) {
        id = "error-fetching-id";
    }
    return id;
}

    private static String executeCommand(String command) throws Exception {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output.toString();
    }

}
