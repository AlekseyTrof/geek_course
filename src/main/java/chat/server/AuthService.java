package chat.server;

import java.sql.SQLException;
import java.util.Optional;

public interface AuthService {
    /**
     * Запустить сервис
     */
    void start();

    /**
     * Отключить сервис.
     */
    void stop();

    /**
     * Получить никнейм по логину/паролю
     * @param login
     * @param pass
     * @return никнейм если найден
     */
    Optional<String> getNickByLoginAndPass(String login, String pass);
}
