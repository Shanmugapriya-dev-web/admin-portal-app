document.getElementById("loginForm").addEventListener("submit", async function (e) {
  e.preventDefault();

  const userName = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value.trim();
  const messageDiv = document.getElementById("message");

  console.log("username : "+userName+", password : "+password);
  
  if (!userName || !password) {
    messageDiv.textContent = "Please enter both username and password.";
    messageDiv.style.color = "red";
    return;
  }

  try {
    const response = await fetch("/api/user/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ userName, password })
    });

    if (response.ok) {
      const data = await response.json();
	  console.log(data);
	  if(data.resp!=null){
      sessionStorage.setItem("username", data.resp.userName || userName); // Fallback
      messageDiv.textContent = "Login successful!";
      messageDiv.style.color = "green";
      // Optionally redirect after login
      window.location.href = "/dashboard.html";
	  }else{
			  messageDiv.textContent = "User Not Found";
		      messageDiv.style.color = "red";
	  }
    } else {
      const errorData = await response.json();
      messageDiv.textContent = errorData.message || "Login failed!";
      messageDiv.style.color = "red";
    }

  } catch (error) {
    console.error("Error:", error);
    messageDiv.textContent = "Network error. Please try again.";
    messageDiv.style.color = "red";
  }
});
