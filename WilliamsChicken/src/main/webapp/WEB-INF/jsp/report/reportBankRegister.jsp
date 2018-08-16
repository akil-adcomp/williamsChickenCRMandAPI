<%-- 
    Document   : reportBankRegister
    Created on : 15 Jul, 2018, 6:38:33 PM
    Author     : Nikhil Pande
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
    <body>
        <!-- START PAGE CONTAINER -->
        <div class="page-container page-navigation-toggled page-container-wide">
            <%@include file="../common/sidebar.jsp" %>

            <!-- PAGE CONTENT -->
            <div class="page-content">
                <%@include file="../common/topbar.jsp" %>

                <!-- START BREADCRUMB -->
                <ul class="breadcrumb">
                    <li><a href="../home/home.htm">Home</a></li>
                    <li><a href="../home/home.htm">Reports</a></li>
                    <li><a href="#">Bank Register Report</a></li>

                </ul>
                <!-- END BREADCRUMB --> 

                <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    <!-- MESSAGE SECTION -->
                    <%@include file="../common/message.jsp"%>
                    <!-- END MESSAGE SECTION -->

                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Bank Register Report</h3>
                                    <ul class="panel-controls">
                                        <c:if test="${fn:length(bankRegisterList)>0}"><li><a href="#" onclick="$('#excelForm').submit();" title="Export to excel"><span class="fa fa-file-excel-o"></span></a></li></c:if>
                                            <li><a href="#" class="panel-refresh"><span class="fa fa-refresh"></span></a></li>
                                        </ul>
                                    </div>
                                    <div class="panel-body">
                                        <!-- START FILTER PANEL -->
                                        <div class="panel panel-warning">
                                            <div class="panel-body">
                                                <form name="form1" id="form1" method="post">
                                                    <div class="row">
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label><spring:message code="startDt"/></label>
                                                            <input name="startDate" id="startDate" value="${startDate}" class="form-control datepicker"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <label><spring:message code="stopDt"/></label>
                                                            <input  name="stopDate"  id="stopDate" value="${stopDate}" class="form-control datepicker"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2 btn-filter">
                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />        
                                                        <button type="submit" class="btn-info btn-sm"><spring:message code="button.Go"/></button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- END FILTER PANEL -->
                                    <div class="row">
                                        <div class="col-md-12 scrollable">
                                            <table class="table datatable table-bordered" >
                                                <thead>
                                                    <tr>
                                                        <th>Submit Date</th>
                                                        <th>Store Name</th>
                                                        <th>Total Amount</th>
                                                        <th>Details</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${bankRegisterList}" var="item">
                                                        <tr>
                                                            <td><fmt:formatDate value="${item.submitDate}" pattern="dd-MM-yyyy"/></td>
                                                            <td>${item.store.storeName}</td>
                                                            <td>${item.totalAmount}</td>
                                                            <td style="width: 50%">
                                                                <table class="table table-condensed table-bordered">
                                                                    <thead>
                                                                        <tr class="active">
                                                                            <td>Manager Name</td>
                                                                            <td >Initials</td>
                                                                            <td >Payment Mode</td>
                                                                            <td class="alignCenter">Amount</td>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:forEach items="${item.listBankRegisterDetails}" var="item1">
                                                                            <tr>
                                                                                <td>${item1.managerName}</td>
                                                                                <td>${item1.initials}</td>
                                                                                <td>${item1.paymentMode.payemntModeDesc}</td>
                                                                                <td>${item1.amount}</td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </tbody>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form name="excelForm" id="excelForm" method="post" action="../report/reportFCWExcel.htm">
                        <input type="hidden" name="startDate" value="${startDate}"/>
                        <input type="hidden" name="stopDate" value="${stopDate}"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />        
                    </form>
                </div>
                <!-- END PAGE CONTENT WRAPPER -->
            </div>
            <!-- END PAGE CONTENT -->
        </div>
        <!-- END PAGE CONTAINER -->

        <%@include file="../common/messagebox.jsp" %>

        <%@include file="../common/script.jsp" %>
        <!-- START THIS PAGE PLUGINS-->    
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->
    </body>
</html>

