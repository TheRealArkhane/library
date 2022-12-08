let defaultPageNumber = 0, defaultSortingField = 'id', defaultSortingDirection = "ASC", pageSize = 10;
let previousPage, nextPage;
let currentSortingField = defaultSortingField,
    currentSortingDirection = defaultSortingDirection,
    currentPageNumber = defaultPageNumber;
let currentBookId;
let currentUrl = "http://localhost:8080/books/all";
let currentUserId = $.get("http://localhost:8080/users/current", function (user) {
    console.log(user);
    currentUserId = user.id;
});

function balanceActionButton(userId, currentUserId) {
    let takeButton="<button type='button' class='take-button'> Take </button>";
    let returnButton="<button type='button' class='return-button'> Return </button>";
    if(userId == null) {
        return takeButton;
    }
    else if (userId === currentUserId) {
        return returnButton;
    }
    else return "";
}

function deleteActionButton(userId) {
    let deleteButton="<button type='button' class='delete-button'> Delete </button>";

    if(userId == null) {
        return deleteButton;
    }
    else return "";
}

$(document).on('click', '.submit-add-button', function () {
    let formData={
        isbn: $("#isbn").val(),
        name: $("#name").val(),
        author: $("#author").val(),
        genre: $("#genre").val()
    }
    $.ajax({
        url: "http://localhost:8080/books/add",
        type: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        data: JSON.stringify(formData),
        dataType: 'json',
        success: function () {
            alert("Book successfully added");
            getList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
            },
            error: function () {
                alert("Book cannot be added")
            }
    })
});

$(document).on("click", ".submit-update-button",function() {
    let formData={
        isbn: $("#isbn").val(),
        name: $("#name").val(),
        author: $("#author").val(),
        genre: $("#genre").val()
    }
    console.log(formData);

    $.ajax({
        url:"http://localhost:8080/books/" + currentBookId,
        type:"PUT",
        headers:{
            "Content-Type":"application/json",
            "Accept":"application/json"
        },
        data : JSON.stringify(formData),
        dataType : 'json',
        success: function(result){
            if(result.status === "success"){
                getList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
                alert("Книга была успешно изменён");
                console.log(result);
            }
            if(result.status === "error"){
                getList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
                alert("Такой книги не существует");
            }
        }
    });
});

$(document).on('click','.book-row-name',function(){
    currentBookId = $(this).closest('tr').find('.book-row-id').text().toString();
    let modal = document.getElementById('addWindow');
    let span = document.getElementsByClassName("close")[0];
    let updateButton=document.getElementById("submit-update-button");
    let addButton=document.getElementById("submit-add-button");

    updateButton.style.display="block";
    addButton.style.display="none";

    let currentBookISBN = $(this).closest('tr').find('.book-row-isbn').text().toString();
    let currentBookName = $(this).closest('tr').find('.book-row-name').text().toString();
    let currentBookAuthor = $(this).closest('tr').find('.book-row-author').text().toString();
    let currentBookGenre = $(this).closest('tr').find('.book-row-genre').text().toString();

    $("#isbn").val(currentBookISBN);
    $("#name").val(currentBookName);
    $("#author").val(currentBookAuthor);
    $("#genre").val(currentBookGenre);
    $("#cr-up-header").text("Изменить книгу");
    modal.style.display = "block";

    span.onclick = function() {
        modal.style.display = "none";
    }
});


$(document).on('click', 'th', function() {
    let lastUsedSortingField = currentSortingField;
    currentSortingField = $(this).closest('th').text().toString().toLowerCase();
    if (currentSortingField === "condition") {
        currentSortingField = "userId";
    }
    if (lastUsedSortingField === currentSortingField) {
        if (currentSortingDirection === defaultSortingDirection) {
            currentSortingDirection = "DESC"
        }
        else {
            currentSortingDirection = defaultSortingDirection;
        }
        getList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
    }
    else {
        currentSortingDirection = defaultSortingDirection;
        getList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
    }
});

$(document).on('click', '.take-button', function () {
    currentBookId = $(this).closest('tr').find('.book-row-id').text().toString();
    $.ajax({
        url: "http://localhost:8080/balance/take",
        type: "PUT",
        data: {
            userId: currentUserId,
            bookId: currentBookId
        },
        success: function (data) {
            console.log(data)
            getList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
        }
    });
});

$(document).on('click', '.delete-button', function () {
    currentBookId = $(this).closest('tr').find('.book-row-id').text().toString();
    if (confirm("Delete this book?")) {
        $.ajax({
            url: "http://localhost:8080/books/" + currentBookId,
            type: "DELETE",
            data: {
                id: currentBookId
            },
            success: function () {
                alert("Book successfully deleted");
                getList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
            },
            error: function(){
                alert("Can't delete the book that already taken");
                getList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
            }
        })
    }
});

$(document).on('click', '.return-button', function () {
    currentBookId = $(this).closest('tr').find('.book-row-id').text().toString();
    $.ajax({
        url: "http://localhost:8080/balance/return",
        type: "PUT",
        data: {
            userId: currentUserId,
            bookId: currentBookId
        },
        success: function (data) {
            console.log(data)
            getList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
        }
    });
});


function getList(url ,pageNumber, sortingField, sortingDirection) {

    if (pageNumber == null) {
        pageNumber = defaultPageNumber;
    }
    if (sortingField == null) {
        sortingField = defaultSortingField;
    }
    if (sortingDirection == null) {
        sortingDirection = defaultSortingDirection
    }

    function displayPageable(pageable, totalElements, totalPages) {
        let currentPage = pageable.pageNumber;
        previousPage = currentPage - 1;
        if (previousPage < 0) {
            previousPage = 0;
        }
        nextPage = currentPage + 1;
        if (nextPage >= totalPages) {
            nextPage = totalPages;
        }
        if (currentPage === totalPages - 1 && currentPage === 0) {
            document.getElementById("pageable_div_id").innerHTML =
                totalElements + ' of ' + totalElements;
        }
        else if (currentPage === totalPages - 1) {
            document.getElementById("pageable_div_id").innerHTML =
                '<button onclick="getList(\'' + currentUrl + '\',' +
                '\'' + previousPage + '\',' +
                '\'' + sortingField + '\',' +
                '\'' + sortingDirection + '\')">Prev</button>' +
                ' ' + totalElements + ' of ' + totalElements;
        }
        else if (currentPage === 0) {
            document.getElementById("pageable_div_id").innerHTML =
                '<button onclick="getList(\'' + currentUrl + '\',' +
                '\'' + nextPage + '\',' +
                '\'' + sortingField + '\',' +
                '\'' + sortingDirection + '\')">Next</button>' +
                ' ' + (pageSize * (currentPage + 1)) + ' of ' + totalElements;
        }
        else {
            document.getElementById("pageable_div_id").innerHTML =
                '<button onclick="getList(\'' + currentUrl + '\',' +
                '\'' + previousPage + '\',' +
                '\'' + sortingField + '\',' +
                '\'' + sortingDirection + '\')">Prev</button>' +
                '<button onclick="getList(\'' + currentUrl + '\',' +
                '\'' + nextPage + '\',' +
                '\'' + sortingField + '\',' +
                '\'' + sortingDirection + '\')">Next</button>' +
                ' ' + (pageSize * (currentPage + 1)) + ' of ' + totalElements;
        }
    }

    function getCurrentData() {
        currentPageNumber = pageNumber;
        currentSortingField = sortingField;
        currentSortingDirection = sortingDirection;
    }

    function fetchData() {
        $.get(
            currentUrl + "?page=" + pageNumber
            + "&size=" + pageSize
            + "&sort=" + sortingField
            + "," + sortingDirection,
            function (data) {
                console.log(data);

                let html = '<tr>\n' +
                    '        <th class="book-column-id">ID</th>\n' +
                    '        <th class="book-column-isbn">ISBN</th>\n' +
                    '        <th class="book-column-name">Name</th>\n' +
                    '        <th class="book-column-author">Author</th>\n' +
                    '        <th class="book-column-genre">Genre</th>\n' +
                    '        <th class="book-column-condition">Condition</th>\n' +
                    '        <th>Actions</th>\n' +
                    '        <th>Admin Actions</th>\n' +
                    '    </tr>';

                for (let i = 0; i < data.content.length; i++) {
                    let condition = data.content[i].userId;
                    if (condition == null) {
                        condition = "Free";
                    } else if (condition === currentUserId) {
                        condition = "Taken By You"
                    } else condition = "Taken";
                    html += '<tr>\n' +
                        '        <td class="book-row-id">' + data.content[i].id + '</td>\n' +
                        '        <td class="book-row-isbn">' + data.content[i].isbn + '</td>\n' +
                        '        <td class="book-row-name">' + data.content[i].name + '</td>\n' +
                        '        <td class="book-row-author">' + data.content[i].author + '</td>\n' +
                        '        <td class="book-row-genre">' + data.content[i].genre + '</td>\n' +
                        '        <td class="book-row-condition">' + condition + '</tdclass>\n' +
                        '        <td>' + balanceActionButton(data.content[i].userId, currentUserId) + '</td>\n' +
                        '        <td>' + deleteActionButton(data.content[i].userId) + '</td>\n' +
                        '    </tr>';
                }
                document.getElementById('books-table').innerHTML = html;
                displayPageable(data.pageable, data.totalElements, data.totalPages);
                getCurrentData(data.pageNumber, sortingField, sortingDirection);
            });
    }
    fetchData();
}

getList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);