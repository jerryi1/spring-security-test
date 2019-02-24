package com.example.demo2.archtechture.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @author lihuaqing
 * @create 2019-02-22 15:25
 **/
@JsonIgnoreProperties(ignoreUnknown=true)
public class RestRequest<T>
        implements Serializable {
    private static final long serialVersionUID = -1296515585582912062L;
    private RestRequestHeader header;

    public void setHeader(RestRequestHeader header) {
        this.header = header;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RestRequest)) {
            return false;
        }
        RestRequest<?> other = (RestRequest) o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$header = getHeader();
        Object other$header = other.getHeader();
        if (this$header == null ? other$header != null : !this$header.equals(other$header)) {
            return false;
        }
        Object this$body = getBody();
        Object other$body = other.getBody();
        return this$body == null ? other$body == null : this$body.equals(other$body);
    }

    protected boolean canEqual(Object other) {
        return other instanceof RestRequest;
    }

    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $header = getHeader();
        result = result * 59 + ($header == null ? 43 : $header.hashCode());
        Object $body = getBody();
        result = result * 59 + ($body == null ? 43 : $body.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RestRequest(header=" + getHeader() + ", body=" + getBody() + ")";
    }

    public RestRequestHeader getHeader() {
        return this.header;
    }

    @Valid
    private T body = null;

    public T getBody() {
        return (T) this.body;
    }

    public RestRequest() {
        this.header = new RestRequestHeader();
    }

    public RestRequest(T body) {
        this.header = new RestRequestHeader();
        this.body = body;
    }

    public static <T> RestRequest<T> instance(T body) {
        return new RestRequest(body);
    }
}