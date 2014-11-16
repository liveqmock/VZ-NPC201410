<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../common.jsp" %>    

<c:if test="${!empty auditLogs && fn:length(auditLogs) > 0 }">  
 <table class="table table-hover">
    <thead>
      <tr>
        <th>序号</th>
        <th>审核人</th>
        <th>审核时间</th>
        <th>备注信息</th>
        <th>审核结果</th>
      </tr>
    </thead>
    <tbody>
<c:forEach var="auditLog" items="${auditLogs}" varStatus="row">
       <tr>
         <td>${row.index + 1 }</td>
         <td>${auditLog.auditor }</td>
         <td><fmt:formatDate value='${auditLog.auditDate}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
         <td>${auditLog.note }</td>
         <td>${npc:formatModelStatus(auditLog.statusTo) }</td>
       </tr>	       
</c:forEach>        
    </tbody>
  </table>
</c:if> 