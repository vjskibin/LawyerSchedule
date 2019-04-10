package com.lightbend.akka.sample;

import akka.actor.ActorRef;

public class ScheduleAppointment {

    public final String time;

    public final ActorRef scheduleAgent;

    public ScheduleAppointment(String time, ActorRef scheduleAgent) {

        this.time = time;

        this.scheduleAgent = scheduleAgent;

    }

}