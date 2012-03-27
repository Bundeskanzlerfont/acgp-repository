// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   verarchivo.java

package sad;

import Acc.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class verarchivo extends HttpServlet
{

    public verarchivo()
    {
        ADatos = new AccDataBase();
        AFunc = new funciones();
        ACopia = new CopiadorDeArchivos();
        UserReg = "";
        RutaSitio = "";
        RutaDocumentos = "";
        Acceso = "00000";
        SubTipo = "";
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        String s = httpservletrequest.getParameter("TIPO");
        String s1 = httpservletrequest.getParameter("ID");
        Acceso = httpservletrequest.getParameter("PERMISO")!=null?httpservletrequest.getParameter("PERMISO").toString():"10000";
        if(s == null || s.length() == 0)
            s = "A";
        if(s1 == null || s1.length() == 0)
            s1 = "0";
        Integer integer = new Integer(s1);
        HttpSession httpsession = httpservletrequest.getSession(true);
        UserReg = (String)httpsession.getValue("SerapisUser");
        if(UserReg != null && UserReg.length() > 0)
        {
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
            printwriter.println("<script type='text/javascript' language='JavaScript' src='../js/funciones.js'></script>");
            printwriter.println("<BODY bgcolor='white' leftmargin='0' topmargin='0'>");
            AFunc.cargamenu(printwriter, 1);
            printwriter.println("<form name='publica' method='POST' action='grabacomentario.jsp'>");
            printwriter.println("<input value = '" + s + "' name='tipodocumento' type='hidden'>");
            printwriter.println("<input value = '" + integer + "' name='codigodocumento' type='hidden'>");
            if(ObtieneRutas() == 1)
            {
                PreparaPublica(printwriter, s, integer.intValue());
            } else
            {
                printwriter.println("<TABLE border='1' align='center' width='80%'>");
                printwriter.println("<tr>");
                printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n de Publicaci\363n</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla' colspan='2'><B>No esta definido la ruta de destino de los archivos. <BR>Informe al administrador para solucionar el problema.</B></td>");
                printwriter.println("</tr>");
                printwriter.println("</table>");
            }
            printwriter.println("</form>");
            printwriter.println("<script language='javascript'>");
            printwriter.println("function Eliminar(Tipo , NroDoc)");
            printwriter.println("{");
            printwriter.println("if(confirm(\"\277Esta seguro que desea eliminar el archivo?\"))");
            printwriter.println("{");
            printwriter.println("window.open(\"elimina.jsp?TIPO=\" + Tipo + \"&DOC=\" + NroDoc + \"\",\"datos\");");
            printwriter.println("}");
            printwriter.println("return(0);");
            printwriter.println("}");
            printwriter.println("</script>");
            printwriter.println("</body>");
            printwriter.println("</html>");
        } else
        {
            AFunc.reindex(httpservletrequest, printwriter, 1, "SAD", 6);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    void PreparaPublica(PrintWriter printwriter, String s, int i)
    {
        rs = new Vector();
        rsfile = new Vector();
        String s2 = "";
        String s3 = "";
        String s4 = "N";
        String s5 = "";
        printwriter.println("<input type='hidden' value = '" + Acceso + "' name='txtAcceso' language='javascript'>");
        printwriter.println("<TABLE border='1' width='80%' align='center'>");
        printwriter.println("<tr>");
        printwriter.println("<td colspan='2' class='texttitulotabla'>Informaci\363n del Archivo</td>");
        printwriter.println("</tr>");
        ADatos.connect();
        String s1;
        if(s.compareTo("A") == 0)
            s1 = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,usuario,fechapublica,fechacaducidad,comentarioadicional from sad.documentos where id = " + i;
        else
            s1 = "select tipodoc,proceso,version,descripcion,cliente,adicional,verdoc,nombrearchivo,extension,comentario,usuario,fechapublica,fechacaducidad,proyecto,comentarioadicional from sad.documentosp where id = " + i;
        int j = ADatos.ConsultaDB(s1);
        rsfile = ADatos.getResult();
        if(j == 1)
        {
            String s7 = (String)rsfile.elementAt(0);
            String s8 = (String)rsfile.elementAt(1);
            String s9 = (String)rsfile.elementAt(2);
            String s10 = (String)rsfile.elementAt(3);
            String s11 = (String)rsfile.elementAt(4);
            String s12 = (String)rsfile.elementAt(5);
            String s13 = (String)rsfile.elementAt(6);
            String s14 = (String)rsfile.elementAt(7);
            String s15 = (String)rsfile.elementAt(8);
            String s16 = (String)rsfile.elementAt(9);
            String s17 = (String)rsfile.elementAt(10);
            Integer integer = new Integer(0);
            Integer integer1 = new Integer(0);
            integer = (Integer)rsfile.elementAt(11);
            integer1 = (Integer)rsfile.elementAt(12);
            String s18 = "";
            String s19 = "";
            String s6;
            if(s.compareTo("P") == 0)
            {
                s18 = (String)rsfile.elementAt(13);
                s19 = (String)rsfile.elementAt(14);
                s6 = "proyecto/" + s11 + "/" + s18 + "/" + s14;
            } else
            {
                s19 = (String)rsfile.elementAt(13);
                s6 = "documentos/" + s8 + "/" + s14;
            }
            try
            {
                //CopiadorDeArchivos _tmp = ACopia;
                CopiadorDeArchivos.copia(RutaDocumentos + "sad\\" + s6, RutaSitio + "WEB-INF\\downloadroot\\" + s14);
                int k = ADatos.ConsultaDB("select sigla,descripcion from gdc.procesos where sigla = '" + s8 + "' order by sigla");
                rs = ADatos.getResult();
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Proceso</td>");
                printwriter.println("<td class='textdesttabla'>");
                printwriter.println((String)rs.elementAt(1));
                printwriter.println("</td>");
                printwriter.println("</tr>");
                k = ADatos.ConsultaDB("select sigla,descripcion from gdc.tipodocumentos where sigla = '" + s7 + "' order by sigla");
                rs = ADatos.getResult();
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Tipo Documento</td>");
                printwriter.println("<td class='textdesttabla'>");
                printwriter.println((String)rs.elementAt(1));
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Nombre</td>");
                printwriter.println("<td class='textdesttabla'>");
                printwriter.println(s10);
                printwriter.println("</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Version Plantilla</td>");
                printwriter.println("<td class='textdesttabla'>" + s9 + "</td>");
                printwriter.println("</tr>");
                k = ADatos.ConsultaDB("select abreviatura,razonsocial from sgc.clientes where abreviatura = '" + s11 + "'");
                rs = ADatos.getResult();
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Cliente</td>");
                printwriter.println("<td class='textdesttabla'>");
                printwriter.println((String)rs.elementAt(1));
                printwriter.println("</td>");
                printwriter.println("</tr>");
                if(s.compareTo("P") == 0)
                {
                    int l = ADatos.ConsultaDB("select c.abreviatura,p.id,p.proyecto,p.abreviatura from gdc.proyectos p, sgc.clientes c where c.rutcliente = p.cliente and p.abreviatura = '" + s18 + "'");
                    rs = ADatos.getResult();
                    printwriter.println("<tr>");
                    printwriter.println("<td width='30%' class='textdesttabla'>Proyecto</td>");
                    printwriter.println("<td class='textdesttabla'>");
                    printwriter.println((String)rs.elementAt(2));
                    printwriter.println("</td>");
                    printwriter.println("</tr>");
                }
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Adicional</td>");
                if(s12 != null && s12.length() > 0)
                    printwriter.println("<td class='textdesttabla'>" + s12 + "</td>");
                else
                    printwriter.println("<td class='textdesttabla'>&nbsp;</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Nro. Correlativo</td>");
                printwriter.println("<td class='textdesttabla'>" + s13 + "</td>");
                printwriter.println("</tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Descripci\363n</td>");
                printwriter.println("<td class='textdesttabla'>");
                if(Acceso.compareTo("10000") == 0 || Acceso.compareTo("01000") == 0)
                {
                    printwriter.println("<textarea rows=3 cols=50 name='comentario' onkeypress='return ValidarCaracteres(9);' maxlength='200'>" + s16 + "</textarea>");
                } else
                {
                    printwriter.println(s16);
                    printwriter.println("<input type='hidden' value = '" + s16 + "' name='comentario' language='javascript' style='WIDTH: 250px' onkeypress='' maxlength='200'>");
                }
                printwriter.println("</td></tr>");
                if(Acceso.compareTo("10000") == 0 && s19 != null && s19.length() > 0)
                {
                    printwriter.println("<tr>");
                    printwriter.println("<td class='textdesttabla'>Comentario Adicional</td>");
                    printwriter.println("<td class='textdesttabla'>" + AFunc.desencripta(s19) + "</td>");
                    printwriter.println("</tr>");
                }
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Usuario</td>");
                printwriter.println("<td class='textdesttabla'>" + s17 + "</td></tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Fecha Publicaci\363n</td>");
                printwriter.println("<td class='textdesttabla'>" + AFunc.ConstruyeFecha(integer.toString(), "/", "dmy") + "</td></tr>");
                printwriter.println("<tr>");
                printwriter.println("<td class='textdesttabla'>Fecha Retenci\363n</td>");
                printwriter.println("<td class='textdesttabla'>" + AFunc.ConstruyeFecha(integer1.toString(), "/", "dmy") + "</td></tr>");
                printwriter.println("</table>");
                printwriter.println("<BR><center>");
                if(Acceso.compareTo("10000") == 0 || Acceso.compareTo("00010") == 0)
                    printwriter.println("<input type='button' name='eliminar' value='Eliminar' class='fondoinput' language='javascript'  onclick=\"return Eliminar('" + s + "'," + i + ");\">&nbsp;");
                if(Acceso.compareTo("10000") == 0 || Acceso.compareTo("01000") == 0 || Acceso.compareTo("00100") == 0)
                    printwriter.println("<input type='button' name='descarga' value='Descargar' class='fondoinput' language='javascript' onclick='window.open(\"descarga.jsp?FILEDESC=" + s14 + "\",\"_blank\")'>");
                if(Acceso.compareTo("10000") == 0 || Acceso.compareTo("01000") == 0)
                    printwriter.println("<input type='submit' name='grabarcom' value='Grabar' class='fondoinput' language='javascript'>");
                printwriter.println("</center>");
            }
            catch(IOException ioexception)
            {                
                printwriter.println("<tr><td colspan='2' class='textdesttabla' align='center'>Documento solicitado no existe</td>");
                //printwriter.println("<tr><td colspan='2' class='textdesttabla' align='left'>" + RutaDocumentos + "sad\\" + s6 + "*" + RutaSitio + "</td>");                
                printwriter.println("</table>");
            }
        } else
        {
            printwriter.println("<tr>");
            printwriter.println("<td colspan='2' class='textdesttabla' align='center'>Documento no existe</td>");
            printwriter.println("</td>");
            printwriter.println("</tr></table>");
        }
    }

    private int ObtieneRutas()
    {
        Vector vector = new Vector();
        int i = 0;
        String s = "select dirsitio,dirfiles from gdc.datosgenerales";
        ADatos.ConsultaDB(s);
        vector = ADatos.getResult();
        if(vector.size() > 0)
        {
            RutaSitio = (String)vector.elementAt(0);
            RutaDocumentos = (String)vector.elementAt(1);
            i = 1;
        }
        return i;
    }

    AccDataBase ADatos;
    funciones AFunc;
    CopiadorDeArchivos ACopia;
    Vector rs;
    Vector rsfile;
    String UserReg;
    String RutaSitio;
    String RutaDocumentos;
    String Acceso;
    String SubTipo;
}