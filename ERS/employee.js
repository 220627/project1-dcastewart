const url = "http://localhost:3000";
document.getElementById("submitButton").onclick = addReimb
document.getElementById("logoutButton").onclick = logout


async function logout(){
    let response = await fetch(url + "/logout", {
        method: "POST", 
        body: "", 
        credentials: "include"
    })

    location.assign("./login.html");
}


async function addReimb(){

    let amt = document.getElementById("amtInput").value
    console.log(amt)
    let description = document.getElementById("descriptionInput").value
    console.log(description)

    let reimb = {
        reimb_amt: amt,
        reimb_description: description
    } 

    console.log(reimb);


    

    let response = await fetch(url + "/reimbursements", {
        method: "POST", 
        body: JSON.stringify(reimb), 
        credentials: "include"
    })

    getReimbs();
}

async function getReimbs(){

    let response = await fetch(url + "/reimbursements")

    console.log(response)

    if(response.status === 200){

        delTable();


        let data = await response.json();

        for(let reimbursement of data){

            let row = document.createElement("tr")


            let cell = document.createElement("td")
            cell.innerHTML = reimbursement.reimb_id
            row.appendChild(cell)


            //cell2 - first_name
            let cell2 = document.createElement("td")
            cell2.innerHTML = reimbursement.reimb_submitted
            row.appendChild(cell2)

            //cell3 - last_name
            let cell3 = document.createElement("td")
            cell3.innerHTML = reimbursement.reimb_amt
            row.appendChild(cell3)

            //cell4 - role.role_title
            let cell4 = document.createElement("td")
            cell4.innerHTML = reimbursement.reimbDescription
            row.appendChild(cell4)

            let cell5 = document.createElement("td")
            cell5.innerHTML = reimbursement.reimbStatus.reimb_status
            row.appendChild(cell5)



            document.getElementById("reimbBody").appendChild(row)

        }

    } else {
        alert("something went wrong! make sure your Java is running")
    } 
} 

function delTable(){
    var e = document.getElementById("reimbBody")
        
        //e.firstElementChild can be used.
        var child = e.lastElementChild; 
        while (child) {
            e.removeChild(child);
            child = e.lastElementChild;
        }
}