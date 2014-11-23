$(document).ready(function () {

    //初始化页面
    npcCommon.initContentPage();

    //初始化届别选择框
    var congressListSource = {
        datatype: "json",
        datafields: [
            { name: "congressName" },
            { name: "congressId" }
        ],
        root: "data",
        url: npcCommon.jsonUrl + "congressList.json"
    };
    var congressListDataAdapter = new $.jqx.dataAdapter(congressListSource);
    $("#congressSelect").jqxDropDownList({
        source: congressListDataAdapter,
        displayMember: "congressName",
        valueMember: "congressId",
        promptText: "请选择届别..."
    });

    //届别选择后执行的事件
    $("#congressSelect").on("select", function (event) {
        if (event.args) {
            var item = event.args.item;
            if (item) {
                $("#belongCongressId").val(item.value);
            }
        }
    });

    //点击选择主题或相关资料按钮
    $("#btnSelectContent").click(function (event) {
        if (!$("#belongCongressId").val()) {
            alert("请先选择届别！");
            return false;
        } else {
            //从后台载入数据
            var source =
            {
                dataType: "json",
                dataFields: [
                    { name: 'id', type: 'number' },
                    { name: 'pid', type: 'number' },
                    { name: 'title', type: 'string' },
                    { name: 'typeDisplay', type: 'string' },
                    { name: 'type', type: 'number' },
                    { name: 'resourceType', type: 'string' },
                    { name: 'resourceId', type: 'number' }
                ],
                hierarchy: {
                    keyDataField: { name: 'id' },
                    parentDataField: { name: 'pid' }
                },
                id: 'id',
                url: npcCommon.jsonUrl + "contentList.json?congressId=" + $("#belongCongressId").val()
            };
            var dataAdapter = new $.jqx.dataAdapter(source);
            //初始化树状列表
            $("#contentDataGridList").jqxTreeGrid(
                {
                    source: dataAdapter,
                    pageable: true,
                    columnsResize: true,
                    height: 500,
                    width: 800,
                    columns: [
                        { text: '内容名称', dataField: 'title', minWidth: 300, width: 500 },
                        { text: '内容类型', dataField: 'typeDisplay', width: 100 },
                        { text: 'TYPE', dataField: 'type', hidden: true  },
                        { text: 'ID', dataField: 'id', hidden: true },
                        { text: 'PID', dataField: 'pid', hidden: true },
                        { text: '资源类型', dataField: 'resourceType', hidden: true },
                        { text: '资源标识', dataField: 'resourceId', hidden: true },
                        { text: '操作', width: 200,
                            cellsrenderer: function (row, columnfield, value, obj) {
                                console.log(obj);
                                var html = '<button type="button" class="btn btn-primary btn-xs btnDGLSelect" data-id="' + obj.id + '" data-type="' + obj.type + '" data-resourceType="' + obj['resourceType'] + '" data-resourceId="' + obj['resourceId'] + '" data-pid="' + obj.pid + '">&nbsp;&nbsp;选择&nbsp;&nbsp;</button>&nbsp;&nbsp;' +
                                    '<button type="button" class="btn btn-primary btn-xs btnDGLUp" data-id="' + obj.id + '" data-type="' + obj.type + '" data-resourceType="' + obj['resourceType'] + '" data-resourceId="' + obj['resourceId'] + '">上移</button>&nbsp;&nbsp;' +
                                    '<button type="button" class="btn btn-primary btn-xs btnDGLDown" data-id="' + obj.id + '" data-type="' + obj.type + '" data-resourceType="' + obj['resourceType'] + '" data-resourceId="' + obj['resourceId'] + '">下移</button>';

                                return html;
                            }
                        }
                    ],
                    rendered: function () {

                        //TODO:完善操作
                        $(".btnDGLSelect").off("click").on("click", function () {
                            var pid = $(this).attr("data-pid");
                            var type = $(this).attr("data-type");
                            var resourceType = $(this).attr("data-resourceType");
                            var resourceId = $(this).attr("data-resourceId");
                            $("#belongContentSelectModal").modal("hide");
                            $("#contentTypeNo").val(resourceType);
                            $("#contentId").val(resourceId);
                            if (type == 0) {
                                $("#belongImageMainId").val("");
                                $("#imageMainId").val(resourceId);
                                $("#imageRelatedId").val("");
                            } else {
                                $("#belongImageMainId").val(pid);
                                $("#imageMainId").val("");
                                $("#imageRelatedId").val(resourceId);
                            }

                            npcCommon.getContentDetail(resourceType, resourceId);
                        });
                        $(".btnDGLUp").off("click").on("click", function () {
                            alert($(this).attr("data-id"));
                        });
                        $(".btnDGLDown").off("click").on("click", function () {
                            alert($(this).attr("data-id"));
                        });
                    }
                });
        }
    });

    //相关资料类型选择触发事件
    $("#imageRelatedType").on("change", function () {
        npcCommon.imageRelatedTypeChange($(this).val());
    });

    //增加段落操作
    $("#btnImageRelatedDocumentParaAdd").click(function () {
        var html = '<div class="col-md-10"><textarea class="form-control" name="paragraphContents"></textarea></div>' +
            '<div class="col-md-2">' +
            '<button type="button" ' +
            'class="btn btn-primary btn-sm btnImageRelatedDocumentParaDelete">删除段落</button>' +
            '</div>';
        $("#imageRelatedDocumentForm .para").append(html);

        //删除段落
        $(".btnImageRelatedDocumentParaDelete").off("click").click(function () {
            $(this).parent().prev().remove();
            $(this).parent().remove();
        });
    });

    //新建主题
    $("#btnCreateImageMain").click(function () {
        if (!$("#belongCongressId").val()) {
            alert("请先选择届别！");
        } else {
            $("#contentContainer").show();
            npcCommon.initImageMain(0);
            $("#auditContainer").hide();
        }
    });

    //新建相关资料
    $("#btnCreateImageRelated").click(function () {
        if (!$("#belongImageMainId").val()&&!$("#imageMainId").val()) {
            alert("请先选择主题！");
        } else {
			$("#belongImageMainId").val($("#belongImageMainId").val()||$("#imageMainId").val());
			$("#contentId").val("")
            $("#contentContainer").show();
            npcCommon.initImageRelated(0);
            $("#auditContainer").hide();
        }
    });

    //修改内容（主题或相关资料）
    $("#btnModify").click(function () {
        if (!$("#imageMainId").val() && !$("#imageRelatedId").val()) {
            alert("请先选择主题或相关资料！");
        } else if ($("#imageMainId").val()) {
            //修改主题
            $("#contentContainer").show();
            npcCommon.getContentDetail(npcCommon.constants.resourceType.ImageMain, $("#imageMainId").val(), 1);
        } else if ($("#imageRelatedId").val()) {
            //修改相关资料
            $("#contentContainer").show();
            
            npcCommon.getContentDetail($("#contentTypeNo").val(), $("#imageRelatedId").val(), 1);
        }
    });

    //删除内容
    $("#btnDelete").click(function () {
        if (confirm("是否确定删除？")) {
            $.ajax({
                type: "POST",
                url: npcCommon.jsonUrl + "deleteContent.json",
                data: {
                    contentType: $("#contentTypeNo").val(),
                    contentId: $("#contentId").val()
                },
                success: function (data) {
                    var jsonData = JSON.parse(data);
                    if (jsonData.success && jsonData.data) {
                        alert("删除成功！");
                        npcCommon.initContentPage();
                        $("#contentTypeNo").val("");
                        $("#contentId").val("");
                        $("#imageMainId").val("");
                    } else {
                        alert("删除失败！");
                    }
                }
            });
        }
    });

    //保存内容
    $("#btnSave").click(function () {

        $.ajax({
            type: "POST",
            url: npcCommon.jsonUrl + "saveContent.json",
            data: npcCommon.getFormData($("body")),
            success: function (data) {
                var jsonData = JSON.parse(data);
                if (jsonData.success && jsonData.data) {
                    npcCommon.setFormData(jsonData.data);
                    $("#btnSave").hide();
                    alert("保存成功！");

                    if ($("#contentTypeNo").val() == 1) {
                        npcCommon.initImageRelated(2);
                    } else {
                        npcCommon.initImageMain(2);
                    }
                } else {
                    alert("保存失败！");
                }
            }
        });
    });

    //提交审批
    $("#btnSubmitAudit").click(function () {

        $.ajax({
            type: "POST",
            url: npcCommon.jsonUrl + "submitAudit.json",
            data: npcCommon.getFormData($("body")),
            success: function (data) {
                var jsonData = JSON.parse(data);
                if (jsonData.success && jsonData.data) {
                    npcCommon.setFormData(jsonData.data);
                    $("#btnSubmitAudit").hide();
                    alert("提交审批成功！");

                    if ($("#contentTypeNo").val() == 'ImageRelated' || $("#contentTypeNo").val() == 'Document') {
                        npcCommon.initImageRelated(2);
                    } else {
                        npcCommon.initImageMain(2);
                    }
                } else {
                    alert("提交审批失败！");
                }
            }
        });
    });

    //选择关键字
    $("#btnSelectKeyword").click(function () {

        //TODO:弹窗，列表，选择
    });

    //选择人物
    $("#btnSelectPerson").click(function () {

        //TODO:弹窗，列表，选择
    });

    //新建人物
    $("#btnCreatePerson").click(function () {

        //TODO:弹窗，列表，新建，元素包含：名称，简介，上传图片
    });

    //清空人物
    $("#btnResetPerson").click(function () {
        $("#contentPerson").val("");
    });


});