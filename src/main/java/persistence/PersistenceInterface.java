package persistence;

import java.io.IOException;
import model.Credential;
import model.User;
import model.WaterReport;

/**
 *
 * @author tybrown
 */
public interface PersistenceInterface {

    /**
     * Sets up the persistence layer (connect to DB, create/lock files, etc)
     * @throws IOException If there was a problem setting up the interface
     */
    void initialize() throws IOException;

    /**
     * Shuts down the persistence layer (disconnect from DB, close files, etc)
     */
    void terminate();

    /**
     * Saves user to underlying persistence implementer
     * If user already exists, update all fields
     * @param u The user to save/update
     */
    void saveUser(User u);

    /**
     * Queries the underlying persistence layer for validation of the given username/password
     * If authentication was successful, subsequent calls to isUserAuthenticated() must return true
     * @param c The credential to validate
     * @return true if the credential matches the persistence layer
     */
    boolean authenticateUser(Credential c);

    /**
     * Logs out the given user
     * @param username The username to logout 
     */
    void deauthenticateUser(String username);

    /**
     * Checks if a given user is currently authenticated (logged in)
     * @param username The username of the user to check
     * @return true if the user is currently authenticated (logged in)
     */
    boolean isUserAuthenticated(String username);

    /**
     * Saves credential to persistence layer.
     * If user already exists, update password
     * @param c The credential to save
     */
    void saveUserCredential(Credential c);

    /**
     * Checks the persistence layer to see if a user exists, and has a password
     * @param username The username of the user to check
     * @return true if the user exists, and has a password
     */
    boolean userExists(String username);

    /**
     * Deletes this user from the underlying persistence implementer
     * @param username The username of the user to delete
     */
    void deleteUser(String username);

    /**
     * Deletes this user from the underlying persistence implementer
     * @param u The User object of the user to delete
     */
    void deleteUser(User u);

    /**
     * Saves a water report to the underlying persistence implementer
     * @param wr The water report to save
     */
    void saveWaterReport(WaterReport wr);

    /**
     * Deletes the given water report and all its child reports from the underlying persistence layer
     * @param wr The water report to delete
     */
    void deleteWaterReport(WaterReport wr);

    /**
     * Saves a given quality report in the given water report in the underlying persistence layer
     * @param wr The water report to save this quality report in
     *
     */
    void saveQualityReport(WaterReport wr);

    /**
     * Deletes a given quality report in the given water report from the underlying persistence layer
     * @param wr The water report to delete this quality report from
     *
     */
    void deleteQualityReport(WaterReport wr);

}
