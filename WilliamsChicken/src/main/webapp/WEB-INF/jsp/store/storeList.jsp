<%-- 
    Document   : storeList
    Created on : 7 May, 2018, 12:33:28 PM
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
                    <li><a href="../home/home">Store</a></li>
                    <li><a href="#">List Store</a></li>
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
                                    <h3 class="panel-title">Store List</h3>
                                    <div class="pull-right">
                                        <button type="button" id="active" name="Publish" class="btn btn-success">Active</button>
                                        <button type="button" id="inActive" name="Unpublish" class="btn btn-danger">Inactive</button>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 scrollable">
                                            <table class="table datatable table-bordered" id="tableId">
                                                <thead>
                                                    <tr><th width="10">
                                                            <input name="select_all" class="toggled"  id="toggleAll" value="1" type="checkbox"/>
                                                        </th>
                                                        <th>Store Name</th>
                                                        <th>Store City</th>
                                                        <th>Store State</th>
                                                        <th>Created Date</th>
                                                        <th>Created By</th>
                                                        <th>Active</th>
                                                        <th>Edit</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${storeList}" var="item">
                                                        <c:if test="${item.active==true}">
                                                            <c:set var="rowClass" value="rowColor1"/>
                                                        </c:if>
                                                        <c:if test="${item.active==false}">
                                                            <c:set var="rowClass" value="error"/>
                                                        </c:if>
                                                        <tr class="<c:out value='${rowClass}'/> "  data-store-id="${item.storeId}">
                                                            <td><c:out value="${item.storeId}"/></td>
                                                            <td><c:out value="${item.storeName}"/></td>
                                                            <td><c:out value="${item.city}"/></td>
                                                            <td><c:out value="${item.stateName}"/></td>
                                                            <td><c:out value="${item.createdDate}"/></td>
                                                            <td><c:out value="${item.user.username}"/></td>
                                                            <td><c:if test="${item.active==true}"><spring:message code="yes"/></c:if>
                                                                <c:if test="${item.active==false}"><spring:message code="no"/></c:if>
                                                                </td>
                                                              <td><a href="#" title="Edit Store" onclick="editFunction(${item.storeId});"><spna class="fa fa-pencil"></spna> Edit</a></td>     
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
                    <form name="form2" id="form2" action="../store/showEditStore.htm" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />        
                        <input type="hidden" id="storeId" name="storeId"/>
                    </form>
                </div>
                <!-- END PAGE CONTENT WRAPPER -->
                <form name="form1" id="form1" action="../store/activeStore.htm" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" id="storeIds" name="storeIds"/>
                    <input type="hidden" id="publishValue" name="publishValue"/>
                </form>
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
        <script type="text/javascript" src="../js/plugins/datatables/jquery.dataTables.min.js"></script>    
        <!-- END THIS PAGE PLUGINS-->        

        <!-- START TEMPLATE -->
        <script type="text/javascript" src="../js/plugins.js"></script>        
        <script type="text/javascript" src="../js/actions.js"></script>
        <!-- END TEMPLATE -->

        <script type="text/javascript" defer="defer">
            //            $('.clickableRow td').click(function(){
            //                $('#vendorId').val($(this).parent().data("vendor-id"));
            //                $('#form2').prop('action', '../admin/showEditVendor.htm');
            //                $('#form2').submit();
            //            });

            var rules=new Array();
            rules[0]='targetUserId|custom|checkOnSubmit()';
            
            // Updates "Select all" control in a data table
            function updateDataTableSelectAllCtrl(table){
                
                var $table             = table.table().node();
                var $chkbox_all        = $('tbody input[type="checkbox"]', $table);
                var $chkbox_checked    = $('tbody input[type="checkbox"]:checked', $table);
                var chkbox_select_all  = $('thead input[name="select_all"]', $table).get(0);

                // If none of the checkboxes are checked
                if($chkbox_checked.length === 0){
                    chkbox_select_all.checked = false;
                    if('indeterminate' in chkbox_select_all){
                        chkbox_select_all.indeterminate = false;
                    }

                    // If all of the checkboxes are checked
                } else if ($chkbox_checked.length === $chkbox_all.length){
                    chkbox_select_all.checked = true;
                    if('indeterminate' in chkbox_select_all){
                        chkbox_select_all.indeterminate = false;
                    }

                    // If some of the checkboxes are checked
                } else {
                    chkbox_select_all.checked = true;
                    if('indeterminate' in chkbox_select_all){
                        chkbox_select_all.indeterminate = true;
                    }
                }
            }

            $(document).ready(function (){
                var rows_selected = [];
                var table = $('#tableId').DataTable( {
                    destroy: true,
                    "autoWidth": false,
                    'columnDefs': [{
                            'targets': 0,
                            'width':'1%',
                            'className': 'dt-body-center',
                            'render': function (data, type, full, meta){
                                return '<input type="checkbox" id="'+data[0]+'">';
                            }
                        }],
                    'order': [1, 'asc'],
                    'rowCallback': function(row, data, dataIndex){
                        // Get row ID
                        var rowId = data[0];
                        // If row ID is in the list of selected row IDs
                        if($.inArray(rowId, rows_selected) !== -1){
                            $(row).find('input[type="checkbox"]').prop('checked', true);
                            $(row).addClass('selected');
                        }
                    }
                } );
                
                // Handle click on checkbox
                $('#tableId tbody').on('click', 'input[type="checkbox"]', function(e){
                    var $row = $(this).closest('tr');
                    // Get row data
                    var data = table.row($row).data();
                    // Get row ID
                    var rowId = data[0];
                    // Determine whether row ID is in the list of selected row IDs 
                    var index = $.inArray(rowId, rows_selected);
                    // If checkbox is checked and row ID is not in list of selected row IDs
                    if(this.checked && index === -1){
                        rows_selected.push(rowId);
                        // Otherwise, if checkbox is not checked and row ID is in list of selected row IDs
                    } else if (!this.checked && index !== -1){
                        rows_selected.splice(index, 1);
                    }
                    if(this.checked){
                        $row.addClass('selected');
                    } else {
                        $row.removeClass('selected');
                    }
                    // Update state of "Select all" control
                    updateDataTableSelectAllCtrl(table);
                    // Prevent click event from propagating to parent
                    e.stopPropagation();
                });

                // Handle click on "Select all" control
                $('thead input[name="select_all"]', table.table().container()).on('click', function(e){
                    if(this.checked){
                        $('#tableId tbody input[type="checkbox"]:not(:checked)').trigger('click');
                    } else {
                        $('#tableId tbody input[type="checkbox"]:checked').trigger('click');
                    }
                    // Prevent click event from propagating to parent
                    e.stopPropagation();
                });

                // Handle table draw event
                table.on('draw', function(){
                    // Update state of "Select all" control
                    updateDataTableSelectAllCtrl(table);
                });
                
               
                $('#active').click(function(){
                    submitForm(1);
                });
                $('#inActive').click(function(){
                    submitForm(0);
                });
                function submitForm(value){
                    $('#storeIds').val(rows_selected);
                    $('#publishValue').val(value);
                    $('#form1').submit();
                }
                
            });
            
            function editFunction(storeId) {
                $('#storeId').val(storeId);
                $('#form2').submit();
            }
        </script>
    </body>
</html>

