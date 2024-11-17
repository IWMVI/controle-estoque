package br.edu.fateczl.controle_estoque.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class RelatorioService {

    @PersistenceContext
    private EntityManager em;

    /**
     * Método que chama a stored procedure sp_relatorio_por_categoria
     * 
     * @param categoriaId ID da categoria para o filtro
     * @param status      Status de atividade do produto (TRUE/FALSE)
     * @return Lista de objetos com os resultados da stored procedure
     */
    public List<Object[]> relatorioPorCategoria(Long categoriaId, Boolean status) {
        // Verificar se a categoria existe no banco de dados
        Long count = (Long) em.createQuery("SELECT COUNT(c) FROM Categoria c WHERE c.id = :categoriaId")
                .setParameter("categoriaId", categoriaId)
                .getSingleResult();

        if (count == 0) {
            throw new IllegalArgumentException("Categoria informada não existe.");
        }

        try {
            // Criar a chamada para a stored procedure
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_relatorio_por_categoria");

            // Registrar os parâmetros da stored procedure
            query.registerStoredProcedureParameter("p_categoria_id", Long.class, jakarta.persistence.ParameterMode.IN);
            query.registerStoredProcedureParameter("p_ativo", Boolean.class, jakarta.persistence.ParameterMode.IN);

            // Passar os parâmetros
            query.setParameter("p_categoria_id", categoriaId);
            query.setParameter("p_ativo", status);

            // Executar a stored procedure e obter os resultados
            List<Object[]> results = query.getResultList();

            return results;
        } catch (Exception e) {
            // Logar ou lançar uma exceção personalizada em caso de erro
            System.out.println("Erro ao executar a stored procedure: " + e.getMessage());
            throw new RuntimeException("Erro ao executar a consulta: " + e.getMessage(), e);
        }
    }
}
