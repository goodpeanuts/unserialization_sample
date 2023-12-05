<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display File</title>
</head>
<body>

<pre>
<%
    String fileName = request.getParameter("name");
    if (fileName != null && !fileName.isEmpty()) {
        File file = new File(request.getRealPath("/") + fileName);
        FileInputStream in = new FileInputStream(file);

        int tempbyte;
        while ((tempbyte = in.read()) != -1) {
            out.write(tempbyte);
        }

        in.close();
    } else {
        out.println("File name not provided.");
    }
%>
</pre>

</body>
</html>
