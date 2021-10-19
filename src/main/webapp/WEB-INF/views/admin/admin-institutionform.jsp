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
                                                    <h1 class="h3 mb-2 text-gray-800">Instytucje</h1>
                                                    <p class="mb-4">Dodawanie instytucji</p>

                                                    <!-- DataTales Example -->
                                                    <div class="card shadow mb-4">
                                                        <div class="card-header py-3">
                                                            <h6 class="m-0 font-weight-bold text-primary">Dodawanie instytucji</h6>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="table-responsive">
                                                                <table class="table table-bordered" id="dataTable"
                                                                    width="100%" cellspacing="0">
                                                                    <tbody>
                                                                        <form:form modelAttribute="institution"
                                                                            action="/admin/institution/form" method="post"
                                                                            name="addinstitution">
                                                                            <input type="hidden" name="institutionid" value="${institution.id}">
                                                                            <tr>
                                                                                <th>Nazwa</th>
                                                                            </tr>
                                                                            <td>
                                                                                <form:input path="name" />
                                                                            </td>
                                                                            <tr>
                                                                                <th>Opis</th>
                                                                            </tr>
                                                                            <td>
                                                                                <form:textarea path="description" />
                                                                            </td>                                                                    
                                                                            <tr>
                                                                                <th>Action</th>
                                                                            </tr>
                                                                            <td><button type="submit">Zapisz</button>
                                                                            </td>

                                                                        </form:form>
                                                                    </tbody>
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