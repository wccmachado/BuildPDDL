package ufc.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Objects {

    private List<String> lander= new ArrayList<>();
    private List<String> mode= new ArrayList<>();
    private List<String> waypoint= new ArrayList<>();
    private List<String> camera= new ArrayList<>();
    private List<String> objective= new ArrayList<>();
    private List<String> rover= new ArrayList<>();
    private List<String> store= new ArrayList<>();

    public void getObjects(String object){
        Pattern p = Pattern.compile(" - ");
        Pattern psw = Pattern.compile(" ");
        Matcher m = p.matcher(object);
        int idxInitial=0,nextToken=-1;
        while (m.find()) {
            String aux = object.substring(m.start()+3,object.length());
            Matcher msw = psw.matcher(aux);
            if (msw.find()){
                String temp = aux.substring(idxInitial, msw.start());
                if (temp.equals("lander")){
                    lander.add(object.substring(idxInitial, m.start()).trim());
                    nextToken=6;
                }
                if (temp.equals("mode")){
                    mode.add(object.substring(idxInitial, m.start()).trim());
                    nextToken=4;
                }
                if (temp.equals("waypoint")){
                    waypoint.add(object.substring(idxInitial, m.start()).trim());
                    nextToken=8;
                }
                if (temp.equals("camera")){
                    camera.add(object.substring(idxInitial, m.start()).trim());
                    nextToken=6;
                }
                if (temp.equals("objective")){
                    objective.add(object.substring(idxInitial, m.start()).trim());
                    nextToken=9;
                }
                if (temp.equals("rover")){
                    rover.add(object.substring(idxInitial, m.start()).trim());
                    nextToken=5;
                }
                if (temp.equals("store")){
                    store.add(object.substring(idxInitial, m.start()).trim());
                    nextToken=5;
                }

                object=aux.substring(nextToken,aux.length());
                m=p.matcher(object);
            }

        }
        for (String t: lander
             ) {
            System.out.println(" Lander " + t);
        }
        for (String t: mode
        ) {
            System.out.println(" mode " + t);
        }
        for (String t: waypoint
        ) {
            System.out.println(" waypoint " + t);
        }
        for (String t: camera
        ) {
            System.out.println(" camera " + t);
        }
        for (String t: objective
        ) {
            System.out.println(" Objective " + t);
        }
        for (String t: rover
        ) {
            System.out.println(" rover " + t);
        }
        for (String t: store
        ) {
            System.out.println(" store " + t);
        }


    }



}
