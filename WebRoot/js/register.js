
var checkUser =function(user)
{
    if(user.value.length<3 || user.value.length>18){
        user.style.borderColor = "red";
    }
    else {
        user.style.borderColor = "green";
    }
};

var checkPassword =function(password)
{
    if(password.value.length<6 || password.value.length>20){
        password.style.borderColor = "red";
    }
    else {
        password.style.borderColor = "green";
    }
};

var checkPasswords =function(password)
{
    var p1=document.getElementById("password1").value;
    var p2=document.getElementById("password2").value;
    if(password.value.length<6 || password.value.length>20 || p1 != p2){
        password.style.borderColor = "red";
    }
    else {
        password.style.borderColor = "green";
    }
};

var checkIdCard =function(id)
{
    if(id.value.length != 18){
        id.style.borderColor = "red";
    }
    else {
        id.style.borderColor = "green";
    }
};

var checkPhone =function(phone)
{
    var num=/^[0-9]+.?[0-9]*$/;
    if(phone.value.length != 11 || !num.test(phone.value)){
        phone.style.borderColor = "red";
    }
    else {
        phone.style.borderColor = "green";
    }
};

