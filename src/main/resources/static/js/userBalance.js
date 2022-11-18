let defaultPageNumber = 0, defaultSortingField = 'id', defaultSortingDirection = 'ASC', pageSize = 10;
let previousPage, nextPage;
let currentBookId, currentSortingField;
let currentUserId;

function getCurrentUser() {
    $.get("http://localhost:8080/users/current", function (response) {
        console.log(response);
        currentUserId = response.id;
        console.log(currentUserId)
    });
}
getCurrentUser();


function balanceActionButton(user,currentUser) {
    let takeButton="<button type='button' class='take-button'> Take </button>";
    let returnButton="<button type='button' class='return-button'> Return </button>";
    if(user == null) {
        return takeButton;
    }
    else if (user === currentUser) {
        return returnButton;
    }
    else return "";
}


$(document).on('click', 'th', function() {
    currentSortingField = $(this).closest('th').text().toString().toLowerCase();
    if (currentSortingField === "condition") {
        currentSortingField = "userId";
    }
    getBalance(null, currentSortingField, null);
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
            getBalance();
        }
    });
});

$(document).on('click', '.book-row-name', function () {
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
                getBalance();
            },
            error: function(){
                alert("Can't delete the book that already taken");
                getBalance();
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
            getBalance();
        }
    });
});

function getBalance(pageNumber, sortingField, sortingDirection) {

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
        if (currentPage === totalPages - 1) {
            document.getElementById("pageable_div_id").innerHTML =
                '<button onclick="getBalance(\'' + previousPage + '\',' +
                '\'' + sortingField + '\',' +
                '\'' + sortingDirection + '\')">Previous</button>' +
                ' ' + totalElements + ' of ' + totalElements;
        }
        else if (currentPage === 0) {
            document.getElementById("pageable_div_id").innerHTML =
                '<button onclick="getBalance(\'' + nextPage + '\',' +
                '\'' + sortingField + '\',' +
                '\'' + sortingDirection + '\')">Next</button>' +
                ' ' + (pageSize * (currentPage + 1)) + ' of ' + totalElements;
        }
        else {
            document.getElementById("pageable_div_id").innerHTML =
                '<button onclick="getBalance(\'' + previousPage + '\',' +
                '\'' + sortingField + '\',' +
                '\'' + sortingDirection + '\')">Previous</button>' +
                '<button onclick="getBalance(\'' + nextPage + '\',' +
                '\'' + sortingField + '\',' +
                '\'' + sortingDirection + '\')">Next</button>' +
                ' ' + (pageSize * (currentPage + 1)) + ' of ' + totalElements;
        }
    }

    function fetchData() {
        $.ajax({
            url: "http://localhost:8080/balance/user"
                + "?page=" + pageNumber
                + "&size=" + pageSize
                + "&sort=" + sortingField
                + "&sortingDirection="
                + sortingDirection,
            type: "GET",
            data:
                {
                    userId: currentUserId //не работает - он не хочет воспринимать переменную
                },
            success:
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
                    '    </tr>';

                for (let i = 0; i < data.content.length; i++) {
                    let condition = data.content[i].userId;
                    if (condition == null) {
                        condition = "Free";
                    } else if (condition === currentUserId) {
                        condition = "Taken By You"
                    } else condition = "Taken";

                    html = html + '<tr>\n' +
                        '        <td class="book-row-id">' + data.content[i].id + '</td>\n' +
                        '        <td class="book-row-isbn">' + data.content[i].isbn + '</td>\n' +
                        '        <td class="book-row-name">' + data.content[i].name + '</td>\n' +
                        '        <td class="book-row-author">' + data.content[i].author + '</td>\n' +
                        '        <td class="book-row-genre">' + data.content[i].genre + '</td>\n' +
                        '        <td class="book-row-condition">' + condition + '</tdclass>\n' +
                        '        <td>' + balanceActionButton(data.content[i].userId, currentUserId) + '</td>\n' +
                        '    </tr>';
                }
                document.getElementById("balance_table").innerHTML = html;
                displayPageable(data.pageable, data.totalElements, data.totalPages);
            }
    });
    }
    fetchData();
}
getBalance();