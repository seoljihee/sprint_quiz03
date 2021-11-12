function daumPost(){
    new daum.Postcode({
        oncomplete: function(data) {
           // R : 도로명, J : 지번
           console.log("data.userSelectedType :" + data.userSelectedType)
           console.log("data.roadAddress : " + data.roadAddress)
           console.log("data.jibunAddress : " + data.jibunAddress)
           console.log("data.zonecode : " + data.zonecode)
           
           var addr = ""
           if(data.userSelectedType === 'R'){
        	   addr = data.roadAddress        	   
           }else{
        	   addr = data.jibunAddress  
           }
           document.getElementById("addr1").value = data.zonecode//자바스크립트 문법
           $("#addr2").val(addr)								//제이쿼리 문법
           $("#addr3").focus
        }
    }).open();
}