/**
 * jquery validation plug-in support Util script
 *
	e.g. invalidHandler
	//잘못된 양식이 제출되면 사용자 정의 코드에 대한 콜백
	var errors = validator.numberOfInvalids();	//에러 요소 수
	if (errors) {
		var message = errors == 1
			? 'You missed 1 field. It has been highlighted'
			: 'You missed ' + errors + ' fields. They have been highlighted';
		$("div.error span").html(message);
		$("div.error").show();
	} else {
		$("div.error").hide();
	}

	e.g.
	//잘못된 요소를 다시 페이드 아웃하여 강조 표시합니다.
	$(element).fadeOut(function() {
		$(element).fadeIn();
	});
 */
	validutil = {

		debug: false,
		onkeyup: false,
		ignore: [],
		errorClass: "error",
		errorElement: "p",
		addMethods: function() {
			//이름 - 한글/영문만 입력
			validutil.addMethod("nameRule_1", function(value, element, params) {
				if ( !comutil.isKorEng(value) ) {
					return false;
				}
				return true;
			}, messageutil.getMessage("M08.0016"));
			//이름 - 한글/영문만 입력
			validutil.addMethod("nameRule_7", function(value, element, params) {
				value = value.replace(/ /g, '');
				if ( !comutil.isKorEng(value) ) {
					return false;
				}
				return true;
			}, messageutil.getMessage("M08.0016"));
	
			//이름 - 한글만 입력
			validutil.addMethod("nameRule_2", function(value, element, params) {
				if ( !comutil.isKor(value) ) {
					return false;
				}
				return true;
			}, messageutil.getMessage("M15.0013", ["이름"]));
	
			//회원 아이디 - 자리수 및 영문숫자 조합 체크
			validutil.addMethod('idRule_1', function(value, element, params) {
				if(!/^[a-zA-Z0-9]{6,12}$/.test(element.value)){
					return false;
				}
				return true;
			}, messageutil.getMessage("M08.0017"));
	
			//회원 아이디 - 영문이 포함되어 있는지 체크
			validutil.addMethod('idRule_2', function(value, element, params) {
	
				if(!/[a-zA-Z]/.test(element.value)){
					return false;
				}
				return true;
			}, messageutil.getMessage("M08.0017"));
	
			//이메일 형식
			validutil.addMethod("emailRule_1", function(value, element, params) {
				return comutil.checkEmail(value);
			}, messageutil.getMessage("M08.0017", ["이메일"]));
	
			//비밀번호 규칙 1 - 8~32자
			validutil.addMethod('pwRule_1', function(value, element, params) {
	
				if(!/^[a-zA-Z0-9!@#$%^&*]{8,32}$/.test(element.value))
				{
					return false;
				}
				return true;
			}, messageutil.getMessage("M06.0007"));
	
			//비밀번호 규칙 2 - 숫자/문자/특수문자 혼용 필수
			validutil.addMethod('pwRule_2', function(value, element, params) {
	
				var chk_num = element.value.search(/[0-9]/g);
				var chk_eng = element.value.search(/[a-z]/ig);
				var chk_spec = element.value.search(/[!@#$%^&*]/ig);
				if(chk_num < 0 || chk_eng < 0 || chk_spec < 0)
				{
					return false;
				}
				return true;
			}, messageutil.getMessage("M06.0007"));
	
			//비밀번호 규칙 3 - 같은 문자 4번 이상 사용 불가
			validutil.addMethod('pwRule_3', function(value, element, params) {
	
				if(/(\w)\1\1\1/.test(element.value))
				{
					return false;
				}
				return true;
			}, messageutil.getMessage("M06.0002"));
			
			//회원 아이디 - 영문/숫자 체크(로그인시 확인)
			validutil.addMethod('idRule_3', function(value, element, params) {
				if(/[^a-zA-Z0-9]/g.test(value)){
					return false;
				}
				return true;
			}, messageutil.getMessage("M15.0014", ["아이디"]));
		},
		addMethod: function(methodname, f, message) {
			$.validator.addMethod(methodname, f, message);
		},
		validate: function(frmId, addOptions) {
	
			console.log("frmId", frmId)
			console.log("addOptions", addOptions)
	
			var rules = addOptions.rules;
			var messages = addOptions.messages;
			var highlight;
			var unhighlight;
			var invalidHandler;
			var submitHandler;
	
			var onkeyup = addOptions.onkeyup != undefined ? addOptions.onkeyup : this.onkeyup;
			var ignore = addOptions.ignore != undefined ? addOptions.ignore : this.ignore;
			var errorClass = addOptions.errorClass != undefined ? addOptions.errorClass : this.errorClass;
			var errorElement = addOptions.errorElement != undefined ? addOptions.errorElement : this.errorElement;
			var ignoreTitle = addOptions.ignoreTitle != undefined ? addOptions.ignoreTitle : this.ignoreTitle;
			var errorPlacement = addOptions.errorPlacement != undefined ? addOptions.errorPlacement : this.errorPlacement;
	
			highlight = addOptions.highlight != undefined ? addOptions.highlight : "";
			unhighlight = addOptions.unhighlight != undefined ? addOptions.unhighlight : "";
			invalidHandler = addOptions.invalidHandler != undefined ? addOptions.invalidHandler : "";
			submitHandler = addOptions.submitHandler != undefined ? addOptions.submitHandler : "";
	
			/*
			 * 필요에 따라 옵션 추가해서 사용 할 것
			 *
			 * -----------------------------------
			 */
	
			//폼 밸리데이션 옵션 설정
			var opts = new Object();
			//[기본 옵션 설정]
			opts.onkeyup = onkeyup;
			opts.ignore = ignore;	//히든 필드도 체크되도록, 기본적으로 히든필드의 경우 rules에 등록해도 체크 안됨.
			opts.errorClass = errorClass;
			opts.errorElement = errorElement;
			//[필수 옵션 설정]
			opts.rules = rules;
			opts.messages = messages;
			//[추가 옵션 설정]
			opts.ignoreTitle = ignoreTitle;	//에러메세지 title값 무시 여부
			opts.errorPlacement = errorPlacement;	//에러메세지 위치
			//유효하지 않은 필드를 강조 표시하는 방법. 강조 표시 할 필드와 방법을 결정하려면 무시하십시오.
			if (highlight != "") {
				opts.highlight = highlight;
			}
			else {
				//입력요소를 감싸고 있는 div 태그에 에러 클래스 설정
				opts.highlight = function(element, errorClass) {
					$(element).closest("div").addClass("valError");
				}
			}
			//유효한 필드일때 강조 표시 제거
			if (unhighlight != "") {
				opts.unhighlight = unhighlight;
			}
			else {
				//입력요소를 감싸고 있는 div 태그에 에러 클래스 제거
				opts.unhighlight = function(element, errorClass, validClass) {
					$(element).closest("div").removeClass("valError");
				}
			}
			//잘못된 양식이 제출되면 사용자 정의 코드에 대한 콜백
			if (invalidHandler != "") { opts.invalidHandler = invalidHandler; }
			if (submitHandler != "") {
				opts.submitHandler = submitHandler;
			}
			else {
				opts.submitHandler = function(form) {
					console.log("submitHandler called ")
					return true;
				}
			}
	
			//사용자 함수 추가
			validutil.addMethods();
			//밸리데이션 등록
			$("#"+frmId).validate(opts);
		}
	};