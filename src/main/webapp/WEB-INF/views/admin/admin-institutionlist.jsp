<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                    <%@ include file="../header/header-admin.jsp" %>

                        <body id="page-top">

                            <!-- Page Wrapper -->
                            <div id="wrapper">

                                <%@ include file="admin-sidebar.jsp" %>

                                    <!-- Content Wrapper -->
                                    <div id="content-wrapper" class="d-flex flex-column">

                                        <!-- Main Content -->
                                        <div id="content">

                                            <%@ include file="admin-topbar.jsp" %>

                                                <!-- Begin Page Content -->
                                                <div class="container-fluid">

                                                    <!-- Page Heading -->
                                                    <h1 class="h3 mb-2 text-gray-800">Tables</h1>
                                                    <p class="mb-4">Lista zarejestrowanych użytkowników</p>

                                                    <!-- DataTales Example -->
                                                    <div class="card shadow mb-4">
                                                        <div class="card-header py-3">
                                                            <h6 class="m-0 font-weight-bold text-primary">Użytkownicy
                                                            </h6>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="table-responsive">
                                                                <table class="table table-bordered" id="dataTable"
                                                                    width="100%" cellspacing="0">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>Nazwa</th>
                                                                            <th>Opis</th>
                                                                            <th>Action</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tfoot>
                                                                        <tr>
                                                                            <th>Nazwa</th>
                                                                            <th>Opis</th>
                                                                            <th>Action</th>
                                                                        </tr>
                                                                    </tfoot>
                                                                    <tbody>
                                                                        <c:forEach items="${institutions}"
                                                                            var="institution">
                                                                            <tr>
                                                                                <td>${institution.name}</td>
                                                                                <td>${institution.description}</td>
                                                                                <td>
                                                                                    <a href="/admin/institution/edit/${institution.id}"
                                                                                        class="btn btn-warning btn-icon-split">
                                                                                        <span
                                                                                            class="icon text-white-50">
                                                                                            <i
                                                                                                class="fas fa-exclamation-triangle"></i>
                                                                                        </span>
                                                                                        <span class="text">Edytuj</span>
                                                                                    </a>
                                                                                    <a href="/admin/institution/delete/${institution.id}"
                                                                                        class="btn btn-danger btn-icon-split">
                                                                                        <span
                                                                                            class="icon text-white-50">
                                                                                            <i class="fas fa-trash"></i>
                                                                                        </span>
                                                                                        <span class="text">Usuń</span>
                                                                                    </a>
                                                                                </td>

                                                                            </tr>
                                                                        </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- /.container-fluid -->

                                        </div>
                                        <!-- End of Main Content -->

                                        <!-- Footer -->
                                        <footer class="sticky-footer bg-white">
                                            <div class="container my-auto">
                                                <div class="copyright text-center my-auto">
                                                    <span>Copyright &copy; Your Website 2020</span>
                                                </div>
                                            </div>
                                        </footer>
                                        <!-- End of Footer -->

                                    </div>
                                    <!-- End of Content Wrapper -->

                            </div>
                            <!-- End of Page Wrapper -->

                            <%@ include file="admin-footer.jsp" %>