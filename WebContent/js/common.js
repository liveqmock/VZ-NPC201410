/**
 * 公用变量和函数
 */

var materialTypeEnum = {
		1 : '图片',
		2 : '视频',
		4 : '文章'
}

function formatMaterialType(materialId) {
	return materialTypeEnum[materialId];
} 

var modelStatusEnum = {
		"submitted": "已提交",
		"ratified": "已通过",
		"rejected": "已拒绝",
		"published": "已发布"
}

function formatModelStatus(status) {
	return modelStatusEnum[status];
}

var resourceTypeEnum = {
		'ImageMain' : '主题',
		'ImageRelated' : '相关资料',
		'Document' : '文章'
}

var userTypes = {
		'editor' : '录入人员',
		'auditor' : '审核人员',
		'manager' : '管理员'
	}
	
function formatUserType(userType) {
	return userTypes[userType];
}

function formatEnabledStatus(enabled) {
	return enabled ? '已启用' : '已禁用';
}