package vn.unicloud.vietqr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;
import vn.unicloud.vietqr.soap.client.StmSoapClient;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;

@Configuration
public class SoapConfig {
    @Value("${soap.uri.inet}")
    private String soapUri;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("vn.unicloud.vietqr.soap.inetservice.model");
        return marshaller;
    }

    @Bean
    public StmSoapClient stmSoapClient(Jaxb2Marshaller marshaller){
        StmSoapClient client = new StmSoapClient();
        client.setDefaultUri(soapUri + "?wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public HttpUrlConnectionMessageSender httpUrlConnectionMessageSender() {
        HttpUrlConnectionMessageSender sender = new HttpUrlConnectionMessageSender();
        Duration timeout = Duration.ofSeconds(60);
        sender.setReadTimeout(timeout);
        sender.setConnectionTimeout(timeout);
        return sender;
    }

    @Bean
    public Boolean disableSSLValidation() throws Exception {
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, null);

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        return true;
    }
}
