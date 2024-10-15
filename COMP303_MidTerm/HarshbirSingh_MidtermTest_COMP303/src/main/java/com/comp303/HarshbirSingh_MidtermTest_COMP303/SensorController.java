package com.comp303.HarshbirSingh_MidtermTest_COMP303;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SensorController {

    @Autowired
    private SensorRepository sensorRepository;

    @RequestMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/show")
    public String showAllSensors(Model model) {
        List<Sensor> sensors = sensorRepository.findAll();
        model.addAttribute("sensor", sensors);
        return "show";
    }

    @GetMapping("/add")
    public String addSensorForm() {
        return "add";
    }

    @PostMapping("/add")
    public String addSensor(@RequestParam("sensorName") String sensorName,
                            @RequestParam("sensorType") String sensorType,
                            @RequestParam("sensorPin") int sensorPin,
                            @RequestParam("sensorLocation") String sensorLocation,
                            @RequestParam("sensorStatus") String sensorStatus) {
        Sensor sensor = new Sensor();
        sensor.setSensorName(sensorName);
        sensor.setSensorType(sensorType);
        sensor.setSensorPin(sensorPin);
        sensor.setSensorLocation(sensorLocation);
        sensor.setSensorStatus(sensorStatus);

        sensorRepository.save(sensor);

        return "redirect:/show";
    }

    @GetMapping("/count")
    public String countSensors(Model model) {
        long count = sensorRepository.count();
        model.addAttribute("count", count);
        return "count";
    }
}
