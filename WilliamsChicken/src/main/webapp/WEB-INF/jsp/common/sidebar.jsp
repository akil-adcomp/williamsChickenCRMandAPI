<%-- 
    Document   : sidebar
    Created on : 27 April, 2018, 11:06:11 AM
    Author     : manish
--%>

<!-- START PAGE SIDEBAR -->
<div class="page-sidebar toggled">
    <!-- START X-NAVIGATION -->
    <ul class="x-navigation x-navigation-minimized">
        <li class="xn-logo">
            <a href="../home/home"><div class="xn-logoImage"></div></a>
            <a href="#" class="x-navigation-control"></a>
        </li>
        <li class="xn-profile">
            <a href="#" class="profile-mini">
                <img src="../assets/images/users/no-image.jpg" alt='<c:out value="${curUser.username}"/>'/>
            </a>
            <div class="profile">
                <div class="profile-image">
                    <img src="../assets/images/users/no-image.jpg" alt='<c:out value="${curUser.username}"/>'/>
                </div>
                <div class="profile-data">
                    <div class="profile-data-name"><c:out value="${curUser.username}"/></div>
                </div>
            </div>
        </li>

        <li class="active">
            <a href="../home/home.htm"><span class="fa fa-home"></span> <span class="xn-text">Home</span></a>
        </li>
        <sec:authorize access="hasAnyRole('ROLE_BF_RELOAD_APP_LAYER,ROLE_BF_CREATE_USER,ROLE_BF_LIST_USER')">
            <li class="xn-openable">
                <a href="#" title="Admin"><span class="fa fa-user"></span><span class="xn-text">Admin</span></a>
                <ul>
                    <sec:authorize access="hasAnyRole('ROLE_BF_RELOAD_APP_LAYER')">
                        <li><a href="../admin/reloadApplicationLayer.htm"><span class="fa fa-refresh fa-spin"></span>Reload application</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_USER,ROLE_BF_LIST_USER')">
                        <li class="xn-openable">
                            <a href="#"><span class="fa fa-user"></span>User</a>
                            <ul>
                                <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_USER')"><li><a href="../admin/addUser.htm"><span class="fa fa-plus"></span>Add User</a></li></sec:authorize>
                                <sec:authorize access="hasAnyRole('ROLE_BF_LIST_USER')"><li><a href="../admin/userList.htm"><span class="fa fa-list-alt"></span>List User</a></li></sec:authorize>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_STORE,ROLE_BF_STORE_LIST')">
                        <li class="xn-openable">
                            <a href="#"><span class="fa fa-user"></span>Store</a>
                            <ul>
                                <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_STORE')"><li><a href="../store/addStore.htm"><span class="fa fa-plus"></span>Add Store</a></li></sec:authorize>
                                <sec:authorize access="hasAnyRole('ROLE_BF_STORE_LIST')"><li><a href="../store/storeList.htm"><span class="fa fa-list-alt"></span>List Store</a></li></sec:authorize>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_MANAGER,ROLE_BF_MANAGER_LIST')">
                        <li class="xn-openable">
                            <a href="#"><span class="fa fa-user"></span>Manager</a>
                            <ul>
                                <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_MANAGER')"><li><a href="../manager/addManager.htm"><span class="fa fa-plus"></span>Add Manager</a></li></sec:authorize>
                                <sec:authorize access="hasAnyRole('ROLE_BF_MANAGER_LIST')"><li><a href="../manager/managerList.htm"><span class="fa fa-list-alt"></span>List Manager</a></li></sec:authorize>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_VENDOR,ROLE_BF_VENDOR_LIST')">
                        <li class="xn-openable">
                            <a href="#"><span class="fa fa-user"></span>Vendor</a>
                            <ul>
                                <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_VENDOR')"><li><a href="../vendor/addVendor.htm"><span class="fa fa-plus"></span>Add Vendor</a></li></sec:authorize>
                                <sec:authorize access="hasAnyRole('ROLE_BF_VENDOR_LIST')"><li><a href="../vendor/vendorList.htm"><span class="fa fa-list-alt"></span>List Vendor</a></li></sec:authorize>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_EMPLOYEE,ROLE_BF_EMPLOYEE_LIST')">
                        <li class="xn-openable">
                            <a href="#"><span class="fa fa-user"></span>Employee</a>
                            <ul>
                                <sec:authorize access="hasAnyRole('ROLE_BF_CREATE_EMPLOYEE')"><li><a href="../employee/addEmployee.htm"><span class="fa fa-plus"></span>Add Employee</a></li></sec:authorize>
                                <sec:authorize access="hasAnyRole('ROLE_BF_EMPLOYEE_LIST')"><li><a href="../employee/employeeList.htm"><span class="fa fa-list-alt"></span>List Employee</a></li></sec:authorize>
                            </ul>
                        </li>
                    </sec:authorize>
                </ul>
            </li>
        </sec:authorize>


        <sec:authorize access="hasAnyRole('ROLE_BF_REPORT_ACCESS_LOG')">
            <li class="xn-openable">
                <a href="#" title="Reports"><span class="fa fa-file-text-o"></span><span class="xn-text">Reports</span></a>
                <ul>
                    <sec:authorize access="hasAnyRole('ROLE_BF_REPORT_ACCESS_LOG')">
                        <li>
                            <a href="../report/reportAccessLog.htm"><span class="fa fa-th-list"></span>Access Log</a>
                        </li>
                        <li>
                            <a href="../report/reportFCW.htm"><span class="fa fa-th-list"></span>FCW Report</a>
                        </li>
                        <li>
                            <a href="../report/reportPayroll.htm"><span class="fa fa-th-list"></span>Payroll Report</a>
                        </li>
                        <li>
                            <a href="../report/reportSales.htm"><span class="fa fa-th-list"></span>Sales Report</a>
                        </li>
                    </sec:authorize>
                </ul>
            </li>
        </sec:authorize>
    </ul>
    <!-- END X-NAVIGATION -->
</div>
<!-- END PAGE SIDEBAR -->