const brandUploadData = [];

function brandDataUploadTemplate() {
	fetch("/api/brand/data/upload/template", {
		method: "GET",
	}).then(response => response.json())
		.then(data => {
			const link = document.createElement('a');
			link.href = data.resp; // URL of the .xls file
			link.download = 'template.xlsx';    // Suggested filename
			document.body.appendChild(link);
			link.click();
			document.body.removeChild(link);
		})
		.catch(error => {
			console.error("Error:", error);
			alert("Download failed");
		});
}



document.getElementById("brandDataUploadForm").addEventListener("submit", async function(e) {
	e.preventDefault();

	const form = document.getElementById('brandDataUploadForm');
	const formData = new FormData(form);

	formData.append("orgType", document.getElementById("orgType").value);
	formData.append("brandId", document.getElementById("brandList").value);
	formData.append("file", document.getElementById("file").files[0]); // ✅ fixed
	formData.append("userId", sessionStorage.getItem("userId")); // ✅ fixed

	try {
		const response = await fetch("/api/brand/data/upload", {
			method: "POST",
			body: formData
		});
		const data = await response.json();
		brandUploadData.push(...data.resp); // update the array correctly
		generateTableFromJSON(brandUploadData); // generate table after upload
	} catch (error) {
		console.error("Error:", error);
		alert("Upload failed");
	}
});

function generateTableFromJSON(data) {
	if (!data || data.length === 0) return;

	const orderedKeys = [
		"companyName", "panNo", "gstnNo", "address", "city", "state", "pincode",
		"mobileNo","email",
		"avgPurchase", "firstMonth", "firstmonthPurchase",
		"secondMonth", "secondMonthPurchase",
		"thirdMonth", "thirdmonthPurchase",
		"fourthMonth", "fourthmonthPurchase",
		"fifthMonth", "fifthmonthPurchase",
		"sixthMonth", "sixthmonthPurchase",
		"remarks"
	];

	const headerMap = {
		companyName: "Company Name",
		panNo: "PAN Number",
		gstnNo: "GST Number",
		address: "Address",
		city: "City",
		state: "State",
		pincode: "Pincode",
		mobileNo: "Mobile Number",
		email: "Email",
		avgPurchase:"Average Purchase",
		firstMonth: "First Month",
		firstmonthPurchase: "First Month Purchase",
		secondMonth: "Second Month",
		secondMonthPurchase: "Second Month Purchase",
		thirdMonth: "Third Month",
		thirdmonthPurchase: "Third Month Purchase",
		fourthMonth: "Fourth Month",
		fourthmonthPurchase: "Fourth Month Purchase",
		fifthMonth: "Fifth Month",
		fifthmonthPurchase: "Fifth Month Purchase",
		sixthMonth: "Sixth Month",
		sixthmonthPurchase: "Sixth Month Purchase",
		remarks: "Remarks"
	};

	const table = document.createElement('table');
	const thead = table.createTHead();
	const tbody = table.createTBody();

	const headerRow = thead.insertRow();
	orderedKeys.forEach(key => {
		const th = document.createElement('th');
		th.textContent = headerMap[key] || key;
		headerRow.appendChild(th);
	});

	// Extra column header for Confirm button
	const thConfirm = document.createElement('th');
	thConfirm.textContent = "Action";
	headerRow.appendChild(thConfirm);

	data.forEach((item, index) => {
		const row = tbody.insertRow();

		orderedKeys.forEach(key => {
			const cell = row.insertCell();
			cell.textContent = item[key] ?? "";
		});
		
		const messageDiv = document.getElementById("message");
		// Add Confirm button conditionally
		const actionCell = row.insertCell();
		if (item.remarks?.toLowerCase() === "success") {
			const btn = document.createElement('button');
			btn.textContent = "Confirm";
			btn.className = "confirm-btn";
			btn.addEventListener("click", () => {
				try {
						const response = fetch("/api/brand/add", {
							method: "POST",
							headers: {
								"Content-Type": "application/json"
							},
							body: JSON.stringify(data)
						});
							console.log(response.json());
						if (response.ok) {
							const jsonData = response.json();
							console.log(jsonData);
							console.log(jsonData.errorCode);
							if (jsonData.errorCode === 200) {
								messageDiv.textContent = jsonData.resp;
								messageDiv.style.color = "green";
								alert(jsonData.resp);
								location.reload();
							} else {
								messageDiv.textContent = jsonData.errorMessage;
								messageDiv.style.color = "red";
							}
						} else {
							const errorData = response.json();
							messageDiv.textContent = errorData.message;
							messageDiv.style.color = "red";
						}

					} catch (error) {
						console.error("Error:", error);
						messageDiv.textContent = "Network error. Please try again.";
						messageDiv.style.color = "red";
					}
			});
			actionCell.appendChild(btn);
		} else {
			actionCell.textContent = "-"; // placeholder
		}
	});

	const container = document.querySelector('#table-container .table-scroll');
	container.innerHTML = ''; // Clear previous table
	container.appendChild(table);
}

const typeId = 1;

fetch(`/api/brand/orgtype/${typeId}`)
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

fetch('/api/brand/list')
	.then(res => res.json())
	.then(data => {
		console.log("Received data:", data); // ✅ inspect the actual response

		const select = document.getElementById('brandList');

		// Check if data is an array
		if (Array.isArray(data.resp)) {
			data.resp.forEach(item => {
				const option = document.createElement('option');
				option.value = item.id;
				option.text = item.name;
				console.log("item.id:", item.id, "item.name:", item.name);
				select.appendChild(option);
			});
		} else {
			console.error("Expected an array but got:", typeof data);
		}
	})
	.catch(err => {
		console.error("Fetch error:", err);
	});

	function toggleSidebar() {
		const sidebar = document.getElementById("sidebar");
		sidebar.classList.toggle("collapsed");
	}

	document.addEventListener("DOMContentLoaded", function() {
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
		const brandTab = document.getElementById('brandDataUpload');
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
			a.getAttribute("onclick")?.includes("brandDataUpload")
		);
		if (brandLink) {
			brandLink.classList.add("active");
		}
		
		const checkDate = new Date();
		checkDate.setDate(checkDate.getDate() - 1);

		const formattedDate = checkDate.toISOString().split('T')[0];
		document.getElementById('startDate').setAttribute("max", formattedDate);
	});