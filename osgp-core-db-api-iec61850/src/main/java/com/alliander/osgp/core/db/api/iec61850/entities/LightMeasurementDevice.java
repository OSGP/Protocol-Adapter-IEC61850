/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.core.db.api.iec61850.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class LightMeasurementDevice extends Device {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3318500857714946908L;

    /**
     * Device type indicator for LMD
     */
    public static final String LMD_TYPE = "LMD";

    @Column
    private String description;

    @Column
    private String code;

    @Column
    private String color;

    @Column
    private Short digitalInput;

    @Column
    private Date lastCommunicationTime;

    public LightMeasurementDevice() {
        // Default constructor.
    }

    public LightMeasurementDevice(final String deviceIdentification) {
        super(deviceIdentification);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public Short getDigitalInput() {
        return this.digitalInput;
    }

    public void setDigitalInput(final Short digitalInput) {
        this.digitalInput = digitalInput;
    }

    public Date getLastCommunicationTime() {
        return this.lastCommunicationTime;
    }

    public void setLastCommunicationTime(final Date lastCommunicationTime) {
        this.lastCommunicationTime = lastCommunicationTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final LightMeasurementDevice device = (LightMeasurementDevice) o;
        return Objects.equals(this.deviceIdentification, device.deviceIdentification);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.deviceIdentification);
    }

    @Override
    public String toString() {
        return this.deviceIdentification + " of type: " + this.deviceType + " with digital input: " + this.digitalInput;
    }
}
