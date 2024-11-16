function deletarCurso(id, nome) {
    document.getElementById("modalName").innerHTML = nome;
    document
        .getElementById("formDeletar")
        .setAttribute("action", "/produtos/excluir/" + id);
}
