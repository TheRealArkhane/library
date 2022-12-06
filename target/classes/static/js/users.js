let defaultPageNumber = 0, defaultSortingField = 'id', defaultSortingDirection = "ASC", pageSize = 10;
let previousPage, nextPage;
let  currentSortingField = defaultSortingField,
    currentSortingDirection = defaultSortingDirection,
    currentPageNumber = defaultPageNumber;
let currentUrl = "http://localhost:8080/users/all";
let currentUserId = $.get("http://localhost:8080/users/current", function (user) {
    console.log(user);
    currentUserId = user.id;
});


$(document).on('click', 'th', function() {
    let lastUsedSortingField = currentSortingField;
    currentSortingField = $(this).closest('th').text().toString().toLowerCase();
    if (currentSortingField === "first name") {
        currentSortingField = "firstName";
    }
    if (currentSortingField === "last name") {
        currentSortingField = "lastName";
    }
    if (lastUsedSortingField === currentSortingField) {
        if (currentSortingDirection === defaultSortingDirection) {
            currentSortingDirection = "DESC"
        }
        else {
            currentSortingDirection = defaultSortingDirection;
        }
        getUsersList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
    }
    else {
        currentSortingDirection = defaultSortingDirection;
        getUsersList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
    }
});


$(document).on('click', '.user-row-last-name', function () {
    let chosenUserId = $(this).closest('tr').find('.user-row-id').text().toString();
    if (confirm("Delete this user?")) {
        $.ajax({
            url: "http://localhost:8080/users/" + chosenUserId,
            type: "DELETE",
            data: {
                id: chosenUserId
            },
            success: function () {
                alert("User successfully deleted");
                getUsersList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
            },
            error: function(){
                alert("Can't delete user with positive balance");
                getUsersList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);
            }
        })
    }
});


function getUsersList(url ,pageNumber, sortingField, sortingDirection) {

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
                '<button onclick="getUsersList(\'' + currentUrl + '\',' +
                '\'' + previousPage + '\',' +
                '\'' + sortingField + '\',' +
                '\'' + sortingDirection + '\')">Previous</button>' +
                ' ' + totalElements + ' of ' + totalElements;
        }
        else if (currentPage === 0) {
            document.getElementById("pageable_div_id").innerHTML =
                '<button onclick="getUsersList(\'' + currentUrl + '\',' +
                '\'' + nextPage + '\',' +
                '\'' + sortingField + '\',' +
                '\'' + sortingDirection + '\')">Next</button>' +
                ' ' + (pageSize * (currentPage + 1)) + ' of ' + totalElements;
        }
        else {
            document.getElementById("pageable_div_id").innerHTML =
                '<button onclick="getUsersList(\'' + currentUrl + '\',' +
                '\'' + previousPage + '\',' +
                '\'' + sortingField + '\',' +
                '\'' + sortingDirection + '\')">Previous</button>' +
                '<button onclick="getUsersList(\'' + currentUrl + '\',' +
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
                    '        <th class="user-column-id">ID</th>\n' +
                    '        <th class="user-column-first-name">First Name</th>\n' +
                    '        <th class="user-column-last-name">Last Name</th>\n' +
                    '        <th class="user-column-role">Role</th>\n' +
                    '    </tr>';

                for (let i = 0; i < data.content.length; i++) {
                    html = html + '<tr>\n' +
                        '        <td class="user-row-id">' + data.content[i].id + '</td>\n' +
                        '        <td class="user-row-first-name">' + data.content[i].firstName + '</td>\n' +
                        '        <td class="user-row-last-name">' + data.content[i].lastName + '</td>\n' +
                        '        <td class="user-row-role">' + data.content[i].role + '</td>\n' +
                        '    </tr>';
                }
                document.getElementById('users-table').innerHTML = html;
                displayPageable(data.pageable, data.totalElements, data.totalPages);
                getCurrentData(data.pageNumber, sortingField, sortingDirection);
            });
    }
    fetchData();
}

getUsersList(currentUrl, currentPageNumber, currentSortingField, currentSortingDirection);