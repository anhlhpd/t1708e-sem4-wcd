<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.images.ImagesService" %>
<%@ page import="com.google.appengine.api.images.ImagesServiceFactory" %>
<%@ page import="com.google.appengine.api.images.ServingUrlOptions" %>
<%@ page import="com.google.appengine.api.blobstore.BlobKey" %>
<%@ page import="entity.Account" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Phuong Anh
  Date: 8/4/2019
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    ImagesService imagesService = ImagesServiceFactory.getImagesService();
    Account currentAccount = (Account) request.getAttribute("currentAccount");
%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <title>Account detail</title>
</head>
<body>
    <a href="/change-language?lang=vi">Vi</a>&nbsp;
    <a href="/change-language?lang=en">En</a>

    <h1>Account detail</h1>

    <form action="<%= blobstoreService.createUploadUrl("/account") %>" method="post" enctype="multipart/form-data">
        <div class="form-group col-6">
            <label>${bundle.email}</label>
            <input type="text" name="email" class="form-control disabled" value="${currentAccount.email}" readonly>
        </div>

        <div class="form-group col-6">
            <label>${bundle.avatar}</label>
            <div>
                <img src="<%= imagesService.getServingUrl(
                        ServingUrlOptions.Builder
                        .withBlobKey(new BlobKey(currentAccount.getAvatar())).secureUrl(true)
                )%>" alt="">
            </div>
            <input type="file" name="avatar" class="form-control-file">
        </div>

        <div class="form-group col-6">
            <input type="submit" class="btn btn-primary" value="${bundle.save}"/>
            <input type="reset" class="btn btn-secondary" value="${bundle.reset}"/>
        </div>
    </form>
</body>
</html>
