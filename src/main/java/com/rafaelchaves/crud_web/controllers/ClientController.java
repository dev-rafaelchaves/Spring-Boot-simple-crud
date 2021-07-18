package com.rafaelchaves.crud_web.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rafaelchaves.crud_web.model.entities.Client;
import com.rafaelchaves.crud_web.model.repositories.ClientRepository;

@Controller
public class ClientController {
	
	@Autowired
	private ClientRepository repository;
	

	@GetMapping
	public String clients(Model model) {
		model.addAttribute("clientsList", repository.findAll());
		return "clients";
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("client") Client client) {
		return "resForm";
	}
	
	@GetMapping("/client/{id}/update")
	public String updade(@PathVariable("id") long id, Model model) {
		Optional<Client> client = repository.findById(id);
		if (client.isEmpty()) throw new IllegalArgumentException("Cliente não encontrado");
		model.addAttribute("client", client.get());
		return "resForm";
	}
	
	@GetMapping("/client/{id}/delete")
	public String delete(@PathVariable("id") long id) {
		Optional<Client> client = repository.findById(id);
		if (client.isEmpty()) throw new IllegalArgumentException("Cliente não encontrado");
		repository.delete(client.get());
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String saveReg(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "resForm";
		}
		repository.save(client);
		return "redirect:/";
	}
}
