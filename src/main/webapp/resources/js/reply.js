
console.log("Reply Module........");
 
// 모듈패턴(관련 있는 함수들을 하나의 모듈처럼 묶음으로 구성하는 방식)으로 ajax 관련 코드를 파일을 분리하여 작성
var replyService = (function(){
 
 	function add(reply, callback, error){		// 여기서 인자로 받은 콜백함수는 ajax 호출이 아래서 성공적으로 이뤄졌을 때 실행될 함수임
 		console.log("reply............");
 		
 		$.ajax({
 			type : 'post',
 			url : '/replies/new',
 			data : JSON.stringify(reply),
 			contentType : "application/json; charset=utf-8",
 			success : function(result, status, xhr){
 					if(callback){ // callback 값으로 적절한 함수가 존재한다면 해당 함수를 호출해서 결과를 반영하게 됨
 					callback(result);
 				}
 			},
 			error : function(xhr, status, er){
 				if(error){
 					error(er);
 				}
 			}
 		});
 	}// add 함수 끝
 	
 	function getList(param, callback, error){
 	
 		var bno = param.bno;
 		var page = param.page || 1; // param.page 값이 null 이라면 디폴트로 1
 		
 		$.getJSON("/replies/pages/" + bno + "/" + page + ".json", 
 			function(data){
 				if(callback){
 					callback(data);
 				}
 			}).fail(function(xhr, status, err){
 					if(error){
 						error();
 					}
 				});
 	}// getList 함수 끝
 	
 	function remove(rno, callback, error){
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(deleteResult, status, xhr){
				if(callback){ 	// callback 파라미터가 undifined가 아니라면
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er){
				if(error){ 		// error 파라미터가 undifined가 아니라면
					error(er);
				}
			}
		});
 	}// remove 함수 끝
 	
 	function update(reply, callback, error){
 		
 		console.log("RNO: " + reply.rno);
 		
 		$.ajax({
 			type : 'put',
 			url : '/replies/' + reply.rno, 	//replies 앞에 슬래시 안붙이면 그 앞에 board가 붙어버려서 에러남
 			data : JSON.stringify(reply),
 			contentType : "application/json; charset=utf-8",
 			success : function(result, status, xhr){
 				if(callback){
 					callback(result);
 				}
 			},
 			error : function(xhr, status, er){
 				if(error){
 					error(er);
 				}
 			}
 		});
 	}
 	
 	function get(rno, callback, error){
 		$.get("/replies/" + rno + ".json", function(result){
 			if(callback){
 				callback(result);
 			}
 		}).fail(function(xhr, status, err){
 			if(error){
 				error();
 			}
 		});
 	}
 	
 	function displayTime(timeValue){
 		var today = new Date();
 		var gap = today.getTime() - timeValue;
 		var dateObj = new Date(timeValue);
 		
 		str = "";
 		
 		if(gap < (1000 * 60 * 60 * 24)){
 			
 			var hh = dateObj.getHours();
 			var mi = dateObj.getMinutes();
 			var ss = dateObj.getMinutes();
 			
 			// 리스트에 출력할 값을 쭉 넣고 join 함수를 통해 문자열로 바꿈
 			return [(hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss].join('');
 		}else{
 			var yy = dateObj.getFullYear();
 			var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
 			var dd = dateObj.getDate();		
 			
 			// 리스트에 출력할 값을 쭉 넣고 join 함수를 통해 문자열로 바꿈
 			return [yy, '/', (mm > 9 ? '' : '0') + m, '/', (dd > 9 ? '': '0') + dd].join('');
 		}
 	}
 	
	return { // 각각의 이름으로 함수가 담긴 객체를 반환한다.
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get,
		displayTime : displayTime
	}; // replyService 객체 내부에 add 라는 메서드, getList라는 메서드가 존재하는 형태로 보여지게 됨

})(); // 마지막에 함수를 호출함으로써 함수 묶음 객체를 반환함
