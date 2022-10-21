let pageNumber = 0, defaultSortingField = 'id', defaultSortingDirection = 'ASC';

let previousPage, nextPage, currentPage;


function displayPageable(pageable, totalElements, totalPages) {
    previousPage = pageable.pageNumber - 1;
    if (previousPage < 0) {
        previousPage = 0;
    }
    nextPage = pageable.pageNumber + 1;
    if (nextPage > totalPages) {
        nextPage = totalPages;
    }
}

function getBooks(page, sortingField, sortingDirection) {
    let pageSize = 100;
    if (page == null) {
        page = pageNumber;
    }
    if (sortingField == null) {
        sortingField = defaultSortingField;
    }
    if (sortingDirection == null) {
        sortingDirection = defaultSortingDirection
    }

    $.get(
        "http://localhost:8080/books/all?page="
        + page + "&size="
        + pageSize
        + "&sortingField="
        + sortingField
        + "&sortingDirection="
        + sortingDirection, function (data) {
        console.log(data);
        let html = '<tr>\n' +
            '        <th onclick="sort(\'' + 'isbn' + '\')">ISBN</th>\n' +
            '        <th onclick="sort(\'' + 'name' + '\')">Name</th>\n' +
            '        <th onclick="sort(\'' + 'author' + '\')">Author</th>\n' +
            '        <th onclick="sort(\'' + 'genre' + '\')">Genre</th>\n' +
            '    </tr>';

        for (let i = 0; i < data.content.length; i++) {
            html = html + '<tr>\n' +
                '        <td>' + data.content[i].isbn + '</td>\n' +
                '        <td>' + data.content[i].name + '</td>\n' +
                '        <td>' + data.content[i].author + '</td>\n' +
                '        <td>' + data.content[i].genre + '</td>\n' +
                '    </tr>';
        }
        document.getElementById("books_table_id").innerHTML = html;
        displayPageable(data.pageable, data.totalElements, data.totalPages);
        currentPage = data.number;
    });
}
getBooks();
