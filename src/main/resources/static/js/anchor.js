function resetForm() {
	document.getElementById("anchorForm").reset();
}

document.getElementById("anchorForm").addEventListener("submit", async function(e) {
	e.preventDefault();

	const userId = sessionStorage.getItem("userId");

	const companyName = document.getElementById("companyName").value.trim();
	const panNo = document.getElementById("panNo").value.trim();
	const mobileNo = document.getElementById("mobileNo").value.trim();
	const name = document.getElementById("name").value.trim();
	const email = document.getElementById("email").value.trim();
	const gstnNo = document.getElementById("gstnNo").value.trim();
	const startDate = document.getElementById("startDate").value.trim();
	const addressLine1 = document.getElementById("addressLine1").value.trim();
	const addressLine2 = document.getElementById("addressLine2").value.trim();
	const city = document.getElementById("city").value.trim();
	const state = document.getElementById("state").value.trim();
	const pincode = document.getElementById("pincode").value.trim();
	const messageDiv = document.getElementById("message");

	console.log("companyName : " + companyName);

	try {
		const response = await fetch("/api/brand/add", {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify([{ companyName, panNo, gstnNo, startDate, addressLine1, addressLine2, city, state, pincode, userId, mobileNo, email }])
		});

		if (response.ok) {
			const data = await response.json();
			console.log(data);
			console.log(data.errorCode);
			if (data.errorCode === 200) {
				messageDiv.textContent = data.resp;
				messageDiv.style.color = "green";
				alert(data.resp);
				location.reload();
			} else {
				messageDiv.textContent = data.errorMessage;
				messageDiv.style.color = "red";
			}
		} else {
			const errorData = await response.json();
			messageDiv.textContent = errorData.message;
			messageDiv.style.color = "red";
		}

	} catch (error) {
		console.error("Error:", error);
		messageDiv.textContent = "Network error. Please try again.";
		messageDiv.style.color = "red";
	}
});

window.onload = function() {
	const username = sessionStorage.getItem("username");
	const userNameElement = document.getElementById("userName");

	if (userNameElement) {
		userNameElement.textContent = username
			? `Welcome, ${username}`
			: "User not found!";
	} else {
		console.warn("Element with id 'userName' not found.");
	}
	// 1. Show the "Brand Data Upload" content div
	const brandTab = document.getElementById('anchorDetails');
	if (brandTab) {
		brandTab.style.display = 'block'; // Make the content visible
	}

	// 2. Expand the "Report" submenu
	const reportMenu = document.querySelector(".menu-item > a.tab-link[onclick*='report']");
	if (reportMenu) {
		const submenu = reportMenu.nextElementSibling;
		if (submenu && submenu.classList.contains("submenu")) {
			submenu.style.display = "block";
		}
	}

	// 3. Highlight "Brand Data Upload" as active
	const brandLink = Array.from(document.querySelectorAll(".submenu a")).find(a =>
		a.getAttribute("onclick")?.includes("anchorDetails")
	);
	if (brandLink) {
		brandLink.classList.add("active");
	}
};