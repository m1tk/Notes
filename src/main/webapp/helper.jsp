<html>
    <%@ page import="org.springframework.security.crypto.argon2.Argon2PasswordEncoder" %>
    <% Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2); %>
    <%= encoder.encode("123") %>
</html>
