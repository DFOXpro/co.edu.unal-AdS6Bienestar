/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author snipercat
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Convocatoria.findAll", query = "SELECT c FROM Convocatoria c"),
    @NamedQuery(name = "Convocatoria.findByIdConvocatoria", query = "SELECT c FROM Convocatoria c WHERE c.idConvocatoria = :idConvocatoria"),
    @NamedQuery(name = "Convocatoria.findByNombre", query = "SELECT c FROM Convocatoria c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Convocatoria.findByDescripcion", query = "SELECT c FROM Convocatoria c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Convocatoria.findByFechaFinRegistro", query = "SELECT c FROM Convocatoria c WHERE c.fechaFinRegistro = :fechaFinRegistro"),
    @NamedQuery(name = "Convocatoria.findByCupos", query = "SELECT c FROM Convocatoria c WHERE c.cupos = :cupos")})
public class ConvocatoriaEntity implements Serializable, Entity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CONVOCATORIA")
    private Integer idConvocatoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaFinRegistro;
    @Basic(optional = false)
    @NotNull
    private int cupos;

    public ConvocatoriaEntity() {
    }

    public ConvocatoriaEntity(Integer idConvocatoria) {
        this.idConvocatoria = idConvocatoria;
    }

    public ConvocatoriaEntity(Integer idConvocatoria, String nombre, String descripcion, Date fechaFinRegistro, int cupos) {
        this.idConvocatoria = idConvocatoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaFinRegistro = fechaFinRegistro;
        this.cupos = cupos;
    }

    public Integer getIdConvocatoria() {
        return idConvocatoria;
    }

    public void setIdConvocatoria(Integer idConvocatoria) {
        this.idConvocatoria = idConvocatoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaFinRegistro() {
        return fechaFinRegistro;
    }

    public void setFechaFinRegistro(Date fechaFinRegistro) {
        this.fechaFinRegistro = fechaFinRegistro;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConvocatoria != null ? idConvocatoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConvocatoriaEntity)) {
            return false;
        }
        ConvocatoriaEntity other = (ConvocatoriaEntity) object;
        if ((this.idConvocatoria == null && other.idConvocatoria != null) || (this.idConvocatoria != null && !this.idConvocatoria.equals(other.idConvocatoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.Convocatoria[ idConvocatoria=" + idConvocatoria + " ]";
    }

    @Override
    public String name() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
