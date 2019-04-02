
package ru.javaops.masterjava.service.mail.auto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for groupResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="groupResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="failed" type="{http://mail.javaops.ru/}mailResult" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="failedCause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "groupResult", propOrder = {
    "failed",
    "failedCause",
    "success"
})
public class GroupResult {

    @XmlElement(nillable = true)
    protected List<MailResult> failed;
    protected String failedCause;
    protected int success;

    /**
     * Gets the value of the failed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the failed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFailed().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MailResult }
     * 
     * 
     */
    public List<MailResult> getFailed() {
        if (failed == null) {
            failed = new ArrayList<MailResult>();
        }
        return this.failed;
    }

    /**
     * Gets the value of the failedCause property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailedCause() {
        return failedCause;
    }

    /**
     * Sets the value of the failedCause property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailedCause(String value) {
        this.failedCause = value;
    }

    /**
     * Gets the value of the success property.
     * 
     */
    public int getSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     */
    public void setSuccess(int value) {
        this.success = value;
    }

}
