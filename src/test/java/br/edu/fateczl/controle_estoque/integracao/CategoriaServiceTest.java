package br.edu.fateczl.controle_estoque.integracao;

import br.edu.fateczl.controle_estoque.model.Categoria;
import br.edu.fateczl.controle_estoque.repository.CategoriaRepository;
import br.edu.fateczl.controle_estoque.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Carrega o contexto completo do spring
@ActiveProfiles("test") // Ativa o perfil de teste
class CategoriaServiceTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService; // Injeta o CategoriaService

    @Test
    void testTodasCategorias() {
        // Arrange
        Categoria categoria1 = new Categoria(null, "Eletrônicos", "Produtos eletrônicos");
        Categoria categoria2 = new Categoria(null, "Livros", "Livros diversos");
        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);

        // Act
        List<Categoria> categorias = categoriaService.todasCategorias();

        // Assert
        assertEquals(2, categorias.size());
    }

    @Test
    void testSalvarCategoria() {
        // Arrange
        Categoria categoria = new Categoria(null, "Eletrônicos", "Produtos eletrônicos");

        // Act
        categoriaService.salvarCategoria(categoria);

        // Assert
        Optional<Categoria> categoriaSalva = categoriaRepository.findById(categoria.getId());
        assertTrue(categoriaSalva.isPresent());
        assertEquals("Eletrônicos", categoriaSalva.get().getNome());
    }

    @Test
    void testCategoriaId() {
        // Arrange
        Categoria categoria = new Categoria(null, "Eletrônicos", "Produtos eletrônicos");
        categoriaRepository.save(categoria);

        // Act
        Categoria result = categoriaService.categoriaId(categoria.getId());

        // Assert
        assertNotNull(result);
        assertEquals("Eletrônicos", result.getNome());
    }

    @Test
    void testCategoriaIdNaoEncontrada() {
        // Arrange
        Long idInexistente = 999L;

        // Act
        Categoria result = categoriaService.categoriaId(idInexistente);

        // Assert
        assertNull(result);
    }

    @Test
    void testDeletarCategoria() {
        // Arrange
        Categoria categoria = new Categoria(null, "Eletrônicos", "Produtos eletrônicos");
        categoriaRepository.save(categoria);

        // Act
        boolean result = categoriaService.deletarCategoria(categoria.getId());

        // Assert
        assertTrue(result);
        Optional<Categoria> categoriaDeletada = categoriaRepository.findById(categoria.getId());
        assertFalse(categoriaDeletada.isPresent());
    }

    @Test
    void testDeletarCategoriaNaoEncontrada() {
        // Arrange
        Long idInexistente = 999L;

        // Act
        boolean result = categoriaService.deletarCategoria(idInexistente);

        // Assert
        assertFalse(result);
    }

    @Test
    void testAtualizarCategoria() {
        // Arrange
        Categoria categoriaAntiga = new Categoria(null, "Eletrônicos", "Produtos eletrônicos");
        categoriaRepository.save(categoriaAntiga);
        Categoria categoriaAtualizada = new Categoria(null, "Eletrônicos Atualizados", "Descrição atualizada");

        // Act
        categoriaService.atualizarCategoria(categoriaAntiga, categoriaAtualizada);

        // Assert
        Optional<Categoria> categoriaAtualizadaSalva = categoriaRepository.findById(categoriaAntiga.getId());
        assertTrue(categoriaAtualizadaSalva.isPresent());
        assertEquals("Eletrônicos Atualizados", categoriaAtualizadaSalva.get().getNome());
        assertEquals("Descrição atualizada", categoriaAtualizadaSalva.get().getDescricao());
    }
}