<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html ng-app="index.module.rootApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/static/jquery/jquery.js"></script>
<script type="text/javascript" src="/static/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="/static/angular-1.2.9/angular.js"></script>
<script type="text/javascript" src="/static/angular-1.2.9/angular-resource.js"></script>
<script type="text/javascript" src="/static/angular-1.2.9/angular-route.js"></script>
<script type="text/javascript" src="/static/angular-1.2.9/angular-sanitize.js"></script>
<script type="text/javascript" src="/static/angular-1.2.9/angular-animate.min.js"></script>

<script src="/static/angularstrap/angular-strap.js"></script>
<script src="/static/angularstrap/angular-strap.tpl.min.js"></script>
<script src="/static/angularstrap/angular-strap.tpl.min.js"></script>

<script type="text/javascript" src="/static/global/modules/global.modules.js"></script>

<script type="text/javascript" src="/static/index/modules/index.root.module.js"></script>
<script type="text/javascript" src="/static/index/configs/index.rootmodule.config.js"></script>
<script type="text/javascript" src="/static/index/controllers/index.controllers.result.js"></script>

<script type="text/javascript" src="/static/jquery/ui/jquery-ui.js"></script>

<script type="text/javascript" src="/static/bootstrap/js/ui-bootstrap-0.10.0.min.js"></script>
<script type="text/javascript" src="/static/bootstrap/js/bootstrap-select.js"></script>

<link href="/static/bootstrap/css/social-buttons-3.css" rel="stylesheet">
<link href="/static/bootstrap/css/font-awesome.css" rel="stylesheet">
<link href="/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/static/index/index.css" rel="stylesheet">
<link href="/static/global/css/global.css" rel="stylesheet">
<link href="/static/index/css/index.css" rel="stylesheet">
<link href="/static/angular/angular-motion.css" rel="stylesheet">
<link href="/static/bootstrap/css/bootstrap-select.min.css" rel="stylesheet">
<title>Okulr</title>
</head>
<body>
	<header-menu></header-menu>
	<div class="container" ng-view>
	</div>
</body>
</html>