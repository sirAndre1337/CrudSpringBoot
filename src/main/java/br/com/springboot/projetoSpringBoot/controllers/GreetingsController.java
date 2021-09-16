package br.com.springboot.projetoSpringBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.projetoSpringBoot.model.Usuario;
import br.com.springboot.projetoSpringBoot.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(value = "/findAll")
	@ResponseBody
	public ResponseEntity<List<Usuario>> findAll() {

		List<Usuario> findAll = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(findAll, HttpStatus.OK);
	}

	@RequestMapping(value = "/{name}/{idade}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name, @PathVariable Integer idade) {

		Usuario u = new Usuario();
		u.setNome(name);
		u.setIdade(idade);

		usuarioRepository.save(u);

		return "Nome :" + name + "Idade : " + idade;
	}

	@RequestMapping(value = "/api/v1/save/{name}&{age}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void save(@PathVariable String name, @PathVariable Integer age) {

		Usuario u = new Usuario();
		u.setIdade(age);
		u.setNome(name);

		usuarioRepository.save(u);

	}

	@PostMapping(value = "/api/v1/save")
	@ResponseBody
	public ResponseEntity<Usuario> save(@RequestBody Usuario user) {

		Usuario userSave = usuarioRepository.save(user);

		return new ResponseEntity<Usuario>(userSave, HttpStatus.CREATED);

	}
	
	@DeleteMapping(value = "/api/v1/delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long id){
		
		usuarioRepository.deleteById(id);
		
		return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/api/v1/findbyid")
	@ResponseBody
	public ResponseEntity<Usuario> findUserById(@RequestParam Long id){
		
		Usuario userFind = usuarioRepository.findById(id).get();
		
		return new ResponseEntity<Usuario>(userFind , HttpStatus.OK);
	}

	@GetMapping(value = "/api/v1/findbyname")
	@ResponseBody
	public ResponseEntity<List<Usuario>> findByName(@RequestParam String name){
		
		List<Usuario> lista = usuarioRepository.findByName(name.toUpperCase());
		
		return new ResponseEntity <List<Usuario>>(lista , HttpStatus.OK);
	}
}
