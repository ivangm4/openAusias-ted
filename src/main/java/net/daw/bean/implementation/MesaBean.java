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

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.EmpleadoDao;

public class MesaBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private String descripcion = "";
    @Expose(serialize = false)
    private Integer id_empleado = 0;
    @Expose(deserialize = false)
    private EmpleadoBean obj_empleado = null;
    
    public MesaBean() {
        this.id = 0;
    }

    public MesaBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Integer id_empleado) {
        this.id_empleado = id_empleado;
    }

    public EmpleadoBean getObj_empleado() {
        return obj_empleado;
    }

    public void setObj_empleado(EmpleadoBean obj_empleado) {
        this.obj_empleado = obj_empleado;
    }

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "descripcion:" + descripcion + ",";
        if (expand) {
            strJson += "obj_empleado:" + obj_empleado.toJson(false) + ",";
        } else {
            strJson += "id_empleado:" + id_empleado + ",";
        }
        strJson += "}";
        return strJson;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "descripcion,";
        strColumns += "id_empleado";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += descripcion + ",";
        strColumns += id_empleado;

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "descripcion=" + descripcion + ",";
        strPairs += "id_empleado=" + id_empleado;

        return strPairs;
    }

    @Override
    public MesaBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        if (expand > 0) {
            EmpleadoBean oEmpleadoBean = new EmpleadoBean();
            EmpleadoDao oEmpleadoDao = new EmpleadoDao(pooledConnection);
            oEmpleadoBean.setId(oResultSet.getInt("id_empleado"));
            oEmpleadoBean = oEmpleadoDao.get(oEmpleadoBean, expand - 1);
            this.setObj_empleado(oEmpleadoBean);
        } else {
            this.setId_empleado(oResultSet.getInt("id_empleado"));
        }
        return this;

    }

}

