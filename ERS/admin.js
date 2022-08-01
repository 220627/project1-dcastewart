const url = "http://localhost:3000";
document.getElementById("allButton").onclick = getAllReimbs
document.getElementById("approveButton").onclick = approve
document.getElementById("denyButton").onclick = deny
document.getElementById("unresolvedButton").onclick = getUnresolvedReimbs
document.getElementById("approvedButton").onclick = getApprovedReimbs
document.getElementById("deniedButton").onclick = getDeniedReimbs
document.getElementById("logoutButton").onclick = logout

var current_sort = 1

async function logout(){
    let response = await fetch(url + "/logout", {
        method: "POST", 
        body: "", 
        credentials: "include"
    })

    location.assign("./login.html");
}

async function approve(){
    let id = document.getElementById("idInput").value

    let response = await fetch(url + "/reimbursements/" + id, {
        method: "PUT", 
        body: 2, 
        credentials: "include"
    })

    if(current_sort == 2){
        getUnresolvedReimbs();
    } else if(current_sort == 3){
        getApprovedReimbs();
    } else if(current_sort == 4){
        getDeniedReimbs();
    } else{
        getAllReimbs();
    }
}

async function deny(){
    let id = document.getElementById("idInput").value

    let response = await fetch(url + "/reimbursements/" + id, {
        method: "PUT", 
        body: 3, 
        credentials: "include"
    })

    if(current_sort == 2){
        getUnresolvedReimbs();
    } else if(current_sort == 3){
        getApprovedReimbs();
    } else if(current_sort == 4){
        getDeniedReimbs();
    } else{
        getAllReimbs();
    }
}

async function getAllReimbs(){

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

            let cell2 = document.createElement("td")
            cell2.innerHTML = reimbursement.reimb_author.first_name + ' ' + reimbursement.reimb_author.last_name
            row.appendChild(cell2)
            console.log(reimbursement)


            //cell2 - first_name
            let cell3 = document.createElement("td")
            cell3.innerHTML = reimbursement.reimb_submitted
            row.appendChild(cell3)

            //cell3 - last_name
            let cell4 = document.createElement("td")
            cell4.innerHTML = reimbursement.reimb_amt
            row.appendChild(cell4)

            //cell4 - role.role_title
            let cell5 = document.createElement("td")
            cell5.innerHTML = reimbursement.reimbDescription
            row.appendChild(cell5)

            let cell6 = document.createElement("td")
            cell6.innerHTML = reimbursement.reimbStatus.reimb_status
            row.appendChild(cell6)



            document.getElementById("reimbBody").appendChild(row)

        }
        current_sort = 1

    } else {
        alert("something went wrong! make sure your Java is running")
    } 
} 

async function getUnresolvedReimbs(){
    let response = await fetch(url + "/reimbursements/1")

    console.log(response)

    if(response.status === 200){

        delTable();


        let data = await response.json();

        for(let reimbursement of data){

            let row = document.createElement("tr")


            let cell = document.createElement("td")
            cell.innerHTML = reimbursement.reimb_id
            row.appendChild(cell)

            let cell2 = document.createElement("td")
            cell2.innerHTML = reimbursement.reimb_author.first_name + ' ' + reimbursement.reimb_author.last_name
            row.appendChild(cell2)
            console.log(reimbursement)


            //cell2 - first_name
            let cell3 = document.createElement("td")
            cell3.innerHTML = reimbursement.reimb_submitted
            row.appendChild(cell3)

            //cell3 - last_name
            let cell4 = document.createElement("td")
            cell4.innerHTML = reimbursement.reimb_amt
            row.appendChild(cell4)

            //cell4 - role.role_title
            let cell5 = document.createElement("td")
            cell5.innerHTML = reimbursement.reimbDescription
            row.appendChild(cell5)

            let cell6 = document.createElement("td")
            cell6.innerHTML = reimbursement.reimbStatus.reimb_status
            row.appendChild(cell6)



            document.getElementById("reimbBody").appendChild(row)

        }
        current_sort = 2

    } else {
        alert("something went wrong! make sure your Java is running")
    } 
}

async function getApprovedReimbs(){
    let response = await fetch(url + "/reimbursements/2")

    console.log(response)

    if(response.status === 200){

        delTable();


        let data = await response.json();

        for(let reimbursement of data){

            let row = document.createElement("tr")


            let cell = document.createElement("td")
            cell.innerHTML = reimbursement.reimb_id
            row.appendChild(cell)

            let cell2 = document.createElement("td")
            cell2.innerHTML = reimbursement.reimb_author.first_name + ' ' + reimbursement.reimb_author.last_name
            row.appendChild(cell2)
            console.log(reimbursement)


            //cell2 - first_name
            let cell3 = document.createElement("td")
            cell3.innerHTML = reimbursement.reimb_submitted
            row.appendChild(cell3)

            //cell3 - last_name
            let cell4 = document.createElement("td")
            cell4.innerHTML = reimbursement.reimb_amt
            row.appendChild(cell4)

            //cell4 - role.role_title
            let cell5 = document.createElement("td")
            cell5.innerHTML = reimbursement.reimbDescription
            row.appendChild(cell5)

            let cell6 = document.createElement("td")
            cell6.innerHTML = reimbursement.reimbStatus.reimb_status
            row.appendChild(cell6)



            document.getElementById("reimbBody").appendChild(row)

        }
        current_sort = 3

    } else {
        alert("something went wrong! make sure your Java is running")
    } 
}

async function getDeniedReimbs(){
    let response = await fetch(url + "/reimbursements/3")

    console.log(response)

    if(response.status === 200){

        delTable();


        let data = await response.json();

        for(let reimbursement of data){

            let row = document.createElement("tr")


            let cell = document.createElement("td")
            cell.innerHTML = reimbursement.reimb_id
            row.appendChild(cell)

            let cell2 = document.createElement("td")
            cell2.innerHTML = reimbursement.reimb_author.first_name + ' ' + reimbursement.reimb_author.last_name
            row.appendChild(cell2)
            console.log(reimbursement)


            //cell2 - first_name
            let cell3 = document.createElement("td")
            cell3.innerHTML = reimbursement.reimb_submitted
            row.appendChild(cell3)

            //cell3 - last_name
            let cell4 = document.createElement("td")
            cell4.innerHTML = reimbursement.reimb_amt
            row.appendChild(cell4)

            //cell4 - role.role_title
            let cell5 = document.createElement("td")
            cell5.innerHTML = reimbursement.reimbDescription
            row.appendChild(cell5)

            let cell6 = document.createElement("td")
            cell6.innerHTML = reimbursement.reimbStatus.reimb_status
            row.appendChild(cell6)



            document.getElementById("reimbBody").appendChild(row)

        }
        current_sort = 4

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