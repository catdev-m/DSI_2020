

<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page contentType="text/html"%>
<%@page import="java.util.*" %>
<%@page import="java.io.File" %>
<%@page import="java.sql.*" %>
<%@page import="org.registrohorasociales.entity.Database" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumnos en servicio social</title>
    </head>
    <body>
        <%
            
    //variables a utilizar
    Database db =new Database();
    Connection con;
    Class.forName(db.getDriver());
    con=DriverManager.getConnection(db.getUrl(), db.getUs(), db.getPass());
    Map parametro =new HashMap();
    parametro.put("para","89");
File jasperFile=new File(application.getRealPath("Reportes/reporteInscritos.jasper"));
byte[] bytes = JasperRunManager.runReportToPdf(jasperFile.getPath(),parametro,con);

response.setContentType("application/pdf");
response.setContentLength(bytes.length);

ServletOutputStream output =response.getOutputStream();
response.getOutputStream();
output.write(bytes,0,bytes.length);
output.flush();
output.close();

            
            %>
    </body>
</html>
