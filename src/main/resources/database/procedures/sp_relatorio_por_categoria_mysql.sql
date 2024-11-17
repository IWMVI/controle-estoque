USE controle_estoque;

DROP PROCEDURE IF EXISTS sp_relatorio_por_categoria;

DELIMITER $$
	
CREATE PROCEDURE sp_relatorio_por_categoria(
	IN p_categoria_id BIGINT,
	IN p_ativo BOOLEAN
)
BEGIN 
	-- Verificar se a categoria existe
	IF NOT EXISTS (SELECT 1 FROM categoria WHERE id = p_categoria_id) THEN 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Categoria informada não existe.';
	END IF;

	-- Retornar os produtos com base nos critérios
	SELECT 
		p.id AS ProdutoId,
		p.nome AS NomeProduto,
		p.preco AS Preco,
		p.ativo AS Ativo,
		p.data_alteracao AS DataAlteração,
		p.descricao AS Descrição,
		c.nome AS NomeCategoria
	FROM
		produto p
	JOIN categoria c ON
		c.id = p.categoria_id 
	WHERE
		p.categoria_id = p_categoria_id
		AND p.ativo = p_ativo
	ORDER BY
		p.nome;
END $$

DELIMITER ;

CALL sp_relatorio_por_categoria(2, TRUE);
CALL sp_relatorio_por_categoria(3, FALSE);
