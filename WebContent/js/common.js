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