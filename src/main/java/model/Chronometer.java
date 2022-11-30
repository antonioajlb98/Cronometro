package model;


import com.mysql.cj.conf.StringProperty;

public class Chronometer {
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;
    public static boolean stop;

    public Chronometer() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.milliseconds = 0;
        this.stop = false;

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
