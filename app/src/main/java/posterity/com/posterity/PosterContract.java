package posterity.com.posterity;

public final class PosterContract {

    public PosterContract() {

    }

    public static abstract class Posters {
        public static final String TABLE_NAME = "posters";
        public static final String COLUMN_NAME_IMAGE_NAME = "imagename";
        public static final String COLUMN_NAME_EVENT_TITLE = "eventtitle";
        public static final String COLUMN_NAME_EVENT_TIME_START_HOUR = "eventtimestarthour";
        public static final String COLUMN_NAME_EVENT_TIME_START_MINUTE = "eventtimestartminute";
        public static final String COLUMN_NAME_EVENT_TIME_END_HOUR = "eventtimeendhour";
        public static final String COLUMN_NAME_EVENT_TIME_END_MINUTE = "eventtimeendminute";
        public static final String COLUMN_NAME_EVENT_DATE_YEAR = "eventdateyear";
        public static final String COLUMN_NAME_EVENT_DATE_MONTH = "eventdatemonth";
        public static final String COLUMN_NAME_EVENT_DATE_DAY = "eventdateday";
        public static final String COLUMN_NAME_EVENT_LOCATION = "eventlocation";
    }
}