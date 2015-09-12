/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.AccesoDatos.Entity;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author snipercat
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM USUARIO u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM USUARIO u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByDocumento", query = "SELECT u FROM USUARIO u WHERE u.documento = :documento"),
    @NamedQuery(name = "Usuario.findByTDocumento", query = "SELECT u FROM USUARIO u WHERE u.tDocumento = :tDocumento"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM USUARIO u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM USUARIO u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM USUARIO u WHERE u.email = :username"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM USUARIO u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByRol", query = "SELECT u FROM USUARIO u WHERE u.rol = :rol")})

public class UsuarioEntity implements Serializable, Entity{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOCUMENTO")
    private int documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "T_DOCUMENTO")
    private String tDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROL")
    private Character rol;



    public UsuarioEntity() {
    }

    public UsuarioEntity(Integer idUsuario, int documento, String tDocumento, String nombres, String apellidos, String username, String password, Character rol) {
        this.idUsuario = idUsuario;
        this.documento = documento;
        this.tDocumento = tDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioEntity other = (UsuarioEntity) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" + "idUsuario=" + idUsuario + ", documento=" + documento + ", tDocumento=" + tDocumento + ", nombres=" + nombres + ", apellidos=" + apellidos + ", username=" + username + ", password=" + password + ", rol=" + rol + '}';
    }

    
    
    
    
    
// GETTER AND SETTER
    
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String gettDocumento() {
        return tDocumento;
    }

    public void settDocumento(String tDocumento) {
        this.tDocumento = tDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getRol() {
        return rol;
    }

    public void setRol(Character rol) {
        this.rol = rol;
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
