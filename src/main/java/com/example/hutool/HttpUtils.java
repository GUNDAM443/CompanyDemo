package com.example.hutool;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/**
 * http工具
 *
 * @author qingzhou
 * 2017-12-08 14:45
 */
@Slf4j
public class HttpUtils {

    private static final String HTTP_METHOD_GET = "GET";
    private static final String HTTP_METHOD_POST = "POST";
    private static final String DEFAULT_CHARACTER = "UTF-8";
    private static final String CONTENT_ENCODING_GZIP = "gzip";
    private static boolean ignoreSSLCheck = true; // 忽略SSL检查
    private static boolean ignoreHostCheck = true; // 忽略HOST检查

    private HttpUtils() {
    }

    public static String doPost(String url, String body, int connectTimeout, int readTimeout) throws IOException {
        String charset = DEFAULT_CHARACTER;
        String ctype = "application/json;charset=" + charset;
        byte[] content = body.getBytes(charset);
        return _doPost(url, ctype, content, connectTimeout, readTimeout, null, null);
    }

    public static void main(String[] args) {
        String body = "{\n" +
                "\"type\":\"areaAlarm\",\n" +
                "\"requestServerUrl\":\"172.16.10.66\",\n" +
                "\"buildId\":200036\n" +
                "}";
        try {
            doPost("http://172.16.10.9:8888/api/v2/subscribe", body, 15000, 3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean apiDoPost(String url, String body, int connectTimeout, int readTimeout) {
        try {
            String charset = DEFAULT_CHARACTER;
            String ctype = "application/json;charset=" + charset;
            byte[] content = body.getBytes(charset);
            Integer stateCode = _apiDoPost(url, ctype, content, connectTimeout, readTimeout, null, null);
            if (stateCode == HttpURLConnection.HTTP_OK) {
                //等于200直接返回
                return true;
            }
        } catch (Exception e) {
            log.error("异常" + e);
        }
        return false;
    }

    public static String doPost(String url, String body, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws IOException {
        String charset = DEFAULT_CHARACTER;
        String ctype = "application/json;charset=" + charset;
        byte[] content = body.getBytes(charset);
        return _doPost(url, ctype, content, connectTimeout, readTimeout, headerMap, null);
    }

    public static String doPost(String url, String body, String charset, int connectTimeout, int readTimeout) throws IOException {
        String ctype = "text/plain;charset=" + charset;
        byte[] content = body.getBytes(charset);
        return _doPost(url, ctype, content, connectTimeout, readTimeout, null, null);
    }

    private static String _doPost(String url, String ctype, byte[] content, int connectTimeout, int readTimeout,
                                  Map<String, String> headerMap, Proxy proxy) throws IOException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            conn = getConnection(new URL(url), HTTP_METHOD_POST, ctype, headerMap, proxy);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);
        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    private static Integer _apiDoPost(String url, String ctype, byte[] content, int connectTimeout, int readTimeout,
                                      Map<String, String> headerMap, Proxy proxy) throws IOException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        Integer rsp = null;
        try {
            conn = getConnection(new URL(url), HTTP_METHOD_POST, ctype, headerMap, proxy);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            out = conn.getOutputStream();
            out.write(content);
            String responseAsString = getResponseAsString(conn);
            log.info("接口返回=========>>>{}", responseAsString);
            rsp = conn.getResponseCode();
        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rsp;
    }

    private static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        if (conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
            String contentEncoding = conn.getContentEncoding();
            if (CONTENT_ENCODING_GZIP.equalsIgnoreCase(contentEncoding)) {
                return getStreamAsString(new GZIPInputStream(conn.getInputStream()), charset);
            } else {
                return getStreamAsString(conn.getInputStream(), charset);
            }
        } else {
            // OAuth bad request always return 400 status
            if (conn.getResponseCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
                InputStream error = conn.getErrorStream();
                if (error != null) {
                    return getStreamAsString(error, charset);
                }
            }
            // Client Error 4xx and Server Error 5xx
            throw new IOException(conn.getResponseCode() + " " + conn.getResponseMessage());
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARACTER;

        if (ctype != null && ctype.trim().length() > 0) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (pair[1] != null && pair[1].trim().length() > 0) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }


    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            Reader reader = new InputStreamReader(stream, charset);
            StringBuilder response = new StringBuilder();

            final char[] buff = new char[1024];
            int read = 0;
            while ((read = reader.read(buff)) > 0) {
                response.append(buff, 0, read);
            }

            return response.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }


    private static HttpURLConnection getConnection(URL url, String method, String ctype, Map<String, String> headerMap, Proxy proxy) throws IOException {
        HttpURLConnection conn = null;
        if (proxy == null) {
            conn = (HttpURLConnection) url.openConnection();
        } else {
            conn = (HttpURLConnection) url.openConnection(proxy);
        }
        if (conn instanceof HttpsURLConnection) {
            HttpsURLConnection connHttps = (HttpsURLConnection) conn;
            if (ignoreSSLCheck) {
                try {
                    SSLContext ctx = SSLContext.getInstance("TLS");
                    ctx.init(null, new TrustManager[]{new TrustAllTrustManager()}, new SecureRandom());
                    connHttps.setSSLSocketFactory(ctx.getSocketFactory());
                    connHttps.setHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });
                } catch (Exception e) {
                    throw new IOException(e.toString());
                }
            } else {
                if (ignoreHostCheck) {
                    connHttps.setHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });
                }
            }
            conn = connHttps;
        }

        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("User-Agent", "openapi-java");
        conn.setRequestProperty("Content-Type", ctype);
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return conn;
    }

    public static class TrustAllTrustManager implements X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }

    public static String doGet(String inputurl, Map<String, Object> params, String headers) {
        if (StringUtils.notEmpty(inputurl)) {
            String paramsString = contactParams(params);
            if (StringUtils.notEmpty(paramsString)) {
                inputurl += paramsString;
            }
            log.info("****** Get Url[" + inputurl + "] ******");
            HttpURLConnection httpUrlConn = null;
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(inputurl);
                httpUrlConn = (HttpURLConnection) url.openConnection();
                httpUrlConn.setReadTimeout(5000);
                httpUrlConn.setConnectTimeout(5000);
                if (StringUtils.notEmpty(headers)) {
                    String[] hs = headers.split("&");
                    if (hs.length > 0) {
                        for (String headerStr : hs) {
                            String[] headerKV = headerStr.split("=");
                            if (headerKV != null && headerKV.length == 2) {
                                httpUrlConn.setRequestProperty(headerKV[0], headerKV[1]);
                            }
                        }
                    }
                }
                httpUrlConn.setDoOutput(false);
                httpUrlConn.setDoInput(true);
                httpUrlConn.setUseCaches(false);

                httpUrlConn.setRequestMethod("GET");
                httpUrlConn.connect();

                bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), "utf-8"));

                String str = null;
                StringBuffer buffer = new StringBuffer();
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                return buffer.toString();
            } catch (Exception e) {
                log.error("****** Get[" + inputurl + "] error ******", e);
                e.printStackTrace();
            } finally {
                if (httpUrlConn != null) {
                    httpUrlConn.disconnect();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return null;
    }

    public static String doGet(String inputurl, Map<String, Object> params) {
        return doGet(inputurl, params, null);
    }

    public static String contactParams(Map<String, Object> params) {
        if (params != null && params.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("?");
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                buffer.append(key + "=" + params.get(key) + "&");
            }
            if (buffer.length() > 0) {
                buffer.deleteCharAt(buffer.length() - 1);
            }
            return buffer.toString();
        }
        return null;
    }
}
