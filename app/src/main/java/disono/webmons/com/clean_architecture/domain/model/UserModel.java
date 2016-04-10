package disono.webmons.com.clean_architecture.domain.model;

/**
 * User model
 */
public class UserModel {
    private int _id;

    public UserModel(int id) {
        _id = id;
    }

    public int getId() {
        return _id;
    }
}
