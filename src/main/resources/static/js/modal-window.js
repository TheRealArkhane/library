window.onload=function(){
    const modal = document.getElementById('addWindow');
    const btn = document.getElementById("add-book-button");
    const updateButton=document.getElementById("submit-update-button");
    const addButton=document.getElementById("submit-add-button");
    const span = document.getElementsByClassName("close")[0];

    btn.onclick = function() {
        modal.style.display = "block";
        updateButton.style.display="none";
        addButton.style.display="block";
        $("#cr-up-header").text("Добавить книгу");
    }
    span.onclick = function() {
        modal.style.display = "none";
    }
    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
}