package com.yizheng.demo;

import com.google.common.io.Files;
import com.yizheng.demo.DAO.UserRepository;
import com.yizheng.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Name;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class RegisterController {

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
            user.setAvataraddr(UUID.randomUUID().toString() + ".jpg");
            user.setUsername(username);
            System.out.println("啦啦啦");
            try {
                Files.write(file.getBytes(),
                        new File("F:\\IDEAworkplave\\yz.upload\\src\\main\\resources\\static\\avatar\\"
                                + user.getAvataraddr()));
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
