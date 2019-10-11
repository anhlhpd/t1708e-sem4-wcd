<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.users.UserService" %><%--
  Created by IntelliJ IDEA.
  User: xuanhung
  Date: 2019-07-27
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserService userService = UserServiceFactory.getUserService();
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div>
        <p>Welcome: <%= userService.getCurrentUser().getEmail()%>
        </p>
        <p>Name: <%= userService.getCurrentUser().getNickname()%>
        </p>
        <p><a href="<%= userService.createLogoutURL("/")%>">Logout</a></p>
    </div>
    <h1>Add crawler source form5</h1>
    <form action="/admin/crawler-source/add" method="post">
        <div class="form-group">
            <label>Source url</label>
            <input type="text" name="url" class="form-control">
        </div>
        <div class="form-group">
            <label>Link selector</label>
            <input type="text" name="linkSelector" class="form-control">
        </div>
        <div class="form-group">
            <label>Link Limit</label>
            <input type="text" name="linkLimit" class="form-control">
        </div>
        <div class="form-group">
            <label>Title Selector</label>
            <input type="text" name="titleSelector" class="form-control">
        </div>
        <div class="form-group">
            <label>Description Selector</label>
            <input type="text" name="descriptionSelector" class="form-control">
        </div>
        <div class="form-group">
            <label>Content Selector</label>
            <input type="text" name="contentSelector" class="form-control">
        </div>
        <div class="form-group">
            <label>Author Selector</label>
            <input type="text" name="authorSelector" class="form-control">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Save"/>
            <input type="reset" class="btn btn-primary" value="Reset"/>
        </div>
    </form>
</div>
</body>
</html>
