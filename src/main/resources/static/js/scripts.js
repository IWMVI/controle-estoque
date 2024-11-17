// ===============================
// Exclusão de registros
// ===============================

// Deleta registros: categoria, produto etc
function deletarRegistro(nome, endpoint) {
    document.getElementById("modalName").innerHTML = nome;
    document.getElementById("formDeletar").setAttribute("action", endpoint);
}

// ===============================
// Página Categoria (Edição de Categoria)
// ===============================

// Evento onclick para preencher os campos no modal
document.addEventListener("DOMContentLoaded", function () {
    const modalEdicao = document.getElementById("modalEdicao");

    modalEdicao.addEventListener("show.bs.modal", function (event) {
        // Botão que acionou o modal
        const button = event.relatedTarget;

        // Extrair informações dos atributos data-*
        const id = button.getAttribute("data-id");
        const nome = button.getAttribute("data-nome");
        const descricao = button.getAttribute("data-descricao");

        // Campos do modal
        const nomeInput = document.getElementById("edit-category-name");
        const descricaoInput = document.getElementById(
            "edit-category-description"
        );

        // Preencher os campos do modal
        nomeInput.value = nome;
        descricaoInput.value = descricao;

        // Alterar a URL do formulário de edição
        const form = modalEdicao.querySelector("form");
        form.action = `/categorias/editar/${id}`;
    });
});
