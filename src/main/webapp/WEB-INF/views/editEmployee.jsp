<%@taglib   uri="http://www.springframework.org/tags/form"    prefix="form"%>
<br> <br>
<form:form   action="updateEmployee"   method="post"    modelAttribute="emp">
 <table>
    <tr>
        <td>Empno</td> <td> <form:input   path="empno"   readonly="true" /> </td>
    </tr>
    <tr>
        <td>Ename</td> <td> <form:input   path="ename"/> </td>
    </tr>
    <tr>
        <td>Salary</td> <td> <form:input   path="sal"/> </td>
    </tr>
    <tr>
        <td>Deptno</td> <td> <form:input   path="deptno"/> </td>
    </tr>
    <tr>
        <td  colspan=2  align="center">
             <input  type="submit"    value="UPDATE">
        </td>
    </tr>
 </table>
</form:form>