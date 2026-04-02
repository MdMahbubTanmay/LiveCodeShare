package com.mahbub.codesharejfx;


import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;

public class CompileCode {
    static String code;
    static String fileName;
    static String execName;
    static String signature = "Developed by Mahbub Tanmay , UFTB";


    static String os = System.getProperty("os.name").toLowerCase();
    static String home = System.getProperty("user.home");


    public static void CompileC(String MyCode, Component mahbub) {



        code = MyCode;

        if (os.contains("win")) {
            fileName = "C:\\Users\\Public\\Documents\\c_code.c";
            execName = "C:\\Users\\Public\\Documents\\c_code_exec.exe";
        } else {
            // Ubuntu Public folder
            fileName = home + "/Public/c_code.c";
            execName = home + "/Public/c_code_exec";
        }

        try {

            java.nio.file.Files.writeString(java.nio.file.Paths.get(fileName), code);


            Process compile = new ProcessBuilder("gcc", fileName, "-o", execName).start();
            int exitCode = compile.waitFor();

            if (exitCode == 0) {
                if (os.contains("win")) {

                    new ProcessBuilder("cmd", "/c", "start", "cmd", "/k",
                            "color 0B & \"" + execName + "\" & echo. & echo " + signature + " & pause").start();
                } else {

                    String coloredSign = "\\e[32m" + signature + "\\e[0m";
                    new ProcessBuilder("gnome-terminal", "--", "bash", "-c",
                            execName + "; echo -e '\\n" + coloredSign + "'; echo 'Press any key...'; read -n1").start();
                }
            } else {
                JOptionPane.showMessageDialog(mahbub, "Compilation Failed! Check your C syntax.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mahbub, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }






    public static void CompileCpp(String MyCode) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        code = MyCode;

        if (os.contains("win")) {
            fileName = "C:\\Users\\Public\\Documents\\cpp_code.cpp";
            execName = "C:\\Users\\Public\\Documents\\cpp_code_exec.exe";
        } else {
            // Ubuntu Public folder
            fileName = home + "/Public/cpp_code.cpp";
            execName = home + "/Public/cpp_code_exec";
        }

        try {

            java.nio.file.Files.writeString(java.nio.file.Paths.get(fileName), code);


            Process compile = new ProcessBuilder("g++", fileName, "-o", execName).start();
            int exitCode = compile.waitFor();

            if (exitCode == 0) {
                if (os.contains("win")) {

                    new ProcessBuilder("cmd", "/c", "start", "cmd", "/k",
                            "color 0B & \"" + execName + "\" & echo. & echo " + signature + " & pause").start();
                } else {

                    String coloredSign = "\\e[32m" + signature + "\\e[0m";
                    new ProcessBuilder("gnome-terminal", "--", "bash", "-c",
                            execName + "; echo -e '\\n" + coloredSign + "'; echo 'Press any key...'; read -n1").start();
                }
            } else {
                alert.setContentText("Compilation Failed");
                alert.show();
            }

        } catch (Exception ex) {
            alert.setContentText("Compilation Failed : " + ex.getMessage());
            alert.show();
        }
    }

}
