package ca.interfaced.dockmaster.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class UsersList {

    private static UsersList sUsersList;
    private final List<User> mUsers;

    public static UsersList get(Context context) {
        if (sUsersList == null) {
            sUsersList = new UsersList(context);
        }
        return sUsersList;
    }

    private UsersList(Context context) {
        mUsers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setFirstName("First Name #" + i);
            user.setLastName("Last Name #" + i);
            user.setCompanyName("Company Name #" + i);
            user.setPhoneNumber("Phone Number #" + i);
            user.setPassword("Password #" + i);
            user.setEmail("Email #" + i);

            mUsers.add(user);
        }
    }

    public List<User> getUsers(){
        return mUsers;
    }

    public User getUser(UUID id) {
        for (User user : mUsers) {
            if (user.getID().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
