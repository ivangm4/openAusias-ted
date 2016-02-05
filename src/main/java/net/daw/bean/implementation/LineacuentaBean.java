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
import net.daw.dao.implementation.CuentaDao;
import net.daw.dao.implementation.ProductoDao;

public class LineacuentaBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private Integer cantidadProducto;
    
    //Claves externas.
    @Expose(serialize = false)
    private Integer id_cuenta = 0;
    @Expose(deserialize = false)
    private CuentaBean obj_cuenta = null;
    @Expose(serialize = false)
    private Integer id_producto = 0;
    @Expose(deserialize = false)
    private ProductoBean obj_producto = null;

    public LineacuentaBean() {
        this.id = 0;
    }

    public LineacuentaBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Integer id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public CuentaBean getObj_cuenta() {
        return obj_cuenta;
    }

    public void setObj_cuenta(CuentaBean obj_cuenta) {
        this.obj_cuenta = obj_cuenta;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public ProductoBean getObj_producto() {
        return obj_producto;
    }

    public void setObj_producto(ProductoBean obj_producto) {
        this.obj_producto = obj_producto;
    }

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "cantidadProducto:" + cantidadProducto + ",";
        if (expand) {
            strJson += "obj_cuenta:" + obj_cuenta.toJson(false) + ",";
            strJson += "obj_producto:" + obj_producto.toJson(false) + ",";
        } else {
            strJson += "id_cuenta:" + id_cuenta + ",";
            strJson += "id_producto:" + id_producto + ",";
        }
        strJson += "}";
        return strJson;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "cantidadProducto,";
        strColumns += "id_cuenta,";
        strColumns += "id_producto";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += cantidadProducto + ",";
        strColumns += id_cuenta + ",";
        strColumns += id_producto;

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "cantidadProducto=" + cantidadProducto + ",";
        strPairs += "id_cuenta=" + id_cuenta + ",";
        strPairs += "id_producto=" + id_producto;
        return strPairs;
    }

    @Override
    public LineacuentaBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setCantidadProducto(oResultSet.getInt("cantidadProducto"));
        if (expand > 0) {
            CuentaBean oCuentaBean = new CuentaBean();
            CuentaDao oCuentaDao = new CuentaDao(pooledConnection);
            oCuentaBean.setId(oResultSet.getInt("id_cuenta"));
            oCuentaBean = oCuentaDao.get(oCuentaBean, expand - 1);
            this.setObj_cuenta(oCuentaBean);
        } else {
            this.setId_cuenta(oResultSet.getInt("id_cuenta"));
        }
        if (expand > 0) {
            ProductoBean oProductoBean = new ProductoBean();
            ProductoDao oProductoDao = new ProductoDao(pooledConnection);
            oProductoBean.setId(oResultSet.getInt("id_producto"));
            oProductoBean = oProductoDao.get(oProductoBean, expand - 1);
            this.setObj_producto(oProductoBean);
        } else {
            this.setId_producto(oResultSet.getInt("id_producto"));
        }
        return this;
    }
}
