package disono.webmons.com.clean_architecture.domain.repository;

import disono.webmons.com.clean_architecture.domain.model.UserModel;

/**
 * A user repository with CRUD operations on a model.
 */
public interface Repository {
    boolean insert(UserModel model);

    boolean update(UserModel model);

    UserModel get(Object id);

    boolean delete(UserModel model);
}
