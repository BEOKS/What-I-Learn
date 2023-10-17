package com.beoks.example.thymeleaf;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data","hello spring!");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data","<b>hello spring!</b>");
        return "basic/text-unescaped";
    }


    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);
        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);
        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);
        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);
        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }
    @Service("hello")
    static class helloBean implements hello{
        public String hello(String data) {
            return "Hello " + data;
        }
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }
    interface hello{
        public String hello(String data);
    }

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping("/attribute")
    public String attribute(){
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model){
        addUsers(model);
        return "basic/each";
    }

    @GetMapping("/condition")
    public String condition(Model model){
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/block")
    public String block(Model model){
        addUsers(model);
        return "basic/block";
    }

    private void addUsers(Model model){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("userA",10));
        users.add(new User("userB",20));
        users.add(new User("userC",30));
        model.addAttribute("users",users);
    }
    @Data
    @AllArgsConstructor
    static class User{
        private String username;
        private int age;
    }
}
