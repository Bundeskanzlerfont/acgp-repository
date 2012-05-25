<%@ page language='java' contentType="text/html"%>
<%@page import="org.apache.log4j.Logger" %>
<%@page import='cl.cmr.Proc.General'%>
<%@page import="cl.cmr.Seguridad.ValidaSesion"%>
<%
	String total="";
	ValidaSesion val = new ValidaSesion();			
	int res = val.valida(request);
	if (res != 0) {
		total="-1";
	}
	else {
	    total="0";
		try {
			String sNumCheque = request.getParameter("NUMCHEQUE")!=null?request.getParameter("NUMCHEQUE").toString().trim():"";
		    General objGeneral = General.getInstance(); 
			total=objGeneral.ValidaNumCheque(sNumCheque);
			
		} catch(Exception ex) {
			Logger.getLogger("AJAX_ValidaNumCheque").error(" [LiqCostas] " , ex);
		}
	}	
 %>
{"total":"<%=total%>"}
