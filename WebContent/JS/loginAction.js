$(function() {

	$("#login").click(function() {

		var name = $("#inputEmail").val();

		var password = $("#inputPassword").val();

		$.POST("login.action", {
			name : name,
			password : password
		}, function(data) {
			alert("data");
		});

	});

});