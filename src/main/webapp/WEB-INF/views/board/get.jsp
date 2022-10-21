<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
			
				<form id="operForm" action="/board/modify" method="get">
					<input type="hidden" id="bno" name="bno" value='<c:out value="${board.bno}"/>'>
					<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
					<input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
				</form>
				
				<div class="form-group">
					<label>Bno</label> 
					<input class="form-control" name="bno" value='<c:out value="${board.bno}"/>' readonly>
				</div>

				<div class="form-group">
					<label>Title</label> 
					<input class="form-control" name="title" value='<c:out value="${board.title}"/>' readonly>
				</div>

				<div class="form-group">
					<label>Text area</label> 
					<input class="form-control" rows="3" name="content" value='<c:out value="${board.content}"/>' readonly>
				</div>

				<div class="form-group">
					<label>Writer</label> <input class="form-control" rows="3" name="writer" value='<c:out value="${board.writer}"/>' readonly>
				</div>

				<button data-oper="modify" class="btn btn-defualt">Modify</button>
				<button data-oper="list" class="btn btn-info">List</button>

			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-6 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading" >
				<i class="fa fa-comments fa-fw"></i> Reply
				<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'> New Reply</button>
			</div>
			<!-- / .panel-heading -->
			
			<div class="panel-body">
				<ul class="chat">
					<!-- start reply, 이 안쪽에 댓글들을 추가하게 됨, 1개 댓글당 li 요소 1개 -->
					<!-- 
					<li class="left clearfix" data-rno='12'>
						<div>
							<div class="header">
								<strong class="primary-font">user00</strong>
								<small class="pull-right text-muted">2018-01-01 13:13</small>
							</div>
							<p>Good job!</p>
						</div>
					</li>
					-->
					<!-- end reply -->
				</ul>
				<!-- ./ end ul -->
			</div>
		</div>
	</div>
</div>
<!-- /.row -->


<%@include file="../includes/footer.jsp"%>

<!-- Modal(댓글[추가/조회/수정/삭제]를 하나로 모두 처리함) -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
			</div>
			<div class="modal-body">
				<div class="modal-body">
					<div class="form-group">
						<label>Reply</label>
						<input class="form-control" name="reply" value="New Reply!!!!">
					</div>
					<div class="form-group">
						<label>Replyer</label>
						<input class="form-control" name="replyer" value="replyer">
					</div>
					<div class="form-group">
						<label>Reply Date</label>
						<input class="form-control" name="replyDate" value="">
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button id="modalModBtn" type="button" class="btn btn-warning">Modify</button>
				<button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
				<button id="modalRegisterBtn" type="button" class="btn btn-primary">Register</button>
				<button id="modalCloseBtn" type="button" class="btn btn-default" data-dismiss="modal">Close</button> <!-- data-dismiss="modal" 가 있어야 닫는 동작이 작동함 -->
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>
	console.log(replyService);

	console.log("=======================");
	console.log("JS TEST");
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	
	/*
	//for replyService add test
	replyService.add(
	//add 함수의 첫번째 인자로 객체를 넘겨줌
	{
		reply : "JS Test",
		replyer : "tester",
		bno : bnoValue
	},
	//add 함수의 두번째 인자로 ajax 처리 성공시 얻은 결과로 콜백으로 호출시킬 함수를 넘겨줌(이름없는 함수)
	function(result) {
		alert("RESULT: " + result);
	});

	// 모든 댓글을 가져오는 메소드 호출
	replyService.getList({
		bno : bnoValue,
		page : 1
	}, function(list) {
		for (var i = 0, len = list.length || 0; i < len; i++) {
			console.log(list[i]);
		}
	});

	replyService.remove(16, function(count) {

		console.log(count);

		if (count == "success") {
			alert("REMOVED");
		}
	}, function(err) {
		alert("ERROR....");
	});

	replyService.update({
		rno : 22,
		bno : bnoValue,
		reply : "Modified Reply..."

	}, function(result) {
		alert("수정완료");
	});

	replyService.get(10, function(data) {
		console.log(data);
	}); 
	*/
	
</script>

<script type="text/javascript">

$(document).ready(function(){
	
	var operForm = $("#operForm");
	
	// 게시글 수정페이지 이동
	$("button[data-oper='modify']").on("click", function(){
		operForm.attr("action", "/board/modify").submit();
	});
	
	// 게시글 삭제버튼 이벤트 처리 
	$("button[data-oper='list']").on("click", function(){
		operForm.find("#bno").remove();
		operForm.attr("action", "/board/list");
		operForm.submit();
	});
});

//페이지가 열리면 자동으로 댓글 목록을 가져와서 <li> 태그를 구성하는 부분
$(document).ready(function(){
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	var replyUL = $(".chat");
	
	showList(1);
	
	function showList(page){
		
		replyService.getList(
				{
					bno : bnoValue,
					page : page || 1 	// null 이면 1
				}, function(list){
					
					var str = "";
					
					if(list == null || list.length == 0){
						replyUL.html("");
						return;
					}
					
					for(var i = 0, len = list.length || 0; i < len; i++){
						str += "<li class='left clearfix' data-rno='"+ list[i].rno +"'>";
						str += "<div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>";
						str += "<small class='pull-right text-muted'>" + replyService.displayTime((list[i].replyDate)) + "</small></div>";
						str += "<p>" + list[i].reply + "</p></div></li>";
					}
					
					replyUL.html(str);
					
				}); // end getList()
	}//end showList()
	
	
	/* 수정창 이벤트 처리 */
	var modal = $(".modal");
	var modalInputReply = modal.find("input[name='reply']");
	var modalInputReplyer = modal.find("input[name='replyer']");
	var modalInputReplyDate = modal.find("input[name='replyDate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	//New Reply 버튼을 눌렀을 때 이벤트 처리, 하나의 Modal에서 댓글 추가/수정/삭제를 다루는데, 댓글을 추가하는데 필요한 요소들만 남기고 모두 숨김
	$("#addReplyBtn").on("click", function(e){
		modal.find("input").val(""); 						// 모든 input 요소의 value 값을 초기화
		modalInputReplyDate.closest("div").hide();			// 사용자에게 입력받을 부분이 아니므로 숨김
		modal.find("button[id != 'modalCloseBtn']").hide();	// Close 버튼을 제외한 나머지를 숨김
		
		modalRegisterBtn.show();
		$(".modal").modal("show");
	});
	
	// 댓글등록(New Reply)모달 -> Regiter 버튼 클릭 이벤트 처리
	modalRegisterBtn.on("click", function(e){
		
		//Modal 내의 input 태그 값들을 읽어와 객체 생성
		var reply = {
			reply : modalInputReply.val(),
			replyer : modalInputReplyer.val(),
			bno : bnoValue
		};
		
		replyService.add(reply, function(result){
			alert(result);
			
			modal.find("input").val("");
			modal.modal("hide");
			
			showList(1); // 댓글목록 갱신
		});
	});
	
	// 댓글조회모달 -> Modify 버튼 클릭 이벤트 처리
	modalModBtn.on("click", function(e){
		var reply = {
				rno : modal.data("rno"),
				reply : modalInputReply.val()
		};
		
		replyService.update(reply, function(result){
			alert(result);
			modal.modal("hide");
			showList(1); // 댓글목록 갱신
		});
	});
	
	// 댓글조회모달 -> Remove 버튼 클릭 이벤트 처리
	modalRemoveBtn.on("click", function(e){
		
		var rno = modal.data("rno");
		
		replyService.remove(rno, function(result){
			alert(result);
			modal.modal("hide");
			showList(1); // 댓글목록 갱신
		});
	});
	
	// 특정 댓글 클릭시 이벤트 처리(class 값이 chat인 요소 안쪽의 li 요소가 클릭되었을 때 이벤트처리)
	$(".chat").on("click", "li", function(e){
		var rno = $(this).data("rno");
		console.log(rno);
		
		replyService.get(rno, function(reply){
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
			modal.data("rno", reply.rno); // 특정요소에 data-rno='12' 와 같이 값을 담을 수 있음
			
			// 수정버튼, 삭제버튼 을 제외한 나머지 버튼을 숨김
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
		});
	});
});
</script>
