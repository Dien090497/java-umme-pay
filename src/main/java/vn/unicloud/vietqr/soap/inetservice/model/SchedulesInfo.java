//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.08 at 01:42:57 PM NOVT 
//


package vn.unicloud.vietqr.soap.inetservice.model;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


/**
 * <p>Java class for SchedulesInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SchedulesInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FrAcct" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="FrRtxnNbr" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ToAcct" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CostAcctNbr" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TranAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CostAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="NgayGd" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="CashBoxNbr" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="OrigPersNbr" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ApprPersNbr" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="IntRtxnDescText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChargeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tronghtyn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PayTo_NN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cmnd_NN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Teldc_NN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SoTK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SHNH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InternalDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NguoiGui" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cmnd_NG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Teldc_NG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NgayTao" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="TrangThai" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GhiChu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransTyp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DateLastMaint" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FrFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FrProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FrProvinceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToProvinceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FrBranchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FrBranchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToBranchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExtCheckNbr" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SchedulesInfo", propOrder = {
    "transId",
    "frAcct",
    "frRtxnNbr",
    "toAcct",
    "costAcctNbr",
    "tranAmount",
    "costAmount",
    "ngayGd",
    "cashBoxNbr",
    "origPersNbr",
    "apprPersNbr",
    "intRtxnDescText",
    "chargeType",
    "tronghtyn",
    "payToNN",
    "cmndNN",
    "teldcNN",
    "soTK",
    "shnh",
    "internalDesc",
    "nguoiGui",
    "cmndNG",
    "teldcNG",
    "ngayTao",
    "dueDate",
    "trangThai",
    "ghiChu",
    "transTyp",
    "dateLastMaint",
    "frFullName",
    "frProvinceCode",
    "frProvinceName",
    "toProvinceCode",
    "toProvinceName",
    "frBranchCode",
    "frBranchName",
    "toBranchName",
    "extCheckNbr"
})
public class SchedulesInfo {

    @XmlElement(name = "TransId")
    protected String transId;
    @XmlElement(name = "FrAcct", required = true)
    protected BigDecimal frAcct;
    @XmlElement(name = "FrRtxnNbr", required = true)
    protected BigDecimal frRtxnNbr;
    @XmlElement(name = "ToAcct", required = true)
    protected BigDecimal toAcct;
    @XmlElement(name = "CostAcctNbr", required = true)
    protected BigDecimal costAcctNbr;
    @XmlElement(name = "TranAmount", required = true)
    protected BigDecimal tranAmount;
    @XmlElement(name = "CostAmount", required = true)
    protected BigDecimal costAmount;
    @XmlElement(name = "NgayGd", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ngayGd;
    @XmlElement(name = "CashBoxNbr", required = true)
    protected BigDecimal cashBoxNbr;
    @XmlElement(name = "OrigPersNbr", required = true)
    protected BigDecimal origPersNbr;
    @XmlElement(name = "ApprPersNbr", required = true)
    protected BigDecimal apprPersNbr;
    @XmlElement(name = "IntRtxnDescText")
    protected String intRtxnDescText;
    @XmlElement(name = "ChargeType")
    protected String chargeType;
    @XmlElement(name = "Tronghtyn")
    protected String tronghtyn;
    @XmlElement(name = "PayTo_NN")
    protected String payToNN;
    @XmlElement(name = "Cmnd_NN")
    protected String cmndNN;
    @XmlElement(name = "Teldc_NN")
    protected String teldcNN;
    @XmlElement(name = "SoTK")
    protected String soTK;
    @XmlElement(name = "SHNH")
    protected String shnh;
    @XmlElement(name = "InternalDesc")
    protected String internalDesc;
    @XmlElement(name = "NguoiGui")
    protected String nguoiGui;
    @XmlElement(name = "Cmnd_NG")
    protected String cmndNG;
    @XmlElement(name = "Teldc_NG")
    protected String teldcNG;
    @XmlElement(name = "NgayTao", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ngayTao;
    @XmlElement(name = "DueDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dueDate;
    @XmlElement(name = "TrangThai")
    protected String trangThai;
    @XmlElement(name = "GhiChu")
    protected String ghiChu;
    @XmlElement(name = "TransTyp")
    protected String transTyp;
    @XmlElement(name = "DateLastMaint", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateLastMaint;
    @XmlElement(name = "FrFullName")
    protected String frFullName;
    @XmlElement(name = "FrProvinceCode")
    protected String frProvinceCode;
    @XmlElement(name = "FrProvinceName")
    protected String frProvinceName;
    @XmlElement(name = "ToProvinceCode")
    protected String toProvinceCode;
    @XmlElement(name = "ToProvinceName")
    protected String toProvinceName;
    @XmlElement(name = "FrBranchCode")
    protected String frBranchCode;
    @XmlElement(name = "FrBranchName")
    protected String frBranchName;
    @XmlElement(name = "ToBranchName")
    protected String toBranchName;
    @XmlElement(name = "ExtCheckNbr", required = true)
    protected BigDecimal extCheckNbr;

    /**
     * Gets the value of the transId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransId() {
        return transId;
    }

    /**
     * Sets the value of the transId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransId(String value) {
        this.transId = value;
    }

    /**
     * Gets the value of the frAcct property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFrAcct() {
        return frAcct;
    }

    /**
     * Sets the value of the frAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFrAcct(BigDecimal value) {
        this.frAcct = value;
    }

    /**
     * Gets the value of the frRtxnNbr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFrRtxnNbr() {
        return frRtxnNbr;
    }

    /**
     * Sets the value of the frRtxnNbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFrRtxnNbr(BigDecimal value) {
        this.frRtxnNbr = value;
    }

    /**
     * Gets the value of the toAcct property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getToAcct() {
        return toAcct;
    }

    /**
     * Sets the value of the toAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setToAcct(BigDecimal value) {
        this.toAcct = value;
    }

    /**
     * Gets the value of the costAcctNbr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCostAcctNbr() {
        return costAcctNbr;
    }

    /**
     * Sets the value of the costAcctNbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCostAcctNbr(BigDecimal value) {
        this.costAcctNbr = value;
    }

    /**
     * Gets the value of the tranAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTranAmount() {
        return tranAmount;
    }

    /**
     * Sets the value of the tranAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTranAmount(BigDecimal value) {
        this.tranAmount = value;
    }

    /**
     * Gets the value of the costAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCostAmount() {
        return costAmount;
    }

    /**
     * Sets the value of the costAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCostAmount(BigDecimal value) {
        this.costAmount = value;
    }

    /**
     * Gets the value of the ngayGd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNgayGd() {
        return ngayGd;
    }

    /**
     * Sets the value of the ngayGd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNgayGd(XMLGregorianCalendar value) {
        this.ngayGd = value;
    }

    /**
     * Gets the value of the cashBoxNbr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCashBoxNbr() {
        return cashBoxNbr;
    }

    /**
     * Sets the value of the cashBoxNbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCashBoxNbr(BigDecimal value) {
        this.cashBoxNbr = value;
    }

    /**
     * Gets the value of the origPersNbr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOrigPersNbr() {
        return origPersNbr;
    }

    /**
     * Sets the value of the origPersNbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOrigPersNbr(BigDecimal value) {
        this.origPersNbr = value;
    }

    /**
     * Gets the value of the apprPersNbr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getApprPersNbr() {
        return apprPersNbr;
    }

    /**
     * Sets the value of the apprPersNbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setApprPersNbr(BigDecimal value) {
        this.apprPersNbr = value;
    }

    /**
     * Gets the value of the intRtxnDescText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntRtxnDescText() {
        return intRtxnDescText;
    }

    /**
     * Sets the value of the intRtxnDescText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntRtxnDescText(String value) {
        this.intRtxnDescText = value;
    }

    /**
     * Gets the value of the chargeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * Sets the value of the chargeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeType(String value) {
        this.chargeType = value;
    }

    /**
     * Gets the value of the tronghtyn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTronghtyn() {
        return tronghtyn;
    }

    /**
     * Sets the value of the tronghtyn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTronghtyn(String value) {
        this.tronghtyn = value;
    }

    /**
     * Gets the value of the payToNN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayToNN() {
        return payToNN;
    }

    /**
     * Sets the value of the payToNN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayToNN(String value) {
        this.payToNN = value;
    }

    /**
     * Gets the value of the cmndNN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmndNN() {
        return cmndNN;
    }

    /**
     * Sets the value of the cmndNN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmndNN(String value) {
        this.cmndNN = value;
    }

    /**
     * Gets the value of the teldcNN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeldcNN() {
        return teldcNN;
    }

    /**
     * Sets the value of the teldcNN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeldcNN(String value) {
        this.teldcNN = value;
    }

    /**
     * Gets the value of the soTK property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoTK() {
        return soTK;
    }

    /**
     * Sets the value of the soTK property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoTK(String value) {
        this.soTK = value;
    }

    /**
     * Gets the value of the shnh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSHNH() {
        return shnh;
    }

    /**
     * Sets the value of the shnh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSHNH(String value) {
        this.shnh = value;
    }

    /**
     * Gets the value of the internalDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternalDesc() {
        return internalDesc;
    }

    /**
     * Sets the value of the internalDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternalDesc(String value) {
        this.internalDesc = value;
    }

    /**
     * Gets the value of the nguoiGui property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNguoiGui() {
        return nguoiGui;
    }

    /**
     * Sets the value of the nguoiGui property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNguoiGui(String value) {
        this.nguoiGui = value;
    }

    /**
     * Gets the value of the cmndNG property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmndNG() {
        return cmndNG;
    }

    /**
     * Sets the value of the cmndNG property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmndNG(String value) {
        this.cmndNG = value;
    }

    /**
     * Gets the value of the teldcNG property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeldcNG() {
        return teldcNG;
    }

    /**
     * Sets the value of the teldcNG property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeldcNG(String value) {
        this.teldcNG = value;
    }

    /**
     * Gets the value of the ngayTao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNgayTao() {
        return ngayTao;
    }

    /**
     * Sets the value of the ngayTao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNgayTao(XMLGregorianCalendar value) {
        this.ngayTao = value;
    }

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDate(XMLGregorianCalendar value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the trangThai property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrangThai() {
        return trangThai;
    }

    /**
     * Sets the value of the trangThai property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrangThai(String value) {
        this.trangThai = value;
    }

    /**
     * Gets the value of the ghiChu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     * Sets the value of the ghiChu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGhiChu(String value) {
        this.ghiChu = value;
    }

    /**
     * Gets the value of the transTyp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransTyp() {
        return transTyp;
    }

    /**
     * Sets the value of the transTyp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransTyp(String value) {
        this.transTyp = value;
    }

    /**
     * Gets the value of the dateLastMaint property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateLastMaint() {
        return dateLastMaint;
    }

    /**
     * Sets the value of the dateLastMaint property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateLastMaint(XMLGregorianCalendar value) {
        this.dateLastMaint = value;
    }

    /**
     * Gets the value of the frFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrFullName() {
        return frFullName;
    }

    /**
     * Sets the value of the frFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrFullName(String value) {
        this.frFullName = value;
    }

    /**
     * Gets the value of the frProvinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrProvinceCode() {
        return frProvinceCode;
    }

    /**
     * Sets the value of the frProvinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrProvinceCode(String value) {
        this.frProvinceCode = value;
    }

    /**
     * Gets the value of the frProvinceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrProvinceName() {
        return frProvinceName;
    }

    /**
     * Sets the value of the frProvinceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrProvinceName(String value) {
        this.frProvinceName = value;
    }

    /**
     * Gets the value of the toProvinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToProvinceCode() {
        return toProvinceCode;
    }

    /**
     * Sets the value of the toProvinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToProvinceCode(String value) {
        this.toProvinceCode = value;
    }

    /**
     * Gets the value of the toProvinceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToProvinceName() {
        return toProvinceName;
    }

    /**
     * Sets the value of the toProvinceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToProvinceName(String value) {
        this.toProvinceName = value;
    }

    /**
     * Gets the value of the frBranchCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrBranchCode() {
        return frBranchCode;
    }

    /**
     * Sets the value of the frBranchCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrBranchCode(String value) {
        this.frBranchCode = value;
    }

    /**
     * Gets the value of the frBranchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrBranchName() {
        return frBranchName;
    }

    /**
     * Sets the value of the frBranchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrBranchName(String value) {
        this.frBranchName = value;
    }

    /**
     * Gets the value of the toBranchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToBranchName() {
        return toBranchName;
    }

    /**
     * Sets the value of the toBranchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToBranchName(String value) {
        this.toBranchName = value;
    }

    /**
     * Gets the value of the extCheckNbr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExtCheckNbr() {
        return extCheckNbr;
    }

    /**
     * Sets the value of the extCheckNbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExtCheckNbr(BigDecimal value) {
        this.extCheckNbr = value;
    }

}
