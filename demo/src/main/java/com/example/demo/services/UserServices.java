package com.example.demo.services;
import org.springframework.stereotype.Service;
import com.example.demo.models.requests.UserCreationRequest;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServices {

    private static final Logger logger = LoggerFactory.getLogger(UserServices.class);
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(UserCreationRequest userCreationRequest){
        return userRepository.save(mapToUser(userCreationRequest));
    }

    /*
     *  public void removeUser(Long id){
        userRepository.deleteById(id);
        }
     */

    public boolean removeUser(Long id){
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error("Error a la hora de eliminar un usuario de la BD", e);
            return false;
        }
    }

    public Optional<User> getUser(final long id){
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error recuperando el usuario con id {}, Exception {}", id,e);
        }
        return null;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User mapToUser(UserCreationRequest userCreationRequest){
        User user = new User();
        user.setNombre(userCreationRequest.nombre());
        user.setContrasena(userCreationRequest.contrasena());
        user.setEdad(userCreationRequest.edad());
        user.setAdministrador(userCreationRequest.administrador());
        return user;
    }
}
