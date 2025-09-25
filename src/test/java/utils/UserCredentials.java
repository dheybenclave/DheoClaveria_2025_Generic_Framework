package utils;

public enum UserCredentials {

    //WEB APPLICATION CREDENTIALS////////////////////////////////////////////////////////////////////////////////////////////
    Customer("0466 134 574", "AWqasde321"),
    Admin("Admin", "Admin"),
    ProDinnerAdmin("o", "1");

    public final String _username, _password;

    // getter method
    public String getUsername() {
        return this._username;
    }

    public String getPassword() {
        return this._password;
    }

    // enum constructor - cannot be public or protected
    UserCredentials(String username, String password) {
        this._username = username;
        this._password = password;
    }

}