package com.mahbub.codesharejfx;

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

   public static void sendChatMessage(String currentChat, String nickname, String message) throws Exception {
      Firestore db = FirebaseInitializer.getFirestore();
      DocumentReference docRef = db.collection("Rooms").document(UserPanelController.getCurrentMachineID());

      String timeStamp = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
      String newMessage = "[" + timeStamp + "] " + nickname + ": " + message + "\n";
      String updatedChat = currentChat + newMessage;

      Map<String, Object> updates = new HashMap<>();
      updates.put("Chat", updatedChat);

      ApiFuture<WriteResult> result = docRef.update(updates);
      System.out.println("Chat updated at: " + result.get().getUpdateTime());
   }

   public static void clearChat() throws Exception {
      Firestore db = FirebaseInitializer.getFirestore();
      DocumentReference docRef = db.collection("Rooms").document(UserPanelController.getCurrentMachineID());

      Map<String, Object> updates = new HashMap<>();
      updates.put("Chat", "");

      ApiFuture<WriteResult> result = docRef.update(updates);
      System.out.println("Chat cleared at: " + result.get().getUpdateTime());
   }
}