package data_access.SignUp;

import entity.SignUp.User;

public interface UserSignupDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
