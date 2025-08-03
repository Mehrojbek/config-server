package uz.dtc.configclient1;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 Created by: Mehrojbek
 DateTime: 03/08/25 12:44
 **/
@RestController
@RequestMapping("/api/env")
public class EnvController {
    private final Environment env;

    public EnvController(Environment env) {
        this.env = env;
    }

    @GetMapping("/{name}")
    public String env(@PathVariable String name){
        return env.getProperty(name);
    }

}
