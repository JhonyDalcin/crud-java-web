package io.github.crud_java_web.Cidade;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntidade, Long> {

    public Optional<CidadeEntidade> findByNomeAndEstado(String nome, String estado);

}
