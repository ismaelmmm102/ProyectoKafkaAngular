package com.api.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/app")
public class UsuarioControlador {

    @Autowired
    private IUsuarioRepositorio userRepository;
    
    @CrossOrigin(origins = "*")
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Usuario> getAllUsers(){
    	return userRepository.findAll();
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping(path="/{id}")
    public @ResponseBody Usuario findUserById(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow();
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "Usuario nº "+id+" eliminado";
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping(path="/delete/all")
    public @ResponseBody String deleteAllUsers(){
        userRepository.deleteAll();
        return "Todos los usuarios eliminados";
    }
    
    @CrossOrigin(origins = "*")
    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateUser(@PathVariable int id, @RequestBody Usuario usuario) {
    	Usuario usuarioExiste = userRepository.findById(id).orElseThrow();
        usuarioExiste.setNombre(usuario.getNombre());
        usuarioExiste.setEdad(usuario.getEdad());
        userRepository.save(usuarioExiste);
        return "Usuario nº "+id+"actualizado";
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping(path="/add")
    public @ResponseBody String addUser(@RequestBody Usuario usuario) {
        userRepository.save(usuario);
        return "Usuario agregado con éxito";
    }


}