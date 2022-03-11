<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
<content tag="nav">
    <div role="navigation">
        <ul>
            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                <li class="controller">
                    <g:link controller="${c.logicalPropertyName}">${c.name.capitalize()}</g:link>
                </li>
            </g:each>
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
