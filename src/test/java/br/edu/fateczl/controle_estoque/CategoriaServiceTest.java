package br.edu.fateczl.controle_estoque;

import br.edu.fateczl.controle_estoque.model.Categoria;
import br.edu.fateczl.controle_estoque.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // Testar apenas a camada de persistência
@ActiveProfiles("test") // Ativar o perfil de teste
public class CategoriaServiceTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void testSalvarCategoria() {
        Categoria categoria = new Categoria(null, "Eletrônicos", "Produtos eletrônicos");
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        assertNotNull(categoriaSalva.getId());
        assertEquals("Eletrônicos", categoriaSalva.getNome());
    }

    @Test
    void testFindById() {
        Categoria categoria = new Categoria(null, "Eletrônicos", "Produtos eletrônicos");
        categoriaRepository.save(categoria);

        Optional<Categoria> buscarCategoria = categoriaRepository.findById(categoria.getId());

        assertTrue(buscarCategoria.isPresent());
        assertEquals("Eletrônicos", buscarCategoria.get().getNome());
    }

    @Test
    void testDeletarCategoria() {
        Categoria categoria = new Categoria(null, "Eletrônicos", "Produtos eletrônicos");
        categoriaRepository.save(categoria);

        categoriaRepository.deleteById(categoria.getId());

        Optional<Categoria> buscarCategoria = categoriaRepository.findById(categoria.getId());

        assertFalse(buscarCategoria.isPresent());
    }
}
