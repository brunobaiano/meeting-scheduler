package com.brunobaiano;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/scheduler")
public class SchedulerResource {

    @Inject
    SchedulerAiService schedulerAiService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getScheduler() {
        return schedulerAiService.getScheduler(3);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public void postScheduler(InitScheduler initScheduler) {
        schedulerAiService.setInitialParameters(3, initScheduler.startingWorkingHour(), initScheduler.endWorkingHour(), initScheduler.daysOnWeek());
    }

    @POST
    @Path("/meeting")
    @Produces(MediaType.TEXT_PLAIN)
    public String addMeeting(Meeting meeting) {
        return schedulerAiService.addMeeting(3, meeting.startAt(), meeting.endAt(), meeting.getFullDay(), meeting.description());
    }


}
