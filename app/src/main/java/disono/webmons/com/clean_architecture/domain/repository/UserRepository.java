package disono.webmons.com.clean_architecture.domain.repository;

import disono.webmons.com.clean_architecture.domain.model.UserModel;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-12 11:26 AM
 *
 * A user repository with CRUD operations on a model.
 */
public interface UserRepository {
    boolean insert(UserModel model);

    boolean update(UserModel model);

    boolean delete(UserModel model);

    UserModel get(Object id);
}
