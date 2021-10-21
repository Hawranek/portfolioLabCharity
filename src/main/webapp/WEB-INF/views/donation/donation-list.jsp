<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                    <%@ include file="../header-form.jsp" %>

                        <div class="slogan container container--90">
                            <div class="slogan--item">
                                <h1>
                                    Lista przekazanych<br />
                                    <span class="uppercase">darów</span>
                                </h1>
                            </div>
                        </div>
                        </header>

                        <!-- Czy dary na liście są posortowane wg:
                        1) statusu odebrane/nieodebrane,
                        2) daty odebrania (jeśli odebrane) i
                        3) daty utworzenia wpisu? -->
                        <section id="donation-list">
                            <table>
                                <thead>
                                    <th>Nazwa instytucji</th>
                                    <th>Ilość przekazanych worków</th>
                                    <th>Status</th>
                                    <th>Data odebrania</th>
                                    <th>Data utworzenia</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${donations}" item="donation">
                                        <tr>
                                            <td>${donation.institution}</td>
                                            <td>${donation.quantity}</td>
                                            <td>${donation.received}</td>
                                            <td>${donation.pickUpDate}</td>
                                            <td>${donation.created}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                        </section>

                        <%@ include file="../footer.jsp" %>