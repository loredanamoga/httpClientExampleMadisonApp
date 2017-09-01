package com.madison.tests.httptests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by loredanamoga on 8/18/2017.
 */
public class ReadCSV {

    public static HashMap csvRead(){

        String csvFile = "C:\\Users\\loredanamoga\\IdeaProjects\\madisonAutoTest\\src\\test\\resources\\csv\\postParams.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "#";
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use # as separator
                String[] params = line.split(cvsSplitBy);
                if(params[1] == "*"){
                    hashMap.put(params[0], "");
                }
//                System.out.println("params [key= " + params[0] + " , value=" + params[1] + "]");
                else {
                hashMap.put(params[0], params[1]);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return hashMap;
    }

}




