
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.1/jquery.form.min.js" integrity="sha384-tIwI8+qJdZBtYYCKwRkjxBGQVZS3gGozr3CtI+5JF/oL1JmPEHzCEnIKbDbLTCer" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

   <%-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>--%>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>

<script type="text/javascript">

    $(document).ready(function() {
        $.ajax({
            url : '/shipinfo/getconfig',
            type : 'get',
            async : false,
            success : function(data) {
                $("#config").val(data);
            }});
    });
    

    
    function update() {
        $.ajax({
            url : '/shipinfo/updateconfig',
            type : 'post',
            async : false,
            data : {
                data : $("#config").val()
            },
            success:function () {
            }
    })}

</script>
<body>
<form id="myForm">

    <div class="input-group">
        <textarea id="config" name="config" class="form-control custom-control" rows="25" cols="90" style="resize:none"></textarea>
    </div>

    <br/>
    <input type="submit" onclick="update()" value="提交" />
    <br/>
</form>


</body>
</html>