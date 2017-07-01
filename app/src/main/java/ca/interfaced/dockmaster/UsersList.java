package ca.interfaced.dockmaster;

import android.content.Context;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class UsersList {

    private static UsersList sUsersList;

    public static UsersList get(Context context) {
        if (sUsersList == null) {
            sUsersList = new UsersList(context);
        }
        return sUsersList;
    }

    private UsersList(Context context) {

    }
}
