package com.yizheng.demo.controller;

import com.google.common.io.Files;
import com.yizheng.demo.DAO.UserRepository;
import com.yizheng.demo.domain.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class RegisterController {

    //private final ResourceLoader resourceLoader;

    //@Autowired
    //public RegisterController(ResourceLoader resourceLoader){
    //    this.resourceLoader = resourceLoader;
    //}

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String provideUploadInfo() {
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/register")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("username") String username) throws IOException {
        if (!file.isEmpty()) {
            User user = new User();
            String filename = UUID.randomUUID().toString() + ".jpg";
            System.out.println("----1");
            user.setUsername(username);
            try {
                Files.write(file.getBytes(),
                        new File("src\\main\\resources\\static\\avatar\\" + filename));
                System.out.println("----2");
                //user.setAvatarurl(ResponseEntity.ok(resourceLoader.getResource("file:" +" src\\main\\resources\\static\\avatar"+filename)).toString());
                user.setAvatarurl("/avatar/" + filename);
                userRepository.save(user);
                return "login";
            } catch (Exception e) {
                return "register";
            }
        }
        return "register";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("username") String username,
                              Model model){
        User user = userRepository.findByUsername(username);
            if (user != null){
                model.addAttribute(user);
                return "showavatar";
            }
        return "login";
    }
}
