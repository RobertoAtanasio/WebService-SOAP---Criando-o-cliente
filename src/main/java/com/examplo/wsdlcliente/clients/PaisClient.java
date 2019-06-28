package com.examplo.wsdlcliente.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.examplo.wsdlcliente.wsdl.GetPaisRequest;
import com.examplo.wsdlcliente.wsdl.GetPaisResponse;

public class PaisClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(PaisClient.class);

    // serviço que vai consumir o serviço externo
    // faz acesso ao servidor remoto soap
    public GetPaisResponse getPais(String pais) {

        GetPaisRequest request = new GetPaisRequest();
        request.setNome(pais);

        log.info("Obtendo dados sobre: " + pais);

        GetPaisResponse response = (GetPaisResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/paises", request,
                        new SoapActionCallback(
                                "http://exemplo.com/wsdlservidor/assets/GetPaisRequest"));

        return response;
    }

}