/**
 *
 */
comutil = {
	isEmpty: function(str) {
		if (str == null || str == "undefined" || str.length == 0) {
			return true;
		}
		return false;
	},
    callAjax: function(_url, _param, _callback){

    	if (_url == null) {
    		if (typeof _callback == "function") {
    			_callback(false);
    		}
    		else {
    			return false;
    		}
    	}

        $.ajax({
            type    : "POST",
            url     : _url,
            data    : _param,
            success : function(rslt, status, err){
//            	console.log("err=", err.status)
    			if(err != null && err.status != null && (err.status == "401" || err.status == "203")) { //인증오류
    				alert(rslt);
    				//commonjs.login();
    			} else {
                	if (typeof _callback == "function") {
            			_callback(rslt);
            		}
                	else {
                		return rslt;
                	}
                
    			}
            }
        });
    },
	callAjaxGet: function(_url, _param, _callback){

    	if (_url == null) {
    		if (typeof _callback == "function") {
    			_callback(false);
    		}
    		else {
    			return false;
    		}
    	}

        $.ajax({
            type    : "GET",
            url     : _url,
            data    : _param,
            success : function(rslt, status, err){
            	//console.log("status=", status)
    			if(err != null && err.status != null && (err.status == "401" || err.status == "203")) { //인증오류
    				alert(rslt);
    				//commonjs.login();
    			} else {
                	if (typeof _callback == "function") {
            			_callback(rslt);
            		}
                	else {
                		return rslt;
                	}
                
    			}
            }
        });
    }
}

$.fn.pagingNavi = function(obj) {
	//required
	var pageNo = 1;
	var totalPage = 1;
	//optional
	var pageSize = 20;
	var pageCallback = obj.pageCallback;

	//전체 페이지 체크
	pageNo 		= obj.pageNo == null || obj.pageNo == undefined ? pageNo : Number(obj.pageNo);
	totalPage 	= obj.totalPage == null || obj.totalPage == undefined ? totalPage : Number(obj.totalPage);
	pageSize 	= obj.pageSize == null || obj.pageSize == undefined ? 10 : Number(obj.pageSize);
	try {
		pageCallback 	= typeof pageCallback == "function"  ? pageCallback : goPage;
	} catch (e) {
		alert("[ERROR] 페이징 오류: 콜백함수가 지정되지 않았습니다. 관리자에게 문의해 주세요.");
		return;
	}

	//draw paging
	var ele = '';
	ele += '<a href="javascript:void(0);" class="first" page="1"><span class="hide">첫페이지</span></a>';
	if (pageNo > 1) {
		ele += '<a href="javascript:void(0);" class="prev" page="'+(pageNo - 1)+'"><span class="hide">이전페이지</span></a>';
        
	}
	else {
		ele += '<a href="javascript:void(0);" class="prev"><span class="hide">이전페이지</span></a>';
	}
// 	ele += '<ol>';
	for( var i = 1 ; i <= totalPage; i++ ) {
		if (i == pageNo) {
			ele += '<a href="#" class="active">'+ i +'</a>';
		}
		else {
			ele += '<a href="javascript:void(0);" page="'+i+'">'+ i +'</a>';
		}
	}
// 	ele += '</ol>';
	if (pageNo < totalPage) {
		ele += '<a href="javascript:void(0);" class="next" page="'+(pageNo + 1)+'"><span class="hide">다음페이지</span></a>';
	}
	else {
		ele += '<a href="javascript:void(0);" class="next"><span class="hide">다음페이지</span></a>';
	}
	ele += '<a href="javascript:void(0);" class="last" page="'+totalPage+'"><span class="hide">마지막페이지</span></a>';

	$(this).html(ele);

	// goto 페이지 이벤트 등록
	$(this).find("a").on("click", function(e) {
		var page = $(this).attr("page");
		if (page == null || page == undefined) return;
		pageCallback(page);
	});
};