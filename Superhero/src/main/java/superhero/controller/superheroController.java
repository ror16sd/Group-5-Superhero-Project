package superhero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import superheroDao.LocationDao;
import superheroDao.PowerDao;
import superheroDao.SuperDao;
import superheroDao.SuperOrganizationDaoDb;
import superheroEntities.Location;
import superheroEntities.Power;
import superheroEntities.Super;
import superheroEntities.SuperOrganization;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class superheroController {

    @Autowired
    LocationDao locationDao;

    @Autowired
    PowerDao powerDao;

    @Autowired
    SuperOrganizationDaoDb superOrgDao;

    @Autowired
    SuperDao superDao;

    @GetMapping("supers")
    public String displaySupers(Model model){
        List<Super> supers = superDao.getAllSupers();
        List<Power> powers = powerDao.getAllPowers();
        List<SuperOrganization> superOrgs = superOrgDao.getAllSuperOrganizations();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("supers", supers);
        model.addAttribute("powers", powers);
        model.addAttribute("superOrgs", superOrgs);
        model.addAttribute("locations", locations);
        return "supers";
    }

    @PostMapping("addSuper")
    public String addSuper(Super superHero, HttpServletRequest request){
        String superId = request.getParameter("superId");
        String superOrgs = request.getParameter("superOrgs");
        String[] superIds = request.getParameterValues("superIds");

        superHero.setPower(powerDao.getPowerById(Integer.parseInt(superId)));
        return "redirect:/supers";


    }
}
