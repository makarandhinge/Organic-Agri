package com.scm.controller;

import com.scm.entity.User;
import com.scm.form.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    @Autowired
   private UserService userService;


    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Homepage handler");
        model.addAttribute("name", "SubString");
        model.addAttribute("youtubeChannel","MakarandHinge");
        return "home";
    }
    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("About page loader");
        return "about";
    }
    @RequestMapping("/service")
    public String servicePage(){
        System.out.println("Service page loader");
        return "service";
    }

    @RequestMapping("/register")
    public String registerPage(Model model){
        System.out.println("Register page loader");
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @RequestMapping("/login")
    public String loginPage(){
        System.out.println("Login page loader");
        return "login";
    }
    @RequestMapping("/disease_identifier")
    public String diseaseIdentifier(){
        System.out.println("Disease Identifier page loader");
        return "disease_identifier";
    }
    @RequestMapping("/market")
    public String market(){
        System.out.println("Market page loader");
        return "market";
    }
    @RequestMapping("/farming")
    public String farming(){
        System.out.println("Farming page loader");
        return "redirect:/category";
    }

    @RequestMapping("/contact")
    public String contactPage(){
        System.out.println("Contact page loader");
        return "contact";
    }
    @RequestMapping(value = "/do-register",method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        System.out.println("Processing Register ");
//        fetch the data
//        userForm
        System.out.println(userForm);
//        validate form data
        if(rBindingResult.hasErrors()){
            return "register";
        }
    

//        save to database
       /*

        User user = User.builder()
                .name(userForm.getName())
                .password(userForm.getPassword())
                .email(userForm.getEmail())
                .phoneNumber(userForm.getPhoneNumber())
                .build();

        */
        User user = new User();
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setPhoneNumber(userForm.getPhoneNumber());

        User savedUser = userService.saveUser(user);
        System.out.println("user saved");
//        message = "Registration Successful"
        Message message = Message.builder().content("Registered Successfully").type(MessageType.blue).build();
        session.setAttribute("message",message);
//        Redirected to login Page
        return "redirect:/register";
    }




}
