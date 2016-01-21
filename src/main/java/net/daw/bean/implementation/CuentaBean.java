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
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.MesaDao;

public class CuentaBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private Date fechahora = new Date();
    @Expose(serialize = false)
    private Integer id_mesa = 0;
    @Expose(deserialize = false)
    private MesaBean obj_mesa = null;
    
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

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
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

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "fechahora:" + fechahora + ",";
        if (expand) {
            strJson += "obj_mesa:" + obj_mesa.toJson(false) + ",";
        } else {
            strJson += "id_mesa:" + id_mesa + ",";
        }
        strJson += "}";
        return strJson;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "fechahora,";
        strColumns += "id_mesa";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += fechahora + ",";
        strColumns += id_mesa;

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "fechahora=" + fechahora + ",";
        strPairs += "id_mesa=" + id_mesa;

        return strPairs;
    }

    @Override
    public CuentaBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setFechahora(oResultSet.getDate("fechahora"));
        if (expand > 0) {
            MesaBean oMesaBean = new MesaBean();
            MesaDao oMesaDao = new MesaDao(pooledConnection);
            oMesaBean.setId(oResultSet.getInt("id_mesa"));
            oMesaBean = oMesaDao.get(oMesaBean, expand - 1);
            this.setObj_mesa(oMesaBean);
        } else {
            this.setId_mesa(oResultSet.getInt("id_mesa"));
        }
        return this;

    }

}
