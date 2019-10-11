<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.images.ImagesService" %>
<%@ page import="com.google.appengine.api.images.ImagesServiceFactory" %><%--
  Created by IntelliJ IDEA.
  User: Phuong Anh
  Date: 8/3/2019
  Time: 6:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    ImagesService imagesService = ImagesServiceFactory.getImagesService();
%>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</head>
<body>
    <h1 style="margin: 100px;">Sign Up</h1>
    <form action="<%= blobstoreService.createUploadUrl("/register") %>" enctype="multipart/form-data"
          method="POST" style="margin: 100px;">
        <div class="form-group">
            <label for="exampleInputEmail1">Email</label>
            <input type="email" class="form-control col-lg-3" id="exampleInputEmail1" aria-describedby="emailHelp"
                   name="email" placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control col-lg-3" id="exampleInputPassword1"
                   name="password" placeholder="Password">
        </div>
        <div class="form-group">
            <label for="exampleFormControlFile1">Avatar</label>
            <input type="file" name="avatar" class="form-control-file" id="exampleFormControlFile1">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <input type="reset" class="btn btn-secondary" value="">Reset</input>
    </form>

</body>
</html>
