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

public class TipoempleadoBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private String cargo = "";
    
    public TipoempleadoBean() {
        this.id = 0;
    }

    public TipoempleadoBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "cargo:" + cargo;
        strJson += "}";
        return strJson;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "cargo";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += cargo;

        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "cargo=" + cargo;

        return strPairs;
    }

    @Override
    public TipoempleadoBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setCargo(oResultSet.getString("cargo"));
        return this;
    }
}

