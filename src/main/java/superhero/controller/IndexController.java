package superhero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String getHomePage() {
        return "SuperHeroHome";
    }
    
    @GetMapping("gallery")
    public String getGalleryPage() {
        return "Gallery";
    }
    
    @PostMapping("gallery")
    public String addGalleryPhoto() {
        return "NOT IMPLEMENTED: add gallery photo";
    }
}
