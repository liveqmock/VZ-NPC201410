package com.weizhen.npc.utils;

import java.util.HashMap;
import java.util.Map;

public class ModelStatusTransformer {
	
	private static Map<ModelStatusEnum, Map<OperationEnum, ModelStatusEnum>> tables;
	
	static {
		tables = new HashMap<ModelStatusEnum, Map<OperationEnum, ModelStatusEnum>>();
		
		Map<OperationEnum, ModelStatusEnum> saveTable = new HashMap<OperationEnum, ModelStatusEnum>();
		saveTable.put(OperationEnum.SAVE, ModelStatusEnum.SAVED);
		saveTable.put(OperationEnum.SUBMIT, ModelStatusEnum.SUBMITTED);
		tables.put(ModelStatusEnum.SAVED, saveTable);
		
		
		Map<OperationEnum, ModelStatusEnum> submitTable = new HashMap<OperationEnum, ModelStatusEnum>();
		submitTable.put(OperationEnum.RATIFY, ModelStatusEnum.RATIFIED);
		submitTable.put(OperationEnum.REJECT, ModelStatusEnum.REJECTED);
		tables.put(ModelStatusEnum.SUBMITTED, submitTable);
		
		Map<OperationEnum, ModelStatusEnum> rejectTable = new HashMap<OperationEnum, ModelStatusEnum>();
		rejectTable.put(OperationEnum.SAVE, ModelStatusEnum.SAVED);
		rejectTable.put(OperationEnum.SUBMIT, ModelStatusEnum.SUBMITTED);
		tables.put(ModelStatusEnum.REJECTED, rejectTable);
		
		Map<OperationEnum, ModelStatusEnum> ratifyTable = new HashMap<OperationEnum, ModelStatusEnum>();
		ratifyTable.put(OperationEnum.PUBLISH, ModelStatusEnum.PUBLISHED);
		tables.put(ModelStatusEnum.RATIFIED, ratifyTable);
		
		Map<OperationEnum, ModelStatusEnum> publishTable = new HashMap<OperationEnum, ModelStatusEnum>();
		publishTable.put(OperationEnum.REJECT, ModelStatusEnum.RATIFIED);
		tables.put(ModelStatusEnum.PUBLISHED, publishTable);
	}
	

	public static String getStatus(String statusFrom, String operation) {
		return tables.get(ModelStatusEnum.from(statusFrom)).get(OperationEnum.from(operation)).getItemCode();
	}
}
