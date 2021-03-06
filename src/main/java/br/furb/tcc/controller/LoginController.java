package br.furb.tcc.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.furb.tcc.model.Usuario;
import br.furb.tcc.repository.UsuarioRepository;

@Controller
public class LoginController {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping("/")
	public String loginForm(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuarioForm", usuario);
		return "login";
	}

	@RequestMapping(value = "/efetuaLogin", method = RequestMethod.POST)
	public String efetuaLogin(Model model, Usuario usuario, HttpSession session) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(usuario.getUsername());
		if ((usuarioOptional.isPresent()) && (usuarioOptional.get().getPassword().equals(usuario.getPassword()))) {
			session.setAttribute("usuarioLogado", usuario);
			return "menu";
		}
		return "redirect:loginForm";
	}
}
