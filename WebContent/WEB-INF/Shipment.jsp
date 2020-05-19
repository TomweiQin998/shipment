<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>船运管理系统</title>

<script type="text/javascript">
function check(){
  if(document.ShipmentServlet.num==""){
   alert("请输入货运批次");
   return;
  }
   if(document.ShipmentServlet.totalWeight==""){
   alert("请输入待运货物总吨数");
   return;
  }
  
   if(document.ShipmentServlet.totalWeight>200&&document.ShipmentServlet.num<=1){
   alert("货运总吨数超过船只最大负载吨位，请输入货运批次大于1");
   return;
  }
}
function submitForm(){
document.ShipmentServlet.submit()
}
function resetForm(){
document.ShipmentServlet.reset()
document.ShipmentServlet.num.focus()
}
</script>
</head>
<body>
<center>
<form name="ShipmentServlet" action="ShipmentServlet" method="post">

<table width="100%">
<tr>
<td align="right">
<font size="+1">
<B>货运批次</B>
</font>
</td>
<td>
<font size="+1">
<input type="text" name="num" maxlength="8">
</font>
</td>
</tr>
<tr>
<td align="right">
<font size="+1">
<B>待运货物总吨数</B>
</font>
</td>
<td>
<font size="+1">
<input type="number" name="totalWeight"
maxlength="8">
</font>
</td>
<tr>
<td align="right">
<font size="+1">
<B>拆分装运|合并装运</B>
</font>
</td>
<td>
<font size="+1">
<select id="select" name="SplitOrMerge" style="width:120px;height:25px">
<option value="0">--请选择--</option>
<option value="1">拆分装运</option>
<option value="2">合并装运</option>
</select>

</font>
</td>
</tr>
<tr>
<td align="right">
<font size="+1">
<B>添加根数量</B>
</font>
</td>
<td>
<font size="+1">
<input type="number" name="increateRootQuantity"
maxlength="8">
</font>
</td>
</tr>
<tr>
<td align="right">
<font size="+1">
<B>减少根数量</B>
</font>
</td>
<td>
<font size="+1">
<input type="number" name="decreaseRootQuantity"
maxlength="8">
</font>
</td>
</tr>
</table>
<br><br><br>
<font size="+1">
<B>
<input type="button" value="确定设置" onclick="submitForm()">
     
<input type="button" value="重新设置" onclick="resetForm()">
<br>
</B>
</font>
</form>
</center>




</body>
</html>