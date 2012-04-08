<%@ page language='java' contentType="text/html"%>
<%@page import="Proc.Remesa"%>
<%@page import="org.jboss.logging.Logger" %>
<%
	String NumCosta="-1";
	Seguridad.ValidaSesion val = new Seguridad.ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		NumCosta="-2";
	} else {	
	    try {
			String idSec = request.getParameter("idSec");
			Remesa remesa = Remesa.getInstance();
			NumCosta = remesa.actualizaEncRemesas(idSec);
			
		} catch(Exception ex) {
			Logger.getLogger("AJAX_ActualizaEncabezadoRemesa").error(" [LiqCostas] " , ex);
		}	
	}	
 %> 
{"total":"<%=NumCosta%>"}