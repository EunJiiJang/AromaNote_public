<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/tlds/user-function.tld" prefix="uf" %>
<!doctype html>
<html lang="ko">
<head>
	<c:import url="/WEB-INF/views/mobile/include/head.jsp"></c:import>
	<script type="text/javascript" src="<c:out value="${_head_cdnurl}" />/resources/extlib/validation/additional-methods.min.js"></script>
</head>
<script type="text/javascript">
	var param = new Object();
	param.d5 = '의사전용몰^의사회원 회원가입^02_정보입력';			//페이지명
	param.d6 = '의사전용몰';								//카테고리 1DEPTH
	param.d7 = '의사전용몰^의사회원 회원가입';					//카테고리 2DEPTH
	param.d8 = '의사전용몰^의사회원 회원가입^02_정보입력';			//카테고리 2DEPTH
	gtagjs.set(param);
</script>
<body>
	<div id="wrap">
		<!-- offer slide -->
		<c:import url="/WEB-INF/views/mobile/include/offerslide.jsp"></c:import>
		<!-- //offer slide -->

		<!-- container -->
		<div id="container">
			<!-- header -->
			<header class="header">
				<c:import url="/WEB-INF/views/mobile/include/header.jsp"></c:import>
			</header>
			<!-- //header -->

			<div class="registerForm join">
				<form:form commandName="memberBean" name="frm_memberCorpJoin2" id="frm_memberCorpJoin2" enctype="multipart/form-data" method="post">
				<form:hidden path="svcUseTrmsCsntFl"/>
				<form:hidden path="indInfoUseTrmsCsntFl"/>
				<form:hidden path="indInfoDealCnsgnmtCsntFl"/>
				<input type="hidden" name="encodingCstmNm"/>
				<%//본인인증 결과값 %>
				<input type="hidden" name="isAuthMember" id="hdn_isAuthMember"/>
				<input type="hidden" name="r_check_1" id="r_check_1"/>
				<input type="hidden" name="r_check_2" id="r_check_2"/>
				<input type="hidden" name="r_check_3" id="r_check_3"/>
				<input type="hidden" name="r_certNum" id="r_certNum"/>
				<input type="hidden" name="indCertCiNo" id="hdn_ciNo"/>
				<input type="hidden" name="baseKey" id="baseKeyJoin">
				<input type="hidden" name="baseKey2" id="baseKeyJoin2">
				<input type="hidden" name="runData" id="runData">
				<input type="hidden" name="pdfErrCheck" id="pdfErrCheck">
				<div class="steps">
					<h2 class="formTitle">의사 회원가입</h2>
					<ol>
						<li>01 약관동의</li>
						<li class="on">02 정보입력</li>
						<li>03 회원가입 완료</li>
					</ol>
				</div>

				<!-- 1. 본인인증 -->
				<div class="userInputArea first">
					<h3 class="formTitle2">
						1. 본인인증
						<span><em class="required">*</em> 필수 항목이므로 모두 입력해 주시기 바랍니다.</span>
					</h3>
					<p class="guideTxt5 mgb30">이름, 생년월일, 휴대폰번호는 반드시 첨부하는 사업자등록증에 기재된 정보와 동일해야 가입이 가능합니다.</p>
					<div class="inputText">
						<label for="cstmNm" class="label"><em class="required">*</em> 이름</label>
						<form:input path="cstmNm" id="txt_cstmNm" maxlength="100" />
					</div>
					<label class="label"><em class="required">*</em>성별</label>
					<div class="inputWrapList">
						<c:forEach items="${sexCdList}" var="sexCd" varStatus="i">
							<span class="inputWrap">
								<input type="radio" name="sexCd" id="rdo_sexCd${sexCd.cd}" value="${sexCd.cd}" <c:if test="${i.index eq 0}"> checked="checked"</c:if>/>
								<label for="rdo_sexCd${sexCd.cd}">${sexCd.cdNm}</label>
							</span>
						</c:forEach>
					</div>
					<label for="txt_brthDate" class="label"><em class="required">*</em> 생년월일(8자리)</label>
					<div class="inputText">
						<form:input path="brthDate" id="txt_brthDate" placeholder="생년월일 8자 입력" maxlength="8" />
					</div>
					<label class="label"><em class="required">*</em>국적</label>
					<div class="inputWrapList">
						<span class="inputWrap" >
							<input type="radio" id="frclCd1" name="frclCd" checked="checked" value="K">
							<label for="frclCd1">내국인</label>
						</span>
						<span class="inputWrap">
							<input type="radio" id="frclCd2" name="frclCd" value="F">
							<label for="frclCd2">외국인</label>
						</span>
						<!-- <p class="error">내국인/외국인 선택해 주세요.</p> -->
					</div>
					<label for="sel_mobileComp" class="label"><em class="required">*</em> 휴대폰 번호</label>
					<div class="inputPhoneCert">
						<div class="select">
							<select name="sel_mobileComp" id="sel_mobileComp">
								<option value="">통신사 선택</option>
							<c:forEach items="${mobileCompList}" var="mobileComp">
								<option value="${mobileComp.ATTRIBUTE1 }">${mobileComp.cdNm }</option>
							</c:forEach>
							</select>
						</div>
						<div class="inputText">
							<form:input path="mobileNo" id="txt_mobileNo" placeholder="-없이 입력하세요." maxlength="11" />
						</div>
					</div>

					<div class="btnWrap mgt30">
						<div id="div_sendAuthNum" class="btn sizeL style1"><a href="javascript:;" id="btn_sendAuthNum">인증번호 발송</a></div>
						<div id="div_resendAuthNum" class="btn sizeL style1" style="display: none;"><a href="javascript:;" id="btn_resendAuthNum">인증번호 재발송</a></div>
					</div>

					<div style="display: none;" class="inputText border tr_mobileAuth">
						<input type="text" title="인증번호" placeholder="인증번호를 입력해 주세요." id="txt_mobileAuthNumber" name="smsNum" maxlength="6">
						<span class="certTime"><span style="display: none;" id="auth_time_h"></span><span id="auth_time_m"></span>:<span id="auth_time_s"></span></span>
						<p class="error" id="p_timeover_err" style="display: none;"><spring:message code='M08.0027'/></p>
						<!-- <p class="error">인증번호를 잘못 입력하셨습니다.</p>
						<p class="error">인증번호를 0회 잘못 입력하셨습니다.</p> -->
					</div>
					<div style="display: none;" class="btnWrap mgt30 tr_mobileAuth">
						<div class="btn sizeL style4" id="spn_sendAuthNumCheck"><a href="javascript:;" id="btn_sendAuthNumCheck">인증하기</a></div>
					</div>
				</div>
				<!-- //1. 본인인증 -->

				<!-- 2. 개인 정보입력 -->
				<div class="userInputArea">
					<h3 class="formTitle2">
						2. 개인 정보입력
						<span><em class="required">*</em> 필수 항목이므로 모두 입력해 주시기 바랍니다.</span>
					</h3>
					<label for="txt_cstmId" class="label"><em class="required">*</em> 아이디</label>
					<div class="inputText">
						<form:input path="cstmId" id="txt_cstmId" placeholder="영문+숫자 조합 6~12자리" maxlength="20" />
					</div>

					<label for="pwd" class="label"><em class="required">*</em> 비밀번호</label>
					<div class="inputText">
						<form:password path="pwd" id="memberCorpJoin2Pwd" placeholder="영문+숫자+특수문자 조합 8자리 이상" maxlength="32" />
					</div>

					<label for="pwd_pwdchk" class="label"><em class="required">*</em> 비밀번호 확인</label>
					<div class="inputText">
						<input type="password" name="pwdchk" id="pwd_pwdchk" placeholder="영문+숫자+특수문자 조합 8자리 이상" maxlength="32">
					</div>

					<label for="" class="label"><em class="required">*</em> 이메일 주소</label>
					<div class="inputMail">
						<form:hidden path="email" id="hdn_email" />
						<div class="inputText">
							<input type="text" name="emailAccount" id="txt_emailAccount" maxlength="50">
						</div>
						<span class="at">@</span>
						<div class="inputText">
							<input type="text" name="emailDomain" id="txt_emailDomain" maxlength="50">
						</div>
						<div class="select">
							<select name="sel_emailDomain" id="sel_emailDomain">
								<option value="">직접입력</option>
								<c:forEach items="${emailDomainList }" var="emailDomain">
								<option value="${emailDomain.cdNm }">${emailDomain.cdNm }</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<!-- //2. 개인 정보입력 -->

				<!-- 3. 병원 정보입력 -->
				<div class="userInputArea">
					<h3 class="formTitle2">
						3. 병원 정보입력
						<span><em class="required">*</em> 필수 항목이므로 모두 입력해 주시기 바랍니다.</span>
					</h3>
					<p class="guideTxt5 mgb30">병원, 사업자번호, 의사면허번호는 반드시 첨부하는 사업자등록증에 기재된 정보와 동일해야 가입이 가능합니다.</p>
					<div class="inputText">
						<label for="" class="label"><em class="required">*</em> 병원명</label>
						<form:input path="hsptlNm" maxlength="100" />
					</div>

					<label for="txt_bizNo1" class="label"><em class="required">*</em> 사업자번호</label>
					<div class="inputLicence">
						<form:hidden path="bizNo" id="hdn_bizNo" />
						<div class="inputText">
							<input type="text" name="bizNo1" id="txt_bizNo1" maxlength="3">
						</div>
						<span class="dash">-</span>
						<div class="inputText">
							<input type="text" name="bizNo2" id="txt_bizNo2" maxlength="2">
						</div>
						<span class="dash">-</span>
						<div class="inputText">
							<input type="text" name="bizNo3" id="txt_bizNo3" maxlength="5">
						</div>
					</div>

					<label for="attachFile" class="label"><em class="required">*</em> 사업자등록증</label>
					<div class="inputFile" id="attachFiletr">
						<div class="inputText">
							<input type="text" readonly="readonly" id="fileNameRead">
							<form:input type="file" id="attachFileData" accept=".jpg,.png,.gif,.jpeg,.pjpeg,.pdf"  path="attachFile" placeholder="사업자등록증 이미지를 첨부하여 주세요." style="display:none;" />
							<form:input type="text" id="attachFileType" path="attachFileType" style="display:none;" />
						</div>
						<div class="btn sizeS style2"><a href="javascript:;">이미지 첨부</a></div>
					</div>
					<div class="inputWrap">
						<input type="checkbox" id="chk_imgFl">
						<label for="chk_imgFl">사업자등록증을 팩스 혹은 이메일로 발송하겠습니다.<br> 보내실 팩스번호 : 02-749-3039<br> 보내실 이메일 주소 : aestura_cs_b2b@aestura.com</label>
					</div>
					<div class="inputText">
						<label for="txt_drLicnsNo" class="label"><em class="required">*</em> 의사면허번호</label>
						<form:input path="drLicnsNo" id="txt_drLicnsNo" placeholder="-없이 입력하세요." maxlength="20" />
					</div>
					<div class="inputText">
						<label for="" class="label"><em class="required">*</em> 요양기관번호</label>
						<form:input path="cnvsOrgaNo" id="cnvsOrgaNo" placeholder="" maxlength="8" />
					</div>
					<label for="" class="label"><em class="required">*</em> 병원전화번호</label>
					
					<div class="hopt_tel_num ">
						<form:hidden path="amhcTphn" id="amhcTphn" />
						<div class="inputText">
							<input type="text" name="hsptNo1" id="txt_hsptNo1" maxlength="3">
						</div>
						<span class="dash">-</span>
						<div class="inputText">
							<input type="text" name="hsptNo2" id="txt_hsptNo2" maxlength="4">
						</div>
						<span class="dash">-</span>
						<div class="inputText">
							<input type="text" name="hsptNo3" id="txt_hsptNo3" maxlength="4">
						</div>
					</div>
					
					
					<label for="sel_membershipPathCd" class="label"><em class="required">*</em> 어떠한 경로로 에스트라 닥터몰을 알게 되셨나요?</label>
					<div class="inputMembershipPathCd">
						<div class="inputWrapList survey_wrap">
							<form:radiobuttons items="${membershipPathCdList}" path="membershipPathCd" itemValue="cd" itemLabel="cdNm"/>					
						</div>
					</div>
					
					<label for="sel_managementFl" class="label"><em class="required">*</em> 원내에 피부관리를 위한 전용 관리실이 있나요?</label>
					<div class="inputManagementFl" name="inputManagementFl">
						<span class="inputWrap">
							<input type="radio" name="managementFl" id="managementFlY" value="Y" checked>
							<label for="managementFlY">예</label>
						</span>
						<span class="inputWrap">
							<input type="radio" name="managementFl" id="managementFlN" value="N">
							<label for="managementFlN">아니요</label>
						</span>	
					</div>
					
					
				</div>
				<!-- //3. 병원 정보입력 -->

				<!-- 4. 마케팅 정보 수신동의 -->
				<div class="userInputArea">
					<h3 class="formTitle2">4. 마케팅 정보 수신동의</h3>
					<p class="guideTxt5 mgb30">사이트 이용 시, 다양한 이벤트 및 혜택 정보 등을 제공 받을 수 있습니다.</p>
					<div class="inputWrapList">
						<ul>
							<li>
								<div class="inputWrap">
									<form:hidden path="smsMktgRcvCsntFl" id="hdn_smsMktgRcvCsntFl" value="N" />
									<input type="checkbox" id="chk_smsMktgRcvCsntFl">
									<label for="chk_smsMktgRcvCsntFl">SMS 수신 동의(선택)</label>
								</div>
							</li>
							<li>
								<div class="inputWrap">
									<form:hidden path="emailMktgRcvCsntFl" id="hdn_emailMktgRcvCsntFl" value="N" />
									<input type="checkbox" id="chk_emailMktgRcvCsntFl">
									<label for="chk_emailMktgRcvCsntFl">이메일 수신 동의(선택)</label>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<!-- //4. 마케팅 정보 수신동의 -->

				<div class="btnWrap btm">
					<div class="btn sizeL style3"><a href="javascript:;" id="btn_memberCorpJoin2">입력 완료</a></div>
				</div>
				</form:form>
			</div>
			<div id="formTemp" style="display: none;">
			</div>
			<!-- footer -->
			<c:import url="/WEB-INF/views/mobile/include/footer.jsp"></c:import>
			<!-- //footer -->

			<!-- 공통 layer -->
			<c:import url="/WEB-INF/views/mobile/include/layer.jsp"></c:import>
			<!-- //layer -->
		</div>
		<!-- //container -->
	</div>
	<input type="text" id="attachFileText" path="attachFileText" style="display:none;" />
	<script type="text/javascript">
	
		// 본인인증 여부
		var authTimer;
		var clickedBtnNm;
		// id 중복체크
		var idDuplCheck = true;
		$(document).ready(function(){
			
			$("#membershipPathCd1").attr("checked","checked") ;
			
			$("#btn_memberCorpJoin2").click(function(e) {
				var formObj = $(this).closest("form");
				$(formObj).submit();
				return false;
			});

			$("#txt_bizNo1,#txt_bizNo2,#txt_bizNo3").change(function(){
				$("#hdn_bizNo").val( $("#txt_bizNo1").val()+"-"+$("#txt_bizNo2").val()+"-"+$("#txt_bizNo3").val()).change();
			});
			
			$("#txt_emailAccount,#txt_emailDomain").change(function(){
				$("#hdn_email").val( $("#txt_emailAccount").val()+"@"+$("#txt_emailDomain").val()).change();
			});

			$("#txt_cstmId").change(function(){
				idDuplCheck = true;
			});

			$("#sel_emailDomain").change(function(){
				if(comutil.isEmpty($(this).val())){
					$("#txt_emailDomain").val("");
					$("#txt_emailDomain").focus();
					$("#txt_emailDomain").prop("readonly",false);
				}else{
					$("#txt_emailDomain").val($(this).val()).change();
					$("#txt_emailDomain").prop("readonly",true);
				}
			});

			// SMS 수신 동의
			$("#chk_smsMktgRcvCsntFl").change(function(){
				if($(this).is(":checked")){
					$("#hdn_smsMktgRcvCsntFl").val("Y");
				}else{
					$("#hdn_smsMktgRcvCsntFl").val("N");
				}
			});
			// 이메일 수신 동의
			$("#chk_emailMktgRcvCsntFl").change(function(){
				if($(this).is(":checked")){
					$("#hdn_emailMktgRcvCsntFl").val("Y");
				}else{
					$("#hdn_emailMktgRcvCsntFl").val("N");
				}
			});

			// 숫자만 입력가능
			$("#txt_brthDate,#txt_mobileNo,#txt_bizNo1,#txt_bizNo2,#txt_bizNo3,#txt_drLicnsNo,#txt_hsptNo1,#txt_hsptNo2,#txt_hsptNo3").change(function(){
				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );
			});
			
			$("#txt_bizNo1").keyup(function(e) {
				if($(this).val().length == 3){
					$("#txt_bizNo2").focus();
				}
			});
			$("#txt_bizNo2").keyup(function(e) {
				if($(this).val().length == 2){
					$("#txt_bizNo3").focus();
				}
			});
			$("#txt_hsptNo1").keyup(function(e) {
				if($(this).val().length == 3){
					$("#txt_hsptNo2").focus();
				}
			});
			$("#txt_hsptNo2").keyup(function(e) {
				if($(this).val().length == 4){
					$("#txt_hsptNo3").focus();
				}
			});

		//옵션 생성
		var validOptions = {
			ignoreTitle: true,
			addMethod: $.validator.addMethod('isAuthMember', function(value, element, params){
				if($("#hdn_isAuthMember").val() != "Y"){
					return false;
				}else{
					return true;
				}
				},"<spring:message code='M06.0014' />"
			),
			addMethod: $.validator.addMethod('hsptlNmRule_1', function(value, element, params){
				if ( !comutil.isKorEng(value) ) {
			    	return false;
				}
				return true;
				},"<spring:message code='M15.0013' arguments='병원명' />"
			),
			addMethod: $.validator.addMethod('idDuplCheck', function(value, element, params){
				if ( ! idDuplCheck ) {
			    	return false;
				}
				return true;
				},"<spring:message code='M08.0004' arguments='아이디' />"
			),
			addMethod: $.validator.addMethod('hsptlNmRule_4', function(value, element, params){
				var check_spc = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
				if(check_spc.test(element.value)) {
					return false;
				}
				return true;
				},"<spring:message code='M15.0013' arguments='병원명' />"
			),
			addMethod: $.validator.addMethod('emailCheck', function(value, element, params){
				if ( !comutil.checkEmail(value) ) {
			    	return false;
				}
				return true;
				},"<spring:message code='M15.0013' arguments='이메일 형식' />"
			),
			addMethod: $.validator.addMethod('accept', function(value, element, params){
				return true;
				},"<spring:message code='M08.0018' />"
			),
			addMethod: $.validator.addMethod('requiredRule_2', function(value, element, params){
				if($("#chk_imgFl").is(":checked")){
		             $("#attachFileType").val("");
		        }else{
					if( $("#attachFileType").val() ==""){
						$("#fileNameRead").focus();
						return false;
					}
		        }
				return true;
				},"<spring:message code='M07.0007' />"
			),
			addMethod: $.validator.addMethod('requiredRule_3', function(value, element, params){
				if( $("#pdfErrCheck").val() =="N"){
					return false;
				}
				return true;
				},"PDF 용량은 1MBYTE 이하 파일만 업로드 가능합니다."
			),
			highlight: function(element, errorClass) {
				var elementNm = element.name;
				if(elementNm == 'sel_mobileComp'){
					$(element).closest("div").addClass("error");
				}else{
					$(element).closest("div").addClass("valError");
				}
	        },
	        unhighlight: function(element, errorClass) {
				var elementNm = element.name;
				if(elementNm == 'sel_mobileComp'){
					$(element).closest("div").removeClass("error");
				}else{
					$(element).closest("div").removeClass("valError");
				}
	        },
			errorPlacement: function(error, element) {
				var elementNm = element[0].name;
				if(elementNm == 'sexCd' ){
					error.insertAfter($("#div_sexCd"));
				}else if(elementNm == 'sel_mobileComp' || elementNm == 'mobileNo' ){
					$(".inputPhoneCert").siblings('p').remove();
					error.insertAfter($(".inputPhoneCert"));
				}else if(elementNm == 'email' || elementNm == 'emailAccount' || elementNm == 'emailDomain'){
					$(".inputMail").siblings('p').remove();
					error.insertAfter($(".inputMail"));
				}else if(elementNm == 'bizNo1' || elementNm == 'bizNo2' || elementNm == 'bizNo3'){
					$(".inputLicence").siblings('p').remove();
					error.insertAfter($(".inputLicence"));
				}else if(elementNm == 'hsptNo1' || elementNm == 'hsptNo2' || elementNm == 'hsptNo3'){
					$(".hopt_tel_num").siblings('p').remove();
					error.insertAfter($(".hopt_tel_num"));	
				}else if(elementNm == 'isAuthMember'){
				}else{
		            error.insertAfter(element);
				}
	        },
	        invalidHandler: function(form, validator) {
	        	console.log(validator);
	            var errors = validator.numberOfInvalids();
	            if (errors) {
	            	if(validator.errorList[0].method == "isAuthMember"){
		                mobileutil.alert("<spring:message code='M06.0014' />");
	            	}
	            	//mobileutil.alert(validator.errorList[0].message);
	                //validator.errorList[0].element.focus();
	            }
	        },
			rules: {
				cstmNm: 	{ required: true, nameRule_7: true },
				//cstmNm: 	{ required: true },
				sexCd: 		{ required: true },
				brthDate: 	{ required: true, number: true, rangelength:[8,8] },
				sel_mobileComp:	{ required: true },
				mobileNo: 	{ required: true, number: true, rangelength:[10,11] },
				isAuthMember:	{ isAuthMember: true },
				cstmId: 	{ required: true, idRule_1: true, idRule_2: true, idDuplCheck: true },
				pwd: 		{ required: true, pwRule_1: true, pwRule_2: true, pwRule_3: true },
				//202008 통합로그인 변경, name=pwd common.jsp에서 선언된 ssoLogin 폼의 필드값과 이름이 중복되어 id값으로 변경
				pwdchk: 	{ required: true, equalTo: "[id=memberCorpJoin2Pwd]", pwRule_1: true, pwRule_2: true, pwRule_3: true },	
// 				pwdchk: 	{ required: true, equalTo: "[name=pwd]", pwRule_1: true, pwRule_2: true, pwRule_3: true },
				email:		{ emailCheck: true },
				emailAccount:{ required: true },
				emailDomain:{ required: true },
				hsptlNm: 	{ required: true, hsptlNmRule_4: true },
				bizNo1:		{ required: true, number: true, rangelength:[3,3] },
				bizNo2:		{ required: true, number: true, rangelength:[2,2] },
				bizNo3:		{ required: true, number: true, rangelength:[5,5] },
				attachFile: { requiredRule_2: true, requiredRule_3: true, accept: ".jpg,.png,.gif,.jpeg,.pjpeg,.pdf", extension: "jpg|png|gif|jpeg|pjpeg|pdf"},
				drLicnsNo: 	{ required: true, number: true },
				hsptNo1:	{ required: true, number: true, rangelength:[2,3]},
				hsptNo2:	{ required: true, number: true, rangelength:[3,4]},
				hsptNo3:	{ required: true, number: true, rangelength:[4,4]},
				cnvsOrgaNo:	{ required: true, number: true, rangelength:[8,8]}
			},
			messages: {
				cstmNm: 	{ required: "<spring:message code='M15.0001' arguments='이름' />" },
				sexCd: 		{ required: "<spring:message code='M15.0003' arguments='성별' />" },
				brthDate: 	"<spring:message code='M15.0002' arguments='생년월일 8자' />",
				sel_mobileComp:	{ required: "<spring:message code='M15.0004' arguments='통신사' />" },
				mobileNo: 	"<spring:message code='M15.0002' arguments='휴대폰 번호' />" ,
				isAuthMember:	"<spring:message code='M06.0014' />",
				cstmId: 	{ required: 	"<spring:message code='M15.0002' arguments='아이디' />" },
				pwd: 		{ required: "<spring:message code='M15.9999' arguments='비밀번호는 공백없이 8자 이상의 영문/숫자/특수문자 조합만 가능합니다. ( 입력가능문자 : !@#$%^&* )' />",
					  pwRule_1: "<spring:message code='M15.9999' arguments='비밀번호는 공백없이 8자 이상의 영문/숫자/특수문자 조합만 가능합니다. ( 입력가능문자 : !@#$%^&* )' />",
					  pwRule_2: "<spring:message code='M15.9999' arguments='비밀번호는 공백없이 8자 이상의 영문/숫자/특수문자 조합만 가능합니다. ( 입력가능문자 : !@#$%^&* )' />",
					  pwRule_3: "<spring:message code='M15.9999' arguments='비밀번호는 공백없이 8자 이상의 영문/숫자/특수문자 조합만 가능합니다. ( 입력가능문자 : !@#$%^&* )' />",
					  },
				pwdchk: 	{
					required: 	"<spring:message code='M06.0008' />",
					equalTo: 	"<spring:message code='M06.0009' />"
				},
				bizNo1:		{
					required: 	"<spring:message code='M15.0001' arguments='사업자번호' />",
					number:		"<spring:message code='M15.0014' arguments='사업자번호' />",
					rangelength:"<spring:message code='M15.0001' arguments='사업자번호' />"
				},
				bizNo2:		{
					required: 	"<spring:message code='M15.0001' arguments='사업자번호' />",
					number:		"<spring:message code='M15.0014' arguments='사업자번호' />",
					rangelength:"<spring:message code='M15.0001' arguments='사업자번호' />"
				},
				bizNo3:		{
					required: 	"<spring:message code='M15.0001' arguments='사업자번호' />",
					number:		"<spring:message code='M15.0014' arguments='사업자번호' />",
					rangelength:"<spring:message code='M15.0001' arguments='사업자번호' />"
				},
				emailAccount: 	{ required: "<spring:message code='M15.0001' arguments='이메일' />" },
				emailDomain: 	{ required: "<spring:message code='M15.0001' arguments='이메일' />" },
				hsptlNm: 	{ required: "<spring:message code='M15.0001' arguments='병원명' />" ,
					hsptlNmRule_4:"<spring:message code='M15.9999' arguments='병원명을 입력해 주세요(특수문자는 허용되지 않습니다.)' />"	},
				attachFile: {
					accept:		"<spring:message code='M08.0018' />",
					extension: 	"<spring:message code='M08.0018' />"

				},
				drLicnsNo: 	{
					required: 	"<spring:message code='M15.0002' arguments='의사면허번호' />",
					number:		"<spring:message code='M15.0014' arguments='의사면허번호' />"
				},
				hsptNo1:		{
					required: 	"<spring:message code='M15.0001' arguments='병원전화번호' />",
					number:		"<spring:message code='M15.0014' arguments='병원전화번호' />",
					rangelength:"<spring:message code='M15.0001' arguments='병원전화번호' />"
					
				},
				hsptNo2:		{
					required: 	"<spring:message code='M15.0001' arguments='병원전화번호' />",
					number:		"<spring:message code='M15.0014' arguments='병원전화번호' />",
					rangelength:"<spring:message code='M15.0001' arguments='병원전화번호' />"
				},
				hsptNo3:		{
					required: 	"<spring:message code='M15.0001' arguments='병원전화번호' />",
					number:		"<spring:message code='M15.0014' arguments='병원전화번호' />",
					rangelength:"<spring:message code='M15.0001' arguments='병원전화번호' />"
				},
				cnvsOrgaNo: 	{
					required: 	"<spring:message code='M15.0002' arguments='요양기관번호' />",
					number:		"<spring:message code='M15.0014' arguments='요양기관번호' />",
					rangelength:"<spring:message code='M15.0001' arguments='요양기관번호' />"
				}
			},
			submitHandler: function(formObj){
				
				if ( !$("input:radio[name=membershipPathCd]").is(":checked") ) {
					mobileutil.alert("회원가입경로를 선택해주세요.");
			    	return;
				}
				
				if (  !$("input:radio[name=managementFl]").is(":checked") ) {
					mobileutil.alert("전용관리실유무를 선택해주세요.");
			    	return;
				}
				
				mobileutil.confirm("<spring:message code='M09.0002' />", function(rslt) {
					if (rslt == false) return;

				/* 	var f = $("form#frm_memberCorpJoin2")[0];
					var formData = new FormData(f); */
					var formData = $("form#frm_memberCorpJoin2").serialize();
					comutil.callAjax("/<c:out value="${_head_prefix}" />/membercorp/join/ajax.do", formData, function(data) {
						if(data == "ID_DUPLICATE"){
							mobileutil.alert("<spring:message code='M08.0004' arguments='아이디' />");
							idDuplCheck = false;
							$("#txt_cstmId").focus();
						}else if(data == "TEMP_EXSIT"){
							mobileutil.alert("<spring:message code='M14.0003' />");
						}else if(data == "SUCCESS"){
							//gtm
							gtagjs.join("%uAC00%uC785%uC644%uB8CC");
							
							var cstmNmTemp = $("#txt_cstmNm").val();
							var cstmIdTemp = $("#txt_cstmId").val();
							window.location.href = "/m/membercorp/joinComplete.do?cstmNm="+cstmNmTemp+"&encodingCstmNm="+cstmNmTemp+"&cstmId="+cstmIdTemp;
						//	formObj.encodingCstmNm.value = encodeURIComponent(formObj.cstmNm.value);
						//	formObj.action = "/<c:out value="${_head_prefix}" />/membercorp/joinComplete.do";
						//	formObj.submit();
						}
					});
				});
			}
		};

		//폼 밸리데이터 등록
		validutil.validate("frm_memberCorpJoin2", validOptions);

		//인증번호 발송
		function fn_sendAuthNum(){
			var f = document.frm_memberCorpJoin2;

			//파라미터
			var param = new Object();
			param.custNm = f.cstmNm.value;
			param.frclCd = comutil.getCheckedValue(f.frclCd);
			param.sxclCd = comutil.getCheckedValue(f.sexCd) == "CS0201" ? "M" : "F";
			param.athtDtbr = f.brthDate.value;
			param.cellNo = f.mobileNo.value;
			param.phoneCorp = comutil.getCheckedValue(f.sel_mobileComp);

			certSelf.sendSms(param, function(data) {
// 				//console.log(data);
				if (data.r_result == 'Y') {
					//본인인증 - 문자전송 성공
					$("#r_check_1").val(data.r_check_1);
					$("#r_check_2").val(data.r_check_2);
					$("#r_check_3").val(data.r_check_3);
					$("#r_certNum").val(data.r_certNum);
					// show & hide
					$(".tr_mobileAuth").show();
					$("#div_sendAuthNum").remove();	//인증하기버튼 숨김
					$("#div_resendAuthNum").show();	//재전송버튼 보임
					// alert 및 설정 변경
					mobileutil.alert("<spring:message code='M08.0024'/>");
					// 인증번호 카운트 다운 관련
					$(".certTime").show();
					clearTimeout(authTimer);
					authCountSec = 180;
					fn_startTime2();
				}else if(data.r_rsltCd == "KISH0006"){
					mobileutil.alert("<spring:message code='M08.0028'/>");
				}else {
					mobileutil.alert("<spring:message code='M06.0012'/>"+"("+data.r_rsltCd+")");
				}

			});
		}
		//인증번호 재발송
		function fn_resendAuthNum(){
			if($("#r_certNum").val() != "" && authCountSec <= 0){
				fn_sendAuthNum();
				return;
			}
			
			var f = document.frm_memberCorpJoin2;
			var param = new Object();
			param.r_check_1 = f.r_check_1.value;
			param.r_check_2 = f.r_check_2.value;
			param.r_check_3 = f.r_check_3.value;
			param.r_certNum = f.r_certNum.value;

			certSelf.resendSms(param, function(data) {
				if (data.r_result == 'Y') {
					//본인인증 - 문자전송 성공
					$("#r_check_1").val(data.r_check_1);
					$("#r_check_2").val(data.r_check_2);
					$("#r_check_3").val(data.r_check_3);
					$("#r_certNum").val(data.r_certNum);
					// alert 및 설정 변경
					mobileutil.alert("<spring:message code='M08.0024'/>");
					// 인증번호 카운트 다운 관련
					$(".certTime").show();
					clearTimeout(authTimer);
					authCountSec = 180;
					fn_startTime2();
				}else if(data.r_rsltCd == "KISH0006"){
					mobileutil.alert("<spring:message code='M08.0028'/>");
				}else{
					mobileutil.alert("<spring:message code='M06.0012'/>"+"("+data.r_rsltCd+")");
				}
			});
		}

		var authCountSec = 0;
		//인증번호 체크
		function fn_checkAuthNum(){
			if(authCountSec <= 0){
				$("#p_timeover_err").show();
				mobileutil.alert("<spring:message code='M08.0027'/>");
				return false;
			}

			var f = document.frm_memberCorpJoin2;

			//파라미터
			var param = new Object();
			param.smsNum = f.smsNum.value;
			param.r_check_1 = f.r_check_1.value;
			param.r_check_2 = f.r_check_2.value;
			param.r_check_3 = f.r_check_3.value;
			param.r_certNum = f.r_certNum.value;
			param.custNm = f.cstmNm.value;
			param.frclCd = comutil.getCheckedValue(f.frclCd);
			param.sxclCd = comutil.getCheckedValue(f.sexCd) == "CS0201" ? "M" : "F";
			param.athtDtbr = f.brthDate.value;
			param.cellNo = f.mobileNo.value;
			param.phoneCorp = comutil.getCheckedValue(f.sel_mobileComp);

			certSelf.checkSms(param, function(data) {
				if (data.r_result == 'Y') {
					mobileutil.alert("<spring:message code='M06.0013'/>");

					clearTimeout(authTimer);
					$("#hdn_isAuthMember").val("Y");
					$("#hdn_ciNo").val(data.ciNo);
					$("#div_mobileComp").addClass("disabled");
					$("#div_mobileComp > ul").remove();
					$("#txt_mobileNo,#txt_mobileAuthNumber").attr("readonly", "readonly");
					$("#div_resendAuthNum").addClass("disabled");
					$("#spn_sendAuthNumCheck").addClass("disabled");
					$(".certTime").hide();
				}else if(data.r_rsltCd == "KISH0006"){
					mobileutil.alert("<spring:message code='M08.0028'/>");
				}else{
					mobileutil.alert("<spring:message code='M08.0025' arguments=' '/>");
				}
			});
		}
		// 인증번호 카운트 다운
		function fn_startTime2() {
			var idParam = ['auth_time_h', 'auth_time_m', 'auth_time_s'];	//시간 텍스트가 삽입 될 위치 (시, 분, 초)
			dateutil.timeCountDown(authCountSec, idParam, "login_auth_timer");
			authCountSec--;
			if(authCountSec > -1){
				authTimer = setTimeout(fn_startTime2, 1000);		// 1초 마다 fn_startTime함수 실행
			}
		}
	</script>
<style>
	.hopt_tel_num, .inputPhone2 {overflow:hidden;}
	.hopt_tel_num .inputText, .inputPhone2 .inputText, .inputLicence .inputText, .inputPhone2 .select {float:left; width:30%;}
	.hopt_tel_num .dash, .inputPhone2 .dash {float:left; display:block; width:5%; line-height:38px; text-align:center;}
</style>
</body>
</html>
