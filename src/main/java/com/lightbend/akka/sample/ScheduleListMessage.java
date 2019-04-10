package com.lightbend.akka.sample;

import java.util.HashMap;

public class ScheduleListMessage {

    public final String name;

    public final String specialty;

    public final HashMap<String, String> schedule;

    public ScheduleListMessage(String name, String specialty, HashMap<String, String> schedule) {

        this.name = name;

        this.specialty = specialty;

        this.schedule = schedule;

    }

}