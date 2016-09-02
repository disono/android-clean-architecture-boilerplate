package disono.webmons.com.clean_architecture.domain.model;

import com.orm.SugarRecord;

import java.util.List;

import disono.webmons.com.clean_architecture.domain.repository.MeRepository;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 */
public class MeModel extends SugarRecord implements MeRepository {
    public int primary_id = 0;

    public String first_name = null;
    public String middle_name = null;
    public String last_name = null;
    public String full_name = null;
    public String avatar = null;

    public String gender = null;
    public String address = null;
    public String phone = null;
    public String birthday = null;

    public String role = null;
    public String email = null;
    public String username = null;

    public int age = 0;

    // Default constructor is necessary for SugarRecord
    public MeModel() {

    }

    private List<MeModel> init() {
        return MeModel.listAll(MeModel.class);
    }

    @Override
    public MeModel single() {
        if (!this.check()) {
            return new MeModel();
        }

        return this.init().get(0);
    }

    @Override
    public MeModel update(int id) {
        return MeModel.find(MeModel.class, "primaryId = ?", Integer.toString(id)).get(0);
    }

    @Override
    public void delete(int id) {
        MeModel.findById(MeModel.class, id).delete();
    }

    @Override
    public void clear() {
        MeModel.deleteAll(MeModel.class);
    }

    @Override
    public boolean check() {
        int count = this.init().size();
        return (count > 0);
    }
}
