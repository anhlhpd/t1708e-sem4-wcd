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
    <h1>Add special content</h1>
    <form action="#" method="post">
        <div class="form-group">
            <label>Source url</label>
            <input type="text" name="url" class="form-control">
        </div>
        <div class="form-group">
            <label>Title Selector</label>
            <input type="text" name="titleSelector" class="form-control">
        </div>
        <div class="form-group">
            <label>Content Selector</label>
            <input type="text" name="contentSelector" class="form-control">
        </div>
        <div class="form-group">
            <input type="button" class="btn btn-primary" value="Preview" id="btn-preview"/>
            <input type="reset" class="btn btn-secondary" value="Reset"/>
            <input type="button" class="btn btn-success" value="Save"/>
        </div>
    </form>
</div>

<div class="modal" tabindex="-1" role="dialog" id="preview-modal">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="article-title"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="article-content">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btn-save">Save article</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    // A $( document ).ready() block.
    $( document ).ready(function() {
        $('#btn-preview').click(function () {
            var data = {
                "url": $('input[name="url"]').val(),
                "titleSelector": $('input[name="titleSelector"]').val(),
                "contentSelector": $('input[name="contentSelector"]').val()
            }
            $.ajax({
                "url": "http://localhost:8080/admin/crawler-source/special-content",
                "method": "post",
                "data": JSON.stringify(data),
                "success": function (responseData) {
                    $('#article-title').text(responseData.title);
                    $('#article-content').html(responseData.content);
                    $('#preview-modal').modal('show');
                },
                "error": function () {
                    console.log("Error.");
                }
            });
        });
        $('#btn-save').click(function () {
            var data = {
                "url": $('input[name="url"]').val(),
                "title": $('#article-title').text(),
                "content": $('#article-content').html()
            }
            $.ajax({
                "url": "http://localhost:8080/admin/crawler-source/save-special-content",
                "method": "post",
                "data": JSON.stringify(data),
                "success": function (responseData) {
                    alert('Save success!');
                    $('#preview-modal').modal('hide');
                },
                "error": function () {
                    console.log("Error.");
                }
            });
        });
    });
</script>
</body>
</html>
