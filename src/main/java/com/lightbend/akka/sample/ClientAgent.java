package com.lightbend.akka.sample;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class ClientAgent extends AbstractActor {

    static public Props props(String name) {

        return Props.create(ClientAgent.class, () -> new ClientAgent(name));

    }

    public final String name;

    public ClientAgent(String name) {

        this.name = name;

    }

    @Override

    public Receive createReceive() {

        return receiveBuilder()

                .match(ScheduleAppointment.class, appointment -> {

                    appointment.scheduleAgent.tell(new AppointmentOnTime(name, appointment.time), getSelf());

                })

                .build();

    }

}