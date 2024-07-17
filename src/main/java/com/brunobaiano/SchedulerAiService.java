package com.brunobaiano;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@ApplicationScoped
public interface SchedulerAiService {

    @SystemMessage("You are an assistant and will help to manage my calendar meetings.")
    @UserMessage("""
    I work on {daysOnWeek} from {startingWorkingHour} to {endWorkingHour}.
    You can't schedule meeting that starts before {startingWorkingHour} or ends after {endWorkingHour}.
    You can't schedule meeting in days that are not in {daysOnWeek}.
    You can't overlap meetings.
    If a meeting is not possible to be scheduled or you can't suggest anything, return a friendly error message.
    """)
    String setInitialParameters(@MemoryId int id, String startingWorkingHour, String endWorkingHour, String daysOnWeek);

    @UserMessage("""
    Add a meeting  on day {day} from {startAt} to {endAt}, with description {description}.
    Return my calendar meetings or a friendly message that is not possible.
    """)
    String addMeeting(@MemoryId int id, String startAt, String endAt, String day, String description);

    @UserMessage("Return my calendar meetings.")
    String getScheduler(@MemoryId int id);
}
