package com.lightbend.akka.sample;

import akka.actor.AbstractActor;
import akka.actor.Props;

import java.util.Map;

public class LoggerAgent extends AbstractActor {

    static public Props props() {

        return Props.create(LoggerAgent.class, () -> new LoggerAgent());

    }

    @Override

    public Receive createReceive() {

        return receiveBuilder()

                .match(ScheduleListMessage.class, this::printMessage)

                .match(AppointmentMessage.class, this::printMessage)

                .build();

    }

    private void printMessage(AppointmentMessage message) {

        StringBuilder result = new StringBuilder();

        result.append(message.successful ? "Запись на прием произведена успешно:" : "Запись на прием на произведена (время занято)");

        result.append("\n");

        result.append("Время: ").append(message.time);

        result.append(" Имя: ").append(message.name);

        result.append(" - ").append(message.specialty);

        result.append("\n");

        System.out.println(result.toString());

    }

    private void printMessage(ScheduleListMessage message) {

        StringBuilder result = new StringBuilder("Очередь на прием к врачу: " + message.name + " (" + message.specialty + "):\n");

        for(Map.Entry<String, String> entry : message.schedule.entrySet()) {

            String time = entry.getKey();

            String patientName = entry.getValue();

            result.append(time).append(" - ").append(patientName);

            result.append("\n");

        }

        System.out.println(result.toString());

    }

}