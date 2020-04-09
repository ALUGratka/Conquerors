package pl.conquerors.app.util;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.domain.model.User;

public class UserUtil {

    static List<User> users = new ArrayList<>();

    public static List<User> addUser(User user){
        users.add(user);
        return users;
    }

    public static boolean findIfUserWithIdExists( final int userId) {
        if(getUsersIds().contains(userId)) return true;
        return false;
    }

    public static boolean findIfUserWithEmailExists( final String email) {
        if(users == null) return false;

        for (User user: users) {
            if(user.getEmail().equals(email)) return true;
        }
        return false;
    }

    public static List<Integer> getUsersIds(){
        if(users == null) return null;

        List<Integer> userIds = new ArrayList<>();
        for (User user: users) userIds.add((int) user.getId());
        return userIds;
    }

    public static int getNumberOfUsers(){
        return users.size();
    }

    public static User checkIfUserLoginCorrect(String nick, String password){
        for(User user: users){
            if(user.getNick().equals(nick)){
                return user;
            }
        }
        return null;
    }
}
