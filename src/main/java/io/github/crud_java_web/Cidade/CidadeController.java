package io.github.crud_java_web.Cidade;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class CidadeController {

    private final CidadeRepository repository;

    public CidadeController( CidadeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("listaCidades", repository.findAll()
                                                        .stream()
                                                        .map(cidade ->
                                                                    new Cidade(
                                                                        cidade.getNome(),
                                                                        cidade.getEstado()))
                                                        .toList());
        return "crud";
    }

    @PostMapping("/criar")
    public String criar(@Valid Cidade cidade, BindingResult validation, Model model) {
        
        if (validation.hasErrors()) {
            validation
            .getFieldErrors()
            .forEach(error -> {
                model.addAttribute(error.getField(), error.getDefaultMessage());
            });
            model.addAttribute("nomeInformado", cidade.getNome());
            model.addAttribute("estadoInformado", cidade.getEstado());
            model.addAttribute("listaCidades", repository.findAll()
                                                        .stream()
                                                        .map(cidadeEntidade ->
                                                                    new Cidade(
                                                                        cidadeEntidade.getNome(),
                                                                        cidadeEntidade.getEstado()))
                                                        .toList());
            return "crud";
        }
        else{
            repository.save(cidade.clonar());
        } 
        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String excluir(
                        @RequestParam String nome,
                        @RequestParam String estado) {

        var cidadeEstadoEncontrada = repository.findByNomeAndEstado(nome, estado);
        cidadeEstadoEncontrada.ifPresent(repository::delete);
        return "redirect:/";
    }

    @GetMapping("/preparaAlterar")
    public String preparaAlterar(
                        @RequestParam String nome,
                        @RequestParam String estado,
                        Model model) {
                            
        var receivedCity = repository.findByNomeAndEstado(nome, estado);
        
        receivedCity.ifPresent(cidade -> {
            model.addAttribute("cityToUpdate", cidade);
            model.addAttribute("listaCidades", repository.findAll()
                                                        .stream()
                                                        .map(cidadeEntidade ->
                                                                    new Cidade(
                                                                        cidadeEntidade.getNome(),
                                                                        cidadeEntidade.getEstado()))
                                                        .toList());
        });

        return "crud";
    }

    @PostMapping("/alterar")
    public String alterar(
                        @RequestParam String nomeOriginal,
                        @RequestParam String estadoOriginal,
                        Cidade cidade) {

        var cidadeAtual = repository.findByNomeAndEstado(nomeOriginal, estadoOriginal);

        if(cidadeAtual.isPresent()) {
            var cidadeEncontrada = cidadeAtual.get();
            cidadeEncontrada.setNome(cidade.getNome());
            cidadeEncontrada.setEstado(cidade.getEstado());
            repository.saveAndFlush(cidadeEncontrada);
        }
        return "redirect:/";
    }

}
