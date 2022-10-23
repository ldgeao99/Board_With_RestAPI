
console.log("Reply Module........");
 
// 모듈패턴(관련 있는 함수들을 하나의 모듈처럼 묶음으로 구성하는 방식)으로 ajax 관련 코드를 파일을 분리하여 작성
var replyService = (function(){
 
 	// javascript 에서는 함수정의할 때 매개변수를 3개 받는다고 했더라도, 왼쪽 순으로 채워서 호출하면 인자가 1개든 2개든 모두 가능하다는 점을 알자.
 
 	function add(reply, callback, error){		// 여기서 인자로 받은 콜백함수는 ajax 호출이 아래서 성공적으로 이뤄졌을 때 실행될 함수임
 		console.log("reply............");
 		
 		$.ajax({
 			type : 'post',
 			url : '/replies/new',		  // 맨 앞에 슬래시를 넣어줘야 그 앞에 board 가 안붙는 다는 점을 알아두자. 
 			data : JSON.stringify(reply), // javascript 객체를 JSON 문자열로 변환 후 이렇게 보내면 컨트롤러는 @RequestBody ReplyVO vo 로 받아서 처리함
 			contentType : "application/json; charset=utf-8",
 			success : function(result, status, xhr){ // result : 컨트롤러에서 ResponseEntity에서 BODY에 담겨온 값
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
 		
 		// 요청 url 맨 뒤에 .json 이 붙었다는 점에 주목.
 		$.getJSON("/replies/pages/" + bno + "/" + page + ".json", 
 			function(data){
 				//alert(typeof(data)); object
 				if(callback){		// callback 파라미터가 undifined가 아니라면(함수 호출시 넘겨받은 인자에 callback 함수가 담겨있었다면)
 					//callback(data);	// 댓글 목록만 가져오는 경우
 					callback(data.replyCnt, data.list); // 총 댓글 수와 댓글목록을 가져오는 경우 
 				}
 			}).fail(function(xhr, status, err){
 					if(error){		// callback 파라미터가 undifined가 아니라면(함수 호출시 넘겨받은 인자에 error 처리 함수가 담겨있었다면)
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
 		$.get("/replies/" + rno + ".json", 
 			function(result){
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
 		
 		if(gap < (1000 * 60 * 60 * 24)){ // 24시간이 아직 지나지 않은 댓글은 시:분:초 로 표시
 			
 			var hh = dateObj.getHours();
 			var mi = dateObj.getMinutes();
 			var ss = dateObj.getSeconds();
 			
 			// 리스트에 출력할 값을 쭉 넣고 join 함수를 통해 문자열로 바꿈
 			return [(hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss].join('');
 		}else{ // 24시간이 지난 댓글은 날짜만 표시
 			
 			var yy = dateObj.getFullYear();
 			var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
 			var dd = dateObj.getDate();		
 			
 			// 리스트에 출력할 값을 쭉 넣고 join 함수를 통해 문자열로 바꿈
 			return [yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '': '0') + dd].join('');
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
