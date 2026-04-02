module com.example.codesharejfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires firebase.admin;
    requires google.cloud.firestore;
    requires google.cloud.core;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires com.google.api.apicommon;


    opens com.mahbub.codesharejfx to javafx.fxml;
    exports com.mahbub.codesharejfx;
}