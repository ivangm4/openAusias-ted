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

package net.daw.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.implementation.EmpleadoBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.AppConfigurationHelper;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

public class EmpleadoDao implements ViewDaoInterface<EmpleadoBean>, TableDaoInterface<EmpleadoBean> {

    private String strTable = "empleado";
    private String strSQL = "select * from empleado where 1=1 ";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;

    public EmpleadoDao(Connection oPooledConnection) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage()));
        }
    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        try {
            pages = oMysql.getPages(strSQL, intRegsPerPag);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    @Override
    public int getCount(ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        int pages = 0;
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    @Override
    public ArrayList<EmpleadoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<EmpleadoBean> arrEmpleado = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    EmpleadoBean oEmpleadoBean = new EmpleadoBean();
                    arrEmpleado.add(oEmpleadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrEmpleado;
    }

    @Override
    public ArrayList<EmpleadoBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<EmpleadoBean> arrEmpleado = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    EmpleadoBean oEmpleadoBean = new EmpleadoBean();
                    arrEmpleado.add(oEmpleadoBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrEmpleado;
    }

    @Override
    public EmpleadoBean get(EmpleadoBean oEmpleadoBean, Integer expand) throws Exception {
        if (oEmpleadoBean.getId() > 0) {
            try {
                ResultSet oResultSet = oMysql.getAllSql(strSQL + " And id= " + oEmpleadoBean.getId() + " ");
                if (oResultSet != null) {
                    while (oResultSet.next()) {
                        oEmpleadoBean = oEmpleadoBean.fill(oResultSet, oConnection, expand);
                    }
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
            }
        } else {
            oEmpleadoBean.setId(0);
        }
        return oEmpleadoBean;
    }

    @Override
    public Integer set(EmpleadoBean oEmpleadoBean) throws Exception {
        Integer iResult = null;
        try {
            if (oEmpleadoBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oEmpleadoBean.getColumns() + ")";
                strSQL += "VALUES(" + oEmpleadoBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
            } else {
                strSQL = "UPDATE " + strTable + " ";
                strSQL += " SET " + oEmpleadoBean.toPairs();
                strSQL += " WHERE id=" + oEmpleadoBean.getId();
                iResult = oMysql.executeUpdateSQL(strSQL);
            }

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return iResult;
    }

    @Override
    public Integer remove(Integer id) throws Exception {
        int result = 0;
        try {
            result = oMysql.removeOne(id, strTable);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
        }
        return result;
    }
    
    public EmpleadoBean getFromLogin(EmpleadoBean oEmpleado) throws Exception {
        try {
            String strId = oMysql.getId("empleado", "login", oEmpleado.getLogin());
            if (strId == null) {
                oEmpleado.setId(0);
            } else {
                Integer intId = Integer.parseInt(strId);
                oEmpleado.setId(intId);
                String pass = oEmpleado.getPassword();
                oEmpleado.setPassword(oMysql.getOne(strSQL, "password", oEmpleado.getId()));
                if (!pass.equals(oEmpleado.getPassword())) {
                    oEmpleado.setId(0);
                }
                oEmpleado = this.get(oEmpleado, AppConfigurationHelper.getJsonDepth());
            }
            return oEmpleado;
        } catch (Exception e) {
            throw new Exception("EmpleadoDao.getFromLogin: Error: " + e.getMessage());
        }
    }

}