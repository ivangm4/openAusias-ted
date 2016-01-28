/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/openAUSIAS
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
 */

'use strict';

//var appName = 'AjaxStockNg';

var openAusias = angular.module('myApp', [
    'ngRoute',
    'Filters',
    'Services',
    'Directives',
    'systemControllers',
    'tipoempleadoControllers',
    'empleadoControllers',
    'mesaControllers',
    'cuentaControllers',
    'lineacuentaControllers',
    'productoControllers',
    'tipoproductoControllers',
    'ui.bootstrap',
    'ngSanitize'
]);

openAusias.config(['$routeProvider', function ($routeProvider) {

        $routeProvider.when('/', {templateUrl: 'js/system/home.html', controller: 'HomeController'});
        //---------------//
        $routeProvider.when('/home', {templateUrl: 'js/system/home.html', controller: 'HomeController'});
        $routeProvider.when('/license', {templateUrl: 'js/system/license.html', controller: 'LicenseController'});
        //---------------//
        
        //------ Tipo de Empleado ------//
        $routeProvider.when('/tipoempleado/plist/:page?/:rpp?', {templateUrl: 'js/tipoempleado/plist.html', controller: 'TipoempleadoPListController'});
        $routeProvider.when('/tipoempleado/view/:id', {templateUrl: 'js/tipoempleado/view.html', controller: 'TipoempleadoViewController'});
        $routeProvider.when('/tipoempleado/new', {templateUrl: 'js/tipoempleado/newedit.html', controller: 'TipoempleadoNewController'});
        $routeProvider.when('/tipoempleado/edit/:id', {templateUrl: 'js/tipoempleado/newedit.html', controller: 'TipoempleadoEditController'});
        $routeProvider.when('/tipoempleado/remove/:id', {templateUrl: 'js/tipoempleado/remove.html', controller: 'TipoempleadoRemoveController'});
        $routeProvider.when('/tipoempleado/selection/:page/:rpp', {templateUrl: 'js/tipoempleado/selection.html', controller: 'TipoempleadoSelectionController'});
        
        //------ Empleado ------//
        $routeProvider.when('/empleado/plist/:page?/:rpp?', {templateUrl: 'js/empleado/plist.html', controller: 'EmpleadoPListController'});
        $routeProvider.when('/empleado/view/:id', {templateUrl: 'js/empleado/view.html', controller: 'EmpleadoViewController'});
        $routeProvider.when('/empleado/new', {templateUrl: 'js/empleado/newedit.html', controller: 'EmpleadoNewController'});
        $routeProvider.when('/empleado/edit/:id', {templateUrl: 'js/empleado/newedit.html', controller: 'EmpleadoEditController'});
        $routeProvider.when('/empleado/remove/:id', {templateUrl: 'js/empleado/remove.html', controller: 'EmpleadoRemoveController'});
        $routeProvider.when('/empleado/selection/:page/:rpp', {templateUrl: 'js/empleado/selection.html', controller: 'EmpleadoSelectionController'});
        
        //------ Mesa ------//
        $routeProvider.when('/mesa/plist/:page?/:rpp?', {templateUrl: 'js/mesa/plist.html', controller: 'MesaPListController'});
        $routeProvider.when('/mesa/view/:id', {templateUrl: 'js/mesa/view.html', controller: 'MesaViewController'});
        $routeProvider.when('/mesa/new', {templateUrl: 'js/mesa/newedit.html', controller: 'MesaNewController'});
        $routeProvider.when('/mesa/edit/:id', {templateUrl: 'js/mesa/newedit.html', controller: 'MesaEditController'});
        $routeProvider.when('/mesa/remove/:id', {templateUrl: 'js/mesa/remove.html', controller: 'MesaRemoveController'});
        $routeProvider.when('/mesa/selection/:page/:rpp', {templateUrl: 'js/mesa/selection.html', controller: 'MesaSelectionController'});
        
        //------ Cuenta ------//
        $routeProvider.when('/cuenta/plist/:page?/:rpp?', {templateUrl: 'js/cuenta/plist.html', controller: 'CuentaPListController'});
        $routeProvider.when('/cuenta/view/:id', {templateUrl: 'js/cuenta/view.html', controller: 'CuentaViewController'});
        $routeProvider.when('/cuenta/new', {templateUrl: 'js/cuenta/newedit.html', controller: 'CuentaNewController'});
        $routeProvider.when('/cuenta/edit/:id', {templateUrl: 'js/cuenta/newedit.html', controller: 'CuentaEditController'});
        $routeProvider.when('/cuenta/remove/:id', {templateUrl: 'js/cuenta/remove.html', controller: 'CuentaRemoveController'});
        $routeProvider.when('/cuenta/selection/:page/:rpp', {templateUrl: 'js/cuenta/selection.html', controller: 'CuentaSelectionController'});
        
        //------ Línea de Cuenta ------//
        $routeProvider.when('/lineacuenta/view/:id', {templateUrl: 'js/lineacuenta/view.html', controller: 'LineacuentaViewController'});
        $routeProvider.when('/lineacuenta/new', {templateUrl: 'js/lineacuenta/newedit.html', controller: 'LineacuentaNewController'});
        $routeProvider.when('/lineacuenta/edit/:id', {templateUrl: 'js/lineacuenta/newedit.html', controller: 'LineacuentaEditController'});
        $routeProvider.when('/lineacuenta/remove/:id', {templateUrl: 'js/lineacuenta/remove.html', controller: 'LineacuentaRemoveController'});
        $routeProvider.when('/lineacuenta/plist/:page?/:rpp?', {templateUrl: 'js/lineacuenta/plist.html', controller: 'LineacuentaPListController'});

        //------ Producto ------//
        $routeProvider.when('/producto/plist/:page?/:rpp?', {templateUrl: 'js/producto/plist.html', controller: 'ProductoPListController'});
        $routeProvider.when('/producto/view/:id', {templateUrl: 'js/producto/view.html', controller: 'ProductoViewController'});
        $routeProvider.when('/producto/new', {templateUrl: 'js/producto/newedit.html', controller: 'ProductoNewController'});
        $routeProvider.when('/producto/edit/:id', {templateUrl: 'js/producto/newedit.html', controller: 'ProductoEditController'});
        $routeProvider.when('/producto/remove/:id', {templateUrl: 'js/producto/remove.html', controller: 'ProductoRemoveController'});
        $routeProvider.when('/producto/selection/:page/:rpp', {templateUrl: 'js/producto/selection.html', controller: 'ProductoSelectionController'});

        //------ Tipo de Producto ------//
        $routeProvider.when('/tipoproducto/plist/:page?/:rpp?', {templateUrl: 'js/tipoproducto/plist.html', controller: 'TipoproductoPListController'});
        $routeProvider.when('/tipoproducto/view/:id', {templateUrl: 'js/tipoproducto/view.html', controller: 'TipoproductoViewController'});
        $routeProvider.when('/tipoproducto/new', {templateUrl: 'js/tipoproducto/newedit.html', controller: 'TipoproductoNewController'});
        $routeProvider.when('/tipoproducto/edit/:id', {templateUrl: 'js/tipoproducto/newedit.html', controller: 'TipoproductoEditController'});
        $routeProvider.when('/tipoproducto/remove/:id', {templateUrl: 'js/tipoproducto/remove.html', controller: 'TipoproductoRemoveController'});
        $routeProvider.when('/tipoproducto/selection/:page/:rpp', {templateUrl: 'js/tipoproducto/selection.html', controller: 'TipoproductoSelectionController'});

        //---------------//
        $routeProvider.otherwise({redirectTo: '/'});
    }]);

var moduloSistema = angular.module('systemControllers', []);
var moduloTipoempleado = angular.module('tipoempleadoControllers', []);
var moduloEmpleado = angular.module('empleadoControllers', []);
var moduloMesa = angular.module('mesaControllers', []);
var moduloCuenta = angular.module('cuentaControllers', []);
var moduloLineacuenta = angular.module('lineacuentaControllers', []);
var moduloProducto = angular.module('productoControllers', []);
var moduloTipoproducto = angular.module('tipoproductoControllers', []);



