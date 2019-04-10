package com.lightbend.akka.sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Launcher {

    public static void main(String[] args) {

        final ActorSystem system = ActorSystem.create("appointmentSchedule");

        final ActorRef loggerActor =

                system.actorOf(LoggerAgent.props(), "loggerActor");

        final ActorRef familyLawyerSchedule =

                system.actorOf(LawyerScheduleAgent.props("Aunt Tanya", "Family", loggerActor), "FamilyLawyerSchedule");

        final ActorRef businessLawyerSchedule =

                system.actorOf(LawyerScheduleAgent.props("Dmitry Medvedev", "Business", loggerActor), "BusinessLawyerSchedule");

        final ActorRef philipMorris = system.actorOf(ClientAgent.props("Philip Morris"), "Client1");
        final ActorRef x5RetailGroup = system.actorOf(ClientAgent.props("X5 Retail Group"), "Client2");
        final ActorRef annaFrank = system.actorOf(ClientAgent.props("Anna Frank"), "Client3");
        final ActorRef jeanneDarc = system.actorOf(ClientAgent.props("Janne d'Arc"), "Client4");


        annaFrank.tell(new ScheduleAppointment("10:00", familyLawyerSchedule), ActorRef.noSender());

        annaFrank.tell(new ScheduleAppointment("11:00", familyLawyerSchedule), ActorRef.noSender());

        x5RetailGroup.tell(new ScheduleAppointment("10:00", businessLawyerSchedule), ActorRef.noSender());

        x5RetailGroup.tell(new ScheduleAppointment("10:00", businessLawyerSchedule), ActorRef.noSender());

        philipMorris.tell(new ScheduleAppointment("13:00", businessLawyerSchedule), ActorRef.noSender());

        philipMorris.tell(new ScheduleAppointment("10:00", businessLawyerSchedule), ActorRef.noSender());

        philipMorris.tell(new ScheduleAppointment("14:00", businessLawyerSchedule), ActorRef.noSender());

        jeanneDarc.tell(new ScheduleAppointment("10:00", familyLawyerSchedule), ActorRef.noSender());
        jeanneDarc.tell(new ScheduleAppointment("11:00", familyLawyerSchedule), ActorRef.noSender());


        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            familyLawyerSchedule.tell(new PrintCurrentSchedule(), ActorRef.noSender());

            businessLawyerSchedule.tell(new PrintCurrentSchedule(), ActorRef.noSender());
        }).start();



    }

}