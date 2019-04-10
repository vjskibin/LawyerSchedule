package com.lightbend.akka.sample;

public class AppointmentOnTime {

    public final String clientName;

    public final String time;

    public AppointmentOnTime(String patientName, String time) {

        this.clientName = patientName;

        this.time = time;

    }

}