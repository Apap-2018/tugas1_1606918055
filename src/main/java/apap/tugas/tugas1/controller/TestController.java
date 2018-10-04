package apap.tugas.tugas1.controller;

import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private IService mService;

    @RequestMapping("/test")
    public String findCities(Model model) {

        List<Jabatan> jabatans = mService.getManager().findAll();

        model.addAttribute("jabatans", jabatans);

        return "test";
    }
}