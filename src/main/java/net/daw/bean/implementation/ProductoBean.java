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
import net.daw.dao.implementation.TipoproductoDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class ProductoBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private String nombre = "";
    @Expose
    private Double precio;
    
    //Claves externas.
    @Expose(serialize = false)
    private Integer id_tipoproducto = 0;
    @Expose(deserialize = false)
    private TipoproductoBean obj_tipoproducto = null;

    public ProductoBean() {
        this.id = 0;
    }

    public ProductoBean(Integer id) {
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getId_tipoproducto() {
        return id_tipoproducto;
    }

    public void setId_tipoproducto(Integer id_tipoproducto) {
        this.id_tipoproducto = id_tipoproducto;
    }

    public TipoproductoBean getObj_tipoproducto() {
        return obj_tipoproducto;
    }

    public void setObj_tipoproducto(TipoproductoBean obj_tipoproducto) {
        this.obj_tipoproducto = obj_tipoproducto;
    }
    
    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "nombre:" + nombre + ",";
        strJson += "precio:" + precio + ",";
        if (expand) {
            strJson += "obj_tipoproducto:" + obj_tipoproducto.toJson(false) + ",";
        } else {
            strJson += "id_tipoproducto:" + id_tipoproducto + ",";
        }
        strJson += "}";
        return strJson;
    }
    
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombre,";
        strColumns += "precio,";
        strColumns += "id_tipoproducto";
        return strColumns;
    }

    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(nombre) + ",";
        strColumns += precio + ",";
        strColumns += id_tipoproducto;
        return strColumns;
    }

    @Override
    public String toPairs() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");        
        String strPairs = "";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre) + ",";
        strPairs += "precio=" +  precio + ",";
        strPairs += "id_tipoproducto=" + id_tipoproducto;
        return strPairs;
    }

    @Override
    public ProductoBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setPrecio(oResultSet.getDouble("precio"));
        if (expand > 0) {
            TipoproductoBean oTipoproductoBean = new TipoproductoBean();
            TipoproductoDao oTipoproductoDao = new TipoproductoDao(pooledConnection);
            oTipoproductoBean.setId(oResultSet.getInt("id_tipoproducto"));
            oTipoproductoBean = oTipoproductoDao.get(oTipoproductoBean, expand - 1);
            this.setObj_tipoproducto(oTipoproductoBean);
        } else {
            this.setId_tipoproducto(oResultSet.getInt("id_tipoproducto"));
        }
        return this;
    }

}