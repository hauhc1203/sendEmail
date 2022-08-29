let mail;
let loop;
let code;
let load=document.getElementById("loader")
function getInfo(){
    mail=document.getElementById("m").value
    console.log(mail)
    if (mail!=""){
        getCode()
    }


}
function getCode(){
    load.style.display="flex"
    clearInterval(loop)
    $.ajax(
        {
            type:"GET",
            url:"http://localhost:8080/mail?email="+mail,
            success: function (data) {
                load.style.display="none"
                code=data;
                waitConfirm();
            },
            error: function (err) {
                console.log(err)
            }
        }

    )
}

function waitConfirm(){
    document.getElementById("cfcode").style.display="flex"
    let time=60
    loop= setInterval(function (){
        if (time>0){
            document.getElementById("cd").innerHTML=time+"s"
            time--;
        }else {
            document.getElementById("cfbtn").disabled = true;
            document.getElementById("cd").innerText="code hết hạn"
            clearInterval(loop)
        }
    },1000)
}

function cf1(){
    let code1=document.getElementById("mxn").value
    if (code==code1){
        clearInterval(loop)
        alert("xac nhận thành công")
    }else {
        alert("Mã xác nhận sai hoặc đã hết hạn")
    }
}
