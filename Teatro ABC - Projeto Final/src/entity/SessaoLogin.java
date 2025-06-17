package entity;


public class SessaoLogin {
    private static String login;

    public static void setLogin(String loginUsuario) {
        login = loginUsuario;
    }

    public static String getLogin() {
        return login;
    }
}
