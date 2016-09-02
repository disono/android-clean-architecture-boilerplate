package disono.webmons.com.clean_architecture.presentation.converters;

import java.util.HashMap;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/31/2016 10:28 AM
 */
public class Inputs {
    HashMap<String, Object> pairedParams;

    /**
     * Initialize inputs
     */
    public Inputs() {
        pairedParams = new HashMap<>();
    }

    /**
     * Get input
     *
     * @return
     */
    public Object setInput(String key, Object object) {
        return pairedParams.put(key, object);
    }

    /**
     * Get input
     *
     * @param key
     * @return
     */
    public Object getInput(String key) {
        return this.pairedParams.get(key);
    }
}
