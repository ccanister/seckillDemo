<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="common/tag.jsp"  %>
<!DOCTYPE html>
<html>
   <head>
      <title>秒杀详细页</title>
      <%@ include file="common/head.jsp" %>
   </head>
   <body>
      <div class="container">
           <div class="panel panel-default text-center">
               <div class="panel-heading ">     
               <h1>${seckill.name}</h1>
               </div>
               <div class="panel-body" >
               	<h2 class="text-danger">
               		<!-- 显示time图标 -->
					<span class="glyphicon glyphicon-time"></span>
					<!-- 展示倒计时 -->
					<span class="glyphicon" id="seckill-box"></span>
				</h2>
               </div>  
           </div>
      </div>
      <!-- 弹出登陆曾，输入电话 -->
      <div id="killPhoneModel" class="modal fade">
      	<div class="modal-dialog">
      		<div class="modal-content">
      			<div class="modal-head">
      				<h3 class="modal-title text-center">
      					<span class="glyphicon glyphicon-phone"></span>秒杀电话
      				</h3>
      			</div>
      			<div class="modal-body">
      				<div class="row">
      					<div class="col-xs-8 col-xs-offset-2">
      						<input type="text" name="killPhone" id="killPhoneKey"
      							placerholder="填手机号" class="form-control">
      					</div>
      				</div>
      			</div>
      			<div class="modal-footer">
      				<!-- 验证信息 -->
      				<span id="killPhoneMessage" class="glyphicon"></span>
   					<button type="button" id="killPhoneBtn" class="btn btn-success" >
   						<span class="glyphicon glyphicon-phone"></span>
   						Submit
   					</button>
      			</div>
      		</div>
      	</div> 
      </div>
      <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
      <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
 
      <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
      <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   	  <!-- 使用cdn获取jquery插件 -->
   	  <script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
   
      <script src="http://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
      <script src="/seckillDemo/sources/js/seckill.js" type="text/javascript" ></script>
      <script type="text/javascript" >
      	$(function(){   
      		//使用El表达式传入参数    
      		seckill.detail.init({
      			seckillId : ${seckill.seckillId},
      			startTime : ${seckill.startTime.time},	//转化毫秒方便Javascript做解析
      			endTime : ${seckill.endTime.time}
      		});
      	});
      </script>
   </body>
</html>