<%-- 
    Document   : editStore
    Created on : 7 May, 2018, 12:37:08 PM
    Author     : altius
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
    <body onLoad="document.getElementById('storeName').focus();">
        <!-- START PAGE CONTAINER -->
        <div class="page-container page-navigation-toggled page-container-wide">
            <%@include file="../common/sidebar.jsp" %>

            <!-- PAGE CONTENT -->
            <div class="page-content">
                <%@include file="../common/topbar.jsp" %>

                <!-- START BREADCRUMB -->
                <ul class="breadcrumb">
                    <li><a href="../home/home.htm">Home</a></li>
                    <li><a href="../home/home.htm">Store</a></li>
                    <li><a href="#">Edit Store</a></li>
                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->

                    <div class="row">
                        <div class="col-md-12">
                            <form:form cssClass="form-horizontal" action="editStore.htm" commandName="store" method="POST" name="form1" id="form1">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Add Store</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Store Name</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="storeName" cssClass="form-control"/>
                                                <form:hidden path="storeId" id="storeId"/>
                                                <span class="help-block">Please enter store name</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Store City</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:input path="city" cssClass="form-control"/>
                                                <span class="help-block">Please enter store city</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="req col-md-2 col-xs-12 control-label">Store State</label>
                                            <div class="col-md-6 col-xs-12">
                                                <form:select path="stateId" id="stateId" cssClass="form-control select">
                                                    <form:option value="0" label="Nothing selected"/>
                                                    <form:options items="${stateList}" itemLabel="stateName" itemValue="stateId"/>
                                                </form:select>
                                                <span class="help-block">Please select state</span>
                                            </div>
                                        </div>
                                    </div> 
                                    <div class="panel-footer">
                                        <div class="pull-right">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />        
                                            <button type="submit" id="_submit" name="btnSubmit"  class="btn btn-success">Submit</button>
                                            <button type="button" id="_cancel" name="_cancel" class="btn btn-primary"><spring:message code="button.Cancel"/></button>
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
            $('#_cancel').click(function(){
                window.location = '../home/home.htm?msg=msg.actionCancelled';
            });
        </script>
    </body>
</html>