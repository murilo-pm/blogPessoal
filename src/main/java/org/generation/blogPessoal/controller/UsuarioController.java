package org.generation.blogPessoal.controller;


import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.UsuarioLogin;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	
	@Autowired
	private UsuarioService usuarioService;
	
	/*
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());			//build renderizar no body
	}
	
	@GetMapping("/usuarios/{nome}")
	public ResponseEntity<List<Usuario>> getByName(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Usuario> post (@RequestBody Usuario usuario ){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put (@RequestBody Usuario usuario ){
		return ResponseEntity.ok(repository.save(usuario));
	}
	
	@DeleteMapping("/{id}") 						//chaves = template literal = interpolação
	public void delete(@PathVariable long id) {		//deletará pelo ID
		repository.deleteById(id);
	}*/
	
	
	 @PostMapping("/logar")
	    public ResponseEntity<UsuarioLogin> Authentication(@RequestBody Optional<UsuarioLogin> user) {
	        return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
	        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	    }

	    @PostMapping("/cadastrar")
	    public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
	    	//Optional<Usuario> user = Optional.ofNullable(usuarioService.CadastrarUsuario(usuario));
	    	Optional<Usuario> user = Optional.ofNullable(usuarioService.CadastrarUsuario(usuario));
	        try {
	    	return ResponseEntity.ok(user.get());
	    } catch(Exception error){
	    	return ResponseEntity.badRequest().build();
	    	}
		
		
		/*
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		Optional<Usuario> user = Optional.of(usuarioService.CadastrarUsuario(usuario));
					//alteração aqui ^^^^^^^
		try {
			return ResponseEntity.ok(user.get());
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
			
		}
		
	}
	
	*/
}
}
