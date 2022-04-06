<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'supplier.label', default: 'Supplier')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-supplier" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-supplier" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form class="form" resource="${this.supplier}" method="GET">
                <g:textField class="search" name="q" />
                <g:submitButton class="search-btn" name="Search" />
            </g:form>
            <g:form class="form" action="export" method="POST">
                <g:submitButton class="export-btn" name="Export to CSV" />
            </g:form>
            <f:table collection="${supplierList}" />

            <div class="pagination">
                <g:paginate total="${supplierCount ?: 0}" />
            </div>
        </div>
    </body>
</html>