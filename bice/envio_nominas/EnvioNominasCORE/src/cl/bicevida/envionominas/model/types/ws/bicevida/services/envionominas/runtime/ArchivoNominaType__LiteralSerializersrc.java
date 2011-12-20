// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.5.0, build 090727.2000.36696)

package cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.runtime;

import oracle.j2ee.ws.common.encoding.*;
import oracle.j2ee.ws.common.encoding.literal.*;
import oracle.j2ee.ws.common.encoding.literal.DetailFragmentDeserializer;
import oracle.j2ee.ws.common.encoding.simpletype.*;
import oracle.j2ee.ws.common.soap.SOAPEncodingConstants;
import oracle.j2ee.ws.common.soap.SOAPEnvelopeConstants;
import oracle.j2ee.ws.common.soap.SOAPVersion;
import oracle.j2ee.ws.common.streaming.*;
import oracle.j2ee.ws.common.wsdl.document.schema.SchemaConstants;
import oracle.j2ee.ws.common.util.xml.UUID;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.AttachmentPart;

public class ArchivoNominaType__LiteralSerializersrc extends LiteralObjectSerializerBase implements Initializable {
    private static final QName ns1_idBancoProceso_QNAME = new QName("http://bicevida.ws/services/envionominas", "idBancoProceso");
    private static final QName ns2_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
    private CombinedSerializer myns2_string__java_lang_String_String_Serializer;
    private static final QName ns1_nombre_QNAME = new QName("http://bicevida.ws/services/envionominas", "nombre");
    private static final QName ns1_cuerpo_QNAME = new QName("http://bicevida.ws/services/envionominas", "cuerpo");
    
    public ArchivoNominaType__LiteralSerializersrc(QName type) {
        this(type,  false);
    }
    
    public ArchivoNominaType__LiteralSerializersrc(QName type, boolean encodeType) {
        super(type, true, encodeType);
        setSOAPVersion(SOAPVersion.SOAP_11);
    }
    
    public void initialize(InternalTypeMappingRegistry registry) throws Exception {
        myns2_string__java_lang_String_String_Serializer = (CombinedSerializer)registry.getSerializer("", java.lang.String.class, ns2_string_TYPE_QNAME);
    }
    
    public java.lang.Object doDeserialize(XMLReader reader,
        SOAPDeserializationContext context) throws Exception {
        cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ArchivoNominaType instance = new cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ArchivoNominaType();
        java.lang.Object member=null;
        QName elementName;
        List values;
        java.lang.Object value;
        
        reader.nextElementContent();
        java.util.HashSet requiredElements = new java.util.HashSet();
        requiredElements.add("idBancoProceso");
        requiredElements.add("nombre");
        requiredElements.add("cuerpo");
        for (int memberIndex = 0; memberIndex <3; memberIndex++) {
            elementName = reader.getName();
            if ((reader.getState() == XMLReader.START) && (matchQName(elementName, ns1_idBancoProceso_QNAME))) {
                myns2_string__java_lang_String_String_Serializer.setNullable( false );
                context.setNillable( false );
                member = myns2_string__java_lang_String_String_Serializer.deserialize(ns1_idBancoProceso_QNAME, reader, context);
                requiredElements.remove("idBancoProceso");
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull",DeserializationException.FAULT_CODE_CLIENT);
                }
                instance.setIdBancoProceso((java.lang.String)member);
                context.setXmlFragmentWrapperName( null );
                reader.nextElementContent();
            }
            if ((reader.getState() == XMLReader.START) && (matchQName(elementName, ns1_nombre_QNAME))) {
                myns2_string__java_lang_String_String_Serializer.setNullable( false );
                context.setNillable( false );
                member = myns2_string__java_lang_String_String_Serializer.deserialize(ns1_nombre_QNAME, reader, context);
                requiredElements.remove("nombre");
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull",DeserializationException.FAULT_CODE_CLIENT);
                }
                instance.setNombre((java.lang.String)member);
                context.setXmlFragmentWrapperName( null );
                reader.nextElementContent();
            }
            if ((reader.getState() == XMLReader.START) && (matchQName(elementName, ns1_cuerpo_QNAME))) {
                myns2_string__java_lang_String_String_Serializer.setNullable( false );
                context.setNillable( false );
                member = myns2_string__java_lang_String_String_Serializer.deserialize(ns1_cuerpo_QNAME, reader, context);
                requiredElements.remove("cuerpo");
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull",DeserializationException.FAULT_CODE_CLIENT);
                }
                instance.setCuerpo((java.lang.String)member);
                context.setXmlFragmentWrapperName( null );
                reader.nextElementContent();
            }
        }
        if (!requiredElements.isEmpty()) {
            throw new DeserializationException( "literal.expectedElementName" , requiredElements.iterator().next().toString(), DeserializationException.FAULT_CODE_CLIENT );
        }
        
        if( reader.getState() != XMLReader.END)
        {
            reader.skipElement();
        }
        XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
        return (java.lang.Object)instance;
    }
    
    public void doSerializeAttributes(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ArchivoNominaType instance = (cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ArchivoNominaType)obj;
        
    }
    public void doSerializeAnyAttributes(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ArchivoNominaType instance = (cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ArchivoNominaType)obj;
        
    }
    public void doSerialize(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ArchivoNominaType instance = (cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.ArchivoNominaType)obj;
        
        myns2_string__java_lang_String_String_Serializer.setNullable( false );
        context.setNillable( false );
        myns2_string__java_lang_String_String_Serializer.serialize(instance.getIdBancoProceso(), ns1_idBancoProceso_QNAME, null, writer, context);
        myns2_string__java_lang_String_String_Serializer.setNullable( false );
        context.setNillable( false );
        myns2_string__java_lang_String_String_Serializer.serialize(instance.getNombre(), ns1_nombre_QNAME, null, writer, context);
        myns2_string__java_lang_String_String_Serializer.setNullable( false );
        context.setNillable( false );
        myns2_string__java_lang_String_String_Serializer.serialize(instance.getCuerpo(), ns1_cuerpo_QNAME, null, writer, context);
    }
}