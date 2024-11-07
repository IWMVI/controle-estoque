// ===============================
// Página Principal (Exibição de Produtos)
// ===============================

// Exibe o formulário de busca ao clicar no botão de busca
document
    .getElementById("search-product-btn")
    .addEventListener("click", function () {
        document.getElementById("search-form").style.display = "flex"; // Mostra o formulário de busca ao definir o display para 'flex'
    });

// ===============================
// Modal de Edição de Produto
// ===============================

// Fecha o modal de edição ao clicar no botão de fechar
document.getElementById("close-modal").addEventListener("click", function () {
    document.getElementById("edit-modal").style.display = "none"; // Fecha o modal de edição alterando o display para 'none'
});

// Mostra o modal de edição ao clicar no botão de editar
document.querySelectorAll(".edit-btn").forEach(function (button) {
    button.addEventListener("click", function () {
        document.getElementById("edit-modal").style.display = "flex"; // Exibe o modal de edição definindo o display para 'flex'

        const produtoId = button.getAttribute("data-id"); // Obtém o ID do produto associado ao botão de edição
        // Lógica de preenchimento dos campos do modal com as informações do produto
    });
});

// ===============================
// Funcionalidade de Busca
// ===============================

// Filtra os produtos da tabela com base no valor inserido no campo de busca
document.getElementById("search-btn").addEventListener("click", function () {
    const searchValue = document.getElementById("search-input").value.trim(); // Obtém o valor da busca e remove espaços extras
    if (searchValue) {
        // Lógica de filtragem da tabela de produtos com base no valor de busca
    }
});
