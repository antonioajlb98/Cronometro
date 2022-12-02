package model;


import com.mysql.cj.conf.StringProperty;

import java.time.LocalDate;

public class Chronometer {
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;
    private StringProperty time;
    private LocalDate date;
    public static boolean stop;

    public Chronometer() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.milliseconds = 0;
        this.stop = false;

    }

    public Chronometer(int hours, int minutes, int seconds, int milliseconds, LocalDate date) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        this.time = time;
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }
    public String getTime() {
        return String.format("%02d:%02d:%02d:%02d", this.getHours(), this.getMinutes(), this.getSeconds(), this.getMilliseconds());
    }
    public LocalDate getDate() {
        return date;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public static void setStop(boolean stop) {
        Chronometer.stop = stop;
    }

    @Override
    public String toString() {
        return "hours=" + hours +
                "minutes=" + minutes +
                "seconds=" + seconds +
                "milliseconds=" + milliseconds;
    }
}
