
package ru.javaops.masterjava.service.mail.auto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.javaops.masterjava.service.mail.auto package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SendToGroup_QNAME = new QName("http://mail.javaops.ru/", "sendToGroup");
    private final static QName _SendBulk_QNAME = new QName("http://mail.javaops.ru/", "sendBulk");
    private final static QName _SendBulkResponse_QNAME = new QName("http://mail.javaops.ru/", "sendBulkResponse");
    private final static QName _SendToGroupResponse_QNAME = new QName("http://mail.javaops.ru/", "sendToGroupResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.javaops.masterjava.service.mail.auto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendToGroupResponse }
     * 
     */
    public SendToGroupResponse createSendToGroupResponse() {
        return new SendToGroupResponse();
    }

    /**
     * Create an instance of {@link SendBulkResponse }
     * 
     */
    public SendBulkResponse createSendBulkResponse() {
        return new SendBulkResponse();
    }

    /**
     * Create an instance of {@link SendBulk }
     * 
     */
    public SendBulk createSendBulk() {
        return new SendBulk();
    }

    /**
     * Create an instance of {@link SendToGroup }
     * 
     */
    public SendToGroup createSendToGroup() {
        return new SendToGroup();
    }

    /**
     * Create an instance of {@link Addressee }
     * 
     */
    public Addressee createAddressee() {
        return new Addressee();
    }

    /**
     * Create an instance of {@link GroupResult }
     * 
     */
    public GroupResult createGroupResult() {
        return new GroupResult();
    }

    /**
     * Create an instance of {@link MailResult }
     * 
     */
    public MailResult createMailResult() {
        return new MailResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendToGroup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mail.javaops.ru/", name = "sendToGroup")
    public JAXBElement<SendToGroup> createSendToGroup(SendToGroup value) {
        return new JAXBElement<SendToGroup>(_SendToGroup_QNAME, SendToGroup.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendBulk }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mail.javaops.ru/", name = "sendBulk")
    public JAXBElement<SendBulk> createSendBulk(SendBulk value) {
        return new JAXBElement<SendBulk>(_SendBulk_QNAME, SendBulk.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendBulkResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mail.javaops.ru/", name = "sendBulkResponse")
    public JAXBElement<SendBulkResponse> createSendBulkResponse(SendBulkResponse value) {
        return new JAXBElement<SendBulkResponse>(_SendBulkResponse_QNAME, SendBulkResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendToGroupResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mail.javaops.ru/", name = "sendToGroupResponse")
    public JAXBElement<SendToGroupResponse> createSendToGroupResponse(SendToGroupResponse value) {
        return new JAXBElement<SendToGroupResponse>(_SendToGroupResponse_QNAME, SendToGroupResponse.class, null, value);
    }

}
