package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class ControllerBoasVindas {


    @GetMapping
    public String boaV(){
        return "Bem Vindo á nossa api";
    }

}
