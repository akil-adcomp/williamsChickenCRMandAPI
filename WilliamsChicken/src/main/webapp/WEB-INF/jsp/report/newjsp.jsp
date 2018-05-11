<%-- 
    Document   : userList
    Created on : 1 Oct, 2016, 1:19:41 PM
    Author     : shrutika
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
                    <li><a href="../home/index.htm">Home</a></li>
                    <li><a href="../home/index.htm">Payments</a></li>
                    <li><a href="#">View Payment Batches</a></li>
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
                                    <h3 class="panel-title"><spring:message code="title.paymentBatch"/></h3>
                                </div>
                                <div class="panel-body">
                                    <!-- START FILTER PANEL -->
                                    <sec:authorize ifAnyGranted="ROLE_BF_RELOAD_APP_LAYER">    
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
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label>Status</label>
                                                                <select id="batchStatusId" name="batchStatusId" class="form-control select">
                                                                    <option value="-1">-All-</option>
                                                                    <c:forEach items="${batchStatuses}" var="batch">
                                                                        <option value="${batch.batchStatusId}" <c:if test="${batchStatusId==batch.batchStatusId}">selected</c:if>>${batch.batchStatusDesc}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2 btn-filter">
                                                            <button type="submit" class="btn-info btn-sm"><spring:message code="button.Go"/></button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </sec:authorize>                
                                    <!-- END FILTER PANEL -->
                                    <div class="row">
                                        <div class="col-md-12 scrollable">
                                            <table class="table table-bordered" >
                                                <thead>
                                                    <tr>
                                                        <th><spring:message code="createdDate"/></th>
                                                        <th><spring:message code="createdBy"/></th>
                                                        <th>Batch status</th>
                                                        <th class="alignCenter"><spring:message code="batchCount"/></th>
                                                        <th class="alignCenter"><spring:message code="batchAmount"/></th>
                                                        <th style="width: 250px;"><spring:message code="batchDetails"/></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${paymentBatches}" var="pItem">
                                                        <tr id="batch" data-batch-id="${pItem.paymentBatchId}" class="clickableRow">
                                                            <td><fmt:formatDate value='${pItem.createdDate}' pattern='E dd-MMM yyyy'/></td>
                                                            <td><c:out value="${pItem.createdBy.emailId}"/></td>
                                                            <td>${pItem.batchStatusDesc}</td>
                                                            <td class="alignCenter"><c:out value="${pItem.totalCount}"/></td>
                                                            <td class="alignCenter"><c:out value="${pItem.totalAmount}"/></td>
                                                            <td style="width: 50%">
                                                                <table class="table table-condensed table-bordered">
                                                                    <thead>
                                                                        <tr class="active">
                                                                            <td>Payment mode</td>
                                                                            <td class="alignCenter">Count</td>
                                                                            <td class="alignCenter">Amount</td>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:forEach items="${pItem.paymentModeList}" var="pmItem">
                                                                            <tr>
                                                                                <td>${pmItem.desc}</td>
                                                                                <td class="alignCenter">${pmItem.count}</td>
                                                                                <td class="alignCenter">${pmItem.amount}</td>
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
                    <!-- edit form page-->
                    <form name="form2" id="form2" action="" method="post">
                        <input type="hidden" id="batchId" name="batchId"/>
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
        <script type='text/javascript' src='../js/plugins/icheck/icheck.min.js'></script>        
        <script type="text/javascript" src="../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        <script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-select.js"></script>
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->

        <script type="text/javascript" defer="defer">
            $(document).ready(function(){
                $('#batch td').click(function(){
                    console.log("inside batch click");
                    var batchId = $(this).parent().data("batch-id");
                    $('#batchId').val(batchId);
                    $('#form2').prop("action","../payments/editPaymentBatch.htm").submit();
                });

                $('.cancelBatch').click(function(e){
                    console.log("inside batch cancel");
                    e.stopPropagation();
                    var batchId = $(this).attr("val");
                    alert(batchId);
                });
            });

        </script>
    </body>
</html>
