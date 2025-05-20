function displayUserName() {
	const username = sessionStorage.getItem("username");
	const userNameElement = document.getElementById("userName");

	if (userNameElement) {
		userNameElement.textContent = username
			? `Welcome, ${username}`
			: "User not found!";
	} else {
		console.warn("Element with id 'userName' not found.");
	}
}

function toggleSidebar() {
	const sidebar = document.getElementById("sidebar");
	if (sidebar) {
		sidebar.classList.toggle("collapsed");
	} else {
		console.warn("Sidebar element with id 'sidebar' not found.");
	}
}

function setActive(clickedElement, tabId) {
	const allLinks = document.querySelectorAll(".sidebar-container .tab-link");
	allLinks.forEach((link) => link.classList.remove("active"));

	if (clickedElement) {
		clickedElement.classList.add("active");
	}

	const allTabs = document.querySelectorAll(".tab-content");
	allTabs.forEach((tab) => tab.classList.remove("active"));

	const selectedTab = document.getElementById(tabId);
	if (selectedTab) {
		selectedTab.classList.add("active");
	} else {
		console.warn(`Tab with id '${tabId}' not found.`);
	}
}

function resetForm() {
	document.getElementById("loanForm").reset();
}

document.getElementById("anchorForm").addEventListener("submit", async function(e) {
	e.preventDefault();
	
	const userId = sessionStorage.getItem("userId");

	const companyName = document.getElementById("companyName").value.trim();
	const panNo = document.getElementById("panNo").value.trim();
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
			body: JSON.stringify({ companyName, panNo, gstnNo, startDate, addressLine1, addressLine2, city, state, pincode, userId })
		});

		if (response.ok) {
			const data = await response.json();
			console.log(data);
			messageDiv.textContent = "Login successful!";
			messageDiv.style.color = "green";
			// Optionally redirect after login
			window.location.href = "/dashboard.html";
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

fetch('/api/brand/orgtype')
	.then(res => res.json())
	.then(data => {
		console.log("Received data:", data); // ✅ inspect the actual response

		const select = document.getElementById('orgType');

		// Check if data is an array
		if (Array.isArray(data.resp)) {
			data.resp.forEach(item => {
				const option = document.createElement('option');
				option.value = item.id;
				option.text = item.type;
				console.log("item.id:", item.id, "item.type:", item.type);
				select.appendChild(option);
			});
		} else {
			console.error("Expected an array but got:", typeof data);
		}
	})
	.catch(err => {
		console.error("Fetch error:", err);
	});

document.getElementById('logoutBtn').addEventListener('click', function() {
	localStorage.clear(); // if you store auth tokens
	window.location.href = '/'; // or the actual login page path
});

const checkDate = new Date();
checkDate.setDate(checkDate.getDate() - 1);

const formattedDate = checkDate.toISOString().split('T')[0];
document.getElementById('startDate').setAttribute("max", formattedDate);

window.onload = displayUserName;
