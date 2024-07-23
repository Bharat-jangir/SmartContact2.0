
window.addEventListener("load",async function(){
let a=await localStorage.getItem("theme");
a==null && localStorage.setItem("theme","light")
a=await localStorage.getItem("theme");
console.log(a)

let spn=document.querySelector("#toggle_theme").childNodes[0];
let page=document.querySelector("html")
if(a=="light"){
spn.textContent="dark";
await page.classList.remove("dark")
await page.classList.add("light")
}else{
spn.textContent="light";
await page.classList.remove("light")
await page.classList.add("dark")
}
console.log("form document")
})
window.onload = function(){
console.log("form window");
}

async function toggleTheme(){
let a=await localStorage.getItem("theme");
let spn=document.querySelector("#toggle_theme").childNodes[0];
let page=document.querySelector("html")
if(a=="light"){
await localStorage.setItem("theme","dark");
//theme="dark";
spn.textContent="light";
await page.classList.remove("light")
await page.classList.add("dark")
}else{
await localStorage.setItem("theme","light");
//theme="light";
spn.textContent="dark";
await page.classList.remove("dark")
await page.classList.add("light")
}
console.log("toggle_theme called")
}

document.querySelector("#toggle_theme").addEventListener("click",toggleTheme);