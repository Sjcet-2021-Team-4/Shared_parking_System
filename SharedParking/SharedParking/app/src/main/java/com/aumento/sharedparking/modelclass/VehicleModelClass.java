package com.aumento.sharedparking.modelclass;

public class VehicleModelClass {

    String vehicle_number;
    String vehicle_type;

    public VehicleModelClass(String vehicle_number, String vehicle_type) {
        this.vehicle_number = vehicle_number;
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }
}
