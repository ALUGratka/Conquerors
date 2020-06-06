package pl.conquerors.app.model.mapper;

import pl.conquerors.app.domain.model.UserRelationship;
import pl.conquerors.app.model.UserRelationshipEntity;

public class UserRelationshipMapper {

    public static UserRelationship transform (UserRelationshipEntity userEntity) {
        UserRelationship userRelationShip = null;
        if(userEntity!=null){
            userRelationShip = new UserRelationship();
            userRelationShip.setUser1Id(userEntity.getUser1Id());
            userRelationShip.setUser2Id(userEntity.getUser2Id());
            userRelationShip.setCanDelete(userEntity.getCanDelete());
            userRelationShip.setCanAccept(userEntity.getCanAccept());
            userRelationShip.setCanInvite(userEntity.getCanInvite());
            userRelationShip.setCanReject(userEntity.getCanReject());
            userRelationShip.setCanUninvite(userEntity.getCanUninvit());
        }
        return userRelationShip;
    }
}
