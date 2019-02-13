package com.sipi.groupOne.train;

import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class SetXML {
    String searchValue;
    String auth;
    public SetXML(String searchValue) {
        this.searchValue = searchValue;
        if(initAuth()){
            generateXML();
        } else {
            System.err.println("Auth gav: " + auth);
        }
    }

    // Trying to load the address for the api
    private boolean initAuth() {
        try {
            File key = new File("src/com/sipi/groupOne/train/application.properties");
            BufferedReader b = new BufferedReader(new FileReader(key));
            auth = b.readLine(); // Reading in single line since it is the only thing in the file
            if(auth != null)
                return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.err.println("Kunde ej hämta informationen från filen med auth");
        return false;
    }

    public abstract Document generateXML();
}
