package ufc.SampleSoil;

import ufc.file.ReadFile;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ActionSampleSoil {
    ReadFile reader;
    List<String> lstWaypoint, lstWaypointVisible, lstCanTraverse, lstVisible, lstRoverAvaliable, lstCanTraverseVersao2;
    Hashtable<String, String> hsttAt_Rover, hstAction = new Hashtable<>();
    Hashtable<String, List<String>> hstCanTraverseVersao2, hstVisible;
    Hashtable<String, Integer> hstCountWaypoint = new Hashtable<>();
    List<String> aux = new ArrayList<>();


}
