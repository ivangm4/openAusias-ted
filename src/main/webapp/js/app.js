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
    'documentoControllers',
    'usuarioControllers',
    'tipodocumentoControllers',
    'tipousuarioControllers',
    'empleadoControllers',
    'lineacuentaControllers',
    'mesaControllers',
    'tipoempleadoControllers',
    'ui.bootstrap',
    'ngSanitize' //http://stackoverflow.com/questions/9381926/insert-html-into-view-using-angularjs
]);

openAusias.config(['$routeProvider', function ($routeProvider) {

        $routeProvider.when('/', {templateUrl: 'js/system/home.html', controller: 'HomeController'});
        //------------
        $routeProvider.when('/home', {templateUrl: 'js/system/home.html', controller: 'HomeController'});
        //------------
        $routeProvider.when('/documento/view/:id', {templateUrl: 'js/documento/view.html', controller: 'DocumentoViewController'});
        $routeProvider.when('/documento/new', {templateUrl: 'js/documento/newedit.html', controller: 'DocumentoNewController'});
        $routeProvider.when('/documento/edit/:id', {templateUrl: 'js/documento/newedit.html', controller: 'DocumentoEditController'});
        $routeProvider.when('/documento/remove/:id', {templateUrl: 'js/documento/remove.html', controller: 'DocumentoRemoveController'});
        $routeProvider.when('/documento/plist/:page?/:rpp?', {templateUrl: 'js/documento/plist.html', controller: 'DocumentoPListController'});

        //------------
        $routeProvider.when('/usuario/view/:id', {templateUrl: 'js/usuario/view.html', controller: 'UsuarioViewController'});
        $routeProvider.when('/usuario/new/:id', {templateUrl: 'js/usuario/new.html', controller: 'UsuarioNewController'});
        $routeProvider.when('/usuario/edit/:id', {templateUrl: 'js/usuario/edit.html', controller: 'UsuarioEditController'});
        $routeProvider.when('/usuario/remove/:id', {templateUrl: 'js/usuario/remove.html', controller: 'UsuarioRemoveController'});
        $routeProvider.when('/usuario/plist/:page?/:rpp?', {templateUrl: 'js/usuario/plist.html', controller: 'UsuarioPListController'});
        //------------
        $routeProvider.when('/tipodocumento/view/:id', {templateUrl: 'js/tipodocumento/view.html', controller: 'TipodocumentoViewController'});
        $routeProvider.when('/tipodocumento/selection/:page/:rpp', {templateUrl: 'js/tipodocumento/selection.html', controller: 'TipodocumentoSelectionController'});
        
        //------ Empleado ------//
        $routeProvider.when('/empleado/view/:id', {templateUrl: 'js/empleado/view.html', controller: 'EmpleadoViewController'});
        $routeProvider.when('/empleado/new', {templateUrl: 'js/empleado/newedit.html', controller: 'EmpleadoNewController'});
        $routeProvider.when('/empleado/edit/:id', {templateUrl: 'js/empleado/newedit.html', controller: 'EmpleadoEditController'});
        $routeProvider.when('/empleado/remove/:id', {templateUrl: 'js/empleado/remove.html', controller: 'EmpleadoRemoveController'});
        $routeProvider.when('/empleado/plist/:page?/:rpp?', {templateUrl: 'js/empleado/plist.html', controller: 'EmpleadoPListController'});
        
        //------ Línea de Cuenta ------//
        $routeProvider.when('/lineacuenta/view/:id', {templateUrl: 'js/lineacuenta/view.html', controller: 'LineacuentaViewController'});
        $routeProvider.when('/lineacuenta/new', {templateUrl: 'js/lineacuenta/newedit.html', controller: 'LineacuentaNewController'});
        $routeProvider.when('/lineacuenta/edit/:id', {templateUrl: 'js/lineacuenta/newedit.html', controller: 'LineacuentaEditController'});
        $routeProvider.when('/lineacuenta/remove/:id', {templateUrl: 'js/lineacuenta/remove.html', controller: 'LineacuentaRemoveController'});
        $routeProvider.when('/lineacuenta/plist/:page?/:rpp?', {templateUrl: 'js/lineacuenta/plist.html', controller: 'LineacuentaPListController'});
        
        //------ Mesa ------//
        $routeProvider.when('/mesa/view/:id', {templateUrl: 'js/mesa/view.html', controller: 'MesaViewController'});
        $routeProvider.when('/mesa/new', {templateUrl: 'js/mesa/newedit.html', controller: 'MesaNewController'});
        $routeProvider.when('/mesa/edit/:id', {templateUrl: 'js/mesa/newedit.html', controller: 'MesaEditController'});
        $routeProvider.when('/mesa/remove/:id', {templateUrl: 'js/mesa/remove.html', controller: 'MesaRemoveController'});
        $routeProvider.when('/mesa/plist/:page?/:rpp?', {templateUrl: 'js/mesa/plist.html', controller: 'MesaPListController'});
        
        //------ Tipo Empleado ------//
        $routeProvider.when('/tipoempleado/view/:id', {templateUrl: 'js/tipoempleado/view.html', controller: 'TipoempleadoViewController'});
        $routeProvider.when('/tipoempleado/new', {templateUrl: 'js/tipoempleado/newedit.html', controller: 'TipoempleadoNewController'});
        $routeProvider.when('/tipoempleado/edit/:id', {templateUrl: 'js/tipoempleado/newedit.html', controller: 'TipoempleadoEditController'});
        $routeProvider.when('/tipoempleado/remove/:id', {templateUrl: 'js/tipoempleado/remove.html', controller: 'TipoempleadoRemoveController'});
        $routeProvider.when('/tipoempleado/plist/:page?/:rpp?', {templateUrl: 'js/tipoempleado/plist.html', controller: 'TipoempleadoPListController'});
        $routeProvider.when('/tipoempleado/selection/:page/:rpp', {templateUrl: 'js/tipoempleado/selection.html', controller: 'TipoempleadoSelectionController'});









        $routeProvider.when('/tipousuario/selection/:page/:rpp', {templateUrl: 'js/tipousuario/selection.html', controller: 'TipousuarioSelectionController'});
        //------------
        $routeProvider.otherwise({redirectTo: '/'});

        //claves ajenas: usar un módulo compartido para apuntarse la url de llamada: http://stackoverflow.com/questions/12008908/how-can-i-pass-variables-between-controllers-in-angularjs
        //ejemplo claves ajenas con objeto promesa: http://stackoverflow.com/questions/14530251/angular-js-model-relationships

    }]);

var moduloSistema = angular.module('systemControllers', []);
var moduloUsuario = angular.module('usuarioControllers', []);
var moduloDocumento = angular.module('documentoControllers', []);
var moduloTipodocumento = angular.module('tipodocumentoControllers', []);
var moduloTipousuario = angular.module('tipousuarioControllers', []);
var moduloEmpleado = angular.module('empleadoControllers', []);
var moduloLineacuenta = angular.module('lineacuentaControllers', []);
var moduloMesa = angular.module('mesaControllers', []);
var moduloTipoempleado = angular.module('tipoempleadoControllers', []);

