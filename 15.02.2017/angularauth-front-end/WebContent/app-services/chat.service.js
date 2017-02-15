(function(){
	'use strict';
	angular.module('app').service("ChatService",function($q,$timeout,$rootscope){
		var user=$rootScope.currentUser;
		var service={},listner=$q.defer(),socket={
			client:null,
			stomp:null
		},messageIds=[];
		
		service.RECONNECT_TIMEOUT=30000;
		service.SOCKET_URL="/angularauth-rest/chat";
		service.CHAT_TOPIC="/topic/message";
		service.CHAT_BROKER="/app/chat";
		
		service.receive=function(){
			console.log("receive")
			return listner.promise;
		};
		
		service.send=function(message){
			console.log("send")
			var id=Math.floor(Math.random()*1000000)
			socket.stomp.send(service.CHAT_BROKER,{
				priority:9
			},JSON.stringify({
				message:message,
				username:user.username,
				id:id
			}
					)
			);
			messageIds.push(id);
		};
		
		var reconnect=function(){
			console.log("reconnect")
			$timeout(function(){
				initialize();
			},this.RECONNECT_TIMEOUT);
		};
		
		var getMessage=function(data){
			console.log("getMessage")
		var message=JSON.parse(data),out={};
			out.messsage=message.message;
			out.username=message.username;
			out.time=new Date(message.time);
			
			return out;
		};
	})
}
		)