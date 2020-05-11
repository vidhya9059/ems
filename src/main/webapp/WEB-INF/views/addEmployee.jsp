<%@taglib   uri="http://www.springframework.org/tags/form"    prefix="form"%>
<br> <br>
<form:form   action="saveEmployee"   method="post"    modelAttribute="emodel">
 <table>
    <tr>
        <td>Empno</td> <td> <form:input   path="empno"/> </td>
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
             <input  type="submit"    value="SAVE">
        </td>
    </tr>
 </table>
</form:form>