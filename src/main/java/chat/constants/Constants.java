package chat.constants;

public class Constants {
    /**
     * Адрес сервера.
     */
    public static final String SERVER_ADDRESS = "localhost";
    /**
     * Порт сервера.
     */
    public static final int SERVER_PORT = 8189;


    /**
     * Команда конца связи.
     */
    public static final String END_COMMAND = "/end";

    public static final String AUTH_COMMAND = "/auth";

    public static final String AUTH_OK_COMMAND = "/authok";

    public static final String SEND_USER = "/w";

    public static final String CLIENTS_LIST_COMMAND = "/clients";

    public static final String ADD_CLIENT_TO_BASE = "/addC";

    public static final String DEL_CLIENT_FROM_BASE = "/delC";

    public static final String SET_LOGIN = "/setLog";

    public static final String CLEAR_BASE = "/clearBD";

    public static final String DROP_BASE = "/dropBD";

    public static final String CREATE_TABLE = "/createT";
}
