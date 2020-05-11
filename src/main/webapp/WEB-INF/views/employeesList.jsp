<%@taglib  uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<%@taglib  uri="http://www.springframework.org/security/tags"  prefix="security" %>

<a  href="addEmployee">Add More Employee</a>
<br>
<br>
<table  border="1">
   <tr>
      <th>EMPNO</th>
      <th>ENAME</th>
      <th>SALARY</th>
      <th>DEPTNO</th>
      <th>ACTIONS</th>
   </tr>
   <c:if  test="${!empty  employees }">
        <c:forEach  items="${employees}"  var="e">
            <tr>
               <td> <c:out  value="${e.empno }"/> </td>
               <td> <c:out  value="${e.ename }"/> </td>
               <td> <c:out  value="${e.sal }"/> </td>
               <td> <c:out  value="${e.deptno }"/> </td>
               <td>
                   <a  href="editEmployee?id=${e.empno}"> <img  src="imgs/edit_image.png"  width="30"  height="30"></a>
                 
                   &nbsp; &nbsp; &nbsp; &nbsp; 
                   
                   <security:authorize  access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')">
                     <a  href="deleteEmployee?id=${e.empno}"> <img  src="imgs/delete_image.jpg"  width="30"  height="30"></a>
                   </security:authorize>
               </td>    
        </c:forEach>
   </c:if>
</table>

<br> <br>
<a  href="logoutMe"> Logout </a>
