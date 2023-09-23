package org.example.advancejava;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class Advancejava1 {

    @Test
    public void trycatch() {
        try {
            System.out.println("TEst1");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("There is InterruptedException");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("There failed beacuse file is not available");
        }
    }

    @Test
    public void checkingng() {
        String timenow = "03:45 am";
        String time = timenow.replaceAll("[a-zA-Z]","").trim();
        String ampm = timenow.replaceAll("[0-9:]","").trim();
        System.out.println(ampm);


        if(ampm.equals("pm")){
          time = time+" p.m.";
        }else{
            time = time+" a.m.";
        }

        System.out.println(time);
    }
}
