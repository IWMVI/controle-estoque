package br.edu.fateczl.controle_estoque.unitarios;

import br.edu.fateczl.controle_estoque.model.Categoria;
import br.edu.fateczl.controle_estoque.repository.CategoriaRepository;
import br.edu.fateczl.controle_estoque.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTodasCategorias() {
        Categoria categoria1 = new Categoria(null, "Eletrônicos", "Produtos eletrônicos");
        Categoria categoria2 = new Categoria(null, "Livros", "Livros diversos");
        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria1, categoria2));


        List<Categoria> categorias = categoriaService.todasCategorias();

        assertEquals(2, categorias.size());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void testSalvarCategoria() {
        Categoria categoria = new Categoria(null, "Eletrônicos", "Produtos eletrônicos");
        categoriaService.salvarCategoria(categoria);

        verify(categoriaRepository, times(1)).save(categoria);
    }

    @Test
    void testCategoriaId() {
        Categoria categoria = new Categoria(null, "Eletrônicos", "Produtos eletrônicos");
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        Categoria resultado = categoriaService.categoriaId(1L);

        assertNotNull(resultado);
        assertEquals("Eletrônicos", resultado.getNome());
        verify(categoriaRepository, times(1)).findById(1L);
    }

    @Test
    void testCategoriaIdNaoEncontrada() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        Categoria resultado = categoriaService.categoriaId(1L);

        assertNull(resultado);
        verify(categoriaRepository, times(1)).findById(1L);
    }

    @Test
    void testDeletarCategoria() {
        Categoria categoria = new Categoria(1L, "Eletrônicos", "Produtos eletrônicos");
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        boolean resultado = categoriaService.deletarCategoria(1L);

        assertTrue(resultado);
        verify(categoriaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletarCategoriaNaoEncontrada() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        boolean resultado = categoriaService.deletarCategoria(1L);

        assertFalse(resultado);
        verify(categoriaRepository, never()).deleteById(1L);
    }

    @Test
    void testAtualizarCategoria() {
        Categoria categoriaAntiga = new Categoria(1L, "Eletrônicos", "Produtos eletrônicos");
        Categoria categoriaAtualizada = new Categoria(1L, "Eletrônicos Atualizados", "Descrição atualizada");
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoriaAntiga));
        when(categoriaRepository.save(categoriaAntiga)).thenReturn(categoriaAntiga);

        categoriaService.atualizarCategoria(categoriaAntiga, categoriaAtualizada);

        assertEquals("Eletrônicos Atualizados", categoriaAntiga.getNome());
        assertEquals("Descrição atualizada", categoriaAntiga.getDescricao());
        verify(categoriaRepository, times(1)).save(categoriaAntiga);
    }
}
