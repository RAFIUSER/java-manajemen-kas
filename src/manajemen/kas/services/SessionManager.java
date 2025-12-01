package manajemen.kas.services;

import manajemen.kas.model.User;

/**
 *
 * @author ASPIRESS
 */
public class SessionManager {
    
    private static SessionManager instance;
    
    private User loggedInUser; 

    private SessionManager() {
    }

    /**
     * Metode untuk mendapatkan instance tunggal dari SessionManager
     * @return 
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    /**
     * @param user object
     * Menyimpan data user setelah login berhasil.
     */
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    /**
     * @return Logged In User Data aObject.
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Clear Session Logged User
     */
    public void clearSession() {
        this.loggedInUser = null;
    }
}
