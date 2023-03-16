package demo.services;

import demo.models.User;
import demo.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public String saveUser(User user) throws IllegalArgumentException {


        if (getUserByEmail(user.getEmail()) != null) {

            throw new IllegalArgumentException ("User with email " +user.getEmail() + " already exists");

        }else if (getUserByUsername(user.getUsername())  != null) {

            throw new IllegalArgumentException ("User " +user.getUsername() + " already exists");

        }else{

            user.setPassword(passwordEncoder(user.getPassword()));

            userRepo.save(user);
            return "New user " + user.getUsername() + " saved to database";
        }
    }


    public String editUser(long id , User userEdited) throws IllegalArgumentException {


        if (getUserById(id) == null) {

            throw new IllegalArgumentException ("User Doesn't exist");

        }else{

            User user = getUserById(id);

            user.setUsername(userEdited.getUsername());
            user.setGender(userEdited.getGender());
            user.setPhone(userEdited.getPhone());
            user.setEmail(userEdited.getEmail());
            user.setPassword(userEdited.getPassword());

            userRepo.save(user);
            return "New user " + user.getUsername() + " saved to database";
        }
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(long id) {
        return userRepo.findById(id).get();
    }


    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }
    public User getUserByUsername(String email) {
        return userRepo.findUserByUsername(email);
    }

    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    public String passwordEncoder(String password){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        return encodedPassword;
    }

    @PostConstruct
    private void init() {
        try {

            User user = new User();
            user.setUsername("admin");
            user.setEmail("admin@sys.ma");
            user.setPassword("QIJsl7z9");

            user.setPhone("0600000000");
            saveUser(user);

            User client = new User();
            client.setUsername("client");
            client.setEmail("client@sys.ma");
            client.setPassword("QIJsl7z9");

            client.setPhone("0600000000");
            saveUser(client);
        }catch (Exception ex){
            log.error(ex.getMessage());
        }

    }

}
