package com.lightbend.akka.sample;

public class AppointmentMessage {

    public final String name;

    public final String time;

    public final String specialty;

    public final boolean successful;

    public AppointmentMessage(String name, String time, String specialty, boolean successful) {

        this.name = name;

        this.time = time;

        this.specialty = specialty;

        this.successful = successful;

    }

}