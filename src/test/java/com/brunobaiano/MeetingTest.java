package com.brunobaiano;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingTest {

    public static Stream<Arguments> getDays() {
        var year = LocalDate.now().getYear();
        var month = String.format("%02d", LocalDate.now().getMonth().getValue());
        return
                Stream.of(
                        Arguments.of("September 1st", year + "-09-01"),
                        Arguments.of("July 16th", year + "-07-16"),
                        Arguments.of("22", year + "-" + month + "-22")
                );
    }

    @ParameterizedTest
    @MethodSource("getDays")
    void getFullDay(String day, String expected) {

        Meeting meeting = new Meeting("09:00", "10:00", day, "Meeting 1");
        assertEquals(expected, meeting.getFullDay());
    }
}