package br.com.henriquepadua.javaspringvscodeexample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    
    @Value("${app.message}")
    private String appmessage;

    @Value("${ENV_DB_URL:NULA}")
    private String dbEnvironmentVariable;

    @GetMapping("/")
    public String getAppMessage(){
        return appmessage;
    }

    @GetMapping("/envVariable")
    public String getEnvironmentVariable(){
        return "A variavel de ambiente passada foi: " + dbEnvironmentVariable;
    }
}
