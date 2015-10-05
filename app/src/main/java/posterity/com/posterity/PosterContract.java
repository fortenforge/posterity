package posterity.com.posterity;

public final class PosterContract {

    public PosterContract() {

    }

    public static abstract class Posters {
        public static final String TABLE_NAME = "posters";
        public static final String COLUMN_NAME_IMAGE_NAME = "imagename";
        public static final String COLUMN_NAME_EVENT_TITLE = "eventtitle";
        public static final String COLUMN_NAME_EVENT_DATE = "eventdate";
        public static final String COLUMN_NAME_EVENT_TIME = "eventtime";
        public static final String COLUMN_NAME_EVENT_LOC = "eventloc";
    }
}
