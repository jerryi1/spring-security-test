package com.example.demo2.archtechture.request;

import com.example.demo2.archtechture.utils.SpringContextUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.tools.javac.util.List;
import org.springframework.core.env.Environment;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author lihuaqing
 * @create 2019-02-22 15:27
 **/
@JsonIgnoreProperties(ignoreUnknown=true)
public class RestRequestHeader
        implements Serializable
{
    private static final long serialVersionUID = -4363387588926602131L;
    private static final String KEY_APP = "spring.application.name";

    public void setApp(String app)
    {
        this.app = app;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }

    public void setSorts(List<RestRequestSort> sorts)
    {
        this.sorts = sorts;
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
        if (!(o instanceof RestRequestHeader)) {
            return false;
        }
        RestRequestHeader other = (RestRequestHeader)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$app = getApp();Object other$app = other.getApp();
        if (this$app == null ? other$app != null : !this$app.equals(other$app)) {
            return false;
        }
        if (getPageSize() != other.getPageSize()) {
            return false;
        }
        if (getPageNum() != other.getPageNum()) {
            return false;
        }
        Object this$sorts = getSorts();Object other$sorts = other.getSorts();
        if (this$sorts == null ? other$sorts != null : !this$sorts.equals(other$sorts)) {
            return false;
        }
        Object this$ext = getExt();Object other$ext = other.getExt();return this$ext == null ? other$ext == null : this$ext.equals(other$ext);
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof RestRequestHeader;
    }

    @Override
    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $app = getApp();result = result * 59 + ($app == null ? 43 : $app.hashCode());result = result * 59 + getPageSize();result = result * 59 + getPageNum();Object $sorts = getSorts();result = result * 59 + ($sorts == null ? 43 : $sorts.hashCode());Object $ext = getExt();result = result * 59 + ($ext == null ? 43 : $ext.hashCode());return result;
    }

    @Override
    public String toString()
    {
        return "RestRequestHeader(app=" + getApp() + ", pageSize=" + getPageSize() + ", pageNum=" + getPageNum() + ", sorts=" + getSorts() + ", ext=" + getExt() + ")";
    }

    public RestRequestHeader(String app, int pageSize, int pageNum, List<RestRequestSort> sorts, String ext)
    {
        this.app = app;this.pageSize = pageSize;this.pageNum = pageNum;this.sorts = sorts;this.ext = ext;
    }

    public String getApp()
    {
        return this.app;
    }

    private String app = null;
    private int pageSize;
    private int pageNum;
    private List<RestRequestSort> sorts;
    private String ext;

    public int getPageSize()
    {
        return this.pageSize;
    }

    public int getPageNum()
    {
        return this.pageNum;
    }

    public List<RestRequestSort> getSorts()
    {
        return this.sorts;
    }

    public String getExt()
    {
        return this.ext;
    }

    public RestRequestHeader()
    {
        this.app = ((String) Optional.ofNullable(this.app).orElse(((Environment) SpringContextUtils.getBean(Environment.class)).getProperty("spring.application.name")));
    }
}
