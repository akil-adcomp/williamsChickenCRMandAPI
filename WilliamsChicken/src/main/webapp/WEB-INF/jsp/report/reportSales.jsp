<%-- 
    Document   : reportSales
    Created on : 22 May, 2018, 4:50:32 PM
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
                    <li><a href="../home/home">Home</a></li>
                    <li><a href="../home/home">Reports</a></li>
                    <li><a href="#">Sales Report</a></li>
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
                                    <h3 class="panel-title">Sales Report</h3>
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
                                                        <th>Date</th>
                                                        <th>Store Name</th>
                                                        <th>Submit Date</th>
                                                        <th>Begning Head Count</h>
                                                        <th>Store Transfer</th>
                                                        <th>Purchase</th>
                                                        <th>Chicken Usage</th>
                                                        <th>Birds Wasted</th>

                                                        <th>Bird On Hand</th>
                                                        <th>Ending Enventory</th>
                                                        <th>Variance</th>
                                                        <th>Details</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${salesList}" var="item">
                                                        <tr>
                                                            <td>${item.date}</td>
                                                            <td>${item.store.storeName}</td>
                                                            <td><fmt:formatDate value="${item.submitDate}" pattern="dd-MM-yyyy"/></td>
                                                            <td>${item.begningHeadCount}</td>
                                                            <td>${item.storeTransfer}</td>
                                                            <td>${item.purchase}</td>
                                                            <td>${item.chickenUsage}</td>
                                                            <td>${item.birdsWasted}</td>

                                                            <td>${item.birdsOnHand}</td>
                                                            <td>${item.endingEnventory}</td>
                                                            <td>${item.variance}</td>
                                                            <td style="width: 80%">
                                                                <table class="table table-condensed table-bordered">
                                                                    <thead>
                                                                        <tr class="active">
                                                                            <td>Total Sales</td>
                                                                            <td class="alignCenter">Non Tax Sales</td>
                                                                            <td class="alignCenter">Net Sales</td>
                                                                            <td class="alignCenter">Sales Tax</td>
                                                                            <td class="alignCenter">Gross Sales</td>
                                                                            <td class="alignCenter">Account Receivable</td>
                                                                            <td class="alignCenter">Total Paid Out</td>
                                                                            <td class="alignCenter">Uber Account</td>
                                                                            <td class="alignCenter">Amount Per Bird</td>
                                                                            <td class="alignCenter">Refounds</td>
                                                                            <td class="alignCenter">Customer Count</td>
                                                                            <td class="alignCenter">Total Deposit</td>
                                                                            <td class="alignCenter">Door Dash Account</td>
                                                                            <td class="alignCenter">Cash</td>
                                                                            <td class="alignCenter">Check Average</td>


                                                                            <td class="alignCenter">Net Sales LW</td>
                                                                            <td class="alignCenter">Net Sales LY</td>
                                                                            <td class="alignCenter">Variance LW</td>
                                                                            <td class="alignCenter">Variance LY</td>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:forEach items="${item.salesDetails}" var="item1">
                                                                            <tr>
                                                                                <td>${item1.totalSales}</td>
                                                                                <td>${item1.nonTaxSales}</td>
                                                                                <td>${item1.netSales}</td>
                                                                                <td>${item1.salesTax}</td>
                                                                                <td>${item1.grossSales}</td>
                                                                                <td>${item1.accountReceivable}</td>
                                                                                <td>${item1.totalPaidOut}</td>
                                                                                <td>${item1.uberAccount}</td>
                                                                                <td>${item1.amountPerBird}</td>
                                                                                <td>${item1.refounds}</td>
                                                                                <td>${item1.customerCount}</td>
                                                                                <td>${item1.totalDeposit}</td>
                                                                                <td>${item1.doorDashAccount}</td>
                                                                                <td>${item1.cash}</td>
                                                                                <td>${item1.checkAverage}</td>

                                                                                <td>0</td>
                                                                                <td>0</td>
                                                                                <td>0</td>
                                                                                <td>0</td>
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
