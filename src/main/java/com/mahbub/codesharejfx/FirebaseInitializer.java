package com.mahbub.codesharejfx;




import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.cloud.FirestoreClient;




import java.io.FileInputStream;
import java.io.IOException;


public class FirebaseInitializer {


    private static Firestore db;


    public static void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");


            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("codesharexcompiler")
                    .build();


            FirebaseApp.initializeApp(options);


            db = FirestoreClient.getFirestore(); // ✅ Correct way to get Firestore after Firebase initialization




            System.out.println("✅ Firebase initialized successfully.");


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Firebase initialization failed.");
        }

    }


    public static Firestore getFirestore() {
        return db;
    }
}