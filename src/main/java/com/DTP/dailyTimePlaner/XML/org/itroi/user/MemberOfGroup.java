//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.12.04 at 05:22:44 PM EET 
//


package com.DTP.dailyTimePlaner.XML.org.itroi.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.DTP.dailyTimePlaner.XML.org.itroi.group.GroupType;


/**
 * <p>Java class for memberOfGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="memberOfGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="group" type="{http://www.itroi.org/group}GroupType"/>
 *         &lt;element name="groupRole" type="{http://www.itroi.org/user}groupRole"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "memberOfGroup", propOrder = {
    "group",
    "groupRole"
})
public class MemberOfGroup {

    @XmlElement(required = true)
    protected GroupType group;
    @XmlElement(required = true)
    protected GroupRole groupRole;

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link GroupType }
     *     
     */
    public GroupType getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupType }
     *     
     */
    public void setGroup(GroupType value) {
        this.group = value;
    }

    /**
     * Gets the value of the groupRole property.
     * 
     * @return
     *     possible object is
     *     {@link GroupRole }
     *     
     */
    public GroupRole getGroupRole() {
        return groupRole;
    }

    /**
     * Sets the value of the groupRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupRole }
     *     
     */
    public void setGroupRole(GroupRole value) {
        this.groupRole = value;
    }

}
