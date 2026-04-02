/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mahbub.codesharejfx;

/**
 *
 * @author mahbub
 */

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;


import java.util.HashMap;
import java.util.Map;

public class AddData {
    public static void sendCode(String code) throws Exception {
       Firestore db = FirebaseInitializer.getFirestore();


       DocumentReference docRef = db.collection("Rooms").document(UserPanelController.getCurrentMachineID());
       Map<String, Object> data = new HashMap<>();
       data.put("Code", code);
       data.put("isLocked", "false");

       ApiFuture<WriteResult> result = docRef.set(data);
       System.out.println("Update time: " + result.get().getUpdateTime());
   }
    
    
    
    public static void createUser() throws Exception {
       Firestore db = FirebaseInitializer.getFirestore();
       String code = "//New room created";


       DocumentReference docRef = db.collection("Rooms").document(UserPanelController.getMyMachineID());
       Map<String, Object> data = new HashMap<>();
       data.put("Code", code);
       data.put("isLocked", "false");

       ApiFuture<WriteResult> result = docRef.set(data);
       System.out.println("Update time: " + result.get().getUpdateTime());
   }

}
