package accident.control;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import accident.service.AccidentService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class AccidentControl {

    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.getAllTypes());
        model.addAttribute("rules", accidentService.getAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Set<Rule> rsl = new LinkedHashSet<>();
        for(String el : ids) {
            rsl.add(accidentService.findByIdRule(Integer.parseInt(el)));
        }
        accident.setRules(rsl);
        String[] accidentType = req.getParameterValues("type.id");
        accidentService.setAccidentType(accident,Integer.parseInt(accidentType[0]));
        accidentService.addAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("types", accidentService.getAllTypes());
        model.addAttribute("rules", accidentService.getAllRules());
        return "accident/edit";
    }
}
