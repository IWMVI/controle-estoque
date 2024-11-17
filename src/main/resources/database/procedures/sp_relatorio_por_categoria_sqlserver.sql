USE controleestoque;
GO

-- Verificar e excluir a procedure existente
IF OBJECT_ID('sp_relatorio_por_categoria', 'P') IS NOT NULL
    DROP PROCEDURE sp_relatorio_por_categoria;
GO

CREATE PROCEDURE sp_relatorio_por_categoria
    @p_categoria_id BIGINT,
    @p_ativo BIT
AS
BEGIN
    SET NOCOUNT ON;

    -- Verificar se a categoria existe
    IF NOT EXISTS (SELECT 1 FROM categoria WHERE id = @p_categoria_id)
    BEGIN
        THROW 50000, 'Categoria informada nao existe.', 1;
    END;

    -- Retornar os produtos com base nos crit�rios
    SELECT 
        p.id AS ProdutoId,
        p.nome AS NomeProduto,
        p.preco AS Preco,
        p.ativo AS Ativo,
        p.dataAlteracao AS DataAlteracao,
        p.descricao AS Descricao,
        c.nome AS NomeCategoria
    FROM
        produto p
    INNER JOIN categoria c ON
        c.id = p.categoria_id
    WHERE
        p.categoria_id = @p_categoria_id
        AND p.ativo = @p_ativo
    ORDER BY
        p.nome;
END;
GO

-- Chamando o procedimento armazenado
EXEC sp_relatorio_por_categoria @p_categoria_id = 1, @p_ativo = 1;
EXEC sp_relatorio_por_categoria @p_categoria_id = 1, @p_ativo = 0;