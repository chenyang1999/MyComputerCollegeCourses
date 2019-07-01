<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- basic scripts -->
	<!--[if !IE]> -->
	<script src="${path}/resources/assets/js/jquery-2.0.3.min.js"></script>
	<!-- <![endif]-->
	<!--[if IE]>
	<script src="${path}/resources/assets/js/jquery-1.10.2.min.js"></script>
	<![endif]-->
	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${path}/resources/assets/js/jquery-2.0.3.min.js'>"
								+ "<" + "/script>");
	</script>
	<!-- <![endif]-->
	<!--[if IE]>
	<script type="text/javascript">
    window.jQuery || document.write("<script src='${path}/resources/assets/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
	</script>
	<![endif]-->
	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='${path}/resources/assets/js/jquery.mobile.custom.min.js'>"
							+ "<" + "/script>");
	</script>
	<script src="${path}/resources/assets/js/bootstrap.min.js"></script>
	<script src="${path}/resources/assets/js/typeahead-bs2.min.js"></script>
	<!-- page specific plugin scripts -->
	<script src="${path}/resources/assets/js/jquery.dataTables.min.js"></script>
	<script
		src="${path}/resources/assets/js/jquery.dataTables.bootstrap.js"></script>
	<script
		src="${path}/resources/assets/js/dataTables.bootstrap.js"></script>
		
	<!-- ace scripts -->
	<script src="${path}/resources/assets/js/ace-elements.min.js"></script>
	<script src="${path}/resources/assets/js/ace.min.js"></script>
	<script src="${path}/resources/assets/js/handlebars.min.js"></script>
	<!-- inline scripts related to this page -->