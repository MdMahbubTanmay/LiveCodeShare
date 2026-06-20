package com.mahbub.codesharejfx;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

public class GetData {

    public static boolean isExist;
    public static String Code;
    public static String isLocked = "false";
    public static String chatHistory = "";

    public static void getCode() throws Exception {
        Firestore db = FirebaseInitializer.getFirestore();
        DocumentReference docRef = db.collection("Rooms").document(UserPanelController.getCurrentMachineID());

        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            isExist = true;
            String fetchedCode = document.getString("Code");
            Code = (fetchedCode != null) ? fetchedCode : " No code found in this document";
            isLocked = document.getString("isLocked");


            String fetchedChat = document.getString("Chat");
            chatHistory = (fetchedChat != null) ? fetchedChat : "";
        } else {
            isExist = false;
            Code = " Error: Invalid Room!";
            chatHistory = "";
        }
    }
}