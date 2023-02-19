<%@ page isErrorPage = "true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<style>
.cover {
    width: 400px;
    margin: auto;
}
.mastfoot {
    padding-top: 0px;
    text-align: center;
    margin: auto;
}
</style>

</head>
<body>
<div class="cover-container">
    <div class="inner cover" style="margin-top: 5vh;">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Access your notes directory</h3>
            </div>
            <div class="panel-body">
                <!-- tabs -->
                <ul id="dTab" class="nav nav-tabs">
                    <li <%= request.getParameter("sign") == null ? "class=\"active\"": "" %>><a href="#log" data-toggle="tab">Login</a></li>
                    <li <%= request.getParameter("sign") != null ? "class=\"active\"": "" %>><a href="#signup" data-toggle="tab">Register</a></li>
                </ul>
                <div class="tab-content">
                    <div id="log" class="tab-pane fade <%= request.getParameter("sign") == null ? "in active": "" %>">
                        <!-- login -->
                        <form role="form" action="?log" method="POST">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input name="log_email" type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email" value="<%= request.getParameter("log_email") == null ? "": request.getParameter("log_email") %>" />
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input name="log_pass" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" />
                            </div>

                            <%
                                if (exception != null && exception instanceof estm.dsic.webby.Errors.AuthError) {
                                    %>
                                    <div class="alert alert-danger" class="form-group"><%= exception.getMessage() %></div>
                                    <%
                                }
                            %>

                            <button name="logsubmit" type="submit" class="btn btn-success">Log in</button>
                        </form>
                        <!-- login-ends-->
                    </div>
                    <div id="signup" class="tab-pane fade <%= request.getParameter("sign") != null ? "in active" : "" %>">
                        <!-- Register Username -->
                        <form action="?sign" method="POST">
                            <fieldset>
                                <div class="form-group">
                                    <!-- E-mail -->
                                    <label class="control-label" for="email">E-mail</label>
                                    <input name="sign_email" type="text" id="email" name="email" placeholder="Please provide your E-mail" class="form-control" value="<%= request.getParameter("sign_email") != null && exception != null ? request.getParameter("sign_email") : "" %>" />
                                </div>
                                <div class="form-group">
                                    <!-- Password-->
                                    <label class="control-label" for="password">Password</label>
                                    <input name="sign_pass" type="password" id="password" name="password" placeholder="Password should be at least 8 characters" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <!-- Password -->
                                    <label class="control-label" for="password_confirm">Password (Confirm)</label>
                                    <input name="sign_pass1" type="password" id="password_confirm" name="password_confirm" placeholder="Please confirm password" class="form-control" />
                                </div>


                                <%
                                    if (exception != null && exception instanceof estm.dsic.webby.Errors.SignError) {
                                        %>
                                        <div class="alert alert-danger" class="form-group"><%= exception.getMessage() %></div>
                                        <%
                                    } else if (request.getAttribute("sign_ok") != null) {
                                        %>
                                        <div class="alert alert-success" class="form-group"><%= request.getAttribute("sign_ok") %></div>
                                        <%
                                    }
                                %>

                                <button name="signsubmit" class="btn btn-success">Register</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
                <!-- -->
            </div>
        </div>
        <!-- <h1 class="cover-heading"></h1>
            <p class="lead"> Click to Register</p>
            <p class="lead">
              <a href="#" class="btn btn-lg btn-default">register</a>
            </p>-->
    </div>
</div>
<script>
$(document).ready(function () {
    $("#forgetBtn").click(function () {
        $("#dTab li:eq(2) a").tab("show");
        $(".tab-content div.active").removeClass("active in");
        $(".tab-content").find("#pane3").addClass("active in");
    });
    $("#loginBtn").click(function () {
        $("#dTab li:eq(1) a").tab("show");
        $(".tab-content div.active").removeClass("active in");
        $(".tab-content").find("#pane2").addClass("active in");
    });
});
</script>

</body>
</html>
