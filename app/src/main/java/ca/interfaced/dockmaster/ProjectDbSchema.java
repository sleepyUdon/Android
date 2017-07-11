package ca.interfaced.dockmaster;

/**
 * Created by vivianechan on 2017-07-03.
 */

public class ProjectDbSchema {
    public static final class ProjectTable {
        public static final String NAME = "projects";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String PROJECTNAME = "projectname";
            public static final String PROJECTADDRESS = "projectaddress";
            public static final String PROJECTBOOKINGDATE = "projectbookingdate";
        }
    }
}
