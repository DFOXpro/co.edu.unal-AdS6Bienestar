
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para student complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="student">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estAge" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="estBenefit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estDocument" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="estEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estGender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="estLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estRoll" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estTelephone" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="estUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "student", propOrder = {
    "estAddress",
    "estAge",
    "estBenefit",
    "estDocument",
    "estEmail",
    "estGender",
    "estId",
    "estLastName",
    "estName",
    "estPassword",
    "estRoll",
    "estTelephone",
    "estUsername"
})
public class Student {

    protected String estAddress;
    protected Integer estAge;
    protected String estBenefit;
    protected long estDocument;
    protected String estEmail;
    protected String estGender;
    protected Integer estId;
    protected String estLastName;
    protected String estName;
    protected String estPassword;
    protected String estRoll;
    protected long estTelephone;
    protected String estUsername;

    /**
     * Obtiene el valor de la propiedad estAddress.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstAddress() {
        return estAddress;
    }

    /**
     * Define el valor de la propiedad estAddress.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstAddress(String value) {
        this.estAddress = value;
    }

    /**
     * Obtiene el valor de la propiedad estAge.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstAge() {
        return estAge;
    }

    /**
     * Define el valor de la propiedad estAge.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstAge(Integer value) {
        this.estAge = value;
    }

    /**
     * Obtiene el valor de la propiedad estBenefit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstBenefit() {
        return estBenefit;
    }

    /**
     * Define el valor de la propiedad estBenefit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstBenefit(String value) {
        this.estBenefit = value;
    }

    /**
     * Obtiene el valor de la propiedad estDocument.
     * 
     */
    public long getEstDocument() {
        return estDocument;
    }

    /**
     * Define el valor de la propiedad estDocument.
     * 
     */
    public void setEstDocument(long value) {
        this.estDocument = value;
    }

    /**
     * Obtiene el valor de la propiedad estEmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstEmail() {
        return estEmail;
    }

    /**
     * Define el valor de la propiedad estEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstEmail(String value) {
        this.estEmail = value;
    }

    /**
     * Obtiene el valor de la propiedad estGender.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstGender() {
        return estGender;
    }

    /**
     * Define el valor de la propiedad estGender.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstGender(String value) {
        this.estGender = value;
    }

    /**
     * Obtiene el valor de la propiedad estId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstId() {
        return estId;
    }

    /**
     * Define el valor de la propiedad estId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstId(Integer value) {
        this.estId = value;
    }

    /**
     * Obtiene el valor de la propiedad estLastName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstLastName() {
        return estLastName;
    }

    /**
     * Define el valor de la propiedad estLastName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstLastName(String value) {
        this.estLastName = value;
    }

    /**
     * Obtiene el valor de la propiedad estName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstName() {
        return estName;
    }

    /**
     * Define el valor de la propiedad estName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstName(String value) {
        this.estName = value;
    }

    /**
     * Obtiene el valor de la propiedad estPassword.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstPassword() {
        return estPassword;
    }

    /**
     * Define el valor de la propiedad estPassword.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstPassword(String value) {
        this.estPassword = value;
    }

    /**
     * Obtiene el valor de la propiedad estRoll.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstRoll() {
        return estRoll;
    }

    /**
     * Define el valor de la propiedad estRoll.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstRoll(String value) {
        this.estRoll = value;
    }

    /**
     * Obtiene el valor de la propiedad estTelephone.
     * 
     */
    public long getEstTelephone() {
        return estTelephone;
    }

    /**
     * Define el valor de la propiedad estTelephone.
     * 
     */
    public void setEstTelephone(long value) {
        this.estTelephone = value;
    }

    /**
     * Obtiene el valor de la propiedad estUsername.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstUsername() {
        return estUsername;
    }

    /**
     * Define el valor de la propiedad estUsername.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstUsername(String value) {
        this.estUsername = value;
    }

}
