package disono.webmons.com.clean_architecture.domain.model;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-12 11:26 AM
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
