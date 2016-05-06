package disono.webmons.com.clean_architecture.domain.repository;

/**
 * Author: Archie, Disono (disono.apd@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-04 09:46 PM
 */
public interface AuthRepository {
    boolean attempt(String email, String password);

    boolean login(int id);

    boolean check();

    boolean logout();

    String user();
}
