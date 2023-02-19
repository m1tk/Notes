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
    <script src="${pageContext.request.contextPath}/js/notetable.js"></script>
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
            <button onClick="$('#AddModal').modal()" class="btn btn-success">Add new</button>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Note</th>
                        <th>Last modified</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%@ page import="estm.dsic.webby.Services.User" %>
                    <%@ page import="estm.dsic.webby.Models.Note" %>
                    <%@ page import="estm.dsic.webby.Models.Recent" %>
                    <%@ page import="estm.dsic.webby.Models.Account" %>
                    <%@ page import="java.util.List" %>

                    <%
                        if (exception == null) {
                            List<Note> accs = request.getAttribute("recent") != null
                            ? ((Recent)session.getAttribute("recent")).r : User.notes((Account)session.getAttribute("account"));
                            for (Note acc : accs) {
                                %>
                                <tr id="<%= acc.id %>">
                                    <td class="note-column"><a href="?show=<%= acc.id %>"><%= acc.title %></a></td>
                                    <td class="note-column"><%= String.format("%1$tY/%1$tm/%1$te %1$tH:%1$tM:%1$tS%n", acc.date) %></td>
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

            <%
            Note note;
            boolean edit = false;
            if ((edit = (note = (Note)request.getAttribute("edit")) != null) || (note = (Note)request.getAttribute("show")) != null) {
                %>
                <div class="modal fade" id="EditModal" tabindex="-1" role="dialog" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                          <h4 class="modal-title"><%= edit ? "Edit note" : "Note" %></h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <form action="?add" method="post">
                          <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Title:</label>
                            <input name="edit-note-title" type="text" class="form-control" id="recipient-name" value="<%= note.title %>">
                          </div>
                          <div class="form-group">
                            <label for="message-text" class="col-form-label">Body:</label>
                            <textarea name="edit-note-body" class="form-control"><%= note.body %></textarea>
                          </div>
                          <input name="edit-note-id" value="<%= note.id %>" hidden>
                          </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <% if (edit) {%>
                            <button name="edit-note" type="submit" class="btn btn-primary">Save</button>
                            <% } %>
                          </div>
                        </form>
                    </div>
                  </div>
                </div>
                <%
            }
            %>

            <div class="modal fade" id="AddModal" tabindex="-1" role="dialog" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h4 class="modal-title">Add note</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    <form action="?add" method="post">
                      <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Title:</label>
                        <input name="add-note-title" type="text" class="form-control" id="recipient-name">
                      </div>
                      <div class="form-group">
                        <label for="message-text" class="col-form-label">Body:</label>
                        <textarea name="add-note-body" class="form-control"></textarea>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button name="add-note" type="submit" class="btn btn-primary">Add</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
            <%= request.getAttribute("edit") != null || request.getAttribute("show") != null ? "<script>$('#EditModal').modal()</script>" : "" %>
        </div>
    </body>
</html>
