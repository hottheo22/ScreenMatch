package br.com.theo.screenmatch.repository;

import br.com.theo.screenmatch.model.Categoria;
import br.com.theo.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface IRepositorio extends JpaRepository<Serie, Long> {

    Optional<Serie> findBytituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findBygenero(Categoria categoria);

    List<Serie> findBytotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int temporadas, double avaliacao);
}
