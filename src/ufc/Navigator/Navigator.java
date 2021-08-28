package ufc.Navigator;

import ufc.file.ReadFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Navigator {
    ReadFile reader;
    List<String> lstWaypoint, lstWaypointVisible, lstCanTraverse, lstVisible, lstRoverAvaliable, lstCanTraverseVersao2;
    Hashtable<String, String> hsttAt_Rover, hstAction = new Hashtable<>();
    Hashtable<String, List<String>> hstCanTraverseVersao2, hstVisible;
    Hashtable<String, Integer> hstCountWaypoint = new Hashtable<>();
    List<String> aux = new ArrayList<>();

    public Navigator(ReadFile reader) {
        this.reader = reader;
    }

    public void createActionNonDeterministicNavigator() {
       List<String> aux = new ArrayList<>();
       hstVisible = reader.getHstVisible();
       Set<String> keys = hstVisible.keySet();
       Iterator<String> it = keys.iterator();
       while (it.hasNext()){
           String from = it.next();
           aux = hstVisible.get(from);
           for (String toLocal1 : aux
           ) {
               BuildConstructAction("rover0", from, toLocal1);
           }
       }

    }

    private void BuildConstructAction(String rover, String from, String toLocal1) {
        String temp;

        lstWaypoint = reader.getLstWaypoint();
        lstVisible = reader.getLstVisible();
        lstRoverAvaliable = reader.getLstRoverAvailable();
        hsttAt_Rover = reader.getHsttAt_Rover();
        lstCanTraverseVersao2 = reader.getHstCanTraverseVersao2().get(rover);
        Integer aux=0;
        temp = from + " " + toLocal1;
            for (int k = 0; k < lstCanTraverseVersao2.size(); k++){
                if (lstCanTraverseVersao2.get(k).equals(temp)) {
                    for (int j = 0; j < lstVisible.size(); j++)  {
                        if (lstVisible.get(j).equals(temp)) {
                            if (hstCountWaypoint.containsKey(from)){
                                aux = hstCountWaypoint.get(from);
                                aux++;
                                hstCountWaypoint.put(from, aux);
                                String action = hstAction.get(from);

                                String posCondictionAux = action.substring(action.lastIndexOf("<pos>")-1, action.length());
                                action = action.substring(0, action.lastIndexOf("<pos>"));

                                posCondictionAux = posCondictionAux.substring(6,posCondictionAux.length()-6);
                                posCondictionAux= posCondictionAux + ":" +"AT-"+rover.toUpperCase()+"-"+toLocal1.toUpperCase()+","
                                        +"~AT-"+rover.toUpperCase()+"-"+ from.toUpperCase()+"<\\pos>";
                                action = action +"<pos>" + posCondictionAux;

                               hstAction.put(from,action);
                            }else{
                                hstCountWaypoint.put(from, aux);
                                String name = "<name>NAVIGATE-"+ rover.toUpperCase()+"-FROM-" + from.toUpperCase() +"<\\name>"+System.lineSeparator() ;
                                String preCondiction= "<pre>AT-"+rover.toUpperCase()+"-"+from.toUpperCase()+","
                                        +"AVAILABLE-"+ rover.toUpperCase()+ "<\\pre>"+ System.lineSeparator();
                                String posCondiction = "<pos>AT-"+rover.toUpperCase()+"-"+toLocal1.toUpperCase()+","
                                        +"~AT-"+rover.toUpperCase()+"-"+ from.toUpperCase()+ "<\\pos>";
                                 hstAction.put(from,name+ preCondiction + posCondiction);
                                //System.out.println( name);
                               // System.out.println( preCondiction);
                              //  System.out.println( posCondiction);
                            }



                        }
                    }
                }

            }
    }

    public void printAction(){
        Iterator<String> keys = hstAction.keySet().iterator();

        while (keys.hasNext())
           System.out.println(hstAction.get(keys.next()));
    }

}







