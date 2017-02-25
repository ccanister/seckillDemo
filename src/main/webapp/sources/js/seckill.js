//存放交互逻辑Js
//模块化
var seckill = {
		//秒杀相关的ajax的URl
		URL : {
			now : function(){
				return '/seckillDemo/time/now';	//方便维护好修改
			},
			exposer:function(seckillId){
				return '/seckillDemo/'+seckillId+'/exposer';
			},
			execution:function(seckillId,md5){
				return '/seckillDemo/'+seckillId+'/'+md5+'/execution';
			},
		},
		handleSeckillkill : function(seckillId,node){
			node.hide()
				.html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
			 $.post(seckill.URL.exposer(seckillId),{},function(result){
				//在回溯函数中，执行交互流程
				if(result && result['success']){
					var exposer = result['data'];
					if(exposer['exposed']){
						//开启秒杀
						//获取秒杀地址
						var md5 = exposer['md5'];
						var seckillUrl = seckill.URL.execution(seckillId,md5);
						$('#seckill-box').show();
						$('#killBtn').one('click',function(){
							//执行秒杀请求
							//1.先禁用按钮
							$(this).addClass('disabled');
							//2,发送秒杀需求请求执行秒杀
							$.post(seckillUrl,{},function(result){
								if(result && result['success']){
									var killResult = result['data'];
									var state = killResult['state'];
									var stateInfo = killResult['stateInfo'];
									console.log(stateInfo);
									//3，显示秒杀结果
									node.html('<span class="label label-success">'+stateInfo+'</span>')
								}
							});
						})
					} else {
						var nowTime = exposer['now'];
						var startTime = exposer['startTime'];
						var endTime = exposer['endTime'];
						//重新计算计时逻辑
						seckill.countdown(seckillId,now,startTime,endTime);
					}
				} else {
					console.log("result"+result);
				}
			})
		},
		validatePhone: function(userPhone){
			if(userPhone && userPhone.length == 11 && !isNaN(userPhone))
				return true;
			else 
				return false;
		},
		countdown: function(seckillId,nowTime,startTime,endTime){
			var seckillBox = $('#seckill-box');
			//时间判断
			if(nowTime>endTime){
				seckillBox.html('秒杀已结束');
			}else if(nowTime<startTime){
				//秒杀未开始，计时时间绑定
				//TOThink 为什么会计时
				var killTime = new Date(startTime+1000);
				seckillBox.countdown(killTime,function(event){
					console.log(killTime);
					//时间格式
					var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
					seckillBox.html(format);
				}).on('finish.countdown',function(){
					//获取秒杀地址，控制显示逻辑，执行秒杀
					seckill.handleSeckillkill(seckillId,seckillBox);
				});
			} else {
				seckill.handleSeckillkill(seckillId,seckillBox);
			}
		},
		//详情页秒杀逻辑
		detail : {
			//详情页初始化
			init:function(params){
				//手机登录验证，计时交互
				var userPhone = $.cookie('killPhone');
				//验证手机号
				if(!seckill.validatePhone(userPhone)){
					//绑定手机号
					//控制输出
					var killPhoneModel = $('#killPhoneModel');
					killPhoneModel.modal({
						show:true,//显示弹出层
						backdrop:'static',//禁止位置关闭
						keyboard:false//关闭键盘事件
					});
					$('#killPhoneBtn').click(function(){
						var inputPhone = $('#killPhoneKey').val();
						if(seckill.validatePhone(inputPhone)){ 
							//电话写入cookie
							$.cookie("killPhone",inputPhone,{expires:7});
							//刷新页面
							window.location.reload();
						} else {
							$('#killPhoneMessage').hide().html('<label class="label label-danger">手机号填写错误</label>').show(300);
							
						}
					});
				}
				var seckillId = params['seckillId'];
				var startTime = params['startTime'];
				var endTime = params['endTime'];
				//计时交互
				$.get(seckill.URL.now(),{},function(result){
					if(result && result['success']){
						var nowTime = result['data'];
						seckill.countdown(seckillId,nowTime,startTime,endTime);
					} else {
						console.log(result);
					}
				})
			}
		}
}	