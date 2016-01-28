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

angular.module('Directives', [])
//        .directive('appVersion', ['version', function (version) {
//                return function (scope, elm, attrs) {
//                    elm.text(version);
//                };
//            }])
//angular.module('Directives', []).
//        directive('datetimez', function () {
//            return {
//                restrict: 'A',
//                require: 'ngModel',
//                link: function (scope, element, attrs, ngModelCtrl) {
//                    element.datetimepicker({
//                        dateFormat: 'dd-MM-yyyy',
//                        language: 'en',
//                        pickTime: false,
//                        startDate: '01-11-2013', // set a minimum date
//                        endDate: '01-11-2030'          // set a maximum date
//                    }).on('changeDate', function (e) {
//                        ngModelCtrl.$setViewValue(e.date);
//                        scope.$apply();
//                    });
//                }
//            };
//        });
//angular.module('Directives', []).
//        directive("rpattern", function () {
//            return {
//                restrict: "A",
//                require: "ngModel",
//                link: function (scope, el, attrs, ngModel) {
//                    var validator, patternValidator,
//                            pattern = attrs.rpattern,
//                            required = true;
//
//                    if (pattern) {
//                        if (pattern.match(/^\/(.*)\/$/)) {
//                            pattern = new RegExp(pattern.substr(1, pattern.length - 2));
//                            patternValidator = function (value) {
//                                return validate(pattern, value)
//                            };
//                        } else {
//                            patternValidator = function (value) {
//                                var patternObj = scope.$eval(pattern);
//                                if (!patternObj || !patternObj.test) {
//                                    throw new Error('Expected ' + pattern + ' to be a RegExp but was ' + patternObj);
//                                }
//                                return validate(patternObj, value);
//                            };
//                        }
//                    }
//
//                    ngModel.$formatters.push(patternValidator);
//                    ngModel.$parsers.push(patternValidator);
//
//                    attrs.$observe("required", function (newval) {
//                        required = newval;
//                        patternValidator(ngModel.$viewValue);
//                    });
//
//                    function validate(regexp, value) {
//                        if (value == null || value === "" || !required || regexp.test(value)) {
//                            ngModel.$setValidity('pattern', true);
//                            return value;
//                        } else {
//                            ngModel.$setValidity('pattern', false);
//                            return;
//                        }
//                    }
//                }
//            };
//        });









//    

//angular.module('Directives', [])
//        .directive('validInteger', function () {
//            return{
//                require: "ngModel",
//                link: function (scope, elm, attrs, ctrl) {
//
//                    var regex = /^\d{2,4}(\.\d{1,2})?$/;
//                    ctrl.$parsers.unshift(function (viewValue) {
//                        var floatValue = parseFloat(viewValue);
//                        if (floatValue >= 50 && floatValue <= 5000 && regex.test(viewValue)) {
//                            ctrl.$setValidity('validPrice', true);
//                            //return viewValue;
//                        } else {
//                            ctrl.$setValidity('validPrice', false);
//                        }
//                        return viewValue;
//                    });
//                }
//            };
//        });


//        .directive('numbersOnly', function () {
//            return {
//                require: 'ngModel',
//                link: function (scope, element, attrs, modelCtrl) {
//                    modelCtrl.$parsers.push(function (inputValue) {
//                        // this next if is necessary for when using ng-required on your input. 
//                        // In such cases, when a letter is typed first, this parser will be called
//                        // again, and the 2nd time, the value will be undefined
//                        if (inputValue == undefined)
//                            return ''
//                        var transformedInput = inputValue.replace(/[^0-9]/g, '');
//                        console.log("inputValue" + inputValue);
//                        if (parseInt(inputValue) > 200 || parseInt(inputValue) < 5) {
//                            return '';
//                        }
//                        if (transformedInput != inputValue) {
//                            modelCtrl.$setViewValue(transformedInput);
//                            modelCtrl.$render();
//                        }
//
//                        return transformedInput;
//                    });
//                }
//            };
//        })

        .directive('linkusuario', function () {
            return {
                restrict: 'E',
                template: '<a ng-show="obj.id" href="#/usuario/view/{{obj.id}}">{{obj.id}} - {{obj.login}} ({{obj.ciudad}})</a>',
                scope: {
                    obj: "=source"
                }
            }
        })
        .directive('linktipodocumento', function () {
            return {
                restrict: 'E',
                template: '<a ng-show="obj.id" href="#/tipodocumento/view/{{obj.id}}">{{obj.id}}-({{obj.descripcion}})</a>',
                scope: {
                    obj: "=source"
                }
            }
        })


        .directive('validatemin', function () {
            return {
                restrict: 'A',
                require: 'ngModel',
                link: function (scope, elem, attr, ctrl) {
                    scope.$watch(attr.validatemin, function () {
                        ctrl.$setViewValue(ctrl.$viewValue);
                    });
                    var minValidator = function (value) {
                        var min = scope.$eval(attr.validatemin) || 0;
                        if (value && value < min) {
                            ctrl.$setValidity('validatemin', false);
                            return undefined;
                        } else {
                            ctrl.$setValidity('validatemin', true);
                            return value;
                        }
                    };

                    ctrl.$parsers.push(minValidator);
                    ctrl.$formatters.push(minValidator);
                }
            };
        })

        .directive('validatemax', function () {
            return {
                restrict: 'A',
                require: 'ngModel',
                link: function (scope, elem, attr, ctrl) {
                    scope.$watch(attr.validatemax, function () {
                        ctrl.$setViewValue(ctrl.$viewValue);
                    });
                    var maxValidator = function (value) {
                        var max = scope.$eval(attr.validatemax) || Infinity;
                        if (value && value > max) {
                            ctrl.$setValidity('validatemax', false);
                            return undefined;
                        } else {
                            ctrl.$setValidity('validatemax', true);
                            return value;
                        }
                    };

                    ctrl.$parsers.push(maxValidator);
                    ctrl.$formatters.push(maxValidator);
                }
            };
        });




//        .directive('lowerThan', [
//            function () {
//
//                var link = function ($scope, $element, $attrs, ctrl) {
//
//                    var validate = function (viewValue) {
//                        var comparisonModel = $attrs.lowerThan;
//
//                        if (!viewValue || !comparisonModel) {
//                            // It's valid because we have nothing to compare against
//                            ctrl.$setValidity('lowerThan', true);
//                        }
//
//                        // It's valid if model is lower than the model we're comparing against
//                        ctrl.$setValidity('lowerThan', parseInt(viewValue, 10) < parseInt(comparisonModel, 10));
//                        return viewValue;
//                    };
//
//                    ctrl.$parsers.unshift(validate);
//                    ctrl.$formatters.push(validate);
//
//                    $attrs.$observe('lowerThan', function (comparisonModel) {
//                        // Whenever the comparison model changes we'll re-validate
//                        return validate(ctrl.$viewValue);
//                    });
//
//                };
//
//                return {
//                    require: 'ngModel',
//                    link: link
//                };
//
//            }
//        ]);