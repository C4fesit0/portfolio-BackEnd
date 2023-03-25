package com.backend.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.backend.dto.LoginDto;
import com.backend.model.User;
import com.backend.repository.UserRepository;
import com.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
//@CrossOrigin(origins = "http://localhost:4200/")
@CrossOrigin(origins = "https://port-front.firebaseapp.com/")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public boolean login(@RequestBody LoginDto data){
        boolean existe=false;
        System.out.println(data.getEmail()+"=="+data.getPassword());
        if(data.getEmail()!= null && data.getPassword()!=null){
        existe =  userRepository.existsByEmailAndPassword(data.getEmail(), data.getPassword());
         System.out.println(existe);
     }
     return existe;
    }

    @PostMapping("/crear")
    public void crearUsuario(@RequestBody User data){
        if(data.getEmail()!= null && data.getPassword()!=null){
            User usuario= new User(data.getEmail(), data.getPassword(),data.getAdmin());
            userService.createUser(usuario);
            System.out.println(usuario.getEmail());
        }

    }

    @PutMapping("/actualizar/{id}")
    public User actualizarDatos(@PathVariable("id")Long id,@RequestBody LoginDto data){
        User user = userService.getUser(id);
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        userService.updateUser(user);
        return user;
    }
}

