package superhero.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import superhero.dao.SightingDao;
import superhero.model.Sighting;

@Controller
@RequestMapping("/")
public class IndexController {
    
    @Autowired
    SightingDao sightingDao;
    
    @GetMapping
    public String getHomePage(Model model) {
        final List<Sighting> topTenSightings = sightingDao.getTop10Sightings();
        model.addAttribute("top10Sightings", topTenSightings);
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
