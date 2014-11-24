/**
 * 后台管理端通用方法
 */
var npcCommon = {

    url: "/npc/",

    /**
     * 后台服务请求地址
     */
    jsonUrl: "/npc/manager/",
    
    /**
     * 上传文件临时存放位置
     */
    uploadFileUrl: "/npc/uploads/",

    /**
     * 获取页面中的各项输入项，保存为对象，用于向后台传送
     * @param page 页面对象
     * @returns {object} 键值对
     */
    getFormData: function (page) {
        var inputElements = page.find("input[type!=file], textarea, select, .checkbox");
        var params = {};
        var source = this;
        
        var duplicateKeys = [];
        
        $.each(inputElements, function (i, obj) {
            var el = $(obj);
            
            var key = el.attr("name") || el.attr("id") || "unknow", value = el.val();
            
            if (params[key]) {
            	duplicateKeys.push(key);
            	
            	if (source.isArray(params[key])) {
            		params[key].push(value.trim());
            	} else {
            		params[key] = [params[key], value.trim()];
            	}
            } else {
            	params[key] = value.trim();
            }
        });
        
        for(var index in duplicateKeys) {
        	var duplicateKey = duplicateKeys[index];
        	var values = params[duplicateKey];
        	
        	for(var i in values) {
        		params[duplicateKey + '[' + i + ']'] = values[i];
        	}
        	
        	delete params[duplicateKey];
        }

        return params;
    },
    
    // 判断对象是否是数组
    isArray : function(o) {   return Object.prototype.toString.call(o) === '[object Array]'; },
    
    constants: {
    	resourceType: {
    		ImageMain : "ImageMain",
    		ImageRelated : "ImageRelated",
    		Document : "Document"
    	}
    },

    /**
     * 将从后台获取的表单数据绑定到前台页面控件
     * @param data 表单数据
     */
    setFormData: function (data) {

        for (var key in data) {
            if (data.hasOwnProperty(key)) {
//            	if(["imageMainImgPath", "imageRelatedImgPath", "imageRelatedVideoPath", "imageRelatedVideoThumbImgPath"].indexOf(key) >= 0)
//            		continue;
            	
                if ($("#" + key).length > 0) {
                    $("#" + key).val(data[key]);
                } else if ($("[name='" + key + "']").length > 0) {
                    $("[name='" + key + "']").val(data[key]);
                }
            }
        }

        //给基本信息赋值
        $("#belongCongress").html(data["belongCongress"] || "");
        $("#submitPerson").html(data["submitPerson"] || "");
        $("#belongImageMain").html(data["belongImageMain"] || "");
        $("#submitTime").html(data["submitTime"] ? new Date(data["submitTime"]).format("yyyy-MM-dd hh:mm:ss") : "");
        $("#contentState").html(data["contentState"] || "");

        this.imageRelatedTypeChange($("#imageRelatedType").val());

        //预览图片
        $("#imageMainImgPreview").attr("src", this.url + data["imageMainImgPath"] || "");
        $("#imageRelatedImgPreview").attr("src", this.url + data["imageRelatedImgPath"] || "");
        $("#imageRelatedVideoThumbImgPreview").attr("src", this.url + data["imageRelatedVideoThumbImgPath"] || "");

        //文章段落
        $("#imageRelatedDocumentForm .para").html('<div class="col-md-10"><textarea class="form-control" name="paragraphContents"></textarea></div><div class="col-md-2"></div>');
        if (data["paragraphs"]) {
            $.each(data["paragraphs"], function (i) {
                if (i > 0) {
                    var html = '<div class="col-md-10"><textarea class="form-control" name="paragraphContents"></textarea></div>' +
                        '<div class="col-md-2">' +
                        '<button type="button" class="btn btn-primary btn-sm btnImageRelatedDocumentParaDelete">删除段落</button>' +
                        '</div>';
                    $("#imageRelatedDocumentForm .para").append(html);
                }
            });
            $("#imageRelatedDocumentForm .para textarea").each(function (i) {
                $(this).val(data["paragraphs"][i]);
            });
            //删除段落
            $(".btnImageRelatedDocumentParaDelete").off("click").click(function () {
                $(this).parent().prev().remove();
                $(this).parent().remove();
            });
        }
    },


    /**
     * 初始化内容页面
     */
    initContentPage: function () {

        var that = this;

        $("#contentContainer").hide();
        $("#btnSave").hide();
        $("#btnSubmitAudit").hide();
        $("#btnPublish").hide();
        $("#btnUnPublish").hide();

        //初始化上传控件
        //注意，这里使用了uploadify控件，用于ajax文件上传，官方网站：www.uploadify.com
        //注意不要改file_upload_1这个id，要不用不了
        //TODO:上传完毕，设置路径等
        
        // 主题图片
        $('#file_upload_1').uploadify({
        	'fileObjName' : 'uploadfile',
            'swf': that.url + 'static/uploadify/uploadify.swf',
            'uploader': that.url + 'manager/upload.html',
            'onUploadSuccess': function(file, data, response) {
            	try {
            		var res = jQuery.parseJSON(data);
    	        	if(res['success']) {
    	        		// 上传文件成功
    	        		$("#imageMainImgPath").val(file.name);
    	        		$("#imageMainImgPathHidden").val(res['data']); // 文件名
    	        		$("#imageMainImgPreview").attr("src", that.uploadFileUrl + res['data']); // 预览
    	        	} else {
    	        		alert("上传文件失败");
    	        	}
            	} catch (e) {
            		alert("上传文件失败");
            	}
            }
        });
        
        // 图片资料图片
        $('#file_upload_2').uploadify({
        	'fileObjName' : 'uploadfile',
            'swf': that.url + 'static/uploadify/uploadify.swf',
            'uploader': that.url + 'manager/upload.html',
            'onUploadSuccess': function(file, data, response) {
            	try {
            		var res = jQuery.parseJSON(data);
    	        	if(res['success']) {
    	        		// 上传文件成功
    	        		$("#imageRelatedImgPath").val(file.name);
    	        		$("#imageRelatedImgPathHidden").val(res['data']); // 文件名
    	        		$("#imageRelatedImgPreview").attr("src", that.uploadFileUrl + res['data']); // 预览
    	        	} else {
    	        		alert("上传文件失败");
    	        	}
            	} catch (e) {
            		alert("上传文件失败");
            	}
            }
        });
        
        // 视频资料文件
        $('#file_upload_3').uploadify({
        	'fileObjName' : 'uploadfile',
            'swf': that.url + 'static/uploadify/uploadify.swf',
            'uploader': that.url + 'manager/upload.html',
            'onUploadSuccess': function(file, data, response) {
            	try {
            		var res = jQuery.parseJSON(data);
    	        	if(res['success']) {
    	        		// 上传文件成功
    	        		$("#imageRelatedVideoPath").val(file.name);
    	        		$("#imageRelatedVideoPathHidden").val(res['data']); // 文件名
    	        	} else {
    	        		alert("上传文件失败");
    	        	}
            	} catch (e) {
            		alert("上传文件失败");
            	}
            }
        });
        
        // 视频资料缩略图
        $('#file_upload_4').uploadify({
        	'fileObjName' : 'uploadfile',
            'swf': that.url + 'static/uploadify/uploadify.swf',
            'uploader': that.url + 'manager/upload.html',
            'onUploadSuccess': function(file, data, response) {
            	try {
            		var res = jQuery.parseJSON(data);
    	        	if(res['success']) {
    	        		// 上传文件成功
    	        		$("#imageRelatedVideoThumbImgPath").val(file.name);
    	        		$("#imageRelatedVideoThumbImgPathHidden").val(res['data']); // 文件名
    	        		$("#imageRelatedVideoThumbImgPreview").attr("src", that.uploadFileUrl + res['data']); // 预览
    	        	} else {
    	        		alert("上传文件失败");
    	        	}
            	} catch (e) {
            		alert("上传文件失败");
            	}
            }
        });
        
    },

    /**
     * 显示编辑按钮
     */
    showEditButtons: function () {
        $("#btnSelectKeyword").show();
        $("#btnSelectPerson").show();
        $("#btnCreatePerson").show();
        $("#btnResetPerson").show();
        $("#btnSelectLocation").show();
        $("#btnCreateLocation").show();
        $("#btnResetLocation").show();
        $("#btnImageRelatedDocumentParaAdd").show();
        $(".btnImageRelatedDocumentParaDelete").show();

        $("input[type=file]").show();
    },

    /**
     * 隐藏编辑按钮
     */
    hideEditButtons: function () {
        $("#btnSelectKeyword").hide();
        $("#btnSelectPerson").hide();
        $("#btnCreatePerson").hide();
        $("#btnResetPerson").hide();
        $("#btnSelectLocation").hide();
        $("#btnCreateLocation").hide();
        $("#btnResetLocation").hide();
        $("#btnImageRelatedDocumentParaAdd").hide();
        $(".btnImageRelatedDocumentParaDelete").hide();

        $("input[type=file]").hide();
    },

    /**
     * 初始化保存，提交审批，发布，撤销发布等操作按钮显示状态
     */
    initOperationButtons: function () {
        switch ($("#contentState").html()) {
            case "未提交":
            case "审核拒绝":
                $("#btnSave").show();
                $("#btnSubmitAudit").show();
                $("#btnPublish").hide();
                $("#btnUnPublish").hide();
                break;
            case "审核中":
                $("#btnSave").hide();
                $("#btnSubmitAudit").hide();
                $("#btnPublish").hide();
                $("#btnUnPublish").hide();
                break;
            case "审核通过":
                $("#btnSave").hide();
                $("#btnSubmitAudit").hide();
                $("#btnPublish").show();
                $("#btnUnPublish").hide();
                break;
            case "已发布":
                $("#btnSave").hide();
                $("#btnSubmitAudit").hide();
                $("#btnPublish").hide();
                $("#btnUnPublish").show();
                break;
            default:
                $("#btnSave").show();
                $("#btnSubmitAudit").show();
                $("#btnPublish").hide();
                $("#btnUnPublish").hide();
                break;
        }
    },

    /**
     * 初始化主题，隐藏相关资料的表单信息
     * @param type 0-新建；1-修改；2-查看
     */
    initImageMain: function (type) {
        $(".imageRelatedItem").hide();
        $(".imageMainItem").show();
        $("#contentType").html("主题");
        $("#contentTypeNo").val(this.constants.resourceType.ImageMain);
        if (0 == type) {
        	$("#contentState").html("");
            $("#operationType").html("新建");
            $("#imageMainId").val("");
            $("#imageRelatedId").val("");
            $("#contentContainer").find("input, select, textarea").prop({
                disabled: false
            });
            $("#contentContainer").find("input, select, textarea").each(function () {
                $(this).val("");
            });
            $("#contentContainer").find(".unEditable").prop({
                disabled: true
            });
            this.showEditButtons();
            $("#btnSave").show();
            $("#btnSubmitAudit").show();
            $("#btnModify, #btnDelete").hide();
            $("div.alert").show();
        } else if (1 == type) {
            $("#operationType").html("修改");
            $("#contentContainer").find("input, select, textarea").prop({
                disabled: false
            });
            $("#contentContainer").find(".unEditable").prop({
                disabled: true
            });
            this.showEditButtons();
            $("#btnModify, #btnDelete").show();
            $("div.alert").show();
        } else {
            $("#operationType").html("查看");
            $("#contentContainer").find("input, select, textarea").prop({
                disabled: true
            });
            this.hideEditButtons();
            
            var contentState = $("#contentState").html();
            if (contentState == '未提交' || contentState == '审核拒绝') {
            	$("#btnModify, #btnDelete").show();
            } else {
            	$("#btnModify, #btnDelete").hide();
            }
            
            $("div.alert").hide();

        }
        this.initOperationButtons();
    },

    /**
     * 初始化相关资料，隐藏主题的表单信息
     * @param type 0-新建；1-修改；2-查看
     */
    initImageRelated: function (type) {

        $(".imageMainItem").hide();
        $(".imageRelatedItem").show();
        $("#contentType").html("相关资料");
//        $("#contentTypeNo").val("ImageRelated");
        if (0 == type) {
        	$("#contentState").html("")
            $("#operationType").html("新建");
            $("#imageMainId").val("");
            $("#imageRelatedId").val("");
            $("#contentContainer").find("input, select, textarea").prop({
                disabled: false
            });
            $("#contentContainer").find("input, select, textarea").each(function () {
                $(this).val("");
            });
            $("#contentContainer").find(".unEditable").prop({
                disabled: true
            });
            this.imageRelatedTypeChange($("#imageRelatedType").val());
            this.showEditButtons();
            $("#btnSave").show();
            $("#btnSubmitAudit").show();
            $("#btnModify, #btnDelete").hide();
            $("div.alert").show();
        } else if (1 == type) {
            $("#operationType").html("修改");
            $("#contentContainer").find("input, select, textarea").prop({
                disabled: false
            });
            $("#contentContainer").find(".unEditable").prop({
                disabled: true
            });
            this.showEditButtons();
            this.imageRelatedTypeChange($("#imageRelatedType").val());
            $("#btnModify, #btnDelete").show();
            $("div.alert").show();
        } else {
            $("#operationType").html("查看");
            $("#contentContainer").find("input, select, textarea").prop({
                disabled: true
            });
            this.hideEditButtons();
            this.imageRelatedTypeChange($("#imageRelatedType").val());
            
            var contentState = $("#contentState").html();
            if (contentState == '未提交' || contentState == '审核拒绝') {
            	$("#btnModify, #btnDelete").show();
            } else {
            	$("#btnModify, #btnDelete").hide();
            }

            $("div.alert").hide();
        }
        this.initOperationButtons();
    },

    /**
     * 相关资料类型改变触发的事件
     * 隐藏相应的表单等
     * @param type 类型：1-图片；2-视频；4-文章
     */
    imageRelatedTypeChange: function (type) {
        switch (type) {
            //图片
            case "1":
                $("#imageRelatedImgForm").show();
                $("#imageRelatedVideoForm").hide();
                $("#imageRelatedDocumentForm").hide();
                $("#contentTypeNo").val("ImageRelated");
                break;
            //视频
            case "2":
                $("#imageRelatedImgForm").hide();
                $("#imageRelatedVideoForm").show();
                $("#imageRelatedDocumentForm").hide();
                $("#contentTypeNo").val("ImageRelated");
                break;
            //文章
            case "4":
                $("#imageRelatedImgForm").hide();
                $("#imageRelatedVideoForm").hide();
                $("#imageRelatedDocumentForm").show();
                $("#contentTypeNo").val("Document");
                break;
            default :
                $("#imageRelatedImgForm").hide();
                $("#imageRelatedVideoForm").hide();
                $("#imageRelatedDocumentForm").hide();
                break;
        }
    },

    /**
     * 载入内容信息，用于修改或查看
     * @param contentType 内容类型：0-主题；1-相关资料
     * @param contentId 内容Id
     * @param isEdit 是否编辑
     */
    getContentDetail: function (contentType, contentId, isEdit) {
        var that = this;
        $.ajax({
            type: "POST",
            url: npcCommon.jsonUrl + "contentDetail.json",
            data: {
                contentType: contentType,
                contentId: contentId
            },
            success: function (data) {
                var jsonData = data;
                if (jsonData.success && jsonData.data) {
                    npcCommon.setFormData(jsonData.data);
                    $("#contentContainer").show();
                    $("#btnSubmitAudit").hide();
                    if ($("#contentTypeNo").val() == "ImageMain") {
                    	npcCommon.initImageMain(isEdit || 2);
                    } else {
                    	npcCommon.initImageRelated(isEdit || 2);
                    }
                    that.initAuditGrid(contentType, contentId);
                } else {
                    alert("获取内容信息失败！");
                }
            }
        });

    },

    /**
     * 初始化审批日志列表
     * @param contentType 内容类型：0-主题；1-相关资料
     * @param contentId 内容Id
     */
    initAuditGrid: function (contentType, contentId) {
        var source = {
            datatype: "json",
            type: "POST",
            datafields: [
                { name: 'auditUser', type: 'string' },
                { name: 'auditTime', type: 'date' },
                { name: 'operation', type: 'string' },
                { name: 'auditContent', type: 'string' },
                { name: 'auditResult', type: 'string' }
            ],
            root: "data",
            url: this.jsonUrl + "auditLog.json",
            data: {
                contentType: contentType,
                contentId: contentId
            }
        };
        var dataAdapter = new $.jqx.dataAdapter(source);
        
        var daterender = function (row, column, value) {
            return value;
        }

        $("#auditGrid").jqxGrid(
            {
                source: dataAdapter,
                columnsresize: true,
                autoheight: true,
                width: 700,
                columns: [
                    { text: '审核人', datafield: 'auditUser', width: 100, align: 'center', cellsalign: 'center' },
                    { text: '审核时间', datafield: 'auditTime', width: 170, align: 'center', cellsalign: 'center', cellsformat: 'yyyy-MM-dd HH:mm:ss' },
                    { text: '审核操作', datafield: 'operation', width: 80, align: 'center', cellsalign: 'center' },
                    { text: '审核意见', datafield: 'auditContent', width: 270, align: 'center' },
                    { text: '审核结果', datafield: 'auditResult', width: 80, align: 'center', cellsalign: 'center' }
                ]
            });

    },
    
    adjustSequence: function(resourceType, resoureId, direction) {
    	var that = this;
        $.ajax({
            type: "POST",
            url: npcCommon.jsonUrl + "adjustSequence.json",
            data: {
                resourceType: resourceType,
                resourceId: resoureId,
                direction: direction
            },
            success: function (data) {
                var jsonData = data;
                if (jsonData.success && jsonData.data) {
                	 $("#contentDataGridList").jqxTreeGrid('updateBoundData');
                } else {
                    alert("调整内容次序失败");
                }
            }
        });    	
    }


};