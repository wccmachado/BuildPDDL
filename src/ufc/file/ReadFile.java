package ufc.file;

import ufc.Objects.Objects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadFile {
    String fileName;
    List<String> lstWaypoint = new ArrayList<>();
    List<String> lstVisible = new ArrayList<>();
    Hashtable<String, List<String>> hstVisible = new Hashtable<>();
    List<String> lstAt_Soil_Sample = new ArrayList<>();
    List<String> lstAt_Rock_Sample = new ArrayList<>();
    Hashtable<String, String> hsttAt_Lander_Sample = new Hashtable<>();
    List<String> lstAt_ChannelFree = new ArrayList<>();
    Hashtable<String, String> hsttAt_Rover = new Hashtable<>();
    List<String> lstRoverAvailable = new ArrayList<>();
    Hashtable<String, String> hstStoreOf = new Hashtable<>();
    List<String> lstStoreEmpty = new ArrayList<>();
    List<String> lstEquippedForSoilAnalysis = new ArrayList<>();
    List<String> lstEquippedForRockAnalysis = new ArrayList<>();
    List<String> lstEquippedForImaging = new ArrayList<>();
    Hashtable<String, Hashtable<String, List<String>>> hstCanTraverse = new Hashtable<>();
    Hashtable<String, List<String>> hstCanTraverseVersao2 = new Hashtable<>();
    Hashtable<String, List<String>> hstOnBoard = new Hashtable<>();
    Hashtable<String, List<String>> hstCalibrationTarget = new Hashtable<>();
    Hashtable<String, List<String>> hstSuportsCamera = new Hashtable<>();
    Hashtable<String, List<String>> hstVisibleFromObjective = new Hashtable<>();


    public ReadFile(String fileName) {
        this.fileName = fileName;
    }

    public void run() {
        Objects obj = new Objects();
        BufferedReader in;
        String line="", nextLine=null;

        {
            try {
                List<String> lstWaypointAux;

                List<String> lstCanTraverseWaypoint,lstCanTraverseWaypointVersao2;
                in = new BufferedReader(new FileReader(fileName));

                while (in.ready()) {
                    line = in.readLine();
                    if (line.contains("(:objects")) {
                        line= line.trim();
                        line = line.replace("(:objects","");
                        do {
                            nextLine = in.readLine().trim();
                            line = line + " " +  nextLine;
                        } while (!line.contains(")"));
                        line = line.replace(")","")+ " ";

                        obj.getObjects(line);
                    }

                    if (line.contains("(:init)")) {
                        while (!line.contains("(:goal")) {
                            line = in.readLine();
                            if (line.contains("visible ")) {
                                lstWaypointAux = new ArrayList<String>();
                                line = line.trim();
                                String aux1 = line.substring(line.indexOf(" ")+1, line.lastIndexOf(" "));
                                String aux2 = line.substring(line.lastIndexOf(" ")+1, line.length() - 1);
                                String aux3 = line.substring(line.indexOf(" ")+1, line.length() - 1);
                                lstVisible.add(aux3);
                                if (hstVisible.isEmpty()) {
                                    lstWaypointAux.add(aux2);
                                    hstVisible.put(aux1, lstWaypointAux);

                                } else {
                                    if (hstVisible.containsKey(aux1)) {
                                        List<String> temp = new ArrayList<String>();
                                        temp.addAll(hstVisible.get(aux1));
                                        temp.add(aux2);
                                        hstVisible.put(aux1, temp);

                                    } else {
                                        lstWaypointAux.add(aux2);
                                        hstVisible.put(aux1, lstWaypointAux);
                                        System.out.println(hstVisible);
                                    }
                                }
                            }
                            if (line.contains("at_soil_sample ")) {
                                line = line.trim();
                                lstAt_Soil_Sample.add(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(")")));
                            }
                            if (line.contains("at_rock_sample ")) {
                                line = line.trim();
                                lstAt_Rock_Sample.add(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(")")));

                            }
                            if (line.contains("at_lander ")) {
                                line = line.trim();
                                String aux1 = line.substring(line.indexOf(" "), line.lastIndexOf(" "));
                                String aux2 = line.substring(line.lastIndexOf(" ") + 1, line.lastIndexOf(")"));
                                hsttAt_Lander_Sample.put(aux1, aux2);
                            }
                            if (line.contains("channel_free ")) {
                                line = line.trim();
                                lstAt_ChannelFree.add(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(")")));

                            }
                            if (line.contains("at rover")) {
                                line = line.trim();
                                String aux1 = line.substring(line.indexOf(" "), line.lastIndexOf(" "));
                                String aux2 = line.substring(line.lastIndexOf(" ") + 1, line.lastIndexOf(")"));
                                hsttAt_Rover.put(aux1, aux2);
                            }
                            if (line.contains("available ")) {
                                line = line.trim();
                                lstRoverAvailable.add(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(")")));

                            }
                            if (line.contains("store_of ")) {
                                line = line.trim();
                                String aux1 = line.substring(line.indexOf(" "), line.lastIndexOf(" "));
                                String aux2 = line.substring(line.lastIndexOf(" ") + 1, line.lastIndexOf(")"));
                                hstStoreOf.put(aux1, aux2);
                            }
                            if (line.contains("empty ")) {
                                line = line.trim();
                                lstStoreEmpty.add(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(")")));

                            }
                            if (line.contains("equipped_for_soil_analysis ")) {
                                line = line.trim();
                                lstEquippedForSoilAnalysis.add(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(")")));

                            }
                            if (line.contains("equipped_for_rock_analysis ")) {
                                line = line.trim();
                                lstEquippedForRockAnalysis.add(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(")")));

                            }
                            if (line.contains("equipped_for_imaging ")) {
                                line = line.trim();
                                lstEquippedForImaging.add(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(")")));
                            }
                            if (line.contains("can_traverse ")) {
                                lstCanTraverseWaypoint = new ArrayList<>();
                                lstCanTraverseWaypointVersao2 = new ArrayList<>();
                                Hashtable hstCanTraverseWaypoint = new Hashtable();
                                line = line.trim();
                                line = line.substring(line.indexOf(" "), line.length()).trim();
                                String lineTemp = line.trim();
                                lineTemp = lineTemp.substring(lineTemp.indexOf(" ")+1, lineTemp.length()-1).trim();
                                String aux1 = line.substring(0, line.indexOf(" "));
                                String aux2 = line.substring(line.indexOf(" ") + 1, line.lastIndexOf(" "));
                                String aux3 = line.substring(line.lastIndexOf(" ") + 1, line.length()-1);
                               // String aux4 = line.substring(line.lastIndexOf(" ") + 1, line.length()-1);

                                if (hstCanTraverseVersao2.isEmpty()) {
                                    lstCanTraverseWaypointVersao2.add(lineTemp);
                                    hstCanTraverseVersao2.put(aux1,lstCanTraverseWaypointVersao2);
                                }
                                if (hstCanTraverse.isEmpty()) {
                                    lstCanTraverseWaypoint.add(aux3);

                                    hstCanTraverseWaypoint.put(aux2, lstCanTraverseWaypoint);
                                    hstCanTraverse.put(aux1, hstCanTraverseWaypoint);


                                } else {
                                    if (hstCanTraverseVersao2.containsKey(aux1)){

                                        lstCanTraverseWaypointVersao2 = hstCanTraverseVersao2.get(aux1);
                                        lstCanTraverseWaypointVersao2.add(lineTemp);
                                        hstCanTraverseVersao2.put(aux1,lstCanTraverseWaypointVersao2);
                                    }
                                    if (hstCanTraverse.containsKey(aux1)) {
                                        Hashtable<String, List<String>> hstAux = hstCanTraverse.get(aux1);


                                        if (hstAux.get(aux2)==null){
                                            lstCanTraverseWaypoint.add(aux3);
                                            hstAux.put(aux2, lstCanTraverseWaypoint);

                                        }else{
                                            lstCanTraverseWaypoint=  hstAux.get(aux2);
                                            lstCanTraverseWaypoint.add(aux3);
                                        }
                                        hstCanTraverse.put(aux1, hstAux);
                                    } else {
                                        Hashtable<String, List<String>> hstAux = new Hashtable<>();
                                        List<String> lstAux = new ArrayList<>();
                                        lstAux.add(aux3);
                                        hstAux.put(aux2, lstAux);
                                        hstCanTraverse.put(aux1, hstAux);

                                    }
                                }
                            }
                            if (line.contains("on_board ")) {
                                List<String> rover = new ArrayList<>();
                                line = line.trim();
                                String aux1 = line.substring(line.indexOf(" "), line.lastIndexOf(" "));
                                String aux2 = line.substring(line.lastIndexOf(" ") + 1, line.lastIndexOf(")"));
                                rover.add(aux2);
                                hstOnBoard.put(aux1, rover);
                            }
                            if (line.contains("calibration_target ")) {
                                List<String> objective = new ArrayList<>();
                                line = line.trim();
                                String camera = line.substring(line.indexOf(" "), line.lastIndexOf(" "));
                                String aux1 = line.substring(line.lastIndexOf(" ") + 1, line.lastIndexOf(")"));
                                objective.add(aux1);
                                hstCalibrationTarget.put(camera, objective);
                            }
                            if (line.contains("supports ")) {
                                List<String> lstmode = new ArrayList<>();
                                line = line.trim();
                                String camera = line.substring(line.indexOf(" "), line.lastIndexOf(" "));
                                String aux1 = line.substring(line.lastIndexOf(" ") + 1, line.lastIndexOf(")"));
                                if (hstSuportsCamera.isEmpty()){
                                    lstmode.add(aux1);
                                    hstSuportsCamera.put(camera, lstmode);

                                }else {
                                    if (hstSuportsCamera.containsKey(camera)) {
                                        lstmode = hstSuportsCamera.get(camera);
                                        lstmode.add(aux1);
                                        hstSuportsCamera.put(camera, lstmode);
                                    }else {
                                        lstmode.add(aux1);
                                        hstSuportsCamera.put(camera, lstmode);
                                    }
                                }

                            }
                            if (line.contains("visible_from ")) {
                                List<String> lstWaypointVisibleFrom = new ArrayList<>();
                                line = line.trim();
                                String objective = line.substring(line.indexOf(" "), line.lastIndexOf(" "));
                                String waypoint = line.substring(line.lastIndexOf(" ") + 1, line.lastIndexOf(")"));

                                if (hstVisibleFromObjective.containsKey(objective)){
                                       lstWaypointVisibleFrom = hstVisibleFromObjective.get(objective);
                                       lstWaypointVisibleFrom.add(waypoint);
                                    }else{
                                        lstWaypointVisibleFrom.add(waypoint);

                                }
                                hstVisibleFromObjective.put(objective, lstWaypointVisibleFrom);
                            }
                        }
                    }
                }
                for (String waypoint : lstWaypoint
                ) {
                    System.out.println("Waypoint: " + waypoint);
                }
                for (String waypoint : lstVisible
                ) {
                    System.out.println("visible: " + waypoint);
                }
                System.out.println("Mappings of ht1 : " + hstVisible);
                for (String soils : lstAt_Soil_Sample
                ) {
                    System.out.println("Amostras de solo: " + soils);
                }
                for (String soils : lstAt_Soil_Sample
                ) {
                    System.out.println("Amostras de rocha: " + soils);
                }
                System.out.println("Landers: " + hsttAt_Lander_Sample);
                for (String channel : lstAt_ChannelFree
                ) {
                    System.out.println("Canal livre: " + channel);
                }
                System.out.println("At rover: " + hsttAt_Rover);
                for (String rover : lstRoverAvailable
                ) {
                    System.out.println("Rover available: " + rover);
                }
                System.out.println("Store Of: " + hstStoreOf);

                for (String empty : lstStoreEmpty
                ) {
                    System.out.println("Rover Store Empty: " + empty);
                }
                for (String aux : lstEquippedForSoilAnalysis
                ) {
                    System.out.println("Rover Equipped For Soil Analysis: " + aux);
                }
                for (String aux : lstEquippedForRockAnalysis
                ) {
                    System.out.println("Rover Equipped For Rock Analysis: " + aux);
                }
                for (String aux : lstEquippedForImaging
                ) {
                    System.out.println("Rover Equipped For Imaging: " + aux);
                }
                System.out.println("Can traverse: " + hstCanTraverse);
                System.out.println("Can traverse versao 2: " + hstCanTraverseVersao2);
                System.out.println("On board: " + hstOnBoard);
                System.out.println("Calibration: " + hstCalibrationTarget);
                System.out.println("Support: " + hstSuportsCamera);
                System.out.println("Visible_from_Objective: " + hstVisibleFromObjective);



            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found ");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error input/output");
                e.printStackTrace();
            }
        }
    }

    public List<String> getLstVisible() {
        return lstVisible;
    }

    public void setLstVisible(List<String> lstVisible) {
        this.lstVisible = lstVisible;
    }

    public Hashtable<String, List<String>> getHstCanTraverseVersao2() {
        return hstCanTraverseVersao2;
    }

    public void setHstCanTraverseVersao2(Hashtable<String, List<String>> hstCanTraverseVersao2) {
        this.hstCanTraverseVersao2 = hstCanTraverseVersao2;
    }

    public List<String> getLstWaypoint() {
        return lstWaypoint;
    }

    public void setLstWaypoint(List<String> lstWaypoint) {
        this.lstWaypoint = lstWaypoint;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Hashtable<String, List<String>> getHstVisible() {
        return hstVisible;
    }

    public void setHstVisible(Hashtable<String, List<String>> hstVisible) {
        this.hstVisible = hstVisible;
    }

    public List<String> getLstAt_Soil_Sample() {
        return lstAt_Soil_Sample;
    }

    public void setLstAt_Soil_Sample(List<String> lstAt_Soil_Sample) {
        this.lstAt_Soil_Sample = lstAt_Soil_Sample;
    }

    public List<String> getLstAt_Rock_Sample() {
        return lstAt_Rock_Sample;
    }

    public void setLstAt_Rock_Sample(List<String> lstAt_Rock_Sample) {
        this.lstAt_Rock_Sample = lstAt_Rock_Sample;
    }

    public Hashtable<String, String> getHsttAt_Lander_Sample() {
        return hsttAt_Lander_Sample;
    }

    public void setHsttAt_Lander_Sample(Hashtable<String, String> hsttAt_Lander_Sample) {
        this.hsttAt_Lander_Sample = hsttAt_Lander_Sample;
    }

    public List<String> getLstAt_ChannelFree() {
        return lstAt_ChannelFree;
    }

    public void setLstAt_ChannelFree(List<String> lstAt_ChannelFree) {
        this.lstAt_ChannelFree = lstAt_ChannelFree;
    }

    public Hashtable<String, String> getHsttAt_Rover() {
        return hsttAt_Rover;
    }

    public void setHsttAt_Rover(Hashtable<String, String> hsttAt_Rover) {
        this.hsttAt_Rover = hsttAt_Rover;
    }

    public List<String> getLstRoverAvailable() {
        return lstRoverAvailable;
    }

    public void setLstRoverAvailable(List<String> lstRoverAvailable) {
        this.lstRoverAvailable = lstRoverAvailable;
    }

    public Hashtable<String, String> getHstStoreOf() {
        return hstStoreOf;
    }

    public void setHstStoreOf(Hashtable<String, String> hstStoreOf) {
        this.hstStoreOf = hstStoreOf;
    }

    public List<String> getLstStoreEmpty() {
        return lstStoreEmpty;
    }

    public void setLstStoreEmpty(List<String> lstStoreEmpty) {
        this.lstStoreEmpty = lstStoreEmpty;
    }

    public List<String> getLstEquippedForSoilAnalysis() {
        return lstEquippedForSoilAnalysis;
    }

    public void setLstEquippedForSoilAnalysis(List<String> lstEquippedForSoilAnalysis) {
        this.lstEquippedForSoilAnalysis = lstEquippedForSoilAnalysis;
    }

    public List<String> getLstEquippedForRockAnalysis() {
        return lstEquippedForRockAnalysis;
    }

    public void setLstEquippedForRockAnalysis(List<String> lstEquippedForRockAnalysis) {
        this.lstEquippedForRockAnalysis = lstEquippedForRockAnalysis;
    }

    public List<String> getLstEquippedForImaging() {
        return lstEquippedForImaging;
    }

    public void setLstEquippedForImaging(List<String> lstEquippedForImaging) {
        this.lstEquippedForImaging = lstEquippedForImaging;
    }

    public Hashtable<String, Hashtable<String, List<String>>> getHstCanTraverse() {
        return hstCanTraverse;
    }

    public void setHstCanTraverse(Hashtable<String, Hashtable<String, List<String>>> hstCanTraverse) {
        this.hstCanTraverse = hstCanTraverse;
    }

    public Hashtable<String, List<String>> getHstOnBoard() {
        return hstOnBoard;
    }

    public void setHstOnBoard(Hashtable<String, List<String>> hstOnBoard) {
        this.hstOnBoard = hstOnBoard;
    }

    public Hashtable<String, List<String>> getHstCalibrationTarget() {
        return hstCalibrationTarget;
    }

    public void setHstCalibrationTarget(Hashtable<String, List<String>> hstCalibrationTarget) {
        this.hstCalibrationTarget = hstCalibrationTarget;
    }

    public Hashtable<String, List<String>> getHstSuportsCamera() {
        return hstSuportsCamera;
    }

    public void setHstSuportsCamera(Hashtable<String, List<String>> hstSuportsCamera) {
        this.hstSuportsCamera = hstSuportsCamera;
    }

    public Hashtable<String, List<String>> getHstVisibleFromObjective() {
        return hstVisibleFromObjective;
    }

    public void setHstVisibleFromObjective(Hashtable<String, List<String>> hstVisibleFromObjective) {
        this.hstVisibleFromObjective = hstVisibleFromObjective;
    }
}
