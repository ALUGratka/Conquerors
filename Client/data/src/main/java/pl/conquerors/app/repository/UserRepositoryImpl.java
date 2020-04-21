package pl.conquerors.app.repository;

import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.domain.repository.UserRepository;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.model.mapper.UserEntityMapper;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User loginUser(String email, String password) {
        return UserEntityMapper.transform(new UserEntity(email, password));
    }

    @Override
    public User registerUser(String name, String email, String password, String born) {
        return UserEntityMapper.transform(new UserEntity(email,name,password,born));
    }
}
