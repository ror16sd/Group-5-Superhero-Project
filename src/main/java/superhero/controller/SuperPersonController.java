/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import superhero.dao.PowerDao;
import superhero.dao.SuperDao;
import superhero.model.Power;
import superhero.model.Super;

/**
 *
 * @author ciruf
 */
@Controller
@RequestMapping("/super-people")
public class SuperPersonController {
   Set<ConstraintViolation<Super>> violations = new HashSet<>();
   @Autowired
   SuperDao superDao;
   
   @Autowired
   PowerDao powerDao;
   
   @GetMapping
    public String getSuperPeople(Model model) {
        List<Super> supers = superDao.getAllSupers();
        List<Power> powers = powerDao.getAllPowers();
        model.addAttribute("powers", powers);
        model.addAttribute("superPeople", supers);
        model.addAttribute("errors", violations);
        return "Who";
    }
    
    @PostMapping
    public String createSuperPerson(Super superPerson, HttpServletRequest request) {
        final boolean isHero = Boolean.parseBoolean(request.getParameter("isHero"));
        final Power power = powerDao.getPowerById(Integer.parseInt(request.getParameter("powerId")));
        superPerson.setIsSuper(isHero);
        superPerson.setPower(power);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superPerson);
       
       if (violations.isEmpty()) {
           superDao.addSuper(superPerson);
       }
        
       return "redirect:/super-people";
    }
    
    @PostMapping("edit")
    public String editSuperPerson(Super superPerson, HttpServletRequest request, Model model) {

        final boolean isHero = Boolean.parseBoolean(request.getParameter("isHero"));
        final Power power = powerDao.getPowerById(Integer.parseInt(request.getParameter("powerId")));
        superPerson.setIsSuper(isHero);
        superPerson.setPower(power);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superPerson);
       
       if (!violations.isEmpty()) {
           return "redirect:/super-people/edit?id=" + superPerson.getSuperId();
       }
        superDao.updateSuper(superPerson);
        return "redirect:/super-people";
    }
    
    @GetMapping("edit")
    public String updateSuperPerson(Integer id, Model model) {
        Super superPerson = superDao.getSuperById(id);
        final List<Power> powers = powerDao.getAllPowers();
        model.addAttribute("superPerson", superPerson);
        model.addAttribute("powers", powers);
        model.addAttribute("errors", violations);
        return "EditSuperPerson";
    }
    
    @GetMapping("delete")
    public String deleteSuperPerson(Integer id) {
        superDao.deleteSuperById(id);
        return "redirect:/super-people";
    }

}
