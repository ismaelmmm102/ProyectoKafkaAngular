package com.api.consumidor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumidor {

    @Autowired
    private UsuarioRepo usuarioRepo;
    
    private final List<Usuario> usuarios = new ArrayList<>();

    @KafkaListener(topics = "usuarios", groupId = "group_id")
    public void consume(String mensaje) {
        String[] datos = mensaje.split(",");
        Usuario user = new Usuario();
        user.setId(Integer.parseInt(datos[0]));
        user.setNombre(datos[1]);
        user.setEdad(Integer.parseInt(datos[2]));
        usuarios.add(user);
        usuarioRepo.save(user);
    }
    public List<Usuario> cogerUsuariosCola() {
        return usuarios;
    }
}