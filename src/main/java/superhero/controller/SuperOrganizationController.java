/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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
import superhero.dao.LocationDao;
import superhero.dao.SuperDao;
import superhero.dao.SuperOrganizationDao;
import superhero.model.Location;
import superhero.model.Super;
import superhero.model.SuperOrganization;

/**
 *
 * @author ciruf
 */
@Controller
@RequestMapping("/super-organizations")
public class SuperOrganizationController {
    Set<ConstraintViolation<SuperOrganization>> violations = new HashSet<>();
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    SuperDao superDao;
    
    @Autowired
    SuperOrganizationDao organizationDao;
    
    
    @GetMapping
    public String getSuperOrganizations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        List<Super> superPeople = superDao.getAllSupers();
        List<SuperOrganization> organizations =  organizationDao.getAllSuperOrganizations();
        model.addAttribute("locations", locations);
        model.addAttribute("superPeople", superPeople);
        model.addAttribute("organizations", organizations);
        model.addAttribute("errors", violations);
        return "Organization";
    }
    
    @PostMapping
    public String createSuperOrganization(SuperOrganization organization, HttpServletRequest request) {
        final Location location = locationDao.getLocationById(Integer.parseInt(request.getParameter("locationId")));
        final String[] SuperMemberIds = request.getParameterValues("superMemberIds");
        final List<Super> superMembers = new ArrayList<>();
        
        if (SuperMemberIds != null) {
            for (String memberId: SuperMemberIds) {
                final Super currentMember = superDao.getSuperById(Integer.parseInt(memberId));
                superMembers.add(currentMember);
         }
        }
        
        organization.setLocation(location);
        organization.setSupers(superMembers);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);
       
       if (violations.isEmpty()) {
           organizationDao.addSuperOrganization(organization);
       }
        return "redirect:/super-organizations";
    }
    
    @GetMapping("/details")
    public String getSuperOrganizationDetails(Integer id, Model model) {
        final SuperOrganization organization = organizationDao.getSuperOrganizationById(id);
        model.addAttribute("organization", organization);
        return "OrganizationDetails";
    }
    
    @GetMapping("/edit")
    public String updateSuperOrganization(Integer id, Model model) {
        final SuperOrganization organization = organizationDao.getSuperOrganizationById(id);
        List<Integer> superMemberIds = null;
        if (organization.getSuperMembers() != null) {
            superMemberIds = returnSuperMemberIdList(organization);
        }
        List<Location> locations = locationDao.getAllLocations();
        List<Super> superPeople = superDao.getAllSupers();
        model.addAttribute("locations", locations);
        model.addAttribute("superPeople", superPeople);
        model.addAttribute("errors", violations);
        model.addAttribute("superMemberIds", superMemberIds);
        model.addAttribute("organization", organization);
        return "EditOrganization";
    }
    
    @PostMapping("/edit")
    public String updateSuperOrganization(SuperOrganization organization, HttpServletRequest request, Model model) {
        List<Location> locations = locationDao.getAllLocations();
        List<Super> superPeople = superDao.getAllSupers();
        final Location location = locationDao.getLocationById(Integer.parseInt(request.getParameter("locationId")));
        final String[] superMemberIds = request.getParameterValues("superMemberIds");
        final List<Super> superMembers = new ArrayList<>();
        
        if (superMemberIds != null) {
            for (String memberId: superMemberIds) {
                final Super currentMember = superDao.getSuperById(Integer.parseInt(memberId));
                superMembers.add(currentMember);
         }
        }
        
        organization.setLocation(location);
        organization.setSupers(superMembers);
        model.addAttribute("locations", locations);
        model.addAttribute("superPeople", superPeople);
        model.addAttribute("errors", violations);
        model.addAttribute("superMemberIds", superMemberIds);
        model.addAttribute("organization", organization);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);
       
        if (!violations.isEmpty()) {  
           return "redirect:/super-organizations/edit?id=" + organization.getOrganizationId();
        }
        organizationDao.updateSuperOrganization(organization);
        return "redirect:/super-organizations";
    }
    
    @GetMapping("/delete")
    public String deleteSuperOrganization(int id) {
        organizationDao.deleteSuperOrganizationById(id);
        return "redirect:/super-organizations";
    }
    
    private List<Integer> returnSuperMemberIdList(SuperOrganization organizationWithMembers) {
        List<Integer> memberIdList = organizationWithMembers.getSuperMembers().stream().
                        map(member -> member.getSuperId())
                .collect(Collectors.toList());
        return memberIdList;
    }
    
//    private void loadTestOrganizationList() {
//        SuperOrganization testOrg1 = new SuperOrganization();
//        SuperOrganization testOrg2 = new SuperOrganization();
//        testOrg1.setOrganizationId(1);
//        testOrg2.setOrganizationId(2);
//        testOrg1.setOrganizationName("Test Org 1");
//        testOrg2.setOrganizationName("Test Org 2");
//        testOrg1.setOrganizationDescription("This is a org for testing");
//        testOrg2.setOrganizationDescription("This also a org for testing");
//        final Location testLocation = locationDao.getLocationById(1);
//        testOrg1.setLocation(testLocation);
//        final List<Super> supers = superDao.getAllSupers();
//        testOrg1.setSupers(supers);
//        organizations.put(1, testOrg1);
//        organizations.put(2, testOrg2);
//    }
    
//    private SuperOrganization getTestOrganization(int id) {
//        final SuperOrganization organization = organizations.get(id);
//        return organization;
//    }
    
}
