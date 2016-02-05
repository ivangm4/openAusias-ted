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
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.EmpleadoDao;
import net.daw.dao.implementation.MesaDao;
import net.daw.helper.statics.EncodingUtilHelper;


public class CuentaBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private Timestamp fecha;
    private Boolean pagada;
    
    //Claves externas.
    @Expose(serialize = false)
    private Integer id_mesa = 0;
    @Expose(deserialize = false)
    private MesaBean obj_mesa = null;
    @Expose(serialize = false)
    private Integer id_empleado = 0;
    @Expose(deserialize = false)
    private EmpleadoBean obj_empleado = null;
    
    public CuentaBean() {
        this.id = 0;
    }

    public CuentaBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Boolean getPagada() {
        return pagada;
    }

    public void setPagada(Boolean pagada) {
        this.pagada = pagada;
    }

    public Integer getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(Integer id_mesa) {
        this.id_mesa = id_mesa;
    }

    public MesaBean getObj_mesa() {
        return obj_mesa;
    }

    public void setObj_mesa(MesaBean obj_mesa) {
        this.obj_mesa = obj_mesa;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "fecha:" + EncodingUtilHelper.quotate(format.format(fecha)) + ",";
        strJson += "pagada:" + pagada + ",";
        if (expand) {
            strJson += "obj_mesa:" + obj_mesa.toJson(false) + ",";
        } else {
            strJson += "id_mesa:" + id_mesa + ",";
        }
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
        strColumns += "fecha,";
        strColumns += "pagada,";
        strColumns += "id_mesa,";
        strColumns += "id_empleado";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotateCuenta(fecha) + ",";
        strColumns += pagada + ",";
        strColumns += id_mesa + ",";
        strColumns += id_empleado;

        return strColumns;
    }

    @Override
    public String toPairs() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
        String strPairs = "";
        strPairs += "fecha=" + EncodingUtilHelper.quotate(format.format(fecha)) + ",";
        strPairs += "pagada=" + pagada + ",";
        strPairs += "id_mesa=" + id_mesa + ",";
        strPairs += "id_empleado=" + id_empleado;

        return strPairs;
    }

    @Override
    public CuentaBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setFecha(oResultSet.getTimestamp("fecha"));
        this.setPagada(oResultSet.getBoolean("pagada"));
        if (expand > 0) {
            MesaBean oMesaBean = new MesaBean();
            MesaDao oMesaDao = new MesaDao(pooledConnection);
            oMesaBean.setId(oResultSet.getInt("id_mesa"));
            oMesaBean = oMesaDao.get(oMesaBean, expand - 1);
            this.setObj_mesa(oMesaBean);
        } else {
            this.setId_mesa(oResultSet.getInt("id_mesa"));
        }
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
