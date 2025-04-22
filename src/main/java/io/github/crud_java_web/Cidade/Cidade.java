package io.github.crud_java_web.Cidade;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Cidade {

    @NotBlank(message = "{app.cidade.blank}")
    @Size(min = 3, max = 50, message = "{app.cidade.size}")
    private String nome;

    @NotBlank(message = "{app.estado.blank}")
    @Size(min = 2, max = 2, message = "{app.estado.size}")   
    private String estado;

    public Cidade(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String state) {
        this.estado = state;
    }

    public CidadeEntidade clonar() {
        var cidadeEntidade = new CidadeEntidade();
        cidadeEntidade.setNome(this.nome);
        cidadeEntidade.setEstado(this.estado);
        return cidadeEntidade;
    }

    public Cidade clonar(CidadeEntidade cidadeEntidade) {
        
        return new Cidade(cidadeEntidade.getNome(), cidadeEntidade.getEstado()); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, estado);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Cidade cidadeComparada = (Cidade) obj;
        return Objects.equals(this.nome, cidadeComparada.nome)
                && Objects.equals(this.estado, cidadeComparada.estado);
    }

    

}
