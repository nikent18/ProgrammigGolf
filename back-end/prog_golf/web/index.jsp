<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Programming Golf</title>
    </head>
    <body>
        <center>
            <form id="myform" name="myform" method="post" action="/prog_golf/MainServlet">
            <h3>Task</h3>
            <textarea disabled rows="1" cols="100">Create a simple "Hello, World" program.</textarea>
            <h3>Code</h3>
            <textarea rows="13" cols="100" name="code" ></textarea> 
            <br>
            Language :
            <select name="lang">
                <option value="C">C</option>
                <option value="C++">C++</option>
                <option value="Java">Java</option>
            </select>
            Task id:
            <input size="5" name="id_task" value="1">
            User id:
            <input size="5" name="id_user" value="user">
            <br><br>
            <input type="submit" value="submit" name="Send" />
            </form>
            <br>
            <table>
                <tr>
                    <td align="right">
                        Compiled file:
                        <br>
                        Language:
                        <br>
                        Symbols:
                    </td>
                    <td aligh="left">
                        <input value="${response.filename}"></input>
                        <br>
                        <input value="${response.lang}"></input>
                        <br>
                        <input value="${response.symb}"></input>
                    </td>
                    <td>
                        
                    </td>
                    <td align="left">
                        Log:
                        <br>
                        <textarea disabled rows="7" cols="50">${response.log}</textarea>
                    </td>
                </tr>
            </table>
            
        </center>
    </body>
</html>
