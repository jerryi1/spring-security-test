package com.example.demo2.archtechture.response;

/**
 * @author lihuaqing
 * @create 2019-02-22 15:30
 **/

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo2.archtechture.utils.MyStringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RestResponse<T>
        implements Serializable
{
    private static final long serialVersionUID = -4118386780685379201L;
    private static final int STACK_TRACE_ELEMENT_INX = 2;
    private RestResponseHeader header;

    public void setHeader(RestResponseHeader header)
    {
        this.header = header;
    }

    public void setBody(T body)
    {
        this.body = body;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RestResponse)) {
            return false;
        }
        RestResponse<?> other = (RestResponse)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$header = getHeader();Object other$header = other.getHeader();
        if (this$header == null ? other$header != null : !this$header.equals(other$header)) {
            return false;
        }
        Object this$body = getBody();Object other$body = other.getBody();return this$body == null ? other$body == null : this$body.equals(other$body);
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof RestResponse;
    }

    @Override
    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $header = getHeader();result = result * 59 + ($header == null ? 43 : $header.hashCode());Object $body = getBody();result = result * 59 + ($body == null ? 43 : $body.hashCode());return result;
    }

    @Override
    public String toString()
    {
        return "RestResponse(header=" + getHeader() + ", body=" + getBody() + ")";
    }

    public RestResponseHeader getHeader()
    {
        return this.header;
    }

    public T getBody()
    {
        return (T)this.body;
    }

    private T body = null;

    public RestResponse()
    {
        this.header = new RestResponseHeader();
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(RestResponseHeader header, T body)
    {
        this.header = header;
        this.body = body;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(RestResponseHeader header)
    {
        this.header = header;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(T body)
    {
        this.header = new RestResponseHeader();
        this.body = body;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(String code, String message)
    {
        this.header = new RestResponseHeader(code, message);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(String code, String message, String errTrace)
    {
        this.header = new RestResponseHeader(code, message, errTrace);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(String code, String message, String errType, String errTrace)
    {
        this.header = new RestResponseHeader(code, message, errType, errTrace);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(String code, String message, String subCode, String subMessage, String errType, String errTrace)
    {
        this.header = new RestResponseHeader(code, message, subCode, subMessage, errType, errTrace);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(String code, String message, T body)
    {
        this.header = new RestResponseHeader(code, message);
        this.body = body;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public String fetchCode()
    {
        return getHeader() != null ? getHeader().getCode() : null;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public String fetchMessage()
    {
        return getHeader() != null ? getHeader().getMessage() : null;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public String fetchSubCode()
    {
        return getHeader() != null ? getHeader().getSubCode() : null;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public String fetchSubMessage()
    {
        return getHeader() != null ? getHeader().getSubMessage() : null;
    }

    public static <T> RestResponse<T> success()
    {
        return new RestResponse();
    }

    public static <T> RestResponse<T> success(T body)
    {
        return new RestResponse(body);
    }

    public static <T> RestResponse<T> buildWithCode(String code)
    {
        RestResponse<T> rr = new RestResponse(code, null);
        rr.makeStackTrace(Thread.currentThread().getStackTrace());
        return rr;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public RestResponse withCode(String code)
    {
        this.header.setCode(code);
        return this;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public RestResponse withMessage(String message)
    {
        this.header.setMessage(message);
        return this;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public RestResponse withMessage(String messagePattern, Object... argArray)
    {
        this.header.setMessage(MyStringUtils.formatMessage(messagePattern, argArray));
        return this;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public RestResponse withSubCode(String subCode)
    {
        this.header.setSubCode(subCode);
        return this;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public RestResponse withSubMessage(String subMessage)
    {
        this.header.setSubMessage(subMessage);
        return this;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public RestResponse withSubMessage(String subMessagePattern, Object... argArray)
    {
        this.header.setSubMessage(MyStringUtils.formatMessage(subMessagePattern, argArray));
        return this;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public RestResponse withErrType(String errType)
    {
        this.header.setType(errType);
        return this;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public RestResponse withErrTrace(String errTrace)
    {
        this.header.setErrTrace(errTrace);
        return this;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public RestResponse withBody(T body)
    {
        this.body = body;
        return this;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public RestResponse withStackTraceElements(StackTraceElement[] stackTraceElements)
    {
        this.header.setStackTraceElements(stackTraceElements);
        return this;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public boolean isSuccess()
    {
        if ((this.header == null) || (StringUtils.isBlank(this.header.getCode()))) {
            return false;
        }
        return StringUtils.equals(this.header.getCode(), "10000");
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public boolean isFailure()
    {
        return !isSuccess();
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public RestResponse transfer()
    {
        RestResponse restResponse = new RestResponse();
        restResponse.setHeader(getHeader());
        restResponse.setBody(getBody());
        return restResponse;
    }

    private void makeStackTrace(StackTraceElement[] stackTraceElements)
    {
        if ((stackTraceElements != null) && (stackTraceElements.length > 1)) {
            this.header.setStackTraceElements(new StackTraceElement[] { stackTraceElements[2] });
        }
    }
}
