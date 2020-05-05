package com.org.sh.scaffot.common.util.http.apache;

import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.ImmutableHttpProcessor;

import java.io.IOException;

public class HttpService {


    public static void main(String[] args) throws IOException {

        get("");


    }


    public void getConnection(){

    }


    public static void get(final String url) throws IOException {


        HttpGet httpGet = new HttpGet("http://114.55.251.123:8134/saps/pingAn/6011");

        CloseableHttpClient httpClient = HttpClients.createDefault();


        HttpRequestInterceptor requestInterceptorFirst = new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
                //TODO: dosomething
                System.out.println("请求前拦截器1");
            }
        };

        HttpRequestInterceptor requestInterceptorLast = new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
                //TODO: dosomething
                System.out.println("请求的拦截器2");
            }
        };


        HttpResponseInterceptor responseInterceptorFirst = new HttpResponseInterceptor() {
            @Override
            public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
                //TODO: dosomething
                System.out.println("响应拦截器1");
            }
        };
        HttpResponseInterceptor responseInterceptorLast = new HttpResponseInterceptor() {
            @Override
            public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
                //TODO: dosomething
                System.out.println("响应拦截器2");
            }
        };

        HttpRequestInterceptor[] requestInterceptors = {requestInterceptorFirst, requestInterceptorLast};
        HttpResponseInterceptor[] responseInterceptors = new HttpResponseInterceptor[]{responseInterceptorFirst, responseInterceptorLast};

        HttpClientBuilder custom = HttpClients.custom();

        custom.addInterceptorLast(requestInterceptorLast);
        custom.addInterceptorFirst(requestInterceptorFirst);
        custom.addInterceptorFirst(responseInterceptorFirst);
        custom.addInterceptorLast(responseInterceptorLast);

        custom.disableAuthCaching();  //会被setHttpProcessor（）覆盖
        custom.disableAutomaticRetries();
        custom.disableConnectionState();
        custom.disableContentCompression();
        custom.disableRedirectHandling();
        custom.disableDefaultUserAgent();
        custom.disableCookieManagement();

        custom.evictExpiredConnections();
        custom.useSystemProperties();

        custom.evictIdleConnections();
        custom.setBackoffManager();
        custom.setConnectionBackoffStrategy();
        custom.setConnectionManager();
        custom.setConnectionManagerShared();
        custom.setConnectionReuseStrategy();
        custom.setConnectionTimeToLive();
        custom.setContentDecoderRegistry();
        custom.setDefaultAuthSchemeRegistry();
        custom.setDefaultConnectionConfig();
        custom.setDefaultCookieSpecRegistry();
        custom.setDefaultCookieStore();
        custom.setDefaultCredentialsProvider();
        custom.setDefaultHeaders();
        custom.setDefaultRequestConfig();
        custom.setDefaultSocketConfig();
        custom.setUserTokenHandler();
        custom.setUserAgent();
        custom.setTargetAuthenticationStrategy();
        custom.setSSLSocketFactory();
        custom.setSSLHostnameVerifier();
        custom.setSSLContext();
        custom.setSchemePortResolver();
        custom.setRoutePlanner();
        custom.setRetryHandler();
        custom.setRequestExecutor();
        custom.setRedirectStrategy();
        custom.setPublicSuffixMatcher();
        custom.setProxyAuthenticationStrategy();
        custom.setProxy();

        HttpProcessor httpProcessor = new ImmutableHttpProcessor(requestInterceptors, responseInterceptors);
        custom.setHttpProcessor(httpProcessor);

        custom.build();


        CloseableHttpResponse execute = httpClient.execute(httpGet);

        System.out.println(execute);

    }




}
