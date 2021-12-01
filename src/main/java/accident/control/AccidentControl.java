package accident.control;

import accident.model.Accident;
import accident.service.AccidentService;
import accident.store.AccidentHibernate;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rules", accidentService.getAllRule());
        model.addAttribute("types", accidentService.getAllAccidentType());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        String[] idT = req.getParameterValues("type.id");
        accidentService.addAccident(accident,ids, idT);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidentService.getAllAccidentType());
        model.addAttribute("rules", accidentService.getAllRule());
        model.addAttribute("accident", accidentService.findAccidentById(id));
        return "accident/edit";
    }
}
