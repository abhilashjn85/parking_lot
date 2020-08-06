package com.gojek.parking.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Base class for all the api classes
 */
public abstract class Base implements Serializable, Cloneable {
    public static final String GET_ID = "getId";
    public static final String GET_UPD = "getLastUpdated";

    private String id;
    private Date created;
    private Date lastUpdated;

    private Integer version;

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getLastUpdated()
    {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }

    public Integer getVersion()
    {
        return version;
    }

    public void setVersion(Integer version)
    {
        this.version = version;
    }

    /**
     * Use this method with caution. The clone is a shallow copy (only).
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException cnse) {
            throw new RuntimeException(cnse);
        }
    }
}
