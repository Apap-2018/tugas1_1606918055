package apap.tugas.tugas1.controller;

import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Component
public class TestController {

    @Autowired
    @Qualifier(value = "JabatanServiceImplV1")
    private JabatanService mBaseService;

    @RequestMapping("/test")
    public String findCities() {

        return "pages/SearchPegawai";
    }
}