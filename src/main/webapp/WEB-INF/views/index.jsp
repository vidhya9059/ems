<%@taglib   uri="http://java.sun.com/jsp/jstl/core"      prefix="c" %>

<h2 style="border: 2px solid cyan;  color: blue;">
    <c:if  test="${message ne null }">
         <c:out  value="${message}" />
    </c:if>     
</h2>

<br>

1. <a  href="addEmployee">  Add  Employee</a>  <br> <br>
2. <a  href="listEmployees"> List  Employees</a>

<br> <br>
<a href="logoutMe"> Logout </a>
