package disono.webmons.com.clean_architecture.domain.repositories;

import disono.webmons.com.clean_architecture.domain.models.MeModel;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 * <p>
 * A user repository with CRUD operations on a model.
 */
public interface MeRepository {
    MeModel single();

    MeModel update(int id);

    void clear();

    boolean check();
}