/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Main
 */
public class Info {
    double arealDistance;
    double drivingDistance;
    String congestionFactor;

    public Info(double arealDistance, double drivingDistance, String congestionFactor) {
        this.arealDistance = arealDistance;
        this.drivingDistance = drivingDistance;
        this.congestionFactor = congestionFactor;
    }

    public Info(double arealDistance) {
        this.arealDistance = arealDistance;
    }

    public double getArealDistance() {
        return arealDistance;
    }

    public double getDrivingDistance() {
        return drivingDistance;
    }

    public String getCongestionFactor() {
        return congestionFactor;
    }

    public void setArealDistance(double arealDistance) {
        this.arealDistance = arealDistance;
    }

    public void setDrivingDistance(double drivingDistance) {
        this.drivingDistance = drivingDistance;
    }

    public void setCongestionFactor(String congestionFactor) {
        this.congestionFactor = congestionFactor;
    }

    @Override
    public String toString() {
        return "Info{" + "arealDistance=" + arealDistance + ", drivingDistance=" + drivingDistance + ", congestionFactor=" + congestionFactor + '}';
    }
    
   
}
