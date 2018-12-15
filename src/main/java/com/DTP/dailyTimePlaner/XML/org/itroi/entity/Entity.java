//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.12.04 at 05:22:44 PM EET 
//


package com.DTP.dailyTimePlaner.XML.org.itroi.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupType;
import com.DTP.dailyTimePlaner.XML.org.itroi.reminder.ReminderType;
import com.DTP.dailyTimePlaner.XML.org.itroi.task.StatusType;
import com.DTP.dailyTimePlaner.XML.org.itroi.task.TaskType;
import com.DTP.dailyTimePlaner.XML.org.itroi.user.GroupRoleType;
import javax.persistence.*;


/**
 * <p>Java class for Entity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Entity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" use="required" type="{http://www.itroi.org/entity}idType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity")
@XmlSeeAlso({
    TaskType.class,
    StatusType.class,
    com.DTP.dailyTimePlaner.XML.org.itroi.task.UserType.class,
    com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType.class,
    GroupRoleType.class,
    GroupType.class,
    ReminderType.class
})
@MappedSuperclass
public class Entity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute(name = "id", required = true)
    protected Integer id;

    /**
     * Gets the value of the id property.
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(Integer value) {
        this.id = value;
    }

}
