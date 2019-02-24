package com.example.demo2.archtechture.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RestRequestSort
        implements Serializable
{
    private static final long serialVersionUID = 5255059459988467968L;
    private String field;
    private String type;

    public void setField(String field)
    {
        this.field = field;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RestRequestSort)) {
            return false;
        }
        RestRequestSort other = (RestRequestSort)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$field = getField();Object other$field = other.getField();
        if (this$field == null ? other$field != null : !this$field.equals(other$field)) {
            return false;
        }
        Object this$type = getType();Object other$type = other.getType();return this$type == null ? other$type == null : this$type.equals(other$type);
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof RestRequestSort;
    }

    @Override
    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $field = getField();result = result * 59 + ($field == null ? 43 : $field.hashCode());Object $type = getType();result = result * 59 + ($type == null ? 43 : $type.hashCode());return result;
    }

    @Override
    public String toString()
    {
        return "RestRequestSort(field=" + getField() + ", type=" + getType() + ")";
    }

    public RestRequestSort(String field, String type)
    {
        this.field = field;this.type = type;
    }

    public String getField()
    {
        return this.field;
    }

    public String getType()
    {
        return this.type;
    }

    public RestRequestSort() {}
}
