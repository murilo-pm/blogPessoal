package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")		//para aceitar sem levar em conta da origin (não importa se é react ou qualquer coisa)
public class PostagemController {
	
	@Autowired			//todos os serviços do tipo PostagemRepository serão acessados a partir do controller
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")		//o que está entre chaves é o @PathVariable
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build()); //é um "optional"
	}
	
	@GetMapping("/titulo/{titulo}")	//o que está entre chaves é o @PathVariable
	public ResponseEntity<List<Postagem>> FindByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem){				//não precisa passar o ID
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));	
		}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem){			//precisa passar o ID do recurso que será alterado
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));	//devolverá o objeto/postagem que foi salvo(a)
		}
	
	@DeleteMapping("/{id}") 						//chaves = template literal = interpolação
	public void delete(@PathVariable long id) {		//deletará pelo ID
		repository.deleteById(id);
	}
}
