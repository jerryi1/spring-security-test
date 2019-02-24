package com.example.demo2.archtechture.response;

/**
 * @author lihuaqing
 * @create 2019-02-22 15:32
 **/
import com.example.demo2.archtechture.utils.SpringContextUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.core.env.Environment;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RestResponseHeader
        implements Serializable
{
    private static final long serialVersionUID = 1031448932631195295L;
    private static final String KEY_APP = "spring.application.name";

    public void setCode(String code)
    {
        this.code = code;
    }

    public void setApp(String app)
    {
        this.app = app;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setSubCode(String subCode)
    {
        this.subCode = subCode;
    }

    public void setSubMessage(String subMessage)
    {
        this.subMessage = subMessage;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setErrTrace(String errTrace)
    {
        this.errTrace = errTrace;
    }

    public void setExt(String ext)
    {
        this.ext = ext;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RestResponseHeader)) {
            return false;
        }
        RestResponseHeader other = (RestResponseHeader)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$code = getCode();Object other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) {
            return false;
        }
        Object this$app = getApp();Object other$app = other.getApp();
        if (this$app == null ? other$app != null : !this$app.equals(other$app)) {
            return false;
        }
        Object this$message = getMessage();Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) {
            return false;
        }
        Object this$subCode = getSubCode();Object other$subCode = other.getSubCode();
        if (this$subCode == null ? other$subCode != null : !this$subCode.equals(other$subCode)) {
            return false;
        }
        Object this$subMessage = getSubMessage();Object other$subMessage = other.getSubMessage();
        if (this$subMessage == null ? other$subMessage != null : !this$subMessage.equals(other$subMessage)) {
            return false;
        }
        Object this$type = getType();Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        Object this$errTrace = getErrTrace();Object other$errTrace = other.getErrTrace();
        if (this$errTrace == null ? other$errTrace != null : !this$errTrace.equals(other$errTrace)) {
            return false;
        }
        Object this$ext = getExt();Object other$ext = other.getExt();
        if (this$ext == null ? other$ext != null : !this$ext.equals(other$ext)) {
            return false;
        }
        return Arrays.deepEquals(getStackTraceElements(), other.getStackTraceElements());
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof RestResponseHeader;
    }

    @Override
    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $code = getCode();result = result * 59 + ($code == null ? 43 : $code.hashCode());Object $app = getApp();result = result * 59 + ($app == null ? 43 : $app.hashCode());Object $message = getMessage();result = result * 59 + ($message == null ? 43 : $message.hashCode());Object $subCode = getSubCode();result = result * 59 + ($subCode == null ? 43 : $subCode.hashCode());Object $subMessage = getSubMessage();result = result * 59 + ($subMessage == null ? 43 : $subMessage.hashCode());Object $type = getType();result = result * 59 + ($type == null ? 43 : $type.hashCode());Object $errTrace = getErrTrace();result = result * 59 + ($errTrace == null ? 43 : $errTrace.hashCode());Object $ext = getExt();result = result * 59 + ($ext == null ? 43 : $ext.hashCode());result = result * 59 + Arrays.deepHashCode(getStackTraceElements());return result;
    }

    @Override
    public String toString()
    {
        return "RestResponseHeader(code=" + getCode() + ", app=" + getApp() + ", message=" + getMessage() + ", subCode=" + getSubCode() + ", subMessage=" + getSubMessage() + ", type=" + getType() + ", errTrace=" + getErrTrace() + ", ext=" + getExt() + ", stackTraceElements=" + Arrays.deepToString(getStackTraceElements()) + ")";
    }

    public String getCode()
    {
        return this.code;
    }

    private String code = "10000";

    public String getApp()
    {
        return this.app;
    }

    private String app = null;
    private String message;
    private String subCode;
    private String subMessage;
    private String type;
    private String errTrace;
    private String ext;
    private StackTraceElement[] stackTraceElements;

    public String getMessage()
    {
        return this.message;
    }

    public String getSubCode()
    {
        return this.subCode;
    }

    public String getSubMessage()
    {
        return this.subMessage;
    }

    public String getType()
    {
        return this.type;
    }

    public String getErrTrace()
    {
        return this.errTrace;
    }

    public String getExt()
    {
        return this.ext;
    }

    public RestResponseHeader()
    {
        this.app = ((String)Optional.ofNullable(this.app).orElse(((Environment)SpringContextUtils.getBean(Environment.class)).getProperty("spring.application.name")));
    }

    public RestResponseHeader(String code, String message)
    {
        this.code = code;
        this.message = message;
        this.app = ((String)Optional.ofNullable(this.app).orElse(((Environment) SpringContextUtils.getBean(Environment.class)).getProperty("spring.application.name")));
    }

    public RestResponseHeader(String code, String message, String errTrace)
    {
        this.code = code;
        this.message = message;
        this.errTrace = errTrace;
        this.app = ((String)Optional.ofNullable(this.app).orElse(((Environment)SpringContextUtils.getBean(Environment.class)).getProperty("spring.application.name")));
    }

    public RestResponseHeader(String code, String message, String errType, String errTrace)
    {
        this.code = code;
        this.message = message;
        this.type = errType;
        this.errTrace = errTrace;
        this.app = ((String)Optional.ofNullable(this.app).orElse(((Environment)SpringContextUtils.getBean(Environment.class)).getProperty("spring.application.name")));
    }

    public RestResponseHeader(String code, String message, String subCode, String subMessage, String errType, String errTrace)
    {
        this.code = code;
        this.message = message;
        this.subCode = subCode;
        this.subMessage = subMessage;
        this.type = errType;
        this.errTrace = errTrace;
        this.app = ((String)Optional.ofNullable(this.app).orElse(((Environment)SpringContextUtils.getBean(Environment.class)).getProperty("spring.application.name")));
    }

    public void setStackTraceElements(StackTraceElement[] stackTraceElements)
    {
        if (stackTraceElements != null) {
            this.stackTraceElements = ((StackTraceElement[])stackTraceElements.clone());
        } else {
            this.stackTraceElements = null;
        }
    }

    public StackTraceElement[] getStackTraceElements()
    {
        if (this.stackTraceElements == null) {
            return null;
        }
        return (StackTraceElement[])this.stackTraceElements.clone();
    }
}