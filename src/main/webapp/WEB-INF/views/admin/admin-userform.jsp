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
                                                    <p class="mb-4">DataTables is a third party plugin that is used to
                                                        generate the demo table below.
                                                        For more information about DataTables, please visit the <a
                                                            target="_blank" href="https://datatables.net">official
                                                            DataTables documentation</a>.</p>

                                                    <!-- DataTales Example -->
                                                    <div class="card shadow mb-4">
                                                        <div class="card-header py-3">
                                                            <h6 class="m-0 font-weight-bold text-primary">DataTables
                                                                Example</h6>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="table-responsive">
                                                                <table class="table table-bordered" id="dataTable"
                                                                    width="100%" cellspacing="0">
                                                                    <tbody>
                                                                        <form:form modelAttribute="user"
                                                                            action="/admin/user/form" method="post"
                                                                            name="adduser">
                                                                            <input type="hidden" name="userid" value="${user.id}">
                                                                            <tr>
                                                                                <th>Imię</th>
                                                                            </tr>
                                                                            <td>
                                                                                <form:input path="firstName" />
                                                                            </td>
                                                                            <tr>
                                                                                <th>Nazwisko</th>
                                                                            </tr>
                                                                            <td>
                                                                                <form:input path="lastName" />
                                                                            </td>
                                                                            <tr>
                                                                                <th>Role</th>
                                                                            </tr>
                                                                            <td>
                                                                                <form:select path="roles"
                                                                                    items="${roles}" itemLabel="name" />
                                                                            </td>
                                                                            <tr>
                                                                                <th>Email</th>
                                                                            </tr>
                                                                            <td>
                                                                                <form:input path="email" />
                                                                            </td>
                                                                            <tr>
                                                                                <th>Hasło</th>
                                                                            </tr>
                                                                            <td>
                                                                                <form:password path="password" />
                                                                            </td>
                                                                            <tr>
                                                                                <th>Status</th>
                                                                            </tr>
                                                                            <td>
                                                                                <form:checkbox path="enabled" /> Aktywny
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