package com.example.adjoe.utils;

public class TimeHelper implements TimeFormatter {

    @Override
    public long getLastActiveTimeInMinutes(long savedTime) {
        if (savedTime == 0) return 0;

        long presentDuration = System.currentTimeMillis() - savedTime;
        return (presentDuration / 1000) / 60;
    }
}
