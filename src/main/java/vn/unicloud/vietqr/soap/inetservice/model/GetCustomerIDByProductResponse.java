//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.08 at 01:42:57 PM NOVT 
//


package vn.unicloud.vietqr.soap.inetservice.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetCustomerIDByProductResult" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getCustomerIDByProductResult"
})
@XmlRootElement(name = "GetCustomerIDByProductResponse")
public class GetCustomerIDByProductResponse {

    @XmlElement(name = "GetCustomerIDByProductResult")
    protected GetCustomerIDByProductResult getCustomerIDByProductResult;

    /**
     * Gets the value of the getCustomerIDByProductResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetCustomerIDByProductResult }
     *     
     */
    public GetCustomerIDByProductResult getGetCustomerIDByProductResult() {
        return getCustomerIDByProductResult;
    }

    /**
     * Sets the value of the getCustomerIDByProductResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCustomerIDByProductResult }
     *     
     */
    public void setGetCustomerIDByProductResult(GetCustomerIDByProductResult value) {
        this.getCustomerIDByProductResult = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class GetCustomerIDByProductResult {


    }

}
