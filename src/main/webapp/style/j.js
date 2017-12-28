$(function(){
	$("#box .pic").eq(0).show();
	var i=0;
	function fad(){
		var m = i%4;
		var n = (i+1)%4;
		$("#box .pic").eq(m).fadeOut(2000);
		$("#box .pic").eq(n).fadeIn(2000);
		i++;
	};
	setInterval(fad,3000);
});


function submit() {
	$('#ff').form('submit', {
		url : 'exist/',
		success : function(msg) {
			msg = JSON.parse(msg);
			if (msg.code == 1) {
				$.messager.alert("提示消息", "登录成功");
				window.location.href = "layout.html";
			} else {
				$.messager.alert("错误消息", "账号或者密码错误");
			}
		}
	});
}