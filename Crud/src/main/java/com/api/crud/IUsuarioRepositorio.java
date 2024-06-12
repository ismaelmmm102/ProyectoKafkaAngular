package com.api.crud;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepositorio extends CrudRepository<Usuario, Integer>{
	
}
