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
    @NamedQuery(name = "Taller.findAll", query = "SELECT t FROM Taller t"),
    @NamedQuery(name = "Taller.findByIdTaller", query = "SELECT t FROM Taller t WHERE t.idTaller = :idTaller"),
    @NamedQuery(name = "Taller.findByTipoTaller", query = "SELECT t FROM Taller t WHERE t.tipoTaller = :tipoTaller"),
    @NamedQuery(name = "Taller.findByNombre", query = "SELECT t FROM Taller t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Taller.findByDescripcion", query = "SELECT t FROM Taller t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Taller.findByFechaFinRegistro", query = "SELECT t FROM Taller t WHERE t.fechaFinRegistro = :fechaFinRegistro"),
    @NamedQuery(name = "Taller.findByFechaInicio", query = "SELECT t FROM Taller t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Taller.findByFechaFin", query = "SELECT t FROM Taller t WHERE t.fechaFin = :fechaFin"),
    @NamedQuery(name = "Taller.findByCosto", query = "SELECT t FROM Taller t WHERE t.costo = :costo"),
    @NamedQuery(name = "Taller.findByCupos", query = "SELECT t FROM Taller t WHERE t.cupos = :cupos")})
public class TallerEntity implements Serializable, Entity{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TALLER")
    private Integer idTaller;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_TALLER")
    private String tipoTaller;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
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
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    private int costo;
    @Basic(optional = false)
    @NotNull
    private int cupos;

    public TallerEntity() {
    }

    public TallerEntity(Integer idTaller) {
        this.idTaller = idTaller;
    }

    public TallerEntity(Integer idTaller, String tipoTaller, String nombre, String descripcion, Date fechaFinRegistro, Date fechaInicio, Date fechaFin, int costo, int cupos) {
        this.idTaller = idTaller;
        this.tipoTaller = tipoTaller;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaFinRegistro = fechaFinRegistro;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
        this.cupos = cupos;
    }

    public Integer getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(Integer idTaller) {
        this.idTaller = idTaller;
    }

    public String getTipoTaller() {
        return tipoTaller;
    }

    public void setTipoTaller(String tipoTaller) {
        this.tipoTaller = tipoTaller;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
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
        hash += (idTaller != null ? idTaller.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TallerEntity)) {
            return false;
        }
        TallerEntity other = (TallerEntity) object;
        if ((this.idTaller == null && other.idTaller != null) || (this.idTaller != null && !this.idTaller.equals(other.idTaller))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity.Taller[ idTaller=" + idTaller + " ]";
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
