/**
 * Datatable plug-in support Util script
 * dom :
 *  l - length changing input control
	f - filtering input
	t - The table!
	i - Table information summary
	p - pagination control
	r - processing display element

	ex> <"top"il>rt<"bottom"p> =>
	-------------------------------------------------------------------------------
		<"top"il> 	: 상단에 i(info), l(lengthChange) 출력,
		rt			: (table)출력,
		<"bottom"p> : 하단에 bottom class를 가진 div로 감싸고 div안에 페이징 출력
 */
		dtutil = {
			language: {
				"zeroRecords": "등록 된 데이터가 없습니다.",
				"lengthMenu": "한 페이지당 _MENU_",
				"info": "현재 _PAGE_ page / _PAGES_",
				"infoEmpty": "현재 0 page / 0",
				"select": {
					rows: ""			//"%d rows selected"
				}
			},
			lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
			init: function(id, options) {
		
				if (comutil.isEmpty(options.columns) == true) {
					console.log("[dtutil.datatable]필수 옵션가 누악되었습니다.(columns)");
					return false;
				}
				if (comutil.isEmpty(options.columnDefs) == true) {
					console.log("[dtutil.datatable]필수 옵션가 누악되었습니다.(columnDefs)");
					return false;
				}
				//필수옵션
				var opt_columns = options.columns;
				var opt_columnDefs = options.columnDefs;
				//옵셔널...
				var opt_info = comutil.isEmpty(options.info) == true ? true : options.info;
				var opt_numbering = comutil.isEmpty(options.numbering) == true ? -1 : options.numbering;
				var opt_language = typeof options.language == 'undefined' ? "" : options.language;
				var opt_paging = comutil.isEmpty(options.paging) == true ? true : options.paging;
				var opt_ordering = comutil.isEmpty(options.ordering) == true ? true : options.ordering;
				var opt_pageLength = comutil.isEmpty(options.pageLength) == true ? 10 : options.pageLength;
				var opt_processing = comutil.isEmpty(options.processing) == true ? false : options.processing;
				var opt_serverSide = comutil.isEmpty(options.serverSide) == true ? false : options.serverSide;
				var opt_scrollY = comutil.isEmpty(options.scrollY) == true ? '' : options.scrollY;
				var opt_scrollCollapse = comutil.isEmpty(options.scrollCollapse) == true ? false : options.scrollCollapse;
				var opt_reorder = comutil.isEmpty(options.rowReorder) == true ? false : options.rowReorder;
				var opt_ajax = comutil.isEmpty(options.ajax) == true ? "" : options.ajax;
				//페이지 사이즈 옵션 설정(디롵르 false)
				var opt_lengthChange = false;
				if (opt_paging == true) {	//페이징 옵션이 있으면 파라미터에 따라 설정
					opt_lengthChange = comutil.isEmpty(options.lengthChange) == true ? true : options.lengthChange;
				}
				//페이지 사이즈 메뉴 설정 ( opt_lengthChange 가 true인 경우 출력 됨)
				var opt_lengthMenu = false;
				if (opt_lengthChange == true) {
					opt_lengthMenu = comutil.isEmpty(options.lengthMenu) == true ? this.lengthMenu : options.lengthMenu;
				}
		
				//drawcallback
				var opt_drawCallback = comutil.isEmpty(options.drawCallback) == true ? false : options.drawCallback;
				if (opt_drawCallback == false) {
					opt_drawCallback = function( settings ) {
						// NO.컬럼 넘버링 설정
						if (opt_numbering != -1) {
							dtutil.setNumbering(dt, opt_numbering);
						}
					}
				}
				
				//rowCallback
				var opt_rowCallback = comutil.isEmpty(options.rowCallback) == true ? false : options.rowCallback;
				
				//footerCallback
				var opt_footerCallback = comutil.isEmpty(options.footerCallback) == true ? false : options.footerCallback;
		
				// 메세지 설정
				var vLanguage = this.language;	// 디폴트 메세지
				if (opt_language != "") {
					for (var _key_ in vLanguage) {
						//옵션으로 넘어온 메세지키와 일치하면 메세지 설정
						for (var key in opt_language) {
							if (_key_ != key) continue;
							//일치하는 key가 있으면 메세지 설정
							vLanguage[key] = opt_language[key];
							break;
						}
					}
				}
				//lanague-> this.language 선언해도 기존 lanuage로 남아있어서 직접 초기화시킴 
				else{
					vLanguage = {
						"zeroRecords": "등록 된 데이터가 없습니다.",
						"lengthMenu": "한 페이지당 _MENU_",
						"info": "현재 _PAGE_ page / _PAGES_",
						"infoEmpty": "현재 0 page / 0",
						"select": {
							rows: ""			//"%d rows selected"
						}
					}
				}
		
				var opt_order = comutil.isEmpty(options.order) == true ? [] : options.order;	//디폴트 오더 컬럼 없음.
				var dt = $('#' + id).DataTable({
					//  responsive: true,
					//  dom: '<"top"il>rt<"bottom"p>',
					searching: false,
					info: false,
					select: true,
					lengthChange: false,
					lengthMenu: false,
					language: vLanguage,
					paging: false,
					ordering: false,
					order: false,
					// pageLength: opt_pageLength,
					processing: opt_processing,
					serverSide: opt_serverSide,
					scrollY: opt_scrollY,
		//	 	    scrollY: '20vh',
					scrollCollapse: opt_scrollCollapse,
		//	 	    stateSave: true,
		//	 	    stateDuration: -1,
					columns: opt_columns,
					// rowReorder: opt_reorder,
					columnDefs: opt_columnDefs,
					ajax: opt_ajax,
					drawCallback: opt_drawCallback,
					rowCallback: opt_rowCallback,
					footerCallback: opt_footerCallback
		//		drawCallback: function( settings ) {
		//			// NO.컬럼 넘버링 설정
		//			if (opt_numbering != -1) {
		//				dtutil.setNumbering(dt, opt_numbering);
		//			}
		//		}
				 });
		
				var dblCallback = options.dblCallback;
				if (comutil.isEmpty(dblCallback) == false && typeof dblCallback == "function") {
					$('#' + id).find("tbody").on('dblclick', 'tr', function () {
						dt.row( this ).select();
						var data = dt.row( this ).data();
						dblCallback(data);
					});
				}
		
				var dtOptions = new Object();
				dtOptions.numbering = opt_numbering;
		
				// datatable 객체 반환
				dt.dtOptions = dtOptions;
				return dt;
			},
			/*
			 * dt 	: datatable 오브젝트
			 * inx 	: 넘버링이 필요한 컬럼 inx
			 */
			setNumbering: function(dt, inx) {
		//		console.log("dt=", dt)
				if (dt != null && dt != "undefined") {
					var rowCount = dt.rows().count();
		//			var rnum = rowCount;
		//			dt.rows().every(function( rowIdx, tableLoop, rowLoop ) {
		//				this.data().rnum = rnum;
		////				dt.cell(rowIdx, inx).innerHTML = rnum--;
		//				this.column(inx).nodes(rowIdx).to$().html(rnum--);
		//			});
		
					dt.column(inx).nodes().each(function(cell, i) {
						cell.innerHTML = rowCount--;
					});
				}
			},
			//row 추가
			addRow: function(dt, data) {
				dt.row.add(data).draw();
			},
			//row list 추가
			addRows: function(dt, list) {
				dt.rows().clear();
				if (list != null && list != undefined && list.length > 0) {
					dt.rows.add(list);
				}
				dt.rows().draw();
			},
			//data 갱신
			renewRow: function(dt, newData, key) {
				dt.rows().every(function( rowIdx, tableLoop, rowLoop ) {
					var data = this.data();
		//			console.log("renewRow.rowIdx=", rowIdx)
		//			console.log("renewRow.tableLoop=", tableLoop)
		//			console.log("renewRow.rowLoop=", rowLoop)
		//			console.log("dt.dtOptions=", dt.dtOptions)
					//수정된 상품
					if (data[key] == newData[key]) {
						var count = dt.rows().count();
						newData.rnum = (count - rowIdx);
						this.data(newData);
						return false;
					}
				});
			},
			removeRow: function(dt, targ) {
				dt.row(targ).remove().draw();
			},
			removeRows: function(dt) {
				dt.rows().remove().draw();
			},
			getDataByIndex: function(dt, inx) {
				return dt.rows(inx).data()[0];
			}
		
		};