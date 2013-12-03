/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package livestreamerJGUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Matthias
 */
public class PreferenceHandler {
    private static PreferenceHandler instance;
    private File preferenceFile;
    private final Map<String,String> preferences = new HashMap<>();

    public static PreferenceHandler getInstance() {
        if(instance == null) {
            instance = new PreferenceHandler();
        }
        return instance;
    }
    
    private PreferenceHandler() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("preferences.txt"));

            while (br.ready()) {
                String[] setting = br.readLine().split("=");
                preferences.put(setting[0], setting[1]);
            }
            br.close();
        } catch (IOException e) {
        }
    }
    
    public void writePreferencesToFile() {
        try {
            FileWriter writer = new FileWriter("preferences.txt");
            for(String setting : this.preferences.keySet()) {
                writer.write(setting + "=" + this.preferences.get(setting));
            }
            writer.close();
        } catch (IOException e) {

        }
    }
    
    public String get(String setting) {
        return this.preferences.get(setting) ;
    }
    
    public void set(String setting, String value) {
        this.preferences.put(setting, value);
        writePreferencesToFile();
    }
}
