package pl.conquerors.app.model.mapper;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.model.UserEntity;

public class UserEntityMapper {

    public static User transform (UserEntity userEntity) {
        User user = null;
        if(userEntity!=null){
            user = new User();
            user.setUserId(userEntity.getUserId());
            user.setUserNick(userEntity.getUserName());
            user.setUserEmail(userEntity.getEmail());
            user.setUserPassword(userEntity.getPassword());
            user.setmBorn(userEntity.getBorn());
        }
        return user;
    }

    public static List<User> transform (List<UserEntity> userEntities) {
        final List<User> users = new ArrayList<>();
        for(UserEntity userEntity : userEntities){
            users.add(transform(userEntity));
        }
        return users;
    }
}
