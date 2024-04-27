package tech.loga.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.loga.vendor.JwtService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserResource implements UserManagement{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final JwtService jwtService;

    @Autowired
    public UserResource(PasswordEncoder passwordEncoder,
                        UserRepository userRepository,
                        UserDTOMapper userDTOMapper,
                        JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.jwtService = jwtService;
    }

    public String registerUser(UserRegisterRequest registerRequest) {

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
        userRepository.save(user);
        return jwtService.generateToken(user.getUsername());
    }

    public String getUserByName(String username){
        if(userRepository.findByUsernameIgnoreCase(username).isPresent()){
            return jwtService.generateToken(username);
        }else {
            throw new UserNotFoundException("User does not exists");
        }
    }

    public List<UserDTO> getAllUser(){
        return userRepository
                .findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public void editUser(UserUpdateRequest updateRequest, Long id){
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

    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresentOrElse(user -> {
            userRepository.findById(user.getId());
        },() -> {
            throw new UserNotFoundException("Fail to delete not exists user.");
        });
    }
}
