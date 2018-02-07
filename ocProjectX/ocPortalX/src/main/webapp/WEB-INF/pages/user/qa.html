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
				<div><span class="f-16">我的课程QA</span></div>
				<div class="split-line" style="margin: 20px 0px;"></div>
				
				<form id="queryPageForm" action="">
				<#if (page.items)??>
				<#list page.items as item>
				<div class="comment clearfix">
					<div class="comment-main" style="width: 100%">
						<div style="font-weight: bold;">
							<a href="${s.base}/course/learn/${item.courseId!}.html" target="_blank">
							${item.courseName!}
							</a>
						</div>
						<div style="margin-top:5px;padding-left:20px;">${item.sectionTitle!}</div>
						
						<div class="comment-content" style="padding-left:20px;">
							<div style="margin-bottom:5px;">
								<span style="font-weight:bold">${item.username!} 评论道： </span>
							</div>
							
							<#if item.refContent?? && item.refContent != ''>
							<div style="padding: 5px;border:1px solid #ccc;background-color:#eee;">
								${item.refContent!}
							</div>
							</#if>
							
							<div style="margin-top:5px;">
								${item.content!}
							</div>
						</div>
						<div class="comment-footer">
							<span>时间：
							<#if item.createTime??>
							${item.createTime?string('yyyy-MM-dd')}
							</#if>
							</span>
							<a href="#qaModal" data-toggle="modal" onclick="doQA(${item.id!})"  style="color:#0089D2;">回 答</a>
						</div>
					</div>
				</div>		
				</#list>
				
				<#include "../common/tailPage2.html">
				</#if>
				</form>
			</div>
		</div>
		<#include "../common/footer.html">
		
		<div class="modal fade" id="qaModal" tabindex="-1" role="dialog"  style="position:fixed; top:30%;">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content">
		        	<div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true" style="font-size:18px;">×</span>
		                </button>
		                <h4 class="modal-title"  >回答</h4>
		                <div class="clearfix"></div>
		            </div>
	            
		            <div class="modal-body">
		               	<form id="commentForm" class="form-horizontal" action="${s.base}/courseComment/doComment.html" method="post" style="padding: 0px 20px;">
							<input type="hidden" id="refId" name="refId" value=""/>
							<div class="form-group">
								<textarea id="qaContent" name="content" rows="" cols="80" maxlength="200"></textarea>
	                      	</div>
	                      	<div class="form-group">
	                      		<input id="indeityCode" class="form-control"  name="indeityCode" maxlength="6" class="input-text" type="text" style="width: 120px;float:left;" placeholder="请输入验证码"/>
								<img  id="identiryCodeQA"  onclick="reloadIndityImg('identiryCodeQA');" src="${s.base}/tools/identiry/code.html" style="width:80px;height:33px;float:left;margin-left:10px;float:left;border-radius:5px;"/>
	                      	</div>
	                      	<div class="form-group">
	                      		<span id="commentTip"></span>
	                      	</div>
	                      	<a href="javascript:void(0)">
	                      		<div onclick="doQASubmit();" class="header-login-btn">回答</div>
	                      	</a>
		                  </form>
		            </div>
		        </div>
		    </div>
		</div>
		
		<script type="text/javascript">
			function doQA(id){
				$('#refId').val(id);
			}
			
			function reloadIndityImg(eleId){
				document.getElementById(eleId).src = CONTEXT_PATH + "/tools/identiry/code.html?ticket=" + Math.random();
			}
			
			function doQASubmit(){
				$('#commentForm').ajaxSubmit({
					datatype : 'json',
					success : function(resp) {
						var resp = $.parseJSON(resp);
						if (resp.errcode == 0) {
							$('#identiryCode').val('');
							$('#qaContent').val('');
							
							$('#commentTip').css('color','green').html('评论成功');
							//自动刷新
							window.location.reload();
						} else if(resp.errcode == 1) {
							$('#commentTip').css('color','red').html('评论失败');
						}else if(resp.errcode == 2) {
							$('#commentTip').css('color','red').html('验证码输入有误');
						}else if(resp.errcode == 3) {
							$('#commentTip').css('color','red').html('评论内容长度请 &gt; 0 , &lt; 200');
						}
						
						reloadIndityImg('identiryCodeQA');
						$('#identiryCode').val('');
						$('#qaContent').val('');
					},
					error : function(xhr) {
					}
				});
			}
			
		</script>
		
	</body>
	
</html>
