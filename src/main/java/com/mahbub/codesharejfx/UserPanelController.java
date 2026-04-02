package com.mahbub.codesharejfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class UserPanelController {

    public TextArea codeSnippet;
    public Button ccppcompiler;
    public TextField myRoomID;
    public TextField joinRoomBox;
    public Text CurrentlyJoinedRoom;
    public String myCode;
    public Circle activeStatus;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public static String myMachineID = MachineHardwareIdentifier.getUniqueId();
    private static String currentMachineID = "";



    public static String getMyMachineID(){
        return myMachineID;
    }

    public static String getCurrentMachineID(){
        return currentMachineID;
    }

    public static void setCurrentMachineID(String id){
        currentMachineID = id;
    }









    public void CompileCCpp(ActionEvent event)
    {

        CompileCode.CompileCpp(codeSnippet.getText().toString());

    }










    public void JoinRoom(ActionEvent event)
    {
        if (joinRoomBox.getText().toString().equals(""))
        {
            alert.setContentText("Text field cant be empty");
            alert.show();
        }
        else
        {

            String tmp = getCurrentMachineID();
            setCurrentMachineID(joinRoomBox.getText().toString());

            try {
                GetData.getCode();

                if (GetData.isExist)
                {
                    activeStatus.setFill(Color.LIME);
                    if (getCurrentMachineID().equals(getMyMachineID())) {
                        CurrentlyJoinedRoom.setText("Joined Room : " + getCurrentMachineID() + " (Your Room)");
                    }
                    else
                    {
                        CurrentlyJoinedRoom.setText("Joined Room : " + getCurrentMachineID());
                    }
                    myCode = GetData.Code;
                    codeSnippet.setText(myCode);
                    alert.setContentText("Successfully joined the room");
                    alert.show();



                }
                else
                {

                    alert.setContentText("Invalid room id or room not exist.");
                    alert.show();
                    setCurrentMachineID(tmp);
                }
            } catch (Exception e) {
                alert.setContentText("Failed to Join : " + e.getMessage());
                alert.show();
                setCurrentMachineID(tmp);
                throw new RuntimeException(e);
            }

        }
    }








    public void CreateMyOwnRoom(ActionEvent event)
    {

        String tmp = getCurrentMachineID();
        setCurrentMachineID(getMyMachineID());

        try {
            GetData.getCode();

            if (GetData.isExist)
            {

                alert.setContentText("Room Already exist. No need to create again, just join");
                alert.show();
                setCurrentMachineID(tmp);
            }
            else
            {
                CurrentlyJoinedRoom.setText(getCurrentMachineID()+" (Your Room)");
                AddData.createUser();
                alert.setContentText("Created your room. now you or anyone can join your room using your room ID");
                alert.show();
            }
        } catch (Exception e) {
            alert.setContentText("Failed to Create : " + e.getMessage());
            alert.show();
            setCurrentMachineID(tmp);
            throw new RuntimeException(e);
        }




    }





    public void UploadCode(ActionEvent event)
    {
        if (getCurrentMachineID().equals(""))
        {
            alert.setContentText("Join a room first");
            alert.show();
        }
        else if (codeSnippet.getText().equals(""))
        {
            alert.setContentText("Code is empty. Write something");
            alert.show();
        }
        else
        {
            myCode = codeSnippet.getText().toString();
            try {
                AddData.sendCode(myCode);
                alert.setContentText("Successfully send");
                alert.show();
            } catch (Exception e) {
                alert.setContentText("Failed to Upload code : " + e.getMessage());
                alert.show();
                throw new RuntimeException(e);
            }
        }
    }





    public void GetCode(ActionEvent event)
    {
        if (getCurrentMachineID().equals(""))
        {
            alert.setContentText("Join a room first");
            alert.show();
        }
        else
        {

            try {
                GetData.getCode();
                myCode = GetData.Code;
                codeSnippet.setText(myCode);
            } catch (Exception e) {
                alert.setContentText("Failed to Get code : " + e.getMessage());
                alert.show();
                throw new RuntimeException(e);
            }
        }
    }



    @FXML
    public void initialize()
    {
        FirebaseInitializer.initialize();
        myRoomID.setText(myMachineID);
    }




}
