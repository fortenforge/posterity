package posterity.com.posterity;

import java.util.ArrayList;
import java.util.List;

public class PosterEvent {

    private String imageName;
    private String eventTitle;
    private int eventTimeStartHour;
    private int eventTimeStartMinute;
    private int eventTimeEndHour;
    private int eventTimeEndMinute;
    private int eventDateYear;
    private int eventDateMonth;
    private int eventDateDay;
    private String eventLocation;

    public PosterEvent(String imageName, String eventTitle, int eventTimeStartHour, int eventTimeStartMinute, int eventTimeEndHour, int eventTimeEndMinute, int eventDateYear, int eventDateMonth, int eventDateDay, String eventLocation) {
        this.imageName = imageName;
        this.eventTitle = eventTitle;
        this.eventTimeStartHour = eventTimeStartHour;
        this.eventTimeStartMinute = eventTimeStartMinute;
        this.eventTimeEndHour = eventTimeEndHour;
        this.eventTimeEndMinute = eventTimeEndMinute;
        this.eventDateYear = eventDateYear;
        this.eventDateMonth = eventDateMonth;
        this.eventDateDay = eventDateDay;
        this.eventLocation = eventLocation;
    }

    public String getImageName() {
        return imageName;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public List<Integer> getTimeRange() {
        List<Integer> timeRange = new ArrayList<>();
        timeRange.add(eventTimeStartHour);
        timeRange.add(eventTimeStartMinute);
        timeRange.add(eventTimeEndHour);
        timeRange.add(eventTimeEndMinute);
        return timeRange;
    }

    public List<Integer> getDate() {
        List<Integer> date = new ArrayList<>();
        date.add(eventDateYear);
        date.add(eventDateMonth);
        date.add(eventDateDay);
        return date;
    }

    public String getLocation() {
        return eventLocation;
    }

    public String getStartTimeRangeString() {
        String timeRangeString;
        if (eventTimeStartHour == 0) {
            timeRangeString = "12:" + eventTimeStartMinute + " AM";
        }
        else if (eventTimeStartHour < 12) {
            timeRangeString = eventTimeStartHour + ":" + eventTimeStartMinute + " AM";
        }
        else if (eventTimeStartHour == 12) {
            timeRangeString = "12:" + eventTimeStartMinute + " PM";
        }
        else {
            timeRangeString = eventTimeStartHour - 12 + ":" + eventTimeStartMinute + " PM";
        }
        return timeRangeString;
    }

    public String getEndTimeRangeString() {
        String timeRangeString;
        if (eventTimeEndHour == 0) {
            timeRangeString = "12:" + eventTimeEndMinute + " AM";
        }
        else if (eventTimeEndHour < 12) {
            timeRangeString = eventTimeEndHour + ":" + eventTimeEndMinute + " AM";
        }
        else if (eventTimeEndHour == 12) {
            timeRangeString = "12:" + eventTimeEndMinute + " PM";
        }
        else {
            timeRangeString = eventTimeEndHour - 12 + ":" + eventTimeEndMinute + " PM";
        }
        return timeRangeString;
    }

    public String getTimeRangeString() {
        return getStartTimeRangeString() + " - " + getEndTimeRangeString();
    }

    public String getDateString() {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return months[eventDateMonth] + " " + eventDateDay + ", " + eventDateYear;
    }

    public void setStartTimes(int eventTimeStartHour, int eventTimeStartMinute) {
        this.eventTimeStartHour = eventTimeStartHour;
        this.eventTimeStartMinute = eventTimeStartMinute;
    }

    public void setEndTimes(int eventTimeEndHour, int eventTimeEndMinute) {
        this.eventTimeEndHour = eventTimeEndHour;
        this.eventTimeEndMinute = eventTimeEndMinute;
    }

    public void setDate(int year, int month, int day) {
        this.eventDateYear = year;
        this.eventDateMonth = month;
        this.eventDateDay = day;
    }
}
