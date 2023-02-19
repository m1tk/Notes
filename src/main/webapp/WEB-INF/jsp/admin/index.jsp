<%@ page isErrorPage = "true" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/account.css">
    <script src="${pageContext.request.contextPath}/js/table.js"></script>
    </head>
    <body>
        <div class="parent-container">
            <%@ include file="../parts/topbar.jsp" %>  

            <%
                if (exception != null) {
                    %>
                    <div class="alert alert-danger" class="form-group"><%= exception.getMessage() %></div>
                    <%
                } else if (request.getAttribute("ok") != null) {
                    %>
                    <div class="alert alert-success" class="form-group"><%= request.getAttribute("ok") %></div>
                    <%
                }
            %>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%@ page import="estm.dsic.webby.Services.Admin" %>
                    <%@ page import="estm.dsic.webby.Models.Account" %>
                    <%@ page import="java.util.List" %>

                    <%
                        if (exception == null) {
                            List<Account> accs = Admin.users();
                            for (Account acc : accs) {
                                %>
                                <tr id="<%= acc.id %>">
                                    <td class="email-column"><%= acc.email %></td>
                                    <td>
                                        <a class="add" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a>
                                        <a class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                        <a class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                                        <a class="cancel" title="Cancel" data-toggle="tooltip"><i class="material-icons">&#xE5CD;</i></a>
                                    </td>
                                </tr>
                                <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
