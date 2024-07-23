console.log("this log is from admin js")


document.querySelector("#file_input").addEventListener("change",function(e){
           let file=e.target.files[0];
           let reader=new FileReader();
           reader.onload=function(){
           document.getElementById("uploaded_image").src=reader.result;
           }
           reader.readAsDataURL(file);
})