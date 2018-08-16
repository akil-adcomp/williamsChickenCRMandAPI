<%-- 
    Document   : reportPayroll
    Created on : 8 May, 2018, 6:36:56 PM
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
                    <li><a href="#">Payroll Report</a></li>
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
                                    <h3 class="panel-title">Payroll Report</h3>
                                    <ul class="panel-controls">
                                        <%--  <c:if test="${fn:length(payrollList)>0}"><li><a href="#" onclick="$('#excelForm').submit();" title="Export to excel"><span class="fa fa-file-excel-o"></span></a></li></c:if> --%>
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
                                                            <input name="startDate" value="${startDate}" id="startDate"  class="form-control customdatepicker"/>
                                                        </div>
                                                    </div>
                                                    <!--                                                    <div class="col-md-2">
                                                                                                            <div class="form-group">
                                                                                                                <label><spring:message code="stopDt"/></label>
                                                                                                                <input  name="stopDate"  id="stopDate" value="${stopDate}" class="form-control datepicker"/>
                                                                                                            </div>
                                                                                                        </div>-->
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
                                                        <th>Date</th>
                                                        <th>Store Name</th>
                                                        <!--                                                        <th>Total Reg Pay</th>
                                                                                                                <th>Total O/T Pay</th>
                                                                                                                <th>Total Pay</th>-->
                                                        <th>Details</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${payrollList}" var="item">
                                                        <tr>
                                                            <td>${item.date}</td>
                                                            <td>${item.store.storeName}</td>
                                                            <td style="width: 70%">
                                                                <table class="table table-condensed table-bordered">
                                                                    <thead>
                                                                        <tr class="active">
                                                                            <td>Employee Name</td>
                                                                            <td class="alignCenter">Reg Hour</td>
                                                                            <td class="alignCenter">O/T</td>
                                                                            <td class="alignCenter">Pay Rate</td>
                                                                            <td class="alignCenter">Total Reg Pay</td>
                                                                            <td class="alignCenter">Total OT Pay</td>
                                                                            <td class="alignCenter">Total Pay</td>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:forEach items="${item.payrollDetails}" var="item1">
                                                                            <tr>
                                                                                <td>${item1.employee.firstName}</td>
                                                                                <td>${item1.regHour}</td>
                                                                                <td>${item1.ot}</td>
                                                                                <td>${item1.payRate}</td>
                                                                                <td><fmt:formatNumber value="${item1.totalRegPay}" maxFractionDigits="2" /></td>
                                                                                <td><fmt:formatNumber value="${item1.totalOTPay}" maxFractionDigits="2" /></td>
                                                                                <td><fmt:formatNumber value="${item1.dailyTotalPay}" maxFractionDigits="2" /></td>
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
        <script>
            $(document).ready(function() {
                $(".customdatepicker").datepicker({format: 'yyyy-mm-dd', autoclose: true,
                    daysOfWeekDisabled: "0,2,3,4,5,6"
                });
            });
        </script>
    </body>
</html>
