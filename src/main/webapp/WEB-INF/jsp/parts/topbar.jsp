<div class="cover-container">
    <div class="inner cover">
        <div class="panel panel-default">
            <div class="top-panel panel-heading">
                <%@ page import="estm.dsic.webby.Models.Account" %>
                <% Account tacc = (Account)session.getAttribute("account"); %>
                <%= tacc.is_admin ? "<a class=\"adm\" href=\""+request.getContextPath()+"/admin\">Administrator</a>" : "" %> 
                <a class="dashboard" href="${pageContext.request.contextPath}/dashboard">Notes</a>
                <a href="${pageContext.request.contextPath}/dashboard/recent">Recent</a> 
                <label class="email-holder"><%= tacc.email %></label>
                <a href="${pageContext.request.contextPath}/logout">Logout</a>
            </div>
        </div>
    </div>
</div>
