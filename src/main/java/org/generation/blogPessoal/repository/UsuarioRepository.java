package org.generation.blogPessoal.repository;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	//pegar usuario pelo nomeUsuario
	//Optional = pode retornar um valor nulo
	public Optional<Usuario> findByUsuario(String usuario);
								//Usuario = atributo String usuario da camada model (Usuario)
	
}
