<%-- 
    Document   : addManager
    Created on : 30 Apr, 2018, 4:07:59 PM
    Author     : manish
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- META SECTION -->
        <%@include file="../common/meta.jsp"%>
        <!-- END META SECTION -->

        <!-- CSS INCLUDE -->
        <%@include file="../common/css.jsp"%>
        <!-- EOF CSS INCLUDE -->
    </head>
    <body onLoad="document.getElementById('emailId').focus();">
        <!-- START PAGE CONTAINER -->
        <div class="page-container page-navigation-toggled page-container-wide">
            <%@include file="../common/sidebar.jsp" %>

            <!-- PAGE CONTENT -->
            <div class="page-content">
                <%@include file="../common/topbar.jsp" %>

                <!-- START BREADCRUMB -->
                <ul class="breadcrumb">
                    <li><a href="../home/home.htm">Home</a></li>
                    <li><a href="../home/home.htm">Manager</a></li>
                    <li><a href="#">Add Manager</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->

                    <div class="row">
                        <div class="col-md-12">
                            <form:form cssClass="form-horizontal" commandName="manager" method="POST" name="form1" id="form1">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Add Manager</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label"><spring:message code="emailId"/></label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="emailId" cssClass="form-control"/>
                                                <span class="help-block">Please Enter email id</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">First Name</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="firstName" cssClass="form-control"/>
                                                <form:hidden path="password" id="password"/>
                                                <span class="help-block">Please enter first name</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Last Name</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="lastName" cssClass="form-control"/>
                                                <span class="help-block">Please enter last name</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label"><spring:message code="mobileNo"/></label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="phoneNo" maxlength="10" cssClass="form-control"/>
                                                <span class="help-block">Please Enter 10 Digit Mobile No.</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Store</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:select path="storeId" id="storeId" cssClass="form-control select" data-live-search="true">
                                                    <form:option value="0" label="Nothing selected"/>
                                                    <form:options items="${storeList}" itemLabel="storeName" itemValue="storeId"/>
                                                </form:select>
                                                <span class="help-block">Please select store</span>
                                            </div>
                                        </div>
                                    </div> 
                                    <div class="panel-footer">
                                        <div class="pull-right">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />        
                                            <button type="submit" id="_submit" name="btnSubmit"  class="btn btn-success" onclick="generatePassword();">Submit</button>
                                            <button type="button" id="_cancel" name="_cancel" class="btn btn-primary" formnovalidate="formnovalidate"><spring:message code="button.Cancel"/></button>
                                        </div>  
                                    </div>
                                </div>
                            </form:form>
                        </div>  
                    </div>
                </div>
                <!-- END PAGE CONTENT WRAPPER -->
            </div>
            <!-- END PAGE CONTENT -->
        </div>
        <!-- END PAGE CONTAINER -->

        <%@include file="../common/messagebox.jsp" %>

        <%@include file="../common/script.jsp" %>
        <!-- START THIS PAGE PLUGINS-->
        <script type='text/javascript' src='../js/plugins/icheck/icheck.min.js'></script>
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <script type='text/javascript' src='../js/plugins/jquery-validation/jquery.validate.js'></script>            

        <!-- END THIS PAGE PLUGINS-->

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->

        <script type="text/javascript">
            var jvalidate = $("#form1").validate({
                ignore: [],
                rules: {                                            
                    emailId:{
                        required: true,
                        email:true
                    },
                    firstName:{
                        required:true
                    },
                    phoneNo:{
                        required: true,
                        minlength: 10,
                        number:true
                    }
                },
                errorPlacement: function(error, element) {
                    if (element.hasClass('select')) {
                        error.insertAfter(element.next(".bootstrap-select"));
                        element.next("div").addClass("error");
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
            //for dropdown 
            $('.select').on('change', function() {
                if($(this).val()!=""){
                    $(this).valid();
                    $(this).next('div').addClass('valid');
                } else {
                    $(this).next('div').removeClass('valid');
                }
            });
            
            function generatePassword() {
                alert("Inside click");
                var form = $("#form1");
                if (form.valid()){ 
                    $.ajax({
                        url: "../admin/ajaxGeneratePassword.htm",
                        dataType:"json",
                        async:false,
                        success:function(json){
                            if (json.id!=0) {
                                alert("Please note your password : "+json.pass);
                                $('#password').val(json.pass);
                            }
                        },
                        errors: function(e){
                            alert('error occured');
                        }
                    });
                }
            }
 
            $('#_cancel').click(function(){
                window.location = '../home/home.htm?msg=msg.actionCancelled';
            });

        </script>
    </body>
</html>