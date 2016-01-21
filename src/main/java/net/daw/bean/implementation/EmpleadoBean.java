/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */

/**
 *
 * @author Iván García Medina
 */

package net.daw.bean.implementation;

import net.daw.bean.publicinterface.GenericBean;
import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.daw.dao.implementation.TipoempleadoDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class EmpleadoBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private String nombre = "";
    @Expose
    private String apellidos = "";
    @Expose
    private String dni = "";
    @Expose
    private String telefono = "";
    @Expose
    private Date fechaNa = new Date();
    @Expose
    private String login = "";
    @Expose
    private String password = "";
    
    //Claves externas.
    @Expose(serialize = false)
    private Integer id_tipoempleado = 0;
    @Expose(deserialize = false)
    private TipoempleadoBean obj_tipoempleado = null;
    
    public EmpleadoBean() {
        this.id = 0;
    }

    public EmpleadoBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNa() {
        return fechaNa;
    }

    public void setFechaNa(Date fechaNa) {
        this.fechaNa = fechaNa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId_tipoempleado() {
        return id_tipoempleado;
    }

    public void setId_tipoempleado(Integer id_tipoempleado) {
        this.id_tipoempleado = id_tipoempleado;
    }

    public TipoempleadoBean getObj_tipoempleado() {
        return obj_tipoempleado;
    }

    public void setObj_tipoempleado(TipoempleadoBean obj_tipoempleado) {
        this.obj_tipoempleado = obj_tipoempleado;
    }
    
    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "nombre:" + nombre + ",";
        strJson += "apellidos:" + apellidos + ",";
        strJson += "dni:" + dni + ",";
        strJson += "telefono:" + telefono + ",";
        strJson += "fechaNa:" + fechaNa + ",";
        strJson += "login:" + login + ",";
        strJson += "password:" + password + ",";
        if (expand) {
            strJson += "obj_tipoempleado:" + obj_tipoempleado.toJson(false) + ",";
        } else {
            strJson += "id_tipoempleado:" + id_tipoempleado + ",";
        }
        strJson += "}";
        return strJson;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombre,";
        strColumns += "apellidos,";
        strColumns += "dni,";
        strColumns += "telefono,";
        strColumns += "fechaNa,";
        strColumns += "login,";
        strColumns += "password,";
        strColumns += "id_tipoempleado";
        return strColumns;
    }

    public String getValues() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombre,";
        strColumns += "apellidos,";
        strColumns += "dni,";
        strColumns += "telefono,";
        strColumns += "fechaNa,";
        strColumns += "login,";
        strColumns += "password,";
        strColumns += "id_tipoempleado";
        return strColumns;
    }

    @Override
    public String toPairs() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");        
        String strPairs = "";
        //strPairs += "id=" + id + ",";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre) + ",";
        strPairs += "apellidos=" +  EncodingUtilHelper.quotate(apellidos) + ",";
        strPairs += "dni=" +  EncodingUtilHelper.quotate(dni) + ",";
        strPairs += "telefono=" +  EncodingUtilHelper.quotate(telefono) + ",";
        strPairs += "fechaNa=" + EncodingUtilHelper.quotate(format.format(fechaNa)) + ",";
        strPairs += "login=" + EncodingUtilHelper.quotate(format.format(login)) + ",";
        strPairs += "password=" + EncodingUtilHelper.quotate(format.format(password)) + ",";
        strPairs += "id_tipoempleado=" + id_tipoempleado;
        return strPairs;
    }

    @Override
    public EmpleadoBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setApellidos(oResultSet.getString("apellidos"));
        this.setDni(oResultSet.getString("dni"));
        this.setTelefono(oResultSet.getString("telefono"));
        this.setFechaNa(oResultSet.getDate("fechaNa"));
        this.setLogin(oResultSet.getString("login"));
        this.setPassword(oResultSet.getString("password"));
        if (expand > 0) {
            TipoempleadoBean oTipoempleadoBean = new TipoempleadoBean();
            TipoempleadoDao oTipoempleadoDao = new TipoempleadoDao(pooledConnection);
            oTipoempleadoBean.setId(oResultSet.getInt("id_tipoempleado"));
            oTipoempleadoBean = oTipoempleadoDao.get(oTipoempleadoBean, expand - 1);
            this.setObj_tipoempleado(oTipoempleadoBean);
        } else {
            this.setId_tipoempleado(oResultSet.getInt("id_tipoempleado"));
        }
        return this;
    }
}
