<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Purchasing Management System</title>
</head>
<body>
<content tag="nav">
    <div role="navigation">
        <ul>
            <sec:ifNotLoggedIn>
                <li class="controller">
                    <g:link controller="login">Log In</g:link>
                </li>
            </sec:ifNotLoggedIn>
            <sec:ifLoggedIn>
                <li class="controller">
                    <g:link controller="article">Article</g:link>
                </li>
                <li class="controller">
                    <g:link controller="articleRequest">Article Request</g:link>
                </li>
                <li class="controller">
                    <g:link controller="brand">Brand</g:link>
                </li>
                <li class="controller">
                    <g:link controller="department">Department</g:link>
                </li>
                <li class="controller">
                    <g:link controller="employee">Employee</g:link>
                </li>
                <li class="controller">
                    <g:link controller="measurementUnit">Measurement Unit</g:link>
                </li>
                <li class="controller">
                    <g:link controller="shoppingOrder">Shopping Order</g:link>
                </li>
                <li class="controller">
                    <g:link controller="shoppingOrderDetail">Shopping Order Details</g:link>
                </li>
                <li class="controller">
                    <g:link controller="supplier">Suppliers</g:link>
                </li>
                <li class="controller">
                    <g:link controller="status">Status</g:link>
                </li>
                <li class="controller">
                    <g:link controller="logout">Log Out</g:link>
                </li>
            </sec:ifLoggedIn>
        </ul>
    </div>
</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <div class="container">
        <section class="row justify-content-center">
            <h1>Welcome to Purchasing Management System</h1>
        </section>
        <div class="row justify-content-center">
            <p>Project developed for the subject of Software Development with Open Source Technology II</p>
        </div>
        <div class="row justify-content-center">
            <p>Project made by:</p>
        </div>
        <div class="row justify-content-center">
            <p>Ronald Ogando, Nancy Campusano and Julio Sánchez</p>
        </div>
    </div>
</div>

</body>
</html>
