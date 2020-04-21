package pl.conquerors.app.domain.repository;

import pl.conquerors.app.domain.model.User;

public interface UserRepository {

    User loginUser (String email, String password);

    User registerUser (String name, String email, String password, String born);
}
