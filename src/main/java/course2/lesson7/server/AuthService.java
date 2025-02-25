package course2.lesson7.server;

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
     * @return никнейм если найден или null, если такого нет
     */
    String getNickByLoginAndPass(String login, String pass);
}
