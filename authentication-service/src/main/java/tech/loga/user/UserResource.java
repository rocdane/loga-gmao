package tech.loga.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserResource {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public UserResource(PasswordEncoder passwordEncoder,
                        UserRepository userRepository,
                        UserDTOMapper userDTOMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public User register(UserRegisterRequest registerRequest) {

        boolean userExist = userRepository
                .findByUsernameIgnoreCase(registerRequest.getUsername())
                .isPresent();

        if(userExist){
            throw new UserAlreadyExistsException("User already exist");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());

        return userRepository.save(user);
    }

    public User find(String username){
        if(userRepository.findByUsernameIgnoreCase(username).isPresent()){
            return userRepository.findByUsernameIgnoreCase(username).get();
        }else {
            throw new UserNotFoundException("User does not exists");
        }
    }

    public List<UserDTO> allUser(){
        return userRepository
                .findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(UserUpdateRequest updateRequest, Long id){
        userRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setUsername(updateRequest.getUsername());
                    up.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
                    up.setRole(updateRequest.getRole());
                    up.setActive(updateRequest.getActive());
                    userRepository.saveAndFlush(up);
                },() -> {
                    throw new UserNotFoundException("Fail to update not exists user.");
                });
    }

    public void delete(Long id) {
        userRepository.findById(id).ifPresentOrElse(user -> {
            userRepository.findById(user.getId());
        },() -> {
            throw new UserNotFoundException("Fail to delete not exists user.");
        });
    }
}
