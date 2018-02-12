<!DOCTYPE html>
<html lang="utf-8">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
		<meta name="keywords" content="">
		<meta name="description" content="">
		<title>个人主页</title>
		<#include "../common/res.html">
	</head>

	<body>
		<#include "../common/header.html">
		<div class="f-main clearfix">
			<div class="setting-left">
					<#include "./nav.html">
			</div>
			
			
			<div class="setting-right"  >
				<div>
					<span class="f-16">个人信息</span>
				</div>
				<div class="split-line" style="margin: 20px 0px;"></div>
				
				<div>
					<form class="oc-form" id="infoForm" method="post" action="${s.base}/user/saveInfo.html" enctype="multipart/form-data">
						<div >
							<input type="file" id="pictureImg" name="pictureImg" style="display: none;" onchange="photoImgChange();">
							<#if (authUser.header)?? && authUser.header != ''>
							<img id="user_header" src="${(authUser.header)!}" style="height:96px;">
							<#else>
							<img id="user_header" src="" style="display: none;height:96px;">
							</#if>
							<div style="margin:15px 0px;" class="clearfix">
								<a href="javascript:void(0);" onclick="doUpload();" style="float:left;" class="btn">更换头像</a>
								<span id="imgErrSpan" style="color:red;font-weight:normal;float:left;margin-left:10px;margin-top:5px;"></span>
							</div>
						</div>
						
						<li><label>昵称</label> 
							<span>${authUser.username!}</span>
						</li>
						<li><label>姓名</label> 
							<input name="realname"  value="${authUser.realname!}" type="text"  class="input-text2">
						</li>
						<li><label>性别</label> 
							<select class="input-select" name="gender">
								<option value="1" <#if authUser.gender?? && authUser.gender == 1> selected="selected"</#if> >男</option>
								<option value="0" <#if authUser.gender?? && authUser.gender == 0> selected="selected"</#if> >女</option>
							</select>
						</li>
						<li><label>学校</label> 
							<input name="collegeName"  value="${authUser.collegeName!}" type="text"  class="input-text2">
						</li>
						<li><label>资格证书</label> 
							<input name="certNo"  value="${authUser.certNo!}" type="text"  class="input-text2">
						</li>
						<li><label>学历</label> 
							<select class="input-select" name="education">
								<option value="本科" <#if authUser.education?? && authUser.education == '本科'> selected="selected"</#if>>本科</option>
								<option value="硕士" <#if authUser.education?? && authUser.education == '硕士'> selected="selected"</#if>>硕士</option>
								<option value="博士" <#if authUser.education?? && authUser.education == '博士'> selected="selected"</#if>>博士</option>
								<option value="博士后" <#if authUser.education?? && authUser.education == '博士后'> selected="selected"</#if>>博士后</option>
								<option value="大专" <#if authUser.education?? && authUser.education == '大专'> selected="selected"</#if>>大专</option>
							</select>
						</li>
						<li><label>qq</label>
							<input name="qq"  value="${authUser.qq!}"  type="text"  class="input-text2">
						</li>
						<li><label>个性签名</label>
							<input name="sign"  value="${authUser.sign!}"  type="text"  class="input-text2">
						</li>
						
						<li class="clearfix" style="margin-top: 50px;padding-left: 170px;">
							<div class="btn" onclick="infoSubmit();">保存</div>
						</li>
						
						<li>
							<div id="myAlert" class="alert alert-success" style="display: none;">
								<span id="myAlert_msg" class="color-oc f-16">保存成功！</span>
							</div>
						</li>
					</form>
				</div>
			</div>
		</div>
		
		<#include "../common/footer.html">
		<script type="text/javascript">
			
			function doUpload(){
				$('#pictureImg').click();
			}
			function photoImgChange(){
				var img = $('#pictureImg').val();
				if(oc.photoValid(img)){
					oc.previewUploadImg('pictureImg','user_header');
					$('#user_header').show();
					$('#imgErrSpan').html('');
					return;
				}else{
					$('#imgErrSpan').html('&nbsp;请选择png,jpeg,jpg格式图片');
					$('#pictureImg').val('');
				}
			}
		
			function infoSubmit(){
				$('#infoForm').ajaxSubmit({
					datatype : 'json',
					success : function(resp) {
						var resp = $.parseJSON(resp);
						if (resp.errcode == 0) {
							$("#myAlert").show().fadeOut(2500);//显示模态框
						} else {
							$("#myAlert").show().fadeOut(2500);//显示模态框
						}
					},
					error : function(xhr) {
					}
				});
			}
		</script>		
	</body>
	
</html>
