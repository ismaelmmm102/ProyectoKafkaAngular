package com.api.productor.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publicar")
public class Productor {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "usuarios";
    
    @PostMapping
    public String add(@RequestParam("id") int id, @RequestParam("nombre") String nombre, @RequestParam("edad") int edad) {
        String mensaje = id + "," + nombre + "," + edad;
        kafkaTemplate.send(TOPIC, mensaje);
        return "Correcto";
    }

}
