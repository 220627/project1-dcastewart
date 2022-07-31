document.getElementById("loginButton").onclick = loginFunction

// async function loginFunction(){
//     location.assign("./admin.html");
// }
const url = "http://localhost:3000"; 

async function loginFunction(){

    let user = document.getElementById("username").value
    let pass = document.getElementById("password").value


    let userCreds = {
        username: user,
        password: pass
    } 


    console.log(userCreds)

    let response = await fetch(url + "/login", {
        method: "POST", 
        body: JSON.stringify(userCreds), 
        credentials: "include"
    })

    console.log(response.status) 

    if(response.status === 202) {

        let data = await response.json();

        document.getElementById("loginRow").innerText = "Welcome " + data.username;

        

        if(data.role.role_id == 1){
            location.assign("./admin.html");
        } else{
            location.assign("./employee.html");
        }
        



    } else {
        document.getElementById("welcomeHead").innerText="Login Failed! Try Again...";
        document.getElementById("welcomeHead").style.color = "red";
    }
}