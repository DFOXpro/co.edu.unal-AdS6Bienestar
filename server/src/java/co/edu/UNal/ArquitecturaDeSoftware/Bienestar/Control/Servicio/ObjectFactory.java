
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Servicio;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Servici package. 
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

    private final static QName _GetBestStudents_QNAME = new QName("http://Service/", "getBestStudents");
    private final static QName _Student_QNAME = new QName("http://Service/", "student");
    private final static QName _GetBestStudentsResponse_QNAME = new QName("http://Service/", "getBestStudentsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Servici
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    public Student createStudent() {
        return new Student();
    }

    /**
     * Create an instance of {@link GetBestStudents }
     * 
     */
    public GetBestStudents createGetBestStudents() {
        return new GetBestStudents();
    }

    /**
     * Create an instance of {@link GetBestStudentsResponse }
     * 
     */
    public GetBestStudentsResponse createGetBestStudentsResponse() {
        return new GetBestStudentsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBestStudents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Service/", name = "getBestStudents")
    public JAXBElement<GetBestStudents> createGetBestStudents(GetBestStudents value) {
        return new JAXBElement<GetBestStudents>(_GetBestStudents_QNAME, GetBestStudents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Student }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Service/", name = "student")
    public JAXBElement<Student> createStudent(Student value) {
        return new JAXBElement<Student>(_Student_QNAME, Student.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBestStudentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Service/", name = "getBestStudentsResponse")
    public JAXBElement<GetBestStudentsResponse> createGetBestStudentsResponse(GetBestStudentsResponse value) {
        return new JAXBElement<GetBestStudentsResponse>(_GetBestStudentsResponse_QNAME, GetBestStudentsResponse.class, null, value);
    }

}
