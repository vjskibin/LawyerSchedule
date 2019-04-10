package com.lightbend.akka.sample;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.HashMap;

public class LawyerScheduleAgent extends AbstractActor {

    static public Props props(String name, String specialty, ActorRef logger) {

        return Props.create(LawyerScheduleAgent.class, () -> new LawyerScheduleAgent(name, specialty, logger));

    }

    private final String name;

    private final String specialty;

    private final ActorRef logger;

    private final HashMap<String, String> schedule;

    public LawyerScheduleAgent(String name, String specialty, ActorRef logger) {

        this.name = name;

        this.specialty = specialty;

        this.logger = logger;

        this.schedule = new HashMap<>();

    }

    @Override

    public Receive createReceive() {

        return receiveBuilder()

                .match(AppointmentOnTime.class, appointment -> {

                    tryCreateAppointment(appointment);

                })

                .match(PrintCurrentSchedule.class, s -> {

                    logger.tell(new ScheduleListMessage(name, specialty, this.schedule), getSelf());

                })

                .build();

    }

    private void tryCreateAppointment(AppointmentOnTime appointment) {

        boolean successfulAdd = !schedule.containsKey(appointment.time);

        logger.tell(new AppointmentMessage(appointment.clientName, appointment.time, specialty, successfulAdd), getSelf());

        if (!schedule.containsKey(appointment.time)) {

            schedule.put(appointment.time, appointment.clientName);

        }

    }

}