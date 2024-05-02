package com.zosh.controller;

import com.zosh.config.JwtProvider;
import com.zosh.model.User;
import com.zosh.repository.UserRepository;
import com.zosh.request.LoginRequest;
import com.zosh.response.AuthResponse;
import com.zosh.service.CustomerUserDetailsService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    /**
     * Método para registrar un nuevo Usuario en la base de datos
     *
     * @param 'user' El Objeto 'Usuario' que contiene todos los datos que se van a almacenar en la base de datos.
     * @return Un token generado al persistir el Objeto el cual debe ser utilizado para poder acceder a
     * los demás Endpoints.
     *         Un mensaje indicando de se pudo realizar exitosamente la operación.
     * @throws Exception Si no pudo persistir el Objeto 'Usuario' en la base de datos.
     *
     * Ejemplo de uso:
     *
     * <pre>{@code
     * AuthResponse authResponse = AuthController.createUser(User);
     * }</pre>
     */
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        User isExist = userRepository.findByEmail(user.getEmail());
        if (isExist != null) {
            throw new Exception("this email already used with another account");
        }

        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        //newUser.setPassword(user.getPassword());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));//Encripta el password enviado por el usuario.
        newUser.setGender(user.getGender());
        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
                savedUser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse(token, "Register Success!");

        return response;
    }

    /**
     * Método para obtener información detallada de un usuario(Hacer login) por su email y password.
     *
     * @param loginRequest El Objeto 'LoginRequest' que contiene como información el email y password enviados por el usuario.
     * @return Un objeto AuthResponse que contiene el nuevo token generado para poder acceder a los otros Endpoints y
    un mesaje de operacion exitosa.
     *
     * Ejemplo de uso:
     *
     * <pre>{@code
     * LoginRequest loginRequest = AuthController.signin(loginRequest);
     * }</pre>
     */
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse(token, "Login Success");

        return response;
    }

    /**
     * Método para validar información proveída por un usuario.
     *
     * @param String email, String password
     * @return Un objeto de tipo Authentication.
     *
     */
    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("invalid username");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("password not matched");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}