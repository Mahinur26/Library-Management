import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;

public class User {
    private final String Username;
    private final String hashedPassword;
    private final byte[] salt;
    //User constructor which will hold then Username and Password for each User object
    //Takes username and plaintext password
    public User(String Username, String Password) {
        this.Username = Username;
        //Generates a unique salt
        this.salt = generateRandomSalt();
        //Hashes the input password with the randomly generated salt
        this.hashedPassword = hashPassword(Password, this.salt);
    }

    //Returns the Username
    public String getUsername(){
        return Username;
    }

    //Instantiates the users arraylist with usernames and passwords the client asked to already have in the system
    //The arraylist is declared in the User class so that it can be used in all classes under
    public static ArrayList<User> users = new ArrayList<User>(){{
        add(new User("John", "Smith1968"));
        add(new User("Steve", "securePAswORd"));
        add(new User("Sarah", "ae$gdhof0"));
        add(new User("a", "a"));
    }};

    //Method to generate a random salt
    //The salt ensures that the same password doesn't have the same hash, making it seem like all the passwords are unique
    //Method is static because it's also used in the CreateNewLogin class for hashing the ACCESS_KEY
    public static byte[] generateRandomSalt() {
        //The salt is an array of 16 bytes
        byte[] salt = new byte[16];
        //A SecureRandom object is created to get a secure random value for the salt
        SecureRandom random = new SecureRandom();
        //Each index in the salt array is filled with a random byte
        random.nextBytes(salt);
        //The random salt is returned
        return salt;
    }

    //Method to hash a password using PBKDF2 (Password Based Key Derivation Function) algorithm
    //The password parameter is what is being hashed and a salt is used to make the hashed password seem more random
    //Also used in the CreateNewLogin class for hashing the ACCESS_KEY
    public static String hashPassword(String password, byte[] salt) {
        try {
            //Higher number of PBKDF2 iterations ensures that the encryption is harder to brute force
            int iterations = 10000;
            //32 byte key length
            int keyLength = 256;
            //Converts the inputted key/password into a character array and combines it with the salt
            //The key will be 256 bits long after being hashed
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
            //Uses PBKDF2WithHmacSHA256 algorithm to derive a key
            //SHA-256 (Secure Hash Algorithm 256-bit) is the hashing algorithm used to hash the password
            //HMAC (Hash-based Message Authentication Code) ensures that the data hasn't been altered by an unauthorized person
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            //Generates the hashed key as a SecretKey using the PBEKeySpec specification
            byte[] hash = keyFactory.generateSecret(spec).getEncoded();
            //Encodes the hash as a Base64 string
            return Base64.getEncoder().encodeToString(hash);
            //Error catcher
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    //Method used to check if the user inputted password is correct
    //Hashes the input password and compare it to the stored hash and salt of the corresponding User object
    public boolean checkPassword(String inputPassword) {
        //Hashes the input with the stored salt of the matching User object
        String hashedInput = hashPassword(inputPassword, this.salt);
        //Compares the hashed inputPassword to the hashedPassword of the User object
        return hashedInput.equals(this.hashedPassword);
    }
}