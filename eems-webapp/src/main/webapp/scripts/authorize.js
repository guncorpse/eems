function authorize() {
	$.ajax({
		type : "GET",
		url : context_path + "/api/business/get_user",
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			if (data.successResponse) {
				$("#admin_user").text(data.datas[0].nickname);
			} else {
				alert(data.message);
				window.location.href = pages_path + "/login.html";
			}
		}
	})
}

function logout() {
	$.ajax({
		type : "GET",
		url : context_path + "/api/business/logout",
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			if (data.successResponse) {
				alert('成功注销登录');
				location.reload();
			} else {
				alert(data.message);
			}
		}
	})
}