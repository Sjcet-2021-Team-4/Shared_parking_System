package com.aumento.sharedparking.modelclass;

public class BookingModelClass {

    String id;
    String pid;
    String place_name;
    String vehicle_type;
    String vehicle_number;
    String bdate;
    String bduration;
    String amount;

    public BookingModelClass(String id, String pid, String place_name, String vehicle_type, String vehicle_number, String bdate, String bduration, String amount) {
        this.id = id;
        this.pid = pid;
        this.place_name = place_name;
        this.vehicle_type = vehicle_type;
        this.vehicle_number = vehicle_number;
        this.bdate = bdate;
        this.bduration = bduration;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getPid() {
        return pid;
    }

    public String getPlace_name() {
        return place_name;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public String getBdate() {
        return bdate;
    }

    public String getBduration() {
        return bduration;
    }

    public String getAmount() {
        return amount;
    }
}
