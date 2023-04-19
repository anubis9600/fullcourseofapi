package tech.anubislab.fullcourseofapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.anubislab.fullcourseofapi.model.User;

@RestController
public class HomeController {
    
    @RequestMapping("/")
    public String home(){ return "Welcome Home"; }

    // @RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping("/user")
    public User user(){
        User user = new User();
        user.setId("1");
        user.setName("Asyst ressources");
        user.setEmailId("asyst@asystressources.com");

        return user;
    }

    @GetMapping("/{id}/{id2}")
    public String pathVariable(@PathVariable String id, @PathVariable("id2") String name){
        return "L Identifiant trouvee: "+id + " : "+name;
    }
    
    @GetMapping("/requestparam")
    public String requestParam(@RequestParam String name, 
                                @RequestParam(required = false, defaultValue = "") Integer age){
        return "Votre nom est "+name+" et votre age "+age;
    }

}
