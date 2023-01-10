package springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springcourse.dao.PersonDAO;
import springcourse.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String index(Model model){
        //get all people from DAO and redirect it to representation
        model.addAttribute("people",personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id,Model model){
        //get one person by its id from DAO and redirect ot to representation
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")Person person){
        //model.addAttribute("person",new Person());
        return ("people/new");
    }

    @PostMapping("")
    public String create(@ModelAttribute("person")Person person){
        personDAO.save(person);
        return "redirect:/people";
    }
}
