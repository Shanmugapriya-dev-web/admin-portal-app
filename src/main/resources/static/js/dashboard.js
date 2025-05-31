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



document.getElementById('logoutBtn').addEventListener('click', function() {
	localStorage.clear(); // if you store auth tokens
	window.location.href = '/'; // or the actual login page path
});


window.onload = function() {
	const username = sessionStorage.getItem("username");
	console.log("username : "+username)
	const userNameElement = document.getElementById("userName");

	if (userNameElement) {
		userNameElement.textContent = username
			? `Welcome, ${username}`
			: "User not found!";
	} else {
		console.warn("Element with id 'userName' not found.");
	}
}
